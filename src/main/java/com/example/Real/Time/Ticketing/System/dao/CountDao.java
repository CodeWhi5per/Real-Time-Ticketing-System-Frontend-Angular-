package com.example.Real.Time.Ticketing.System.dao;

import com.example.Real.Time.Ticketing.System.domain.Counts;

public interface CountDao {
    public Counts updateCount(Counts counts);
    public Counts getLatestCount();
}
