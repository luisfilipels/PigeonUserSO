package correio;

import animacao.UsuarioAnimacao;
import uiApp.Controller;

public class Escritor implements Runnable{

    private Buffer buffer;
    private int cont = 1;
    private int tmpEscrita;
    private String id;
    public boolean running;
    public Mensagem MsgEmPosse = null;
    private UsuarioAnimacao animacao;
    public Controller mainController;

    public Escritor(Buffer buffer, String id, int tmpEscrita, UsuarioAnimacao animacao, Controller mainController) {
        this.buffer = buffer;
        this.id = id;
        this.tmpEscrita = tmpEscrita;
        this.animacao = animacao;
        this.mainController = mainController;
        new Thread(this).start();
    }

    public void matar() {
        this.running = false;
    }

    public void escreveCarta() {
        MsgEmPosse = new Mensagem(id, cont++);
    }

    //public void enviaCarta() throws InterruptedException{
    //    buffer.insereCarta(MsgEmPosse);
    //}

    public String getsId() {
        return this.id;
    }

    public void setsId (String id) {
        this.id = id;
    }

    public void run() {
        this.running = true;
        while(this.running) {
            try {
                escreveCarta();
                System.out.println(mainController.tableUsers.getItems().size());
                for (int i = 0; i < mainController.tableUsers.getItems().size(); i++) {
                    if (mainController.listaUsuarios.get(i).getId().equals(this.id)) {
                        mainController.listaUsuarios.get(i).setStatus("Escrevendo");
                        System.out.println("Escrevendo");
                    }
                }
                animacao.escrever(tmpEscrita);
                executando(tmpEscrita);
                //enviaCarta();
                MsgEmPosse = null;
                buffer.insereCarta(new Mensagem(id, cont), animacao);
                System.out.println(mainController.tableUsers.getItems().size());
                for (int i = 0; i < mainController.tableUsers.getItems().size(); i++) {
                    if (mainController.listaUsuarios.get(i).getId().equals(this.id)) {
                        mainController.listaUsuarios.get(i).setStatus("Enviando");
                        System.out.println("Enviando");
                    }
                }
                if (!this.running) {
                    break;
                }

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
