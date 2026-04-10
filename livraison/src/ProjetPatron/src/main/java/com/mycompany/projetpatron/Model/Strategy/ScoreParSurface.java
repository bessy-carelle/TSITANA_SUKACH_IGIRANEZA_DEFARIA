package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Rectangle;

/**
 * Implémentation de la stratégie de calcul de score basée sur la surface
 * des formes correctement placées (sans collision).
 */
public class ScoreParSurface implements CalculScore {

    private static final int MAX_FORMES = 4;
    private int surfaceTotale = 1;

    /**
     * Définit la surface totale de référence.
     *
     * @param surface la surface totale
     */
    public void setSurfaceTotale(int surface) {
        this.surfaceTotale = surface;
    }

    /**
     * Calcule le score basé sur le nombre de formes bleues actives
     * sans collision avec les formes rouges (limité à MAX_FORMES).
     *
     * @param formesBleues les formes bleues
     * @param formesRouges les formes rouges
     * @return le score calculé
     */
    @Override
    public double calculer(GroupeForme formesBleues, GroupeForme formesRouges) {
        double score = 0;
        int compteur = 0;

        for (Forme bleue : formesBleues.getFormes()) {
            if (!bleue.active) continue;
            if (compteur >= MAX_FORMES) break;
            compteur++;

            boolean enCollision = false;
            for (Forme rouge : formesRouges.getFormes()) {
                if (collision(bleue, rouge)) {
                    enCollision = true;
                    break;
                }
            }
            if (!enCollision) {
                score++;
            }
        }
        return score;
    }

    /**
     * Calcule le pourcentage de surface occupée par les formes bleues
     * sans collision par rapport à la surface totale.
     *
     * @param formesBleues les formes bleues
     * @param formesRouges les formes rouges
     * @return le pourcentage de surface valide
     */
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

    /**
     * Génère une représentation textuelle du résultat du score.
     *
     * @param formesBleues les formes bleues
     * @param formesRouges les formes rouges
     * @return une chaîne décrivant le résultat
     */
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

        return valides + "/" + total + " formes bien placees" +
               "\nSurface couverte : " +
               String.format("%.1f", (surfaceValide / (double) this.surfaceTotale) * 100) + "%";
    }

    /**
     * Vérifie s'il y a collision entre deux formes.
     *
     * @param a première forme
     * @param b deuxième forme
     * @return true si collision, false sinon
     */
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

    /**
     * Calcule la surface d'une forme.
     *
     * @param f la forme
     * @return la surface de la forme
     */
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
