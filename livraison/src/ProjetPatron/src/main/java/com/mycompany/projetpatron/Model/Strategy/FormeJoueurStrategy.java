
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author defaria241@CAMPUS
 */
package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;

public class FormeJoueurStrategy implements GenerationStrategy {

    private GroupeForme formesDessinées = new GroupeForme();
    private boolean prêt = false;

    public GroupeForme getFormesDessinee() {
        return formesDessinées;
    }
    // appelé à chaque fois que le joueur 1 dessine une forme rouge
    public void ajouterForme(Forme f) {
        f.couleur = "ROUGE";
        formesDessinées.ajoutForme(f);
    }

    public void valider() {
        prêt = true;
    }

    public boolean isPrêt() {
        return prêt;
    }

    public void reset() {
        formesDessinées = new GroupeForme();
        prêt = false;
    }

    @Override
    public GroupeForme generer(int borneMaxX, int borneMaxY) {
        return formesDessinées;
    }
}