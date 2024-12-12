package com.example.Real.Time.Ticketing.System.model.request;

public class CountRequest {

        private int vendorCount;
        private int customerCount;
        private int vipCustomerCount;

        public CountRequest() {
        }

        public CountRequest(int vendorCount, int customerCount, int vipCustomerCount) {
            this.vendorCount = vendorCount;
            this.customerCount = customerCount;
            this.vipCustomerCount = vipCustomerCount;
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
}
