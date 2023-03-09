package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.model.Returns;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReturnsOperations {

    private static Session session;

    public ReturnsOperations(Session session) {
        super();
        this.session = session;
    }

    public static List<Returns> findAllReturns() {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Returns> cq = cb.createQuery(Returns.class);
        Root<Returns> rootEntry = cq.from(Returns.class);
        CriteriaQuery<Returns> all = cq.select(rootEntry);
        TypedQuery<Returns> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
