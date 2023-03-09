package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.model.Clients;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientsOperations {

    private static Session session;

    public ClientsOperations(Session session) {
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
}
