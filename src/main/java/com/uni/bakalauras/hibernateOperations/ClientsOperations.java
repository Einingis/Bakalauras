package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Groups;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientsOperations {

    private static Session session;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Clients> cq;

    public ClientsOperations(Session session) {
        super();
        this.session = session;
    }

    public static List<Clients> findAllClients() {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Clients.class);
        Root<Clients> rootEntry = cq.from(Clients.class);
        CriteriaQuery<Clients> all = cq.select(rootEntry);
        TypedQuery<Clients> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Clients> findByNamePart(String condition) {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Clients.class);
        Root<Clients> root = cq.from(Clients.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.like(root.get("name"), "%"+condition+"%");
        predicates[1] = cb.like(root.get("surname"), "%"+condition+"%");
        cq.select(root).where(cb.or(predicates));

        Query<Clients> query = session.createQuery(cq);
        return query.getResultList();
    }


}
