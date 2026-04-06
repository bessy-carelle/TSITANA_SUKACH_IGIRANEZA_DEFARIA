/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

import java.util.ArrayList;

/**
 *
 * @author tsitana251
 */
public class GroupeForme extends AbstractModeleEcoutable implements EcouteurModele{
    public ArrayList<Forme> formes = new ArrayList<>();
    public int getSurfaceTotale(){
        return 60;
    }

    public ArrayList<Forme> getFormes() {
        return formes;
    }
    
    public void ajoutForme(Forme f){
        formes.add(f);
        f.ajoutEcouteurModele(this);
        fireChangement();
    }
    
    @Override
    public void modeleMisAJour(Object source) {
        //fireChangement();
    }
    
}
