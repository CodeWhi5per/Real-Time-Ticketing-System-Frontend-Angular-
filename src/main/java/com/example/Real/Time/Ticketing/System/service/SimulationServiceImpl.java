package com.example.Real.Time.Ticketing.System.service;

import com.example.Real.Time.Ticketing.System.core.Customer;
import com.example.Real.Time.Ticketing.System.core.TicketPool;
import com.example.Real.Time.Ticketing.System.core.VIPCustomer;
import com.example.Real.Time.Ticketing.System.core.Vendor;

import com.example.Real.Time.Ticketing.System.domain.Counts;
import com.example.Real.Time.Ticketing.System.domain.SystemConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SimulationServiceImpl implements SimulationService {

    @Autowired
    private SystemConfigService systemConfigService;

    private final List<Thread> threads = new ArrayList<>(); // List to store threads.



    @Override
    public void startSimulation() throws Exception{

            SystemConfig systemConfig = systemConfigService.loadLatestSystemConfig();
            Counts counts = systemConfigService.loadLatestCount();

            TicketPool ticketPool = new TicketPool(systemConfig.getTotalTickets(), systemConfig.getMaxTicketCapacity());

            // Create and start vendor threads.
            for (int i = 0; i < counts.getVendorCount(); i++) {

                int vendorId = i + 1;

                Vendor vendor = new Vendor(vendorId, ticketPool, systemConfig.getTicketReleaseRate());  // Create a new vendor.
                Thread vendorThread = new Thread(vendor); // Create a new thread for the vendor.
                threads.add(vendorThread);
                vendorThread.start();


                // Start the thread.
            }

            // Create and start VIP customer threads.
            for (int i = 0; i < counts.getVipCustomerCount(); i++) {

                int vipCustomerId = i + 1;

                VIPCustomer vipCustomer = new VIPCustomer(vipCustomerId, ticketPool, systemConfig.getTicketReleaseRate());  // Create a new VIP customer.
                Thread vipCustomerThread = new Thread(vipCustomer);     // Create a new thread for the VIP customer.
                threads.add(vipCustomerThread);
                vipCustomerThread.start();  // Start the thread.

            }

            // Create and start customer threads.
            for (int i = 0; i < counts.getVipCustomerCount(); i++) {

                int customerId = i + 1;

                Customer customer = new Customer(customerId, ticketPool, systemConfig.getTicketReleaseRate()); // Create a new customer.
                Thread customerThread = new Thread(customer);   // Create a new thread for the customer.
                threads.add(customerThread);
                customerThread.start();     // Start the thread.
            }

    }

    @Override
    public void stopSimulation() throws Exception{
        for (Thread thread : threads) { // Iterate through the threads.
            if (thread.isAlive()) {
                thread.interrupt();     // Interrupt the thread.
            }
        }
        threads.clear();
    }
}
