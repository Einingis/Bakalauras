package com.uni.bakalauras.hibernateOperations;

import java.util.List;

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
