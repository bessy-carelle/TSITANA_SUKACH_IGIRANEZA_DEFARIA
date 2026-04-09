/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class VueJeu2D extends JPanel implements EcouteurModele {

    EtatCreationCercle circle;
    EtatCreationRectangle rectangle;
    JeuFormes jeu;
    final static int sizeX = 5;

    public VueJeu2D(EtatCreationCercle circle, EtatCreationRectangle rectangle, JeuFormes jeu) {
        this.circle = circle;
        this.rectangle = rectangle;
        this.jeu = jeu;
        setOpaque(false);
        circle.ajoutEcouteurModele(this);
        rectangle.ajoutEcouteurModele(this);
        jeu.ajoutEcouteurModele(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        // obstacles rouges
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

        //formes bleues définitives
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

    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }
}









































