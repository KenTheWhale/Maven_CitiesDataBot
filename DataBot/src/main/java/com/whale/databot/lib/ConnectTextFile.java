/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whale.databot.lib;

import com.whale.databot.DTO.CityData;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ConnectTextFile {
    public static void writeToFile(ArrayList<CityData> listC)throws Exception{
        BufferedWriter bf = new BufferedWriter(new FileWriter("1-50.txt"));
        for (CityData cd : listC) {
            bf.write(cd.getDd().getDeparture() + "," + cd.getDd().getDestination() + "," + cd.getDistance() + "," + cd.getShipTime() + "\n");
        }
        bf.close();
    }
    
   
}
