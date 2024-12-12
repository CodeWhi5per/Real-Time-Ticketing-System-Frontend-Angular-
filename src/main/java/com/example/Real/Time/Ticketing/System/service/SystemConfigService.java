package com.example.Real.Time.Ticketing.System.service;

import com.example.Real.Time.Ticketing.System.domain.Counts;
import com.example.Real.Time.Ticketing.System.domain.SystemConfig;
import com.example.Real.Time.Ticketing.System.model.request.CountRequest;
import com.example.Real.Time.Ticketing.System.model.request.SystemConfigRequest;
import org.springframework.stereotype.Service;

@Service
public interface SystemConfigService {

    public String saveSystemConfig(SystemConfigRequest systemConfigRequest) throws Exception;
    public String updateCount(CountRequest countRequest) throws Exception;
    public SystemConfig loadLatestSystemConfig() throws Exception ;
    public Counts loadLatestCount() throws Exception;
}
