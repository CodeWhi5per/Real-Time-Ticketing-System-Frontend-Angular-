package com.example.Real.Time.Ticketing.System.service;

import com.example.Real.Time.Ticketing.System.dao.CountDao;
import com.example.Real.Time.Ticketing.System.dao.SystemConfigDao;
import com.example.Real.Time.Ticketing.System.domain.Counts;
import com.example.Real.Time.Ticketing.System.domain.SystemConfig;
import com.example.Real.Time.Ticketing.System.enums.RtsError;
import com.example.Real.Time.Ticketing.System.exeption.RtsExeption;
import com.example.Real.Time.Ticketing.System.model.request.CountRequest;
import com.example.Real.Time.Ticketing.System.model.request.SystemConfigRequest;
import com.example.Real.Time.Ticketing.System.model.response.SystemConfigResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Autowired
    private CountDao countDao;

    @Override
    @Transactional
    public String saveSystemConfig(SystemConfigRequest systemConfigRequest) throws Exception {

        SystemConfig systemConfig = new SystemConfig();

        systemConfig.setTotalTickets(systemConfigRequest.getTotalTickets());
        systemConfig.setTicketReleaseRate(systemConfigRequest.getTicketReleaseRate());
        systemConfig.setCustomerRetrievalRate(systemConfigRequest.getCustomerRetrievalRate());
        systemConfig.setMaxTicketCapacity(systemConfigRequest.getMaxTicketCapacity());
        systemConfig.setCreatedDate(new Date());

        SystemConfig systemConfig1 = systemConfigDao.saveSystemConfig(systemConfig);

        if (systemConfig1 != null) {
            return "System Configuration Saved Successfully!";

        } else {
            throw new RtsExeption(RtsError.SYSTEM_CONFIGURATION_SAVED_FAILED);
        }
    }
    @Override
    public SystemConfig loadLatestSystemConfig() throws Exception {

        SystemConfig systemConfig = systemConfigDao.getLatestSystemConfig();
        if (systemConfig != null) {
            return systemConfig;
        } else {
            throw new RtsExeption(RtsError.SYSTEM_DATA_LOAD_FAILED);
        }
    }
    @Override
    @Transactional
    public String updateCount(CountRequest countRequest) throws Exception{

        Counts counts = loadLatestCount();

        counts.setVendorCount(countRequest.getVendorCount());
        counts.setCustomerCount(countRequest.getCustomerCount());
        counts.setVipCustomerCount(countRequest.getVipCustomerCount());
        counts.setCreatedDate(new Date());

        Counts counts1 = countDao.updateCount(counts);

        if (counts1 != null) {
            return "Count Updated Successfully!";
        } else {
            throw new RtsExeption(RtsError.COUNT_UPDATE_FAILED);
        }
    }

    @Override
    public Counts loadLatestCount() throws Exception{

        Counts counts = countDao.getLatestCount();

        if (counts != null) {
            return counts;
        } else {
            throw new RtsExeption(RtsError.COUNT_LOAD_FAILED);
        }
    }
}
