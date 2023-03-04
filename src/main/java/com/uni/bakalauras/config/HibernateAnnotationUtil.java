package com.uni.bakalauras.config;


import com.uni.bakalauras.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateAnnotationUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * Utility class
     */
    private HibernateAnnotationUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySettings(dbSettings())
          .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
            .addAnnotatedClass(Clients.class)
            .addAnnotatedClass(Employees.class)
            .addAnnotatedClass(Groups.class)
            .addAnnotatedClass(Have.class)
            .addAnnotatedClass(Orders.class)
            .addAnnotatedClass(Places.class)
            .addAnnotatedClass(Products.class)
            .addAnnotatedClass(Returning.class)
            .addAnnotatedClass(Returns.class)
            .addAnnotatedClass(Stored.class)
            .buildMetadata();

        return metadata.buildSessionFactory();
    }

    private static Map<String, Object> dbSettings() {
        Map<String, Object> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, "jdbc:mysql://localhost/bakalaurasdb");
        dbSettings.put(Environment.USER, "root");
        dbSettings.put(Environment.PASS, "");
        dbSettings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        dbSettings.put(Environment.SHOW_SQL, "true");
        dbSettings.put(Environment.HBM2DDL_AUTO, "update");
        return dbSettings;
    }
}
