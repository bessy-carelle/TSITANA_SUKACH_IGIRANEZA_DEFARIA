package com.mycompany.projetpatron.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * Contrôleur principal de la vue du jeu, implémentant le patron de conception
 * <b>État</b> (State Pattern) pour la gestion des interactions souris.
 * <p>
 * Cette classe étend {@link JPanel} et délègue tous les événements souris
 * à l'état courant ({@link VueControlleurState}). Changer l'état courant
 * modifie le comportement des interactions sans modifier cette classe.
 * </p>
 *
 * @author tsitana251
 * @version 1.0
 * @see VueControlleurState
 * @see MouseListener
 * @see MouseMotionListener
 */
public class VueControlleurJeu extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * L'état courant du contrôleur, déterminant le comportement
     * des interactions souris (ex. création de cercle, création de rectangle).
     */
    public VueControlleurState etatCourant;

    /**
     * Définit le nouvel état courant du contrôleur.
     * <p>
     * Tous les événements souris suivants seront délégués à ce nouvel état.
     * </p>
     *
     * @param nouvelEtat le {@link VueControlleurState} à activer
     */
    public void setEtatCourant(VueControlleurState nouvelEtat) {
        this.etatCourant = nouvelEtat;
    }

    /**
     * Délègue l'événement de clic souris à l'état courant.
     *
     * @param me l'événement souris généré lors du clic
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if (etatCourant != null) {
            etatCourant.mouseClicked(me);
        }
    }

    /**
     * Délègue l'événement de déplacement souris à l'état courant.
     *
     * @param me l'événement souris généré lors du déplacement
     */
    @Override
    public void mouseMoved(MouseEvent me) {
        if (etatCourant != null) {
            etatCourant.mouseMoved(me);
        }
    }

    /**
     * Appelé lorsque le bouton de la souris est pressé.
     * <p>
     * Non implémenté dans cette version.
     * </p>
     *
     * @param me l'événement souris généré lors de l'appui
     */
    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("M");
    }

    /**
     * Appelé lorsque le bouton de la souris est relâché.
     * <p>
     * Non implémenté dans cette version.
     * </p>
     *
     * @param me l'événement souris généré lors du relâchement
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        System.out.println("M");
    }

    /**
     * Appelé lorsque le curseur entre dans la zone du composant.
     * <p>
     * Non implémenté dans cette version.
     * </p>
     *
     * @param me l'événement souris généré lors de l'entrée
     */
    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("M");
    }

    /**
     * Appelé lorsque le curseur quitte la zone du composant.
     * <p>
     * Non implémenté dans cette version.
     * </p>
     *
     * @param me l'événement souris généré lors de la sortie
     */
    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("M");
    }

    /**
     * Appelé lorsque la souris est déplacée avec un bouton pressé (glisser).
     * <p>
     * Non implémenté dans cette version.
     * </p>
     *
     * @param me l'événement souris généré lors du glisser
     */
    @Override
    public void mouseDragged(MouseEvent me) {
        System.out.println("M");
    }
}
