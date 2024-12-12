package com.example.Real.Time.Ticketing.System.dao;

import com.example.Real.Time.Ticketing.System.domain.Transactions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager em;

    public Transactions saveTransactions(String Transaction){
        try {
            Transactions transactions = new Transactions();
            transactions.setTransactionMessage(Transaction);
            em.persist(transactions);
            return transactions;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
