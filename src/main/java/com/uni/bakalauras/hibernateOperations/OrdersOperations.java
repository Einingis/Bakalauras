package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Orders;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrdersOperations {

    private static Session session;

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

}
