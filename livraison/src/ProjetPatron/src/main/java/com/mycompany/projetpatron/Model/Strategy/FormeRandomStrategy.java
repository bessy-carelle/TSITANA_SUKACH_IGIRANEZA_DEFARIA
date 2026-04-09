package com.mycompany.projetpatron.Model.Strategy;

import java.util.Random;

import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;





public class FormeRandomStrategy implements GenerationStrategy{

    private int nbFormes;

    public FormeRandomStrategy (int nbFormes) {
        this.nbFormes = nbFormes;
    }

    @Override
    public GroupeForme generer(int borneMaxX, int borneMaxY) {
        GroupeForme groupe = new GroupeForme();
        Random aleatoire = new Random();

        for (int i = 0; i < nbFormes; i++) {
            if (aleatoire.nextBoolean()) {
                int rayon = 20 + aleatoire.nextInt(40);
                int xMin = rayon;
                int xMax = borneMaxX - rayon;
                int yMin = rayon;
                int yMax = borneMaxY - rayon;
                System.out.println("Cercle → xMin=" + xMin + " xMax=" + xMax + " yMin=" + yMin + " yMax=" + yMax);
                if (xMax <= xMin || yMax <= yMin) {
                    System.out.println("SKIP cercle — fenêtre trop petite !");
                    continue;
                }
                int x = xMin + aleatoire.nextInt(xMax - xMin);
                int y = yMin + aleatoire.nextInt(yMax - yMin);

                System.out.println("Cercle placé en (" + x + ", " + y + ") rayon=" + rayon);

                Cercle c = new Cercle(new Point(x, y), rayon);
                c.couleur = "ROUGE";
                groupe.ajoutForme(c);
            } else {
                int width = 30 + aleatoire.nextInt(80);
                int height = 30 + aleatoire.nextInt(80);
                int xMax = borneMaxX - width;
                int yMax = borneMaxY - height;

                System.out.println("Rect → xMax=" + xMax + " yMax=" + yMax + " w=" + width + " h=" + height);

                if (xMax <= 0 || yMax <= 0){
                    System.out.println("SKIP rect — fenêtre trop petite !");
                    continue;

                } 
                int x = aleatoire.nextInt(xMax);
                int y = aleatoire.nextInt(yMax);

                System.out.println("Rect placé en (" + x + ", " + y + ")");

                Rectangle r = new Rectangle(new Point(x, y), width, height);
                r.couleur = "ROUGE";
                groupe.ajoutForme(r);
            }
        }
        return groupe;
    }
}





