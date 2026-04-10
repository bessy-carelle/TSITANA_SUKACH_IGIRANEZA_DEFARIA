package com.mycompany.projetpatron.Model;

import java.util.ArrayList;

/**
 * Classe abstraite permettant de gérer des écouteurs de modèle
 * et de notifier les changements.
 */
public abstract class AbstractModeleEcoutable {

    private ArrayList<EcouteurModele> ecouteurs = new ArrayList<>();

    /**
     * Ajoute un écouteur au modèle.
     *
     * @param e l'écouteur à ajouter
     */
    public void ajoutEcouteurModele(EcouteurModele e) {
        ecouteurs.add(e);
    }

    /**
     * Retire un écouteur du modèle.
     *
     * @param e l'écouteur à retirer
     */
    public void retraitEcouteurModele(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    /**
     * Notifie tous les écouteurs qu'un changement du modèle a eu lieu.
     */
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurs) {
            e.modeleMisAJour(this);
        }
    }
}
