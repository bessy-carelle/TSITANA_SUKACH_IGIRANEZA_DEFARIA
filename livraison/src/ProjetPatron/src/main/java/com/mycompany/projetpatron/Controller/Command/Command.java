package com.mycompany.projetpatron.Controller.Command;

/**
 * Interface représentant une commande dans le patron de conception <b>Commande</b> (Command Pattern).
 * <p>
 * Toute classe implémentant cette interface doit fournir une logique d'exécution
 * et d'annulation de la commande.
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 */
public interface Command {

    /**
     * Exécute la commande.
     * <p>
     * Contient la logique principale de l'action à réaliser.
     * </p>
     */
    void execute();

    /**
     * Annule la commande précédemment exécutée.
     * <p>
     * Permet de revenir à l'état antérieur à l'appel de {@link #execute()}.
     * </p>
     */
    void undo();
}

