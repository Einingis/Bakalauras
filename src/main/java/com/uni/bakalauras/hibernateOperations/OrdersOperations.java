package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrdersOperations {

    private static Session session;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Orders> cq;

    public OrdersOperations(Session session) {
        super();
        this.session = session;
    }

    public static List<Orders> findAllOrders() {

        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> rootEntry = cq.from(Orders.class);
        CriteriaQuery<Orders> all = cq.select(rootEntry);
        TypedQuery<Orders> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
    public static List<Orders> findOrdersById(String condition) {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Orders.class);
        Root<Orders> root = cq.from(Orders.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("id"), condition);
        cq.select(root).where(predicates);

        Query<Orders> query = session.createQuery(cq);
        return query.getResultList();
    }
}
