package uiApp;

import javafx.beans.property.SimpleStringProperty;

public class Usuario {

    public final SimpleStringProperty id;
    //public final SimpleStringProperty status;

    public Usuario(String id) {
        this.id = new SimpleStringProperty(id);
        //this.status = new SimpleStringProperty(Status);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
