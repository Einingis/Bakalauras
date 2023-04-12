package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import com.uni.bakalauras.model.Employees;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class EmployeesOperations {

    private static Session session;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Employees> cq;

    public static List<Employees> findAllEmployees() {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Employees.class);
        Root<Employees> rootEntry = cq.from(Employees.class);

        CriteriaQuery<Employees> all = cq.select(rootEntry);
        TypedQuery<Employees> allQuery = session.createQuery(all);
        List<Employees> results = allQuery.getResultList();
        session.close();
        return results;
    }

    public static Employees findEmployeesByName(String condition) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Employees.class);
        Root<Employees> root = cq.from(Employees.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("name"), condition);
        cq.select(root).where(predicates);

        Query<Employees> query = session.createQuery(cq);
        Employees result = query.getSingleResult();
        session.close();
        return result;
    }

    public static Optional<Employees> findEmployeesLogin(String name, String password) {
        session = HibernateAnnotationUtil.getSessionFactory().openSession();

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Employees.class);
        Root<Employees> root = cq.from(Employees.class);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.equal(root.get("name"), name);
        predicates[1] = cb.equal(root.get("password"), password);
        cq.select(root).where(predicates);

        Query<Employees> query = session.createQuery(cq);
        Optional<Employees> result = query.uniqueResultOptional();
        session.close();
        return result;
    }
}
