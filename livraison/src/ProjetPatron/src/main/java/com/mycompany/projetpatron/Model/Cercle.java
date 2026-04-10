package com.mycompany.projetpatron.Model;

/**
 * Représente une forme de type cercle.
 */
public class Cercle extends Forme {

    /** Centre du cercle. */
    public Point centre;

    /** Rayon du cercle. */
    public int rayon;

    /**
     * Construit un cercle avec une couleur donnée.
     *
     * @param couleur la couleur du cercle
     */
    public Cercle(String couleur) {
        super(couleur);
    }

    /**
     * Construit un cercle avec un centre et un rayon.
     *
     * @param centre le centre du cercle
     * @param rayon le rayon du cercle
     */
    public Cercle(Point centre, int rayon) {
        super("ok");
        this.centre = centre;
        this.rayon = rayon;
    }

    /**
     * Retourne le centre du cercle.
     *
     * @return le centre
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Retourne le rayon du cercle.
     *
     * @return le rayon
     */
    public double getRayon() {
        return rayon;
    }
}
