/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whale.databot.DTO;

/**
 *
 * @author Admin
 */
public class DepartDesti {
    private String departure, destination;

    public DepartDesti() {
    }

    public DepartDesti(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
}
