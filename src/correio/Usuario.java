package correio;

public class Usuario implements Runnable{

    private Buffer buffer;
    private int cont = 1;
    private int id;
    public boolean running;
    public Mensagem MsgEmPosse;

    public Usuario(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
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
