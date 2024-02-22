module com.example.stackcalculator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.stackcalculator to javafx.fxml;
    exports com.example.stackcalculator;
}