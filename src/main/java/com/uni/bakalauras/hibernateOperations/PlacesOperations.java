package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Places;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PlacesOperations {

    private static Session session;

    public static List<Places> findAllPlaces() {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Places> cq = cb.createQuery(Places.class);
        Root<Places> rootEntry = cq.from(Places.class);
        CriteriaQuery<Places> all = cq.select(rootEntry);
        TypedQuery<Places> allQuery = session.createQuery(all);

        List<Places> results = allQuery.getResultList();
        session.close();
        return results;
    }
}
