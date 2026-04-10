package com.mycompany.projetpatron.Model;

/**
 * Représente un point dans un espace 2D.
 * <p>
 * Un point est défini par ses coordonnées x et y.
 * </p>
 */
public class Point {

    /**
     * Coordonnée en abscisse.
     */
    public double x;

    /**
     * Coordonnée en ordonnée.
     */
    public double y;

    /**
     * Constructeur d’un point 2D.
     *
     * @param x coordonnée x du point
     * @param y coordonnée y du point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calcule la distance entre ce point et un autre point.
     *
     * @param autrePoint le point cible
     * @return la distance euclidienne entre les deux points
     */
    public double getDistance(Point autrePoint) {
        return Math.sqrt(
            Math.pow(this.x - autrePoint.x, 2) +
            Math.pow(this.y - autrePoint.y, 2)
        );
    }
}
