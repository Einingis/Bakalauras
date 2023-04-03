package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Returns;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReturnsOperations {

    private static Session session;

    public static List<Returns> findAllReturns() {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Returns> cq = cb.createQuery(Returns.class);
        Root<Returns> rootEntry = cq.from(Returns.class);
        CriteriaQuery<Returns> all = cq.select(rootEntry);
        TypedQuery<Returns> allQuery = session.createQuery(all);

        List<Returns> results = allQuery.getResultList();
        session.close();
        return results;
    }
}
