package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Rectangle;

public class ScoreParSurface implements CalculScore {
    private static final int MAX_FORMES = 4;
    private int surfaceTotale = 1;

    public void setSurfaceTotale(int surface) {
        this.surfaceTotale = surface;
    }
    
    
    @Override
    public double calculer(GroupeForme formesBleues, GroupeForme formesRouges) {
        double score = 0;
        int compteur = 0;


        for (Forme bleue : formesBleues.getFormes()) {
            if (!bleue.active) continue;
            if (compteur >= MAX_FORMES) break;
            compteur ++;


            boolean enCollision = false;
            for (Forme rouge : formesRouges.getFormes()) {
                if (collision(bleue, rouge)) {
                    enCollision = true;
                    break;
                }
            }
            if (!enCollision) {
                score ++;
            }
        }
        return score;
    }

    public double calculerPourcentage(GroupeForme formesBleues, GroupeForme formesRouges) {
        double surfaceValide = 0;
        for (Forme bleue : formesBleues.getFormes()) {
            if (!bleue.active) continue;
            boolean enCollision = false;
            for (Forme rouge : formesRouges.getFormes()) {
                if (collision(bleue, rouge)) {
                    enCollision = true;
                    break;
                }
            }
            if (!enCollision) surfaceValide += surface(bleue);
        }
        return (surfaceValide / (double) surfaceTotale) * 100.0;
    }
    
    public String calculerResultat(GroupeForme formesBleues, GroupeForme formesRouges) {
        int valides = (int) calculer(formesBleues, formesRouges);
        int total = Math.min(4, (int) formesBleues.getFormes().stream().filter(f -> f.active).count());

        
        double surfaceValide = 0;
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
                surfaceValide += surface(bleue);
            }
        }

        return valides + "/" + total + " formes bien placees" + "\nSurface couverte : " + String.format("%.1f", (surfaceValide / (double) this.surfaceTotale) * 100) + "%";
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