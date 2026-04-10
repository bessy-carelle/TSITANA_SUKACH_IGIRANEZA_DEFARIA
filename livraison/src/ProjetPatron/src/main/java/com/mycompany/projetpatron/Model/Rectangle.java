package com.mycompany.projetpatron.Model;

/**
 * Représente un rectangle dans le modèle.
 * <p>
 * Un rectangle est une forme définie par un point d’origine (coin supérieur gauche),
 * une largeur et une hauteur.
 * </p>
 */
public class Rectangle extends Forme {

    /**
     * Coin supérieur gauche du rectangle.
     */
    public Point coinRectangle;

    /**
     * Largeur du rectangle.
     */
    public int width;

    /**
     * Hauteur du rectangle.
     */
    public int height;

    /**
     * Constructeur simple avec seulement une couleur.
     *
     * @param couleur couleur du rectangle
     */
    public Rectangle(String couleur) {
        super(couleur);
    }

    /**
     * Constructeur complet d’un rectangle.
     *
     * @param coinRectangle point représentant le coin supérieur gauche
     * @param width largeur du rectangle
     * @param height hauteur du rectangle
     */
    public Rectangle(Point coinRectangle, int width, int height) {
        super("couleur"); // valeur par défaut (à corriger si besoin)
        this.coinRectangle = coinRectangle;
        this.width = width;
        this.height = height;
    }

    /**
     * Retourne le coin supérieur gauche du rectangle.
     *
     * @return le point du coin supérieur gauche
     */
    public Point getCoinRectangle() {
        return coinRectangle;
    }

    /**
     * Retourne la largeur du rectangle.
     *
     * @return largeur
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du rectangle.
     *
     * @return hauteur
     */
    public int getHeight() {
        return height;
    }
}