package com.example.microservicio_pagos.dto;

public class PayerDTO {
    private String name;
    private String surname;
    private String email;
    private PhoneDTO phone;
    private IdentificationDTO identification;
    private AddressDTO address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public PhoneDTO getPhone() { return phone; }
    public void setPhone(PhoneDTO phone) { this.phone = phone; }
    public IdentificationDTO getIdentification() { return identification; }
    public void setIdentification(IdentificationDTO identification) { this.identification = identification; }
    public AddressDTO getAddress() { return address; }
    public void setAddress(AddressDTO address) { this.address = address; }
}
