package com.mycompany.projetpatron.Model;

/**
 * Classe abstraite représentant une forme dans le modèle.
 * <p>
 * Une forme possède une couleur et un état d'activation.
 * Elle hérite de {@link AbstractModeleEcoutable} pour permettre l'observation des changements.
 * </p>
 */
public abstract class Forme extends AbstractModeleEcoutable {
    public boolean active = true;
    public String couleur;

    /**
     * Constructeur de la classe Forme.
     *
     * @param couleur la couleur initiale de la forme
     */
    public Forme(String couleur) {
        this.couleur = couleur;
    }
}
