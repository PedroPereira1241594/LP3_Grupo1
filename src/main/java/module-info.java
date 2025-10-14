module com.example.lp3_grupo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.lp3_grupo1 to javafx.fxml;
    opens com.example.lp3_grupo1.Controller to javafx.fxml;
    opens com.example.lp3_grupo1.Model to javafx.base;
    exports com.example.lp3_grupo1;
}