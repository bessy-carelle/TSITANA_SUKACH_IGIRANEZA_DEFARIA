/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

import javax.swing.Timer;

import com.mycompany.projetpatron.Model.Strategy.CalculScore;
import com.mycompany.projetpatron.Model.Strategy.GenerationStrategy;
import com.mycompany.projetpatron.Model.Strategy.ScoreParSurface;


public class JeuFormes extends AbstractModeleEcoutable {

    private GenerationStrategy generationStrategy;
    private GroupeForme formesJoueur;   //formes bleues
    private GroupeForme obstacles;        //formes rouges
    private CalculScore calculScoreStrategy;
    private Timer timerObstacles;
    private CalculScore calculScore = new ScoreParSurface(); 

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

        timerObstacles = new Timer(5000, e -> {
        // on cache les obstacles SANS les supprimer
        for (Forme f : obstacles.getFormes()) {
            f.active = false; 
        }
        fireChangement();
        timerObstacles.stop();
    });
    timerObstacles.setRepeats(false);
    timerObstacles.start();

    }

    public GroupeForme getObstacles() { 
        return obstacles; 
    }

    public GroupeForme getFormesDuJoueur() { 
        return formesJoueur; 
    }

    public double calculerScore() {
        return calculScore.calculer(formesJoueur, obstacles);
    }

    public int calculerPourcentage(int largeur, int hauteur) {
        return ((ScoreParSurface) calculScore).calculerPourcentage(formesJoueur, obstacles, largeur, hauteur);
    }

    public void notifierVue() {
        fireChangement();
    }
}
