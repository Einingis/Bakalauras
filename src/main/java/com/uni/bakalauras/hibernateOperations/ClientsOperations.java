package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class ClientsOperations {

    private static Session session;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Clients> cq;

    public static List<Clients> findAllClients() {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Clients.class);
        Root<Clients> rootEntry = cq.from(Clients.class);
        CriteriaQuery<Clients> all = cq.select(rootEntry);
        TypedQuery<Clients> allQuery = session.createQuery(all);
        List<Clients> results = allQuery.getResultList();
        session.close();
        return results;

    }

    public static List<Clients> findByNamePart(String condition) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Clients.class);
        Root<Clients> root = cq.from(Clients.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.like(root.get("name"), "%"+condition+"%");
        predicates[1] = cb.like(root.get("surname"), "%"+condition+"%");
        cq.select(root).where(cb.or(predicates));

        Query<Clients> query = session.createQuery(cq);
        List<Clients> results = query.getResultList();

        session.close();

        return results;
    }

    public static Optional<Clients> findByFullName(String name, String surname) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Clients.class);
        Root<Clients> root = cq.from(Clients.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.like(root.get("name"), name);
        predicates[1] = cb.like(root.get("surname"), surname);
        cq.select(root).where(cb.and(predicates));

        Query<Clients> query = session.createQuery(cq);
        Optional<Clients> result = query.uniqueResultOptional();

        session.close();

        return result;
    }


    public static List<Clients> findClientsByFilters(List<String> filters) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        List<Predicate> conditionsList = new ArrayList<Predicate>();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Clients.class);
        Root<Clients> root = cq.from(Clients.class);

        if (!Objects.equals(filters.get(0), "")) {
            conditionsList.add(cb.like(root.get("name"), "%"+filters.get(0)+"%"));
        }
        if (!Objects.equals(filters.get(1), "")) {
            conditionsList.add(cb.like(root.get("surname"), "%"+filters.get(1)+"%"));
        }
        if (!Objects.equals(filters.get(2), "")) {
            conditionsList.add(cb.like(root.get("number"), "%"+filters.get(2)+"%"));
        }
        if (!Objects.equals(filters.get(3), "")) {
            conditionsList.add(cb.like(root.get("city"), "%"+filters.get(3)+"%"));
        }
        if (!Objects.equals(filters.get(4), "")) {
            conditionsList.add(cb.like(root.get("address"), "%"+filters.get(4)+"%"));
        }
        
        cq.select(root).where(cb.and(conditionsList.toArray(new Predicate[]{})));

        Query<Clients> query = session.createQuery(cq);

        List<Clients> results = query.getResultList();
        session.close();
        return results;

    }
}
