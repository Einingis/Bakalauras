package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Clients;
import com.uni.bakalauras.model.Have;
import com.uni.bakalauras.model.Orders;
import com.uni.bakalauras.model.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

public class OrdersOperations {

    private static Session session;
    private static Transaction transaction;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Orders> cq;

    public OrdersOperations(Session session) {
        super();
        this.session = session;
    }

    public static List<Orders> findAllOrders() {
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

    public static List<Have> findOrdersProduct(Long orderId) {

        cb = session.getCriteriaBuilder();
        CriteriaQuery<Have> cq = cb.createQuery(Have.class);
        Root<Have> root = cq.from(Have.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("order"), orderId);
        cq.select(root).where(predicates);

        Query<Have> query = session.createQuery(cq);
        return query.getResultList();
    }

    public static List<Orders> findOrderByFilters(List<String> filters) {

        List<Predicate> conditionsList = new ArrayList<Predicate>();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Orders.class);
        Root<Orders> root = cq.from(Orders.class);

        if (!Objects.equals(filters.get(0), "")) {
            conditionsList.add(cb.equal(root.get("id"), filters.get(0)));
        }
        if (!Objects.equals(filters.get(1), "")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(filters.get(1), formatter);

            conditionsList.add(cb.greaterThanOrEqualTo(root.get("created"), date));

        }
        if (!Objects.equals(filters.get(2), "")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(filters.get(2), formatter);

            conditionsList.add(cb.lessThanOrEqualTo(root.get("created"), date));
        }
        if (!Objects.equals(filters.get(3), "")) {
            ClientsOperations clientsOperations = new ClientsOperations(session);

           List<Clients> clients =  ClientsOperations.findByNamePart(filters.get(3));
            conditionsList.add(root.get("client").in(clients));

        }
        if (!Objects.equals(filters.get(4), "")) {
            conditionsList.add(cb.equal(root.get("orderAddress"), "%"+filters.get(4)+"%"));
        }
        if (!Objects.equals(filters.get(5), "") && !Objects.equals(filters.get(5), "Visi")) {
            if (Objects.equals(filters.get(5), "Neapmoketi")) {
                conditionsList.add(cb.equal(root.get("payedFor"), false));
            }
            else if (Objects.equals(filters.get(5), "Apmoketi")) {
                conditionsList.add(cb.equal(root.get("payedFor"), true));
            }
        }
        if (!Objects.equals(filters.get(6), "")) {
            conditionsList.add(cb.equal(root.get("sum"), Double.parseDouble(filters.get(6))));
        }
        if (!Objects.equals(filters.get(7), "")) {
            conditionsList.add(cb.equal(root.get("status"), "%"+filters.get(7)+"%"));
        }
        if (!Objects.equals(filters.get(8), "")) {
            conditionsList.add(cb.equal(root.get("deliveryType"), "%"+filters.get(8)+"%"));
        }

        cq.select(root).where(cb.and(conditionsList.toArray(new Predicate[]{})));

        Query<Orders> query = session.createQuery(cq);

        return query.getResultList();
    }
}
