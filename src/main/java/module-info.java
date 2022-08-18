module HW3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.media;


    opens Sample.Graphic to javafx.fxml;
    exports Sample.Graphic;
    exports Sample.Controller;
    exports Sample.Model;
    opens Sample.Model to com.google.gson;
    opens Sample.Controller to com.google.gson;
}