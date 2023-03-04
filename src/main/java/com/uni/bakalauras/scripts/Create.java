package com.uni.bakalauras.scripts;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Create {

    private static Session session;
    private static Transaction transaction;

    public Create(Session session, Transaction transaction) {
        super();
        this.session = session;
        this.transaction = transaction;
    }

    public static void createAllInList(List<?> list) {

        for (Object element : list) {
            transaction = session.beginTransaction();
            session.save(element);

            transaction.commit();
        }

    }
}
