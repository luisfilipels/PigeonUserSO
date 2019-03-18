package correio;

import java.util.concurrent.Semaphore;

public class Buffer {

    private Mensagem[] buffer;
    private int carga;
    private int indiceEntrada = 0;
    private int indiceSaida = 0;
    private int mensagens = 0;
    private Semaphore empty, mutex, full;

    public Buffer(int tamanho, int carga){
        empty = new Semaphore(tamanho);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
        buffer = new Mensagem[tamanho];
        this.carga = carga;
    }

    public void insereCarta(Mensagem valor) throws InterruptedException{

        if(mensagens == buffer.length) {
            System.out.println("Buffer cheio ."+valor.getAutor()+" espera");	//TODO Tirar isso depois!!!
        }

        empty.acquire();
        mutex.acquire();

        buffer[indiceEntrada] = valor;
        indiceEntrada = (indiceEntrada+1) % buffer.length;
        mensagens++;

        System.out.println(valor.getAutor()+" escreveu mensagem "+valor.getNumero());

        if(mensagens%carga == 0 && mensagens > 0){
            full.release();
        }
        mutex.release();

    }


    public Mensagem[] removeCarta() throws InterruptedException{

        Mensagem saida[] = new Mensagem[carga];

        if(mensagens < carga) {
            System.out.println("Carga insuficiente, Pombo espera");
        }

        full.acquire();
        mutex.acquire();

        System.out.println("-----buffer-----\n");
        for(int i=0; i<buffer.length; i++)
            System.out.println(i+":["+buffer[i]+"]");
        System.out.println("----------------\n");

        for(int i=0; i<carga; i++) {

            saida[i] = buffer[indiceSaida];
            buffer[indiceSaida] = null;
            indiceSaida = (indiceSaida+1) % buffer.length;
            mensagens--;

            empty.release();
            System.out.println("Pombo carregando "+saida[i]);
        }

        mutex.release();


        return saida;
    }

}
