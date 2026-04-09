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
                // garde au moins rayon de marge sur chaque bord
                int xMin = rayon;
                int xMax = borneMaxX - rayon;
                int yMin = rayon;
                int yMax = borneMaxY - rayon;
                // sécurité si la fenêtre est trop petite
                if (xMax <= xMin || yMax <= yMin) continue;
                int x = xMin + aleatoire.nextInt(xMax - xMin);
                int y = yMin + aleatoire.nextInt(yMax - yMin);
                Cercle c = new Cercle(new Point(x, y), rayon);
                c.couleur = "ROUGE";
                groupe.ajoutForme(c);
            } else {
                int width = 30 + aleatoire.nextInt(80);
                int height = 30 + aleatoire.nextInt(80);
                int xMax = borneMaxX - width;
                int yMax = borneMaxY - height;
                if (xMax <= 0 || yMax <= 0) continue;
                int x = aleatoire.nextInt(xMax);
                int y = aleatoire.nextInt(yMax);
                Rectangle r = new Rectangle(new Point(x, y), width, height);
                r.couleur = "ROUGE";
                groupe.ajoutForme(r);
            }
        }
        return groupe;
    }
}








/* 
    @Override
    public GroupeForme generer(int borneMaxX,int borneMaxY){
        GroupeForme groupe = new GroupeForme();
        Random aleatoire = new Random();

        for (int i=0; i<nbFormes ; i++){

            if (aleatoire.nextBoolean()){
                int rayon = 20 + aleatoire.nextInt(40);
                int x = rayon + aleatoire.nextInt(borneMaxX * rayon);
                int y = rayon + aleatoire.nextInt(borneMaxY * rayon);
                Cercle c = new Cercle(new Point(x, y), rayon);
                c.couleur = "ROUGE";
                groupe.ajoutForme(c);

            }else{
                int width = 30 + aleatoire.nextInt(60);
                int height = 30 + aleatoire.nextInt(80);
                int x = aleatoire.nextInt(borneMaxX- width);
                int y = aleatoire.nextInt(borneMaxY - height);
                Rectangle r = new Rectangle(new Point(x, y), width,height);
                r.couleur = "ROUGE";

                groupe.ajoutForme(r);

            }
        }
        return groupe;


    }
*/



