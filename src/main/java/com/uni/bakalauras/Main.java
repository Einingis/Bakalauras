package com.uni.bakalauras;

import com.uni.bakalauras.hibernateOperations.FindAll;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private static Session session;
    private static Transaction transaction;

    private static FindAll refreshDataBase;

    public static void main(String[] args) throws SQLException {
        launch();

        //RefactorDataBase.refactor();

//        session = HibernateAnnotationUtil.getSessionFactory().openSession();
//        //transaction = session.beginTransaction();
//        refreshDataBase = new FindAll(session);
//
//        List<Products> list = FindAll.findAllProducts();
//
//        List<Clients> clientsList = FindAll.findAllClients();
//
//        for (Products element : list) {
//            transaction = session.beginTransaction();
//
//            session.delete(element);
//            transaction.commit();
//        }
//
//        //transaction.rollback();
//        session.close();



//        Groups group = new Groups("g1");
//
//        Products product1 = new Products(group, "pagalve");
//        Products product2 = new Products(group, "pledas");
//        Set<Products> itemsSet = new HashSet<>();
//        itemsSet.add(product1);
//        itemsSet.add(product2);
//
//        group.setProducts(itemsSet);
//
//        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        System.out.println("Session created");
//
//        // start transaction
//        Transaction tx = session.beginTransaction();
//
//        // Save the Model object
//        session.save(group);
//        session.save(product1);
//        session.save(product2);
//
//        // Commit transaction
//        tx.commit();
//        System.out.println("Types ID= " + group.getId());
//        System.out.println("product1 ID= " + product1.getId() + ", Foreign Key Types ID= " + product1.getGroup().getId());
//        System.out.println("product2 ID= " + product2.getId() + ", Foreign Key Types ID= " + product2.getGroup().getId());
    }
}