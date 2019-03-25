package correio;

public class Mensagem {

    private String idAutor;
    private int numero;

    public Mensagem(String id, int numero){
        this.idAutor = id;
        this.numero = numero;

    }

    public String getAutor(){
        return idAutor;
    }

    public int getNumero(){
        return numero;
    }

    public String toString(){
        return String.format("mensagem %d do escritor %s ", numero, idAutor);
    }

}
