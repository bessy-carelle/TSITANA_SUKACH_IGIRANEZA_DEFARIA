package com.mycompany.projetpatron.View;

import java.awt.Graphics;
import javax.swing.JPanel;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.EcouteurModele;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;

/**
 * Vue graphique 2D du jeu.
 * <p>
 * Cette classe est responsable de l'affichage des formes (obstacles et formes du joueur)
 * ainsi que des formes en cours de création.
 * Elle observe le modèle et se redessine automatiquement en cas de modification.
 * </p>
 */
public class VueJeu2D extends JPanel implements EcouteurModele {

    /** État de création des cercles */
    EtatCreationCercle circle;

    /** État de création des rectangles */
    EtatCreationRectangle rectangle;

    /** Modèle du jeu */
    JeuFormes jeu;

    /** Taille du marqueur de point */
    final static int sizeX = 5;

    /**
     * Constructeur de la vue 2D.
     *
     * @param circle état de création de cercle
     * @param rectangle état de création de rectangle
     * @param jeu modèle du jeu
     */
    public VueJeu2D(EtatCreationCercle circle,
                    EtatCreationRectangle rectangle,
                    JeuFormes jeu) {

        this.circle = circle;
        this.rectangle = rectangle;
        this.jeu = jeu;

        setOpaque(false);

        circle.ajoutEcouteurModele(this);
        rectangle.ajoutEcouteurModele(this);
        jeu.ajoutEcouteurModele(this);
    }

    /**
     * Dessine les éléments graphiques du jeu.
     * <ul>
     *     <li>Obstacles (rouges)</li>
     *     <li>Formes en cours de création</li>
     *     <li>Formes du joueur (bleues)</li>
     * </ul>
     *
     * @param g contexte graphique
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessin des obstacles (rouge)
        if (jeu != null && jeu.getObstacles() != null) {
            g.setColor(java.awt.Color.RED);
            for (Forme f : jeu.getObstacles().getFormes()) {
                if (f.active) {
                    if (f instanceof Cercle) {
                        Cercle c = (Cercle) f;
                        int x = (int) c.getCentre().x;
                        int y = (int) c.getCentre().y;
                        int r = (int) c.getRayon();
                        g.drawOval(x - r, y - r, 2 * r, 2 * r);
                    } else if (f instanceof Rectangle) {
                        Rectangle r = (Rectangle) f;
                        g.drawRect((int) r.getCoinRectangle().x,
                                   (int) r.getCoinRectangle().y,
                                   r.getWidth(), r.getHeight());
                    }
                }
            }
        }

        // Dessin du cercle en cours de création
        g.setColor(java.awt.Color.BLUE);
        if (circle.getCentre() != null) {
            Point centre = circle.getCentre();
            int x = (int) centre.x;
            int y = (int) centre.y;

            g.drawLine(x - sizeX, y - sizeX, x + sizeX, y + sizeX);
            g.drawLine(x + sizeX, y - sizeX, x - sizeX, y + sizeX);

            if (circle.getRayon() > 0.0) {
                int rayon = (int) circle.getRayon();
                g.drawOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
            }
        }

        // Dessin du rectangle en cours de création
        if (rectangle.getCoinRectangle() != null) {
            Point coin = rectangle.getCoinRectangle();
            int x = (int) coin.x;
            int y = (int) coin.y;

            g.drawLine(x - sizeX, y - sizeX, x + sizeX, y + sizeX);
            g.drawLine(x + sizeX, y - sizeX, x - sizeX, y + sizeX);

            if (rectangle.getLargeur() > 0) {
                g.drawRect(x, y, rectangle.getLargeur(), rectangle.getHauteur());
            }
        }

        // Dessin des formes du joueur (bleu)
        g.setColor(java.awt.Color.BLUE);
        for (Forme f : jeu.getFormesDuJoueur().getFormes()) {
            if (f.active) {
                if (f instanceof Cercle) {
                    Cercle c = (Cercle) f;
                    int x = (int) c.getCentre().x;
                    int y = (int) c.getCentre().y;
                    int r = (int) c.getRayon();
                    g.drawOval(x - r, y - r, 2 * r, 2 * r);
                } else if (f instanceof Rectangle) {
                    Rectangle r = (Rectangle) f;
                    g.drawRect((int) r.getCoinRectangle().x,
                               (int) r.getCoinRectangle().y,
                               r.getWidth(), r.getHeight());
                }
            }
        }
    }

    /**
     * Méthode appelée lorsqu'un changement est détecté dans le modèle.
     * Déclenche le rafraîchissement de la vue.
     *
     * @param source source du changement
     */
    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }
}
