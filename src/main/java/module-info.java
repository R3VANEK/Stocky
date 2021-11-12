module com.example.stocky {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires gson;


    opens com.example.stocky to javafx.fxml;
    exports com.example.stocky;
}