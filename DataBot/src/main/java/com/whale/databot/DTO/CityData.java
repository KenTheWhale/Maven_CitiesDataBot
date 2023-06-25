/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whale.databot.DTO;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class CityData {
    private DepartDesti dd;
    private float distance;
    private int shipTime;// unit: day

    public CityData() {
    }

    public CityData(DepartDesti dd, float distance, int shipTime) {
        this.dd = dd;
        this.distance = distance;
        this.shipTime = shipTime;
    }

    public DepartDesti getDd() {
        return dd;
    }

    public void setDd(DepartDesti dd) {
        this.dd = dd;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getShipTime() {
        return shipTime;
    }

    public void setShipTime(int shipTime) {
        this.shipTime = shipTime;
    }
    
}
