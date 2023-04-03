package com.uni.bakalauras.scripts;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.hibernateOperations.*;
import com.uni.bakalauras.model.*;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.*;

public class RefactorDataBase {

    private static Session session;
    private static Transaction transaction;


    public static void refactor() {

        session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();

        fillClients();

        fillEmployees();

        fillGroups();

        fillPlaces();

        fillProducts();

        fillOrders();

        addProductsToOrders();

        fillReturns();

        System.out.println("done");

        session.close();
    }

    private static void addProductsToOrders() {

        List<Orders> orders = OrdersOperations.findAllOrders();
        List<Products> products = ProductsOperations.findAllProducts();

        Have have = new Have(products.get(0) ,orders.get(0), 1);
        Have have1 = new Have(products.get(3) ,orders.get(0), 2);
        Have have2 = new Have(products.get(5) ,orders.get(1), 3);
        Have have3 = new Have(products.get(2) ,orders.get(1), 1);
        Have have4 = new Have(products.get(0) ,orders.get(1), 5);

        List<Have> haveList = new ArrayList<>();

        haveList.add(have);
        haveList.add(have1);
        haveList.add(have2);
        haveList.add(have3);
        haveList.add(have4);

        Create.createAllInList(haveList);
    }

    private static void fillReturns() {
    }

    private static void fillOrders() {
        List<Orders> ordersList = OrdersOperations.findAllOrders();
        Delete.delete(ordersList);

        ordersList.clear();


        List<Clients> clients = ClientsOperations.findAllClients();
        Employees employees = EmployeesOperations.findEmployeesByName("Jurgita");

        Orders order = new Orders(clients.get(0), employees, "sukurtas", false,"Telsiai", "gatve1", "kurjeris", LocalDate.of(2023, 3, 1), 25.5);
        Orders order1 = new Orders(clients.get(1), employees, "sukurtas", false,"Vilnius" ,"gatve2", "Atvaziuos", LocalDate.of(2023, 3, 5), 50.0);

        ordersList.add(order);
        ordersList.add(order1);

        Create.createAllInList(ordersList);
    }

    private static void fillProducts() {
        int i = 0;

        List<Products> productsList = ProductsOperations.findAllProducts();
        Delete.delete(productsList);

        productsList.clear();

        List<Groups> groups = GroupsOperations.findAllGroups();

        Products product = new Products("3D", "paveiksliukai","200x220", 8.0, 10.5, 15);
        Products product1 = new Products("pliusas", "zalia","200x220", 25.0, 32.5, 8);
        Products product2 = new Products("Vyriskos", "pilka","40-42", 3.5, 5.0, 20);
        Products product3 = new Products("Kaledines", "raudonos","38-40", 5.0, 9.5, 10);
        Products product4 = new Products("paprasti", "geltonas","180x200", 15.3, 18.5, 15);
        Products product5 = new Products("Gauruoti", "Rudas","200x220", 23.5, 30.4, 6);
        Products product6 = new Products("vilnos", "margos","200x220", 20.5, 28.0, 5);
        Products product7 = new Products("Vasarines", "margos","180x200", 10.5, 15.0, 12);
        Products product8 = new Products("N11", "Juodi","L", 8.0, 10.5, 3);
        Products product9 = new Products("N15", "Raudoni","M", 7.0, 10.0, 4);

        productsList.add(product);
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product6);
        productsList.add(product7);
        productsList.add(product8);
        productsList.add(product9);

        for (Groups group : groups) {
            productsList.get(i).setGroup(group);
            productsList.get(i+1).setGroup(group);
            i += 2;
        }

        Create.createAllInList(productsList);
    }

    public static void fillClients() {

        List<Clients> clientsList = ClientsOperations.findAllClients();
        Delete.delete(clientsList);

        clientsList.clear();

        Clients client = new Clients("Jonas", "Jonaitis", "860350957", "gatve 1123", "Vilnius");
        Clients client2 = new Clients("Petras", "Petraitis", "+37069888", "Kauno 1", "Siauliai");
        Clients client3= new Clients("Onute", "Onyte", "86995599", "Grybiskiu 13", "Grybiskes");

        clientsList.add(client);
        clientsList.add(client2);
        clientsList.add(client3);

        Create.createAllInList(clientsList);
    }

    public static void fillEmployees() {

        List<Employees> employeesList = EmployeesOperations.findAllEmployees();
        Delete.delete(employeesList);

        employeesList.clear();

        Employees employee = new Employees("Mintautas", "Einingis", "123456asd", "tech support");
        Employees employee2 = new Employees("Ruta", "Misiukeviciute", "Rope558", "Marketing");
        Employees employee3 = new Employees("Jurgita", "Einingiene", "Salta789", "Boss");

        employeesList.add(employee);
        employeesList.add(employee2);
        employeesList.add(employee3);

        Create.createAllInList(employeesList);
    }

    public static void fillGroups() {

        List<Groups> groupsList = GroupsOperations.findAllGroups();
        Delete.delete(groupsList);

        groupsList.clear();

        Groups group = new Groups("patalyne");
        Groups group1 = new Groups("Kojnes");
        Groups group2 = new Groups("Pledai");
        Groups group3 = new Groups("Kaldros");
        Groups group4 = new Groups("G11");

        groupsList.add(group);
        groupsList.add(group1);
        groupsList.add(group2);
        groupsList.add(group3);
        groupsList.add(group4);

        Create.createAllInList(groupsList);
    }

    public static void fillPlaces() {

        List<Places> placesList = PlacesOperations.findAllPlaces();
        Delete.delete(placesList);

        placesList.clear();

        Places place = new Places("nr2", "k15", "1", false);
        Places place1 = new Places("nr2", "k15", "2", false);
        Places place2 = new Places("nr2", "k15", "3", true);
        Places place3 = new Places("nr2", "g5", "1", false);
        Places place4 = new Places("nr2", "k15", "5", true);
        Places place5 = new Places("nr2", "k15", "9", false);

        placesList.add(place);
        placesList.add(place1);
        placesList.add(place2);
        placesList.add(place3);
        placesList.add(place4);
        placesList.add(place5);

        Create.createAllInList(placesList);
    }



}
