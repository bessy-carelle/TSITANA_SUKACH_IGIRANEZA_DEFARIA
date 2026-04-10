package com.mycompany.projetpatron.Model.Strategy;

import com.mycompany.projetpatron.Model.GroupeForme;

/**
 * Interface définissant une stratégie de génération de formes.
 */

public interface GenerationStrategy {
    /* @param borneMaxX La largeur du plateau de jeu
     * @param borneMaxY La hauteur du plateau de jeu
    */
    GroupeForme generer(int borneMaxX,int borneMaxY);

}
