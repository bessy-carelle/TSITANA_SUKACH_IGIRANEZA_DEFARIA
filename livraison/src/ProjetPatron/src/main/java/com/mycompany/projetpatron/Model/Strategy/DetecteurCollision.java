package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.Rectangle;

public class DetecteurCollision {

    // si distance entre centres <= somme des rayons c'est-à-dire que soit les cercles se touchent soit ils s'entremelent 
    public static boolean cercleVsCercle(Cercle c1, Cercle c2) {
        double dx = c1.getCentre().x - c2.getCentre().x;
        double dy = c1.getCentre().y - c2.getCentre().y;

        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= (c1.getRayon() + c2.getRayon());
    }

    // vérification collision entre deux rectangles dstincts
    public static boolean rectangleVsRectangle(Rectangle r1, Rectangle r2) {
        boolean separeX = r1.getCoinRectangle().x + r1.getWidth()  < r2.getCoinRectangle().x
                       || r2.getCoinRectangle().x + r2.getWidth()  < r1.getCoinRectangle().x;
        boolean separeY = r1.getCoinRectangle().y + r1.getHeight() < r2.getCoinRectangle().y
                       || r2.getCoinRectangle().y + r2.getHeight() < r1.getCoinRectangle().y;

        return !separeX && !separeY;
    }

    // Méthode de clamping pour trouver un point plus proche selon où se situe le cercle par rapport au rectangle 
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

