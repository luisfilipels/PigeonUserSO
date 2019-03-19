package correio;

import animacao.PomboAnimacao;

public class Pombo implements Runnable{

    private Buffer buffer;
    private int carga, tv, tc, td;
    private boolean running;
    private PomboAnimacao animacao;

    public Pombo(Buffer buffer, int tc, int tv, int td, PomboAnimacao animacao) {
        this.buffer = buffer;
        this.tc = tc;
        this.tv = tv;
        this.td = td;
        this.animacao = animacao;
        new Thread(this).start();
        this.running = true;

    }

    public void matar() {
        this.running = false;
    }

    public void run() {
        while(running) {
            Mensagem[] cartas;
            System.out.println("Pombo vivo!");
            try {
                cartas = buffer.removeCarta();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            executando(tc);

            System.out.println("Pombo voando...\n");
            executando(500);
            animacao.vooIda(tv/10);

            System.out.println("Pombo descarregando...\n");
            executando(td);

            System.out.println("Pombo voltando...\n");
            executando(tv/10);

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
