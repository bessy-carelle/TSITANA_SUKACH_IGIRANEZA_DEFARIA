/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

/**
 *
 * @author tsitana251
 */
public class Rectangle extends Forme{
    public Point coinRectangle;
    public int width;
    public int height;

     public Rectangle(String couleur){
        super(couleur);
        
    }
     
    public Rectangle(Point coinRectangle, int width, int height) {
        super("couleur");
        //super(couleur);
        this.coinRectangle = coinRectangle;
        this.width = width;
        this.height = height;
    }

    public Point getCoinRectangle() {
        return coinRectangle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}
