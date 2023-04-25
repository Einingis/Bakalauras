package com.uni.bakalauras.hibernateOperations;

import com.uni.bakalauras.config.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Delete {

    private static Session session;
    private static Transaction transaction;

    public static void delete(List<?> list) {
        for (Object element : list) {
            session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.delete(element);
            transaction.commit();
            session.close();
        }
    }
}
