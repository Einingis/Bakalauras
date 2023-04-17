package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Groups;
import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Products;
import com.uni.bakalauras.model.Stored;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductsOperations {

    private static Session session;
    private static Transaction transaction;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Products> cq;

    public static List<Products> findAllProducts() {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> rootEntry = cq.from(Products.class);
        CriteriaQuery<Products> all = cq.select(rootEntry);
        TypedQuery<Products> allQuery = session.createQuery(all);
        List<Products> results = allQuery.getResultList();
        session.close();
        return results;
    }

    public static List<Products> findProductsByFilters(List<String> filters) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        List<Predicate> conditionsList = new ArrayList<Predicate>();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Products.class);
        Root<Products> root = cq.from(Products.class);

        if (!Objects.equals(filters.get(0), "")) {
            conditionsList.add(cb.equal(root.get("id"), filters.get(0)));
        }
        if (!Objects.equals(filters.get(1), "")) {
            List<Groups> groups = GroupsOperations.findByNamePart(filters.get(1));
            conditionsList.add(root.get("group").in(groups));
        }
        if (!Objects.equals(filters.get(2), "")) {
            conditionsList.add(cb.like(root.get("name"), "%" + filters.get(2) + "%"));
        }
        if (!Objects.equals(filters.get(3), "")) {
            conditionsList.add(cb.like(root.get("color"), "%" + filters.get(3) + "%"));
        }
        if (!Objects.equals(filters.get(4), "")) {
            conditionsList.add(cb.like(root.get("measurement"), "%" + filters.get(4) + "%"));
        }
        if (!Objects.equals(filters.get(5), "") && !Objects.equals(filters.get(6), "")) {
            conditionsList.add(cb.between(root.get("primeCost"), Double.valueOf(filters.get(5)), Double.valueOf(filters.get(6))));
        }
        else if (!Objects.equals(filters.get(5), "")) {
            conditionsList.add(cb.greaterThanOrEqualTo(root.get("primeCost"), Double.valueOf(filters.get(5))));
        }
        else if (!Objects.equals(filters.get(6), "")) {
            conditionsList.add(cb.lessThanOrEqualTo(root.get("primeCost"),filters.get(6)));
        }

        if (!Objects.equals(filters.get(7), "") && !Objects.equals(filters.get(8), "")) {
            conditionsList.add(cb.between(root.get("sellCost"), Double.valueOf(filters.get(7)), Double.valueOf(filters.get(8))));
        }
        else if (!Objects.equals(filters.get(7), "")) {
            conditionsList.add(cb.greaterThanOrEqualTo(root.get("sellCost"), filters.get(7)));
        }
        else if (!Objects.equals(filters.get(8), "")) {
            conditionsList.add(cb.lessThanOrEqualTo(root.get("sellCost"),filters.get(8)));
        }

        if (!Objects.equals(filters.get(9), "") && !Objects.equals(filters.get(10), "")) {
            conditionsList.add(cb.between(root.get("primeCost"), Integer.valueOf(filters.get(9)), Integer.valueOf(filters.get(10))));
        }
        else if (!Objects.equals(filters.get(9), "")) {
            conditionsList.add(cb.greaterThanOrEqualTo(root.get("primeCost"), filters.get(9)));
        }
        else if (!Objects.equals(filters.get(10), "")) {
            conditionsList.add(cb.lessThanOrEqualTo(root.get("primeCost"),filters.get(10)));
        }

        cq.select(root).where(cb.and(conditionsList.toArray(new Predicate[]{})));

        Query<Products> query = session.createQuery(cq);

        List<Products> results = query.getResultList();
        session.close();
        return results;
    }

    public static List<Stored> findProductPlaces(Long ProductId) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        CriteriaQuery<Stored> cq = cb.createQuery(Stored.class);
        Root<Stored> root = cq.from(Stored.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("product"), ProductId);
        cq.select(root).where(predicates);

        Query<Stored> query = session.createQuery(cq);

        List<Stored> results = query.getResultList();
        session.close();
        return results;

    }

}
