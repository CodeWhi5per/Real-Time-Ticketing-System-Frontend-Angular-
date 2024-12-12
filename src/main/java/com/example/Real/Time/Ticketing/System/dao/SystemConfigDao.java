package com.example.Real.Time.Ticketing.System.dao;

import com.example.Real.Time.Ticketing.System.domain.SystemConfig;

public interface SystemConfigDao {
    public SystemConfig saveSystemConfig(SystemConfig systemConfig);
    public SystemConfig getLatestSystemConfig();
}
