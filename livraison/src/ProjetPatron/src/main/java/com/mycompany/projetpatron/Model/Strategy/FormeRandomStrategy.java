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
    public GroupeForme generer(int borneMaxX,int borneMaxY){
        GroupeForme groupe = new GroupeForme();
        Random aleatoire = new Random();

        for (int i=0; i<nbFormes ; i++){

            if (aleatoire.nextBoolean()){
                int rayon = 20 + aleatoire.nextInt(40);
                int x = rayon + aleatoire.nextInt(borneMaxX- 2 * rayon);
                int y = rayon + aleatoire.nextInt(borneMaxY - 2 * rayon);
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



}
