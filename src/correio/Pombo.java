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
                cartas = buffer.removeCarta(tc, animacao);
                //buffer.removeCarta(tc, animacao);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            executando(tc);

            System.out.println("Pombo voando...\n");
            animacao.vooIda(tv/5);
            executando(1000*tv/2);

            System.out.println("Pombo descarregando...\n");
            executando(td*1000);

            System.out.println("Pombo voltando...\n");
            animacao.vooVolta(tv/5);
            executando(1000*tv/2);

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
