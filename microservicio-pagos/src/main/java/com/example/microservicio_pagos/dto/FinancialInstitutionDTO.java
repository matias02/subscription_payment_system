package com.example.microservicio_pagos.dto;

public class FinancialInstitutionDTO {
    private String id;
    private String name;

    public FinancialInstitutionDTO() {
    }

    public FinancialInstitutionDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
