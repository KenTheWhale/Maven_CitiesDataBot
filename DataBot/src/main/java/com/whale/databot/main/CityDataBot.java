/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.whale.databot.main;

import com.whale.databot.DTO.CityData;
import com.whale.databot.DTO.DepartDesti;
import com.whale.databot.interfaces.Interfaces;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.whale.databot.lib.ConnectTextFile;

/**
 *
 * @author Admin
 */
public class CityDataBot {

    private static WebDriver myBrowserCity;

    public static void main(String[] args) throws Exception {
        ArrayList<String> listCityFrom = new ArrayList<>();
        ArrayList<String> listCityTo = new ArrayList<>();
        ArrayList<DepartDesti> listDD = new ArrayList<>();
        ArrayList<CityData> listCD = new ArrayList<>();

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--lang=vi-VN");
        option.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        myBrowserCity = new ChromeDriver(option);

        myBrowserCity.get("https://distancecalculator.globefeed.com/Vietnam_Distance_Calculator.asp");

        Thread.sleep(5000);
        for (int i = 0; i < 62; i++) {
            int j = i + 1;
            WebElement data = myBrowserCity.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[3]/div[1]/div[1]/ul[1]/li[" + j + "]"));
            if (!(data.getText().equalsIgnoreCase("Song Be Tinh")
                    || data.getText().equalsIgnoreCase("Quang Nam-Da Nang Tinh")
                    || data.getText().equalsIgnoreCase("vinh phu tinh")
                    || data.getText().equalsIgnoreCase("Minh Hai Tinh")
                    || data.getText().equalsIgnoreCase("Long An")
                    || data.getText().equalsIgnoreCase("Hai Hung Tinh")
                    || data.getText().equalsIgnoreCase("ha bac tinh")
                    || data.getText().equalsIgnoreCase("binh phuoc")
                    || data.getText().equalsIgnoreCase("quang ninh")
                    || data.getText().equalsIgnoreCase("dac lak")
                    || data.getText().equalsIgnoreCase("bac thai tinh")
                    || data.getText().equalsIgnoreCase("Ha Tay"))) {
                listCityFrom.add(data.getText());
                listCityTo.add(data.getText());
            }
        }
        for (int i = 0; i < listCityFrom.size() - 1; i++) {
            for (int j = i + 1; j < listCityTo.size(); j++) {
                String departure = listCityFrom.get(i);
                String destination = listCityTo.get(j);
                if (!departure.equalsIgnoreCase(destination)) {
                    listDD.add(new DepartDesti(departure, destination));
                }
            }
        }

        for (int i = 0; i < listDD.size(); i++) {
            Thread.sleep(800);
           
            WebElement txtFrom = myBrowserCity.findElement(By.id("placename1"));
            WebElement txtTo = myBrowserCity.findElement(By.id("placename2"));
            WebElement btnCalDis = myBrowserCity.findElement(By.xpath("(//button[@type=\'button\'])[2]"));
            txtFrom.sendKeys(listDD.get(i).getDeparture() + ", Viet Nam");
            Thread.sleep(1500);
            myBrowserCity.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
            Thread.sleep(800);
            txtTo.sendKeys(listDD.get(i).getDestination() + ", Viet Nam");
            Thread.sleep(1500);
            myBrowserCity.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/ul[1]/li[1]")).click();

            Thread.sleep(800);
            btnCalDis.click();
            Thread.sleep(3500);
            WebElement txtDistance = myBrowserCity.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]"));
            float distance = 0;

            if (txtDistance != null) {
                String d[] = txtDistance.getText().split(" ");
                distance = Float.parseFloat(d[0]);
                int shipTime = (int) Math.round(distance / (50 * 24) + 2);
                System.out.println(String.format("%.3f", ((i * 1.0 + 1) / listDD.size() * 100)) + "%: Completed: " + (i + 1) + "/" + listDD.size());
                listCD.add(new CityData(listDD.get(i), distance, shipTime));
            }
            myBrowserCity.get("https://distancecalculator.globefeed.com/Vietnam_Distance_Calculator.asp");
        }
        ConnectTextFile.writeToFile(listCD);

        myBrowserCity.close();
    }
}
