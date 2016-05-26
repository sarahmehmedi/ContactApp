package com.solsticemobile.sarahmehmedi.ContactsApp.model;

import java.io.Serializable;
//contants getters and setters for each item in json file
public class ContactModel implements Serializable {

    private String name;
    private int employeeId;
    private String company;
    private String detailsURL;

    private String smallImageURL;
    private String birthdate;
    private String workPhone;
    private String homePhone;
    private String mobilePhone;

    private boolean favorite;
    private String largeImageURL;
    private String email;
    private String website;
    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    private double latitude;
    private double longitude;


    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }



    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean getFavorite(){
        return favorite;
    }
    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }
    public String getLargeImageURL(){
        return largeImageURL;
    }
    public void setLargeImageURL(String largeImageURL){
        this.largeImageURL = largeImageURL;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getWebsite(){
        return website;
    }
    public void setWebsite(String website){
        this.website = website;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getStreet(){
        return street;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

