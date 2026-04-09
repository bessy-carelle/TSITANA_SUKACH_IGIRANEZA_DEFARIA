/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetpatron;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Controller.VueControlleurJeu;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Strategy.FormeRandomStrategy;
import com.mycompany.projetpatron.View.FenetreGlobale;

/**
 *
 * @author tsitana251
 */
public class ProjetPatron {

   public static void main(String[] args) {
      int borneMaxX= 600;
      int borneMaxY = 500;

      System.out.println("___Project Start____");

      JeuFormes jeu = new JeuFormes();
      jeu.setGenerationStrategy(new FormeRandomStrategy(7));
      jeu.demarrerPartie(borneMaxX,borneMaxY);

      VueControlleurJeu controleur = new VueControlleurJeu();
      EtatCreationCercle etatCercle = new EtatCreationCercle(jeu);
      EtatCreationRectangle etatRect = new EtatCreationRectangle(jeu);

      new FenetreGlobale(controleur,etatCercle,etatRect,jeu,borneMaxX,borneMaxY);
   }
}
