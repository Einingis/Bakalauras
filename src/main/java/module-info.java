module com.example.bakalauras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.bakalauras to javafx.fxml;
    exports com.uni.bakalauras;
    opens com.uni.bakalauras.model to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.uni.bakalauras.model;
}