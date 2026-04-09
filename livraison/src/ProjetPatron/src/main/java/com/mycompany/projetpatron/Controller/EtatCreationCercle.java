/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Controller;

import java.awt.event.MouseEvent;

import com.mycompany.projetpatron.Controller.Command.Command;
import com.mycompany.projetpatron.Controller.Command.CommandAjoutForme;
import com.mycompany.projetpatron.Controller.Command.GestionnaireCommandes;
import com.mycompany.projetpatron.Model.AbstractModeleEcoutable;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Strategy.FormeJoueurStrategy;


/**
 *
 * @author tsitana251
 */
public class EtatCreationCercle extends AbstractModeleEcoutable implements VueControlleurState{
    private Point centre;
    private Point rayonPoint;
    private double rayon;
    private int cercleDone = 0;
    private JeuFormes jeu;
    private FormeJoueurStrategy strategie;

    public void setJeu(JeuFormes jeu) { this.jeu = jeu; }
    public void setStrategie(FormeJoueurStrategy s) { this.strategie = s; }
    
    
    public EtatCreationCercle(JeuFormes jeu) {  
        this.jeu = jeu;
    }


    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse clicked sur cercleeeee");
        if(centre == null){
            System.out.println("Centre mis en place");
            centre = new Point(me.getX(),me.getY());
            fireChangement();
        }else {
            cercleDone = 1;
            rayonPoint = new Point(me.getX(),me.getY());
            rayon = rayonPoint.getDistance(centre);

            System.out.println("rayon mis en place");
            fireChangement();
        }
        
        if(cercleDone == 1){
          System.out.println("creation nouveau cercle");
            Cercle ancienCercle = new Cercle(centre, (int) rayon);
            if (jeu != null && jeu.isModeDeuxJoueurs() 
                && jeu.getPhase() == JeuFormes.Phase.JOUEUR1_PLACE_ROUGE) {
                ancienCercle.couleur = "ROUGE";
                strategie.ajouterForme(ancienCercle);
                } else {
                    ancienCercle.couleur = "BLEU";
                    Command cmd = new CommandAjoutForme(jeu.getFormesDuJoueur(), ancienCercle);
                    GestionnaireCommandes.getInstance().executerCommande(cmd);
                }
            centre = null;
            rayon = 0.0;
            cercleDone=0;
            jeu.notifierVue();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent me){
        if(centre != null & cercleDone==0){
            rayonPoint = new Point(me.getX(),me.getY());
            rayon = rayonPoint.getDistance(centre);
            fireChangement();
        }
    }

    public Point getCentre() {
        return centre;
    }

    public Point getRayonPoint() {
        return rayonPoint;
    }
    
    
    
    public double getRayon(){
        return rayon;
    }
    
}
