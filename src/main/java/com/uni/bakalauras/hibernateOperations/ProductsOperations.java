package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.model.Products;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductsOperations {

    private static Session session;

    public ProductsOperations(Session session) {
        super();
        this.session = session;
    }

    public static List<Products> findAllProducts() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> rootEntry = cq.from(Products.class);
        CriteriaQuery<Products> all = cq.select(rootEntry);
        TypedQuery<Products> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

}
