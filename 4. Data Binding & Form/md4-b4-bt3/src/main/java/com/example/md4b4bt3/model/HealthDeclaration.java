package com.example.md4b4bt3.model;

public class HealthDeclaration {
    private String fullName;
    private Integer yearOfBirth;
    private String gender;
    private String passportNumber;
    private String transportType;
    private String transportNumber;
    private String departureDate;
    private String visitedCities;
    private String province;
    private String district;
    private String address;
    private String phone;
    private Boolean fever;
    private Boolean cough;
    private Boolean shortnessOfBreath;
    private Boolean soreThroat;
    private Boolean visitedAnimalFarm;
    private Boolean contactedCovidPatient;

    // Constructor rỗng - BẮT BUỘC
    public HealthDeclaration() {
    }

    // GETTER & SETTER ĐẦY ĐỦ
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getVisitedCities() {
        return visitedCities;
    }

    public void setVisitedCities(String visitedCities) {
        this.visitedCities = visitedCities;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getFever() {
        return fever;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }

    public Boolean getCough() {
        return cough;
    }

    public void setCough(Boolean cough) {
        this.cough = cough;
    }

    public Boolean getShortnessOfBreath() {
        return shortnessOfBreath;
    }

    public void setShortnessOfBreath(Boolean shortnessOfBreath) {
        this.shortnessOfBreath = shortnessOfBreath;
    }

    public Boolean getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(Boolean soreThroat) {
        this.soreThroat = soreThroat;
    }

    public Boolean getVisitedAnimalFarm() {
        return visitedAnimalFarm;
    }

    public void setVisitedAnimalFarm(Boolean visitedAnimalFarm) {
        this.visitedAnimalFarm = visitedAnimalFarm;
    }

    public Boolean getContactedCovidPatient() {
        return contactedCovidPatient;
    }

    public void setContactedCovidPatient(Boolean contactedCovidPatient) {
        this.contactedCovidPatient = contactedCovidPatient;
    }
}