/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

/**
 *
 * @author tsitana251
 */

public abstract class Forme extends AbstractModeleEcoutable{
    public boolean active = true;
    public String couleur;
    // pas de couleur ici
    
    public Forme(String couleur){
        this.couleur = couleur;
    }
}
