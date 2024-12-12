package com.example.Real.Time.Ticketing.System.dao;

import com.example.Real.Time.Ticketing.System.domain.SystemConfig;
import com.example.Real.Time.Ticketing.System.domain.Transactions;

public interface TransactionDao {
    public Transactions saveTransactions(String Transaction);
}
