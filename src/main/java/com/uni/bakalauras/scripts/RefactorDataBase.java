package com.uni.bakalauras.scripts;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.hibernateOperations.FindAll;
import com.uni.bakalauras.model.*;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class RefactorDataBase {
//
//    private static Session session;
//    private static Transaction transaction;
//
//
//    public static void refactor() {
//
//        session = HibernateAnnotationUtil.getSessionFactory().openSession();
//
//        FindAll findAll = new FindAll(session);
//        Delete delete = new Delete(session, transaction);
//        Create create =new Create(session, transaction);
//
//        List<Clients> clientsList = FindAll.findAllClients();
//        Delete.deleteAll(clientsList);
//
//        List<Groups> groupsList = FindAll.findAllGroups();
//        Delete.deleteAll(groupsList);
//
//        List<Employees> employeesList = FindAll.findAllEmployees();
//        Delete.deleteAll(employeesList);
//
//        List<Orders> ordersList = FindAll.findAllOrders();
//        Delete.deleteAll(ordersList);
//
//        List<Places> placesList = FindAll.findAllPlaces();
//        Delete.deleteAll(placesList);
//
//        List<Products> productsList = FindAll.findAllProducts();
//        Delete.deleteAll(productsList);
//
//        clientsList.clear();
//        employeesList.clear();
//        groupsList.clear();
//        placesList.clear();
//        suppliersList.clear();
//        productsList.clear();
//
//        ordersList.clear();
//        stockOrdersList.clear();
//
//
//
//        Clients client = new Clients("Jonas", "860350957", "gatve 1123", "Vilnius");
//        Clients client2 = new Clients("Petras", "+37069888", "Kauno 1", "Siauliai");
//        Clients client3= new Clients("Onute", "86995599", "Grybiskiu 13", "Grybiskes");
//
//        clientsList.add(client);
//        clientsList.add(client2);
//        clientsList.add(client3);
//
//        Create.createAllInList(clientsList);
//
//        Employees employee = new Employees("Mintautas", "Einingis", "123456asd", "tech support");
//        Employees employee2 = new Employees("Ruta", "Misiukeviciute", "Rope558", "Marketing");
//        Employees employee3 = new Employees("Jurgita", "Einingiene", "Salta789", "Boss");
//
//        employeesList.add(employee);
//        employeesList.add(employee2);
//        employeesList.add(employee3);
//
//        Create.createAllInList(employeesList);
//
//        Groups group = new Groups("patalyne");
//        Groups group1 = new Groups("Kojnes");
//        Groups group2 = new Groups("Pledai");
//        Groups group3 = new Groups("Kaldros");
//        Groups group4 = new Groups("G11");
//
//        groupsList.add(group);
//        groupsList.add(group1);
//        groupsList.add(group2);
//        groupsList.add(group3);
//        groupsList.add(group4);
//
//        Create.createAllInList(groupsList);
//
//        Places place = new Places("nr2", "k15", "1", false);
//        Places place1 = new Places("nr2", "k15", "2", false);
//        Places place2 = new Places("nr2", "k15", "3", true);
//        Places place3 = new Places("nr2", "g5", "1", false);
//        Places place4 = new Places("nr2", "k15", "5", true);
//        Places place5 = new Places("nr2", "k15", "9", false);
//
//        placesList.add(place);
//        placesList.add(place1);
//        placesList.add(place2);
//        placesList.add(place3);
//        placesList.add(place4);
//        placesList.add(place5);
//
//        Create.createAllInList(placesList);
//
//        Suppliers supplier = new Suppliers("siuvykla", "861559", "Mazeikiai gatve 1", "pastovus tiekejas");
//        Suppliers supplier1 = new Suppliers("Gariunai Inga", "869995559", "Vilnius", "New");
//        Suppliers supplier2 = new Suppliers("Warsaw", "88", "warsaw", "-");
//
//        suppliersList.add(supplier);
//        suppliersList.add(supplier1);
//        suppliersList.add(supplier2);
//
//        Create.createAllInList(suppliersList);
//
//
//        List<Groups> results = FindAll.findGroupByExpressions("name", "patalyne");
//        List<Places> pRes = FindAll.findPlaceByExpressions();
//
//        Set<Places> setPlaces = new HashSet<>();
//
//        setPlaces.addAll(pRes);
//
//        Groups g = null;
//
//
//        for (Groups result : results) {
//            g = result;
//        }
//
//        Products product = new Products(g,"3D", 5,"200x220", "paveiksliukai", 10.5, 15.0, setPlaces);
//        Products product1 = new Products(g,"3D", 5,"180x200", "paveiksliukai", 10.5, 14.0, setPlaces);
//
//        productsList.add(product);
//        productsList.add(product1);
//
//        results.clear();
//
//        results = FindAll.findGroupByExpressions("name", "Kojnes");
//
//        for (Groups result : results) {
//            g = result;
//        }
//
//        Products product2 = new Products(g,"vyr kojnes", 10,"40-42", "juodos", 3.0, 5.0, setPlaces);
//        Products product3 = new Products(g,"kojnes elniukai", 7,"38-40", "-", 4.0, 7.5, setPlaces);
//
//        productsList.add(product2);
//        productsList.add(product3);
//
//        Create.createAllInList(productsList);
//
//
//
//
//
//
//        System.out.println("done");
//
//
//
//        session.close();
//    }
}
