package com.example.Real.Time.Ticketing.System.controller;

import com.example.Real.Time.Ticketing.System.logs.WebLogAppender;
import com.example.Real.Time.Ticketing.System.model.request.CountRequest;
import com.example.Real.Time.Ticketing.System.model.request.SystemConfigRequest;
import com.example.Real.Time.Ticketing.System.model.response.RtsRespose;
import com.example.Real.Time.Ticketing.System.service.SimulationService;
import com.example.Real.Time.Ticketing.System.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api.rts")
public class SystemController {

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private SimulationService simulationService;


    @RequestMapping(value="/saveSystemConfig" , method = RequestMethod.POST)
    public String saveSystemConfigToDb(@RequestBody SystemConfigRequest systemConfigRequest) throws Exception{

    return systemConfigService.saveSystemConfig(systemConfigRequest);
    }


    @RequestMapping(value="/updateCount" , method = RequestMethod.PUT)
    public String updateCount(@RequestBody CountRequest countRequest) throws Exception{

        return systemConfigService.updateCount(countRequest);
    }

    @RequestMapping(value="/startSimulation" , method = RequestMethod.GET)
    public String startSimulation() throws Exception{
        simulationService.startSimulation();
        WebLogAppender.getLogQueue().clear();
        return "Simulation started!";
    }

    @RequestMapping(value="/stopSimulation" , method = RequestMethod.GET)
    public String stopSimulation() throws Exception{
        simulationService.stopSimulation();
        return "Simulation stopped!";
    }

    @RequestMapping(value="/getLogs" , method = RequestMethod.GET)
    public List<String> sendLogsToWeb() throws Exception{

        return WebLogAppender.getLogQueue().stream().map(String::trim).collect(Collectors.toList());
    }
}
