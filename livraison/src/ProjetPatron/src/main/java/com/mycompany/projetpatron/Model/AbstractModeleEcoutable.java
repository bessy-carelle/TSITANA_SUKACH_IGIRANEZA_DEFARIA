package com.mycompany.projetpatron.Model;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tsitana251
 */
public abstract class AbstractModeleEcoutable {
    private ArrayList<EcouteurModele> ecouteurs = new ArrayList<>();
    
    public void ajoutEcouteurModele(EcouteurModele e){
        ecouteurs.add(e);
    }
    
    public void retraitEcouteurModele(EcouteurModele e){
        ecouteurs.remove(e);
    }
    
    protected void fireChangement(){ // accessible qu'à lui meme et à ses sous classes
        for(EcouteurModele e : ecouteurs){
            e.modeleMisAJour(this);
        }
    }
}
