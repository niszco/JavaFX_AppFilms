module com.example.films {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.films to javafx.fxml;
    exports com.example.films;
}