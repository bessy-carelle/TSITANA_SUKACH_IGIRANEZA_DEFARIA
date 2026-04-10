package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.Rectangle;

/**
 * Classe utilitaire fournissant des méthodes statiques de détection de collisions
 * entre différents types de formes géométriques.
 * <p>
 * Supporte les combinaisons suivantes :
 * <ul>
 *   <li>Cercle vs Cercle</li>
 *   <li>Rectangle vs Rectangle</li>
 *   <li>Cercle vs Rectangle</li>
 * </ul>
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 * @see Cercle
 * @see Rectangle
 */
public class DetecteurCollision {

    /**
     * Détecte une collision entre deux cercles.
     * <p>
     * Deux cercles sont en collision si la distance entre leurs centres
     * est inférieure ou égale à la somme de leurs rayons (ils se touchent
     * ou se superposent).
     * </p>
     *
     * @param c1 le premier {@link Cercle}
     * @param c2 le second {@link Cercle}
     * @return {@code true} si les deux cercles sont en collision, {@code false} sinon
     */
    public static boolean cercleVsCercle(Cercle c1, Cercle c2) {
        double dx = c1.getCentre().x - c2.getCentre().x;
        double dy = c1.getCentre().y - c2.getCentre().y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= (c1.getRayon() + c2.getRayon());
    }

    /**
     * Détecte une collision entre deux rectangles.
     * <p>
     * Utilise le principe de séparation d'axes (SAT) : deux rectangles alignés
     * aux axes ne sont pas en collision s'il existe une séparation sur l'axe X
     * ou sur l'axe Y.
     * </p>
     *
     * @param r1 le premier {@link Rectangle}
     * @param r2 le second {@link Rectangle}
     * @return {@code true} si les deux rectangles se chevauchent, {@code false} sinon
     */
    public static boolean rectangleVsRectangle(Rectangle r1, Rectangle r2) {
        boolean separeX = r1.getCoinRectangle().x + r1.getWidth()  < r2.getCoinRectangle().x
                       || r2.getCoinRectangle().x + r2.getWidth()  < r1.getCoinRectangle().x;
        boolean separeY = r1.getCoinRectangle().y + r1.getHeight() < r2.getCoinRectangle().y
                       || r2.getCoinRectangle().y + r2.getHeight() < r1.getCoinRectangle().y;
        return !separeX && !separeY;
    }

    /**
     * Détecte une collision entre un cercle et un rectangle.
     * <p>
     * Utilise la technique du <b>clamping</b> : on trouve le point du rectangle
     * le plus proche du centre du cercle, puis on vérifie si la distance entre
     * ce point et le centre est inférieure ou égale au rayon du cercle.
     * </p>
     *
     * @param c le {@link Cercle} à tester
     * @param r le {@link Rectangle} à tester
     * @return {@code true} si le cercle est en collision avec le rectangle,
     *         {@code false} sinon
     */
    public static boolean cercleVsRectangle(Cercle c, Rectangle r) {
        double procheX = Math.max(r.getCoinRectangle().x,
                         Math.min(c.getCentre().x, r.getCoinRectangle().x + r.getWidth()));
        double procheY = Math.max(r.getCoinRectangle().y,
                         Math.min(c.getCentre().y, r.getCoinRectangle().y + r.getHeight()));
        double dx = c.getCentre().x - procheX;
        double dy = c.getCentre().y - procheY;
        return Math.sqrt(dx * dx + dy * dy) <= c.getRayon();
    }
}
