package com.uni.bakalauras.hibernateOperations;

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

    public GroupsOperations(Session session) {
        super();
        this.session = session;

    }

    public static List<Groups> findAllGroups() {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Groups.class);
        Root<Groups> root = cq.from(Groups.class);
        CriteriaQuery<Groups> all = cq.select(root);
        TypedQuery<Groups> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Groups> findGroupsByName(String condition) {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Groups.class);
        Root<Groups> root = cq.from(Groups.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("name"), condition);
        cq.select(root).where(predicates);

        Query<Groups> query = session.createQuery(cq);
        return query.getResultList();
    }
}
