package correio;

public class Mensagem {

    private int idAutor;
    private int numero;

    public Mensagem(int id, int numero){
        this.idAutor = id;
        this.numero = numero;

    }

    public int getAutor(){
        return idAutor;
    }

    public int getNumero(){
        return numero;
    }

    public String toString(){
        return String.format("mensagem %d do escritor %d ", numero, idAutor);
    }

}
