package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Groups;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GroupsOperations {
    private static Session session;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Groups> cq;

    public static List<Groups> findAllGroups() {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Groups.class);
        Root<Groups> root = cq.from(Groups.class);
        CriteriaQuery<Groups> all = cq.select(root);
        TypedQuery<Groups> allQuery = session.createQuery(all);
        List<Groups> results = allQuery.getResultList();
        session.close();
        return results;
    }

    public static List<Groups> findByNamePart(String name) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Groups.class);
        Root<Groups> root = cq.from(Groups.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.like(root.get("name"), "%"+name+"%");
        cq.select(root).where(cb.or(predicates));

        Query<Groups> query = session.createQuery(cq);
        List<Groups> results = query.getResultList();

        session.close();

        return results;

    }
}
