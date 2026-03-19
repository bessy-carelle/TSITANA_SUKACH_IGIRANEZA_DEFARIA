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
    public Point haut_gauche;
    public Point bas_droite;
    
    public Rectangle(Point haut_gauche, Point bas_droite){
        super("couleur ici");
        this.haut_gauche = haut_gauche;
        this.bas_droite = bas_droite;
    }
}
