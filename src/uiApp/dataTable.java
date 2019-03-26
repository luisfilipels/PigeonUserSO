package uiApp;

import javafx.beans.property.SimpleStringProperty;

public class dataTable {

    public final SimpleStringProperty ID;
    public final SimpleStringProperty Status;

    public dataTable (String ID, String Status) {
        this.ID = new SimpleStringProperty(ID);
        this.Status = new SimpleStringProperty(Status);
    }

    public String getID() {
        return ID.get();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getStatus() {
        return Status.get();
    }

    public SimpleStringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

}
