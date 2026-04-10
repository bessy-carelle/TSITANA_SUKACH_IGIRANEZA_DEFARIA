package com.mycompany.projetpatron.Model.Strategy;

import java.util.Random;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.GroupeForme;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;

/**
 * Stratégie de génération aléatoire de formes géométriques (cercles et rectangles),
 * dans le cadre du patron de conception <b>Stratégie</b> (Strategy Pattern).
 * <p>
 * Cette implémentation de {@link GenerationStrategy} génère un nombre défini de
 * formes rouges placées aléatoirement dans les bornes données. Chaque forme est
 * soit un {@link Cercle} soit un {@link Rectangle}, choisi aléatoirement.
 * Les formes sont ignorées si les dimensions de la fenêtre sont trop petites
 * pour les contenir.
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 * @see GenerationStrategy
 * @see GroupeForme
 * @see Cercle
 * @see Rectangle
 */
public class FormeRandomStrategy implements GenerationStrategy {

    /**
     * Nombre de formes à générer.
     */
    private int nbFormes;

    /**
     * Construit une stratégie de génération aléatoire avec un nombre de formes défini.
     *
     * @param nbFormes le nombre de formes à générer
     */
    public FormeRandomStrategy(int nbFormes) {
        this.nbFormes = nbFormes;
    }

    /**
     * Génère un groupe de formes aléatoires (cercles et rectangles) positionnées
     * aléatoirement dans les bornes données.
     * <p>
     * Pour chaque forme :
     * <ul>
     *   <li>Si {@code aleatoire.nextBoolean()} retourne {@code true}, un {@link Cercle}
     *       est créé avec un rayon aléatoire entre 20 et 59 pixels, positionné de façon
     *       à rester entièrement dans les bornes.</li>
     *   <li>Sinon, un {@link Rectangle} est créé avec une largeur aléatoire entre 30 et
     *       109 pixels et une hauteur aléatoire entre 30 et 109 pixels.</li>
     * </ul>
     * Toutes les formes générées ont la couleur {@code "ROUGE"}.
     * Une forme est ignorée ({@code continue}) si la fenêtre est trop petite pour
     * la contenir.
     * </p>
     *
     * @param borneMaxX la largeur maximale disponible en pixels
     * @param borneMaxY la hauteur maximale disponible en pixels
     * @return un {@link GroupeForme} contenant les formes générées aléatoirement
     */
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
                System.out.println("Cercle → xMin=" + xMin + " xMax=" + xMax
                        + " yMin=" + yMin + " yMax=" + yMax);

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
                int width  = 30 + aleatoire.nextInt(80);
                int height = 30 + aleatoire.nextInt(80);
                int xMax   = borneMaxX - width;
                int yMax   = borneMaxY - height;
                System.out.println("Rect → xMax=" + xMax + " yMax=" + yMax
                        + " w=" + width + " h=" + height);

                if (xMax <= 0 || yMax <= 0) {
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
