package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Rectangle;

public class ScoreParSurface implements CalculScore {

    @Override
    public double calculer(GroupeForme formesBleues, GroupeForme formesRouges) {
        double score = 0;


        for (Forme bleue : formesBleues.getFormes()) {
            if (!bleue.active) continue;

            boolean enCollision = false;
            for (Forme rouge : formesRouges.getFormes()) {
                if (collision(bleue, rouge)) {
                    enCollision = true;
                    break; 
                }
            }
            if (!enCollision) {
                score+= surface(bleue);
            }
        }
        return score;
    }

    //calcul du score en pourcentage pour un joueur
    public int calculerPourcentage(GroupeForme formesBleues, GroupeForme formesRouges, int largeur, int hauteur) {
        double surfaceFenetre = largeur * hauteur;

        //surface occupée par les obstacles rouges
        double surfaceObstacles = 0;
        for (Forme rouge : formesRouges.getFormes()) {
            if (rouge.active) {
                surfaceObstacles += surface(rouge);
            }
        }

        // surface libre hors obstacles 
        double surfaceDisponible = surfaceFenetre - surfaceObstacles;

        // surface valide où on peut positionner les formes bleues avec 100% de réussite 
        double surfaceBleueValide = calculer(formesBleues, formesRouges);

        
        if (surfaceDisponible <= 0) return 0;
        
        double scoreJoueur = (surfaceBleueValide / surfaceDisponible) * 100;
        return (int) Math.floor(scoreJoueur);
    }


    private boolean collision(Forme a, Forme b) {

        if (a instanceof Cercle && b instanceof Cercle)
            return DetecteurCollision.cercleVsCercle((Cercle) a, (Cercle) b);

        if (a instanceof Cercle && b instanceof Rectangle)
            return DetecteurCollision.cercleVsRectangle((Cercle) a, (Rectangle) b);

        if (a instanceof Rectangle && b instanceof Cercle)
            return DetecteurCollision.cercleVsRectangle((Cercle) b, (Rectangle) a);

        if (a instanceof Rectangle && b instanceof Rectangle)
            return DetecteurCollision.rectangleVsRectangle((Rectangle) a, (Rectangle) b);

        return false;
    }

    private double surface(Forme f) {
        if (f instanceof Cercle) {
            double r = ((Cercle) f).getRayon();
            return Math.PI * r * r;
        }
        if (f instanceof Rectangle) {
            Rectangle r = (Rectangle) f;
            return r.getWidth() * r.getHeight();
        }
        return 0;
    }
}