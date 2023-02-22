package com.example.dell.bslshop.model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String password;

    public Customer(int id, String name, String phone, String address, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
