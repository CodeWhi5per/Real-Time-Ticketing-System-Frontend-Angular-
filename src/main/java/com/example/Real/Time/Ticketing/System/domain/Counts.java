package com.example.Real.Time.Ticketing.System.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="counts")
@NamedQuery(name="Counts.findAll", query="SELECT c FROM Counts c")
public class Counts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="vendor_count")
    private int vendorCount;

    @Column(name="customer_count")
    private int customerCount;

    @Column(name="vip_customer_count")
    private int vipCustomerCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    public Counts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getVipCustomerCount() {
        return vipCustomerCount;
    }

    public void setVipCustomerCount(int vipCustomerCount) {
        this.vipCustomerCount = vipCustomerCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
