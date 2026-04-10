package com.mycompany.projetpatron.Controller.Command;

import java.util.Stack;
import com.mycompany.projetpatron.Model.JeuFormes;

/**
 * Gestionnaire central des commandes, implémenté selon le patron de conception
 * <b>Singleton</b> combiné au patron <b>Commande</b>.
 * <p>
 * Cette classe gère l'historique des commandes exécutées afin de permettre
 * les opérations d'annulation ({@code undo}) et de rétablissement ({@code redo}).
 * Une seule instance est créée et partagée dans toute l'application.
 * </p>
 *
 * @author defaria241@CAMPUS
 * @version 1.0
 * @see Command
 * @see JeuFormes
 */
public class GestionnaireCommandes {

    /**
     * Instance unique du gestionnaire (patron Singleton).
     */
    private static GestionnaireCommandes instance;

    /**
     * Pile des commandes pouvant être annulées (undo).
     */
    private Stack<Command> historiqueUndo = new Stack<>();

    /**
     * Pile des commandes pouvant être rétablies (redo).
     */
    private Stack<Command> historiqueRedo = new Stack<>();

    /**
     * Référence au jeu de formes associé au gestionnaire.
     */
    private JeuFormes jeu;

    /**
     * Constructeur privé empêchant l'instanciation directe (patron Singleton).
     */
    private GestionnaireCommandes() {}

    /**
     * Retourne l'instance unique du {@code GestionnaireCommandes}.
     * <p>
     * Crée l'instance si elle n'existe pas encore (initialisation paresseuse).
     * </p>
     *
     * @return l'instance unique de {@code GestionnaireCommandes}
     */
    public static GestionnaireCommandes getInstance() {
        if (instance == null) {
            instance = new GestionnaireCommandes();
        }
        return instance;
    }

    /**
     * Définit le jeu de formes associé à ce gestionnaire.
     *
     * @param jeu le {@link JeuFormes} à associer
     */
    public void setJeu(JeuFormes jeu) {
        this.jeu = jeu;
    }

    /**
     * Exécute une commande, l'ajoute à l'historique undo et vide l'historique redo.
     * <p>
     * Toute nouvelle commande exécutée efface les commandes en attente de rétablissement.
     * </p>
     *
     * @param cmd la {@link Command} à exécuter
     */
    public void executerCommande(Command cmd) {
        cmd.execute();
        historiqueUndo.push(cmd);
        historiqueRedo.clear();
    }

    /**
     * Annule la dernière commande exécutée et la déplace dans l'historique redo.
     * <p>
     * Ne fait rien si l'historique undo est vide.
     * </p>
     */
    public void undo() {
        if (!historiqueUndo.isEmpty()) {
            Command cmd = historiqueUndo.pop();
            cmd.undo();
            historiqueRedo.push(cmd);
        }
    }

    /**
     * Rétablit la dernière commande annulée et la remet dans l'historique undo.
     * <p>
     * Ne fait rien si l'historique redo est vide.
     * </p>
     */
    public void redo() {
        if (!historiqueRedo.isEmpty()) {
            Command cmd = historiqueRedo.pop();
            cmd.execute();
            historiqueUndo.push(cmd);
        }
    }

    /**
     * Indique si une opération d'annulation est possible.
     *
     * @return {@code true} si l'historique undo contient au moins une commande,
     *         {@code false} sinon
     */
    public boolean canUndo() {
        return !historiqueUndo.isEmpty();
    }

    /**
     * Indique si une opération de rétablissement est possible.
     *
     * @return {@code true} si l'historique redo contient au moins une commande,
     *         {@code false} sinon
     */
    public boolean canRedo() {
        return !historiqueRedo.isEmpty();
    }
}
