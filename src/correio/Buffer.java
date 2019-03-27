package correio;

import java.util.concurrent.Semaphore;

import animacao.PomboAnimacao;
import animacao.UsuarioAnimacao;
import javafx.scene.control.Label;
import uiApp.Controller;

public class Buffer {

    private Mensagem[] buffer;
    private int carga;
    private int indiceEntrada = 0;
    private int indiceSaida = 0;
    public int mensagens = 0;
    private Semaphore empty, mutex, full;
    public boolean pomboCarregando;
    public static Controller mainController;

    public Buffer(int tamanho, int carga, Controller mainController){
        empty = new Semaphore(tamanho);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
        buffer = new Mensagem[tamanho];
        this.carga = carga;
        this.mainController = mainController;
    }

    public void insereCarta(Mensagem valor, UsuarioAnimacao animacao) throws InterruptedException{

        mutex.acquire();
        if(mensagens >= buffer.length) {
            System.out.println("Buffer cheio ."+valor.getAutor()+" espera");	//TODO Tirar isso depois!!!
            animacao.usuarioDormir();
            //full.acquire();
        }
        mutex.release();

        empty.acquire();
        mutex.acquire();

        animacao.enviarCarta();

        executando(1000);
        mainController.atualizaTabela();
        //Controller.TabelaNumCartas.setText("2");
        //String strCartas = Controller.TabelaNumCartas.getText();
        //int numCartas = Integer.parseInt(strCartas);
        //Controller.TabelaNumCartas.setText(Integer.toString(numCartas+1));


        buffer[indiceEntrada] = valor;
        indiceEntrada = (indiceEntrada+1) % buffer.length;
        mensagens++;

        System.out.println(valor.getAutor()+" escreveu mensagem "+ (valor.getNumero()-1));

        mutex.release();

        if(mensagens%carga == 0 && mensagens > 0){
            full.release();
        }

    }


    public Mensagem[] removeCarta(int tc, PomboAnimacao pomboAnimado) throws InterruptedException{

        Mensagem saida[] = new Mensagem[carga];
        mutex.acquire();
        if(mensagens < carga) {
            System.out.println("Carga insuficiente, Pombo espera");
            pomboAnimado.dormir();
            //mutex.release();
            //full.acquire();
            //while (mensagens < carga) {

            //}
            //mutex.acquire();
        }
        mutex.release();

        full.acquire();
        mutex.acquire();
        

        

        for(int i=0; i<carga; i++) {
        	pomboCarregando = true;
            pomboAnimado.carregando(tc/carga);
            executando((tc*500)/carga);

            saida[i] = buffer[indiceSaida];
            buffer[indiceSaida] = null;
            indiceSaida = (indiceSaida+1) % buffer.length;
            mensagens--;
            mainController.atualizaTabela();

            empty.release();
            System.out.println("Pombo carregando "+saida[i]);
            pomboCarregando = false;
        }
        mutex.release();

        System.out.println("-----buffer-----\n");
        for(int i=0; i<buffer.length; i++)
            System.out.println(i+":["+buffer[i]+"]");
        System.out.println("----------------\n");

        return saida;
    }

    public int getCarga () {
        return carga;
    }

    public void setCarga(int carga) {
        if (carga <= buffer.length) {
            this.carga = carga;
        }
        else {
            System.out.println("Valor inválido para negocio.");
        }
    }

    public void executando(int duracao) {
        long lastTime = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while(now-lastTime < duracao) {
            now = System.currentTimeMillis();
        }
    }

}
