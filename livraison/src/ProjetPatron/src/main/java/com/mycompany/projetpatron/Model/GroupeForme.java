package com.mycompany.projetpatron.Model;

import java.util.ArrayList;

/**
 * Classe représentant un groupe de formes.
 * <p>
 * Un groupe de formes permet de regrouper plusieurs objets de type {@link Forme}
 * et d'être notifié des changements de ses éléments via le pattern observateur.
 * </p>
 */
public class GroupeForme extends AbstractModeleEcoutable implements EcouteurModele {

    /**
     * Liste des formes appartenant à ce groupe.
     */
    public ArrayList<Forme> formes = new ArrayList<>();

    /**
     * Retourne la surface totale du groupe.
     * <p>
     * (Actuellement valeur temporaire / non implémentée correctement)
     * </p>
     *
     * @return la surface totale du groupe
     */
    public int getSurfaceTotale() {
        return 60;
    }

    /**
     * Retourne la liste des formes du groupe.
     *
     * @return la liste des formes
     */
    public ArrayList<Forme> getFormes() {
        return formes;
    }

    /**
     * Ajoute une forme au groupe et enregistre le groupe comme écouteur de cette forme.
     * Puis notifie les observateurs du groupe.
     *
     * @param f la forme à ajouter
     */
    public void ajoutForme(Forme f) {
        formes.add(f);
        f.ajoutEcouteurModele(this);
        fireChangement();
    }

    /**
     * Déclenche une notification de changement du groupe.
     */
    public void notifierChangement() {
        fireChangement();
    }

    /**
     * Méthode appelée lorsqu'une forme du groupe est mise à jour.
     *
     * @param source l'objet source du changement
     */
    @Override
    public void modeleMisAJour(Object source) {
        // fireChangement();
    }
}
