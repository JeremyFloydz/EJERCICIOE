module es.jeremy.ejee {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.jeremy.ejee to javafx.fxml;
    exports es.jeremy.ejee;
}