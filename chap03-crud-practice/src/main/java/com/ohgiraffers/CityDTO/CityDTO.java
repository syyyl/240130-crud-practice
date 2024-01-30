package com.ohgiraffers.CityDTO;

public class CityDTO {

    private int id;
    private String name;
    private String conutryCode;
    private String district;
    private int population;

    public CityDTO() {
    }

    public CityDTO(int id, String name, String conutryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.conutryCode = conutryCode;
        this.district = district;
        this.population = population;
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

    public String getConutryCode() {
        return conutryCode;
    }

    public void setConutryCode(String conutryCode) {
        this.conutryCode = conutryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}
