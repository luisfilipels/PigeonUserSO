package correio;

public class Pombo implements Runnable{

    private Buffer buffer;
    private int carga;
    private boolean running;

    public Pombo(Buffer buffer) {
        this.buffer = buffer;
        new Thread(this).start();
        this.running = true;
    }

    public void matar() {
        this.running = false;
    }

    public void run() {
        while(running) {
            correio.Mensagem[] cartas;

            try {
                cartas = buffer.removeCarta();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            System.out.println("Pombo voando...\n");
            executando(500);

            System.out.println("Pombo descarregando...\n");
            executando(10000);

            System.out.println("Pombo voltando...\n");
            executando(500);

            System.out.println("Pombo voltou!\n");

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
