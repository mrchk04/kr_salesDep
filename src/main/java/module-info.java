module com.salesDep {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.salesDep to javafx.fxml;
    opens com.salesDep.model to javafx.base;
    exports com.salesDep;
    exports com.salesDep.controllers;
    opens com.salesDep.controllers to javafx.fxml;
}