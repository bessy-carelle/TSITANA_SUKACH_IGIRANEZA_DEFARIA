package com.mycompany.projetpatron.Controller.Command;

import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;

/**
 * Commande responsable de l'ajout d'une {@link Forme} dans un {@link GroupeForme}.
 * <p>
 * Implémente le patron de conception <b>Commande</b> afin de permettre
 * l'exécution et l'annulation de l'ajout d'une forme dans le groupe.
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 * @see Command
 * @see Forme
 * @see GroupeForme
 */
public class CommandAjoutForme implements Command {

    /**
     * Le groupe de formes dans lequel la forme sera ajoutée.
     */
    private GroupeForme groupe;

    /**
     * La forme à ajouter dans le groupe.
     */
    private Forme forme;

    /**
     * Construit une commande d'ajout de forme.
     *
     * @param groupe le {@link GroupeForme} cible dans lequel la forme sera ajoutée
     * @param forme  la {@link Forme} à ajouter
     */
    public CommandAjoutForme(GroupeForme groupe, Forme forme) {
        this.groupe = groupe;
        this.forme = forme;
    }

    /**
     * Exécute la commande : active la forme et l'ajoute dans le groupe.
     * <p>
     * Met le champ {@code active} de la forme à {@code true} puis appelle
     * {@link GroupeForme#ajoutForme(Forme)} pour l'insérer dans le groupe.
     * </p>
     */
    @Override
    public void execute() {
        forme.active = true;
        groupe.ajoutForme(forme);
    }

    /**
     * Annule la commande : désactive la forme et notifie le groupe de se redessiner.
     * <p>
     * Met le champ {@code active} de la forme à {@code false} puis appelle
     * {@link GroupeForme#notifierChangement()} pour que le groupe se redessine
     * sans cette forme.
     * </p>
     */
    @Override
    public void undo() {
        forme.active = false;
        groupe.notifierChangement();
    }
}