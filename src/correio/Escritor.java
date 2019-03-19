package correio;

import animacao.UsuarioAnimacao;

public class Escritor implements Runnable{

    private Buffer buffer;
    private int cont = 1;
    private int id, tmpEscrita;
    public boolean running;
    public Mensagem MsgEmPosse = null;
    private UsuarioAnimacao animacao;

    public Escritor(Buffer buffer, int id, int tmpEscrita, UsuarioAnimacao animacao) {
        this.buffer = buffer;
        this.id = id;
        this.tmpEscrita = tmpEscrita;
        this.animacao = animacao;
        new Thread(this).start();
    }

    public void matar() {
        this.running = false;
    }

    public void escreveCarta() {
        MsgEmPosse = new Mensagem(id, cont++);
    }

    public void enviaCarta() throws InterruptedException{
        buffer.insereCarta(MsgEmPosse);
        MsgEmPosse = null;
    }

    public int getsId() {
        return this.id;
    }

    public void setsId (int id) {
        this.id = id;
    }

    public void run() {
        this.running = true;
        while(this.running) {
            try {
                executando(1000);
                if (!this.running) {
                    break;
                }
                escreveCarta();
                enviaCarta();
                buffer.insereCarta(new Mensagem(id, cont++));
                animacao.play(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void executando(int duracao) {
        long lastTime = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        while(now-lastTime < duracao) {
            now = System.currentTimeMillis();
        }
    }

}