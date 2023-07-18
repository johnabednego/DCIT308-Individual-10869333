module com.example.abednego_10869333 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.abednego_10869333 to javafx.fxml;
    exports com.example.abednego_10869333;
}