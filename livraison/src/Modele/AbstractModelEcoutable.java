/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

import java.util.ArrayList;

/**
 *
 * @author sukach251
 */
public abstract class AbstractModelEcoutable {
    private ArrayList<EcouteurModel> ecouteurs = new ArrayList();
    public void ajoutEcouteurModele(EcouteurModel e){
        ecouteurs.add(e);
    }
    
    public void retraitEcouteurModele(EcouteurModel e){
        ecouteurs.remove(e);
    }
    
    protected void fireChangement(){ // on ne mis pas public car si on le fait
        // on peut utiliser ce methode meme dans les classes qui ne sont pas 
        // ecoutables
        for(EcouteurModel e: ecouteurs){
            e.ModeleMisAJour(this);
        }
    }
}
