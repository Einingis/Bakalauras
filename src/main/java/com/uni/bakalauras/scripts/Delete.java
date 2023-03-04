package com.uni.bakalauras.scripts;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Delete {

    private static Session session;
    private static Transaction transaction;

    public Delete(Session session, Transaction transaction) {
        super();
        this.session = session;
        this.transaction = transaction;
    }

    public static void deleteAll(List<?> list) {
        for (Object element : list) {
            transaction = session.beginTransaction();

            session.delete(element);
            transaction.commit();
        }
    }
}
