package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.model.Employees;
import com.uni.bakalauras.model.Groups;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeesOperations {

    private static Session session;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<Employees> cq;

    public EmployeesOperations(Session session) {
        super();
        this.session = session;
    }

    public static List<Employees> findAllEmployees() {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Employees.class);
        Root<Employees> rootEntry = cq.from(Employees.class);

        CriteriaQuery<Employees> all = cq.select(rootEntry);
        TypedQuery<Employees> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Employees> findEmployeesByName(String condition) {

        cb = session.getCriteriaBuilder();
        cq = cb.createQuery(Employees.class);
        Root<Employees> root = cq.from(Employees.class);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = cb.equal(root.get("name"), condition);
        cq.select(root).where(predicates);

        Query<Employees> query = session.createQuery(cq);
        return query.getResultList();
    }
}
