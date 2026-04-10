package com.mycompany.projetpatron.Controller;

import java.awt.event.MouseEvent;

/**
 * Interface représentant un état du contrôleur de vue dans le cadre du
 * patron de conception <b>État</b> (State Pattern).
 * <p>
 * Chaque implémentation de cette interface définit un comportement spécifique
 * en réponse aux interactions souris de l'utilisateur (ex. création de cercle,
 * création de rectangle). Le contrôleur {@link VueControlleurJeu} délègue
 * ses événements souris à l'état courant implémentant cette interface.
 * </p>
 *
 * @author tsitana251
 * @version 1.0
 * @see VueControlleurJeu
 */
public interface VueControlleurState {

    /**
     * Gère l'événement de clic souris selon l'état courant.
     *
     * @param m l'événement souris contenant les coordonnées et informations du clic
     */
    public void mouseClicked(MouseEvent m);

    /**
     * Gère l'événement de déplacement souris selon l'état courant.
     * <p>
     * Typiquement utilisé pour afficher un aperçu dynamique de la forme
     * en cours de création.
     * </p>
     *
     * @param m l'événement souris contenant les coordonnées courantes du curseur
     */
    public void mouseMoved(MouseEvent m);
}

