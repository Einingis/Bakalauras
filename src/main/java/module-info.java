module com.uni.bakalauras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;


    opens com.uni.bakalauras to javafx.fxml;
    exports com.uni.bakalauras;
    exports com.uni.bakalauras.fxmlControllers;
    opens com.uni.bakalauras.fxmlControllers to javafx.fxml;
    exports com.uni.bakalauras.model;
    opens com.uni.bakalauras.model to java.persistence, javafx.fxml, org.hibernate.orm.core;
    exports com.uni.bakalauras.model.Ids;
    opens com.uni.bakalauras.model.Ids to java.persistence, javafx.fxml, org.hibernate.orm.core;
}