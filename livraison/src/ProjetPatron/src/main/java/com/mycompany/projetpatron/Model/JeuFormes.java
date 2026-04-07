/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

import com.mycompany.projetpatron.Model.Strategy.GenerationStrategy;


public class JeuFormes extends AbstractModeleEcoutable {

    private GenerationStrategy generationStrategy;
    private GroupeForme formesJoueur;   //formes bleues
    private GroupeForme obstacles;        //formes rouges

    public JeuFormes(){
        this.formesJoueur = new GroupeForme();
        this.obstacles = new GroupeForme();
    }

    public void setGenerationStrategy(GenerationStrategy s) {
        this.generationStrategy = s;
    }

    public void demarrerPartie(int borneMaxX, int borneMAxY) {
        this.obstacles = generationStrategy.generer(borneMaxX, borneMAxY); // on stockera ces formes comme des obstacles rouge.
        this.formesJoueur = new GroupeForme(); 
        fireChangement();

    }

    public GroupeForme getObstacles() { 
        return obstacles; 
    }

    public GroupeForme getFormesDuJoueur() { 
        return formesJoueur; 
    }

}
