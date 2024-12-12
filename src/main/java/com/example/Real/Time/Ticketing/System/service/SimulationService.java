package com.example.Real.Time.Ticketing.System.service;

import com.example.Real.Time.Ticketing.System.model.request.CountRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimulationService {
    public void startSimulation() throws Exception;
    public void stopSimulation() throws Exception;

}
