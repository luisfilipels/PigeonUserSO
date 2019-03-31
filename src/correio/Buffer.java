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
    public Semaphore empty, mutex, full;
    public boolean pomboCarregando;
    public static Controller mainController;
    public int tamanho;

    public Buffer(int tamanho, int carga, Controller mainController){
        this.tamanho = tamanho;
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

        buffer[indiceEntrada] = valor;
        indiceEntrada = (indiceEntrada+1) % buffer.length;
        mensagens++;

        System.out.println(valor.getAutor()+" escreveu mensagem "+ (valor.getNumero()-1));

        mutex.release();

        if(mensagens%carga == 0 && mensagens > 0){
            full.release();
            System.out.printf("mensagens = %d; carga = %d ", mensagens, carga);
        }
        System.out.println(full.toString());
    }


    public Mensagem[] removeCarta(int tc, PomboAnimacao pomboAnimado) throws InterruptedException{

        Mensagem saida[] = new Mensagem[carga];
        mutex.acquire();
        if(mensagens < carga) {
            System.out.println("Carga insuficiente, Pombo espera");
            pomboAnimado.dormir();
        }
        mutex.release();

        full.acquire();
        //mutex.acquire();
        System.out.println(mensagens + " " + carga);


        pomboCarregando = true;
        for(int i=0; i<carga; i++) {
            executando((tc*1500)/carga);
            pomboAnimado.carregando(tc/carga);

            saida[i] = buffer[indiceSaida];
            buffer[indiceSaida] = null;
            indiceSaida = (indiceSaida+1) % buffer.length;
            mensagens--;
            mainController.atualizaTabela();

            empty.release();
            System.out.println("Pombo carregando "+saida[i]);
        }
        pomboCarregando = false;

        //mutex.release();

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
            full.release(mensagens/carga);
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
