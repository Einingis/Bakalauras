package com.uni.bakalauras.hibernateOperations;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.uni.bakalauras.model.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class FindAll {

    private static Session session;

    public FindAll(Session session) {
        super();
        this.session = session;
    }

    public static List<Clients> findAllClients() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Clients> cq = cb.createQuery(Clients.class);
        Root<Clients> rootEntry = cq.from(Clients.class);
        CriteriaQuery<Clients> all = cq.select(rootEntry);
        TypedQuery<Clients> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Employees> findAllEmployees() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employees> cq = cb.createQuery(Employees.class);
        Root<Employees> rootEntry = cq.from(Employees.class);
        CriteriaQuery<Employees> all = cq.select(rootEntry);
        TypedQuery<Employees> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Groups> findAllGroups() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Groups> cq = cb.createQuery(Groups.class);
        Root<Groups> rootEntry = cq.from(Groups.class);
        CriteriaQuery<Groups> all = cq.select(rootEntry);
        TypedQuery<Groups> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Orders> findAllOrders() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> rootEntry = cq.from(Orders.class);
        CriteriaQuery<Orders> all = cq.select(rootEntry);
        TypedQuery<Orders> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Places> findAllPlaces() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Places> cq = cb.createQuery(Places.class);
        Root<Places> rootEntry = cq.from(Places.class);
        CriteriaQuery<Places> all = cq.select(rootEntry);
        TypedQuery<Places> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Products> findAllProducts() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> rootEntry = cq.from(Products.class);
        CriteriaQuery<Products> all = cq.select(rootEntry);
        TypedQuery<Products> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Groups> findGroupByExpressions(String model, String criteria) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Groups> cr = cb.createQuery(Groups.class);
        Root<Groups> root = cr.from(Groups.class);
        cr.select(root).where(root.get(model).in(criteria));

        Query<Groups> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static List<Places> findPlaceByExpressions() {

        Predicate[] predicates = new Predicate[3];


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Places> cr = cb.createQuery(Places.class);
        Root<Places> root = cr.from(Places.class);

        predicates[0] = root.get("warehouse").in("nr2");
        predicates[1] = root.get("shelf").in("k15");
        predicates[2] = root.get("place").in("2");

        cr.select(root).where(predicates);

        Query<Places> query = session.createQuery(cr);
        return query.getResultList();
    }

}
