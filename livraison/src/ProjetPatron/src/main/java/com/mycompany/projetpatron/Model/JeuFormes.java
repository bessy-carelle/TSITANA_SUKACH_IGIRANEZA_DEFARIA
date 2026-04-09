/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import com.mycompany.projetpatron.Model.Strategy.CalculScore;
import com.mycompany.projetpatron.Model.Strategy.GenerationStrategy;
import com.mycompany.projetpatron.Model.Strategy.ScoreParSurface;


public class JeuFormes extends AbstractModeleEcoutable {

    private GenerationStrategy generationStrategy;
    private GroupeForme formesJoueur;   //formes bleues
    private GroupeForme obstacles;        //formes rouges
    private Timer timerObstacles;
    private CalculScore calculScore = new ScoreParSurface(); 

    // jeu en 10 parties 
    private int numPartie = 0;
    private static final int TOTAL_PARTIES = 4;
    private List<Integer> scores = new ArrayList<>(); 

    public JeuFormes(){
        this.formesJoueur = new GroupeForme();
        this.obstacles = new GroupeForme();
    }

    public void setGenerationStrategy(GenerationStrategy s) {
        this.generationStrategy = s;
    }

    public void demarrerPartie(int borneMaxX, int borneMAxY) {
        numPartie++;
        this.obstacles = generationStrategy.generer(borneMaxX, borneMAxY); // on stockera ces formes comme des obstacles rouge.
        this.formesJoueur = new GroupeForme(); 
        fireChangement();

        timerObstacles = new Timer(10000, e -> {
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

     public boolean validerPartie(int borneMaxX, int borneMaxY) {
        int score = (int) calculerScore();
        scores.add(score);
        fireChangement();
        return numPartie >= TOTAL_PARTIES;
    }

    public double calculerScore() {
        return calculScore.calculer(formesJoueur, obstacles);
    }


    public String calculerResultat() {
        return ((ScoreParSurface) calculScore).calculerResultat(formesJoueur, obstacles);
    }
    
    public double getMoyenne() {
        if (scores.isEmpty()) {
            return 0;
        }
        return scores.stream().mapToInt(i -> i).average().orElse(0);
    }

    public int getMoyennePourcentage() {
        if (scores.isEmpty()) return 0;
        int scoreTotal = scores.stream().mapToInt(i -> i).sum();
        int scoreMax = TOTAL_PARTIES * 4; // 40
        return (int) Math.floor((scoreTotal / (double) scoreMax) * 100);
    }

public int getNumeroPartie() { 
        return numPartie; 
    }
    public int getTotalParties() { 
        return TOTAL_PARTIES; 
    }
    public List<Integer> getScores() { 
        return scores; 
    
    }

    public void notifierVue() {
        fireChangement();
    }
}
