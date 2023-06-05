module com.nt.throne {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    opens com.nt.throne to javafx.fxml;
    exports com.nt.throne;
    exports com.nt.throne.controller;
    opens com.nt.throne.controller to javafx.fxml;
}