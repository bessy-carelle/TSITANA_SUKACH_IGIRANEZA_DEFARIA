package com.mycompany.projetpatron.Controller;

import java.awt.event.MouseEvent;
import com.mycompany.projetpatron.Controller.Command.Command;
import com.mycompany.projetpatron.Controller.Command.CommandAjoutForme;
import com.mycompany.projetpatron.Controller.Command.GestionnaireCommandes;
import com.mycompany.projetpatron.Model.AbstractModeleEcoutable;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;
import com.mycompany.projetpatron.Model.Strategy.FormeJoueurStrategy;

/**
 * État du contrôleur correspondant à la création interactive d'un {@link Rectangle}
 * dans le cadre du patron de conception <b>État</b> (State Pattern).
 * <p>
 * Cet état gère les interactions souris pour définir un rectangle en deux clics :
 * le premier clic place le coin supérieur gauche, et le second clic détermine
 * le point diagonal opposé, permettant de calculer la largeur et la hauteur.
 * En mode deux joueurs, la forme est attribuée selon la phase de jeu courante.
 * </p>
 *
 * @author tsitana251
 * @version 1.0
 * @see VueControlleurState
 * @see Rectangle
 * @see JeuFormes
 * @see FormeJoueurStrategy
 */
public class EtatCreationRectangle extends AbstractModeleEcoutable implements VueControlleurState {

    /**
     * Point du coin supérieur gauche du rectangle en cours de création.
     * Vaut {@code null} tant que le premier clic n'a pas été effectué.
     */
    public Point coinRectangle;

    /**
     * Point diagonal opposé au coin, définissant la taille du rectangle.
     */
    public Point diagPoint;

    /**
     * Largeur calculée du rectangle en pixels.
     */
    public int largeur;

    /**
     * Hauteur calculée du rectangle en pixels.
     */
    public int hauteur;

    /**
     * Indicateur de fin de saisie du rectangle.
     * Vaut {@code 1} lorsque les deux coins ont été définis.
     */
    private int rectangleDone = 0;

    /**
     * Longueur de la diagonale du rectangle, calculée entre {@code coinRectangle}
     * et {@code diagPoint}.
     */
    private double diagonale;

    /**
     * Référence au jeu de formes dans lequel le rectangle sera ajouté.
     */
    private JeuFormes jeu;

    /**
     * Stratégie d'ajout de forme utilisée en mode deux joueurs.
     */
    private FormeJoueurStrategy strategie;

    /**
     * Construit un état de création de rectangle associé au jeu de formes donné.
     *
     * @param jeu le {@link JeuFormes} dans lequel le rectangle sera créé
     */
    public EtatCreationRectangle(JeuFormes jeu) {
        this.jeu = jeu;
    }

    /**
     * Définit le jeu de formes associé à cet état.
     *
     * @param jeu le {@link JeuFormes} à associer
     */
    public void setJeu(JeuFormes jeu) {
        this.jeu = jeu;
    }

    /**
     * Définit la stratégie d'ajout de forme pour le mode deux joueurs.
     *
     * @param s la {@link FormeJoueurStrategy} à utiliser
     */
    public void setStrategie(FormeJoueurStrategy s) {
        this.strategie = s;
    }

    /**
     * Retourne le coin supérieur gauche du rectangle en cours de création.
     *
     * @return le {@link Point} représentant le coin, ou {@code null} si non encore défini
     */
    public Point getCoinRectangle() {
        return coinRectangle;
    }

    /**
     * Définit manuellement le coin supérieur gauche du rectangle.
     *
     * @param coinRectangle le {@link Point} à utiliser comme coin
     */
    public void setCoinRectangle(Point coinRectangle) {
        this.coinRectangle = coinRectangle;
    }

    /**
     * Retourne la largeur actuelle du rectangle.
     *
     * @return la largeur en pixels
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Retourne la hauteur actuelle du rectangle.
     *
     * @return la hauteur en pixels
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Gère les clics de souris pour la création du rectangle.
     * <p>
     * <ul>
     *   <li>Premier clic : définit le coin supérieur gauche du rectangle.</li>
     *   <li>Deuxième clic : définit le point diagonal, calcule la largeur et la hauteur,
     *       crée le {@link Rectangle} et l'ajoute au jeu via une commande ou
     *       la stratégie selon le mode de jeu.</li>
     * </ul>
     * </p>
     *
     * @param me l'événement souris contenant les coordonnées du clic
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse clicked sur rectangle");
        if (coinRectangle == null) {
            System.out.println("eto");
            coinRectangle = new Point(me.getX(), me.getY());
            System.out.println("Coin : " + coinRectangle);
            fireChangement();
        } else {
            rectangleDone = 1;
            diagPoint = new Point(me.getX(), me.getY());
            diagonale = diagPoint.getDistance(coinRectangle);
            largeur = (int) Math.abs(diagPoint.x - coinRectangle.x);
            hauteur = (int) Math.abs(diagPoint.y - coinRectangle.y);
            System.out.println("diag mis en place");
            fireChangement();
        }

        if (rectangleDone == 1) {
            System.out.println("creation nouveau rect");
            Rectangle ancienRectangle = new Rectangle(coinRectangle, largeur, hauteur);
            if (jeu != null && jeu.isModeDeuxJoueurs()
                    && jeu.getPhase() == JeuFormes.Phase.JOUEUR1_PLACE_ROUGE) {
                ancienRectangle.couleur = "ROUGE";
                strategie.ajouterForme(ancienRectangle);
            } else {
                ancienRectangle.couleur = "BLEU";
                Command cmd = new CommandAjoutForme(jeu.getFormesDuJoueur(), ancienRectangle);
                GestionnaireCommandes.getInstance().executerCommande(cmd);
            }
            coinRectangle = null;
            largeur = 0;
            hauteur = 0;
            rectangleDone = 0;
            jeu.notifierVue();
        }
    }

    /**
     * Gère le déplacement de la souris pour afficher un aperçu dynamique du rectangle.
     * <p>
     * Met à jour en temps réel la diagonale, la largeur et la hauteur du rectangle
     * lorsque le coin est défini et que le rectangle n'est pas encore finalisé.
     * </p>
     *
     * @param me l'événement souris contenant les coordonnées courantes du curseur
     */
    @Override
    public void mouseMoved(MouseEvent me) {
        if (coinRectangle != null & rectangleDone == 0) {
            diagPoint = new Point(me.getX(), me.getY());
            diagonale = diagPoint.getDistance(coinRectangle);
            largeur = (int) Math.abs(diagPoint.x - coinRectangle.x);
            hauteur = (int) Math.abs(diagPoint.y - coinRectangle.y);
            fireChangement();
        }
    }
}
