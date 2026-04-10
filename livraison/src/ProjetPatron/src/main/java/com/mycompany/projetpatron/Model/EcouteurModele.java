package com.mycompany.projetpatron.Model;

/**
 * Interface représentant un écouteur de modèle (pattern Observateur).
 */
public interface EcouteurModele {

    /**
     * Méthode appelée lorsque le modèle est mis à jour.
     *
     * @param source le modèle ayant déclenché la mise à jour
     */
    void modeleMisAJour(Object source);

}
