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
 * État du contrôleur correspondant à la création interactive d'un {@link Cercle}
 * dans le cadre du patron de conception <b>État</b> (State Pattern).
 * <p>
 * Cet état gère les interactions souris de l'utilisateur pour définir le centre
 * et le rayon d'un cercle en deux clics. Le premier clic place le centre, et
 * le second clic détermine le rayon. En mode deux joueurs, la forme est attribuée
 * selon la phase de jeu courante.
 * </p>
 *
 * @author tsitana251
 * @version 1.0
 * @see VueControlleurState
 * @see Cercle
 * @see JeuFormes
 * @see FormeJoueurStrategy
 */
public class EtatCreationCercle extends AbstractModeleEcoutable implements VueControlleurState {

    /**
     * Point central du cercle en cours de création.
     * Vaut {@code null} tant que le premier clic n'a pas été effectué.
     */
    private Point centre;

    /**
     * Point sur la circonférence utilisé pour calculer le rayon.
     */
    private Point rayonPoint;

    /**
     * Rayon calculé du cercle, en pixels.
     */
    private double rayon;

    /**
     * Indicateur de fin de saisie du cercle.
     * Vaut {@code 1} lorsque le centre et le rayon ont été définis.
     */
    private int cercleDone = 0;

    /**
     * Référence au jeu de formes dans lequel le cercle sera ajouté.
     */
    private JeuFormes jeu;

    /**
     * Stratégie d'ajout de forme utilisée en mode deux joueurs.
     */
    private FormeJoueurStrategy strategie;

    /**
     * Construit un état de création de cercle associé au jeu de formes donné.
     *
     * @param jeu le {@link JeuFormes} dans lequel le cercle sera créé
     */
    public EtatCreationCercle(JeuFormes jeu) {
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
     * Gère les clics de souris pour la création du cercle.
     * <p>
     * <ul>
     *   <li>Premier clic : définit le centre du cercle.</li>
     *   <li>Deuxième clic : définit le point de rayon, calcule le rayon,
     *       crée le {@link Cercle} et l'ajoute au jeu via une commande ou
     *       la stratégie selon le mode de jeu.</li>
     * </ul>
     * </p>
     *
     * @param me l'événement souris contenant les coordonnées du clic
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse clicked sur cercleeeee");
        if (centre == null) {
            System.out.println("Centre mis en place");
            centre = new Point(me.getX(), me.getY());
            fireChangement();
        } else {
            cercleDone = 1;
            rayonPoint = new Point(me.getX(), me.getY());
            rayon = rayonPoint.getDistance(centre);
            System.out.println("rayon mis en place");
            fireChangement();
        }

        if (cercleDone == 1) {
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
            cercleDone = 0;
            jeu.notifierVue();
        }
    }

    /**
     * Gère le déplacement de la souris pour afficher un aperçu dynamique du rayon.
     * <p>
     * Met à jour le rayon en temps réel lorsque le centre est défini
     * et que le cercle n'est pas encore finalisé.
     * </p>
     *
     * @param me l'événement souris contenant les coordonnées courantes du curseur
     */
    @Override
    public void mouseMoved(MouseEvent me) {
        if (centre != null & cercleDone == 0) {
            rayonPoint = new Point(me.getX(), me.getY());
            rayon = rayonPoint.getDistance(centre);
            fireChangement();
        }
    }

    /**
     * Retourne le centre du cercle en cours de création.
     *
     * @return le {@link Point} représentant le centre, ou {@code null} si non encore défini
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Retourne le point de rayon utilisé pour le calcul du rayon du cercle.
     *
     * @return le {@link Point} sur la circonférence
     */
    public Point getRayonPoint() {
        return rayonPoint;
    }

    /**
     * Retourne la valeur actuelle du rayon du cercle.
     *
     * @return le rayon en pixels sous forme de {@code double}
     */
    public double getRayon() {
        return rayon;
    }
}
