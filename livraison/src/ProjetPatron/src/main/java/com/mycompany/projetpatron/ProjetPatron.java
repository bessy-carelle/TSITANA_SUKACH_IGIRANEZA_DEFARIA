package com.mycompany.projetpatron;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Controller.VueControlleurJeu;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Strategy.FormeRandomStrategy;
import com.mycompany.projetpatron.View.FenetreGlobale;

/**
 * Classe principale de l'application.
 * <p>
 * Initialise le modèle, les contrôleurs et la vue,
 * puis lance l'application graphique.
 * </p>
 */
public class ProjetPatron {

    /**
     * Point d'entrée du programme.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("___Project Start____");

        // Initialisation du modèle
        JeuFormes jeu = new JeuFormes();
        jeu.setGenerationStrategy(new FormeRandomStrategy(10));

        // Initialisation du contrôleur et des états
        VueControlleurJeu controleur = new VueControlleurJeu();
        EtatCreationCercle etatCercle = new EtatCreationCercle(jeu);
        EtatCreationRectangle etatRect = new EtatCreationRectangle(jeu);

        // Lancement de l'interface graphique
        new FenetreGlobale(controleur, etatCercle, etatRect, jeu);
    }
}
