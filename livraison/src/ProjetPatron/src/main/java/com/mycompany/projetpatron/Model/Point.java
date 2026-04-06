/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;
/**
 *
 * @author tsitana251
 */
public class Point {
    public double x;
    public double y;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getDistance(Point autrePoint){
        return Math.sqrt(
            Math.pow(this.x - autrePoint.x, 2) +
            Math.pow(this.y - autrePoint.y, 2)
          );
    }
}
