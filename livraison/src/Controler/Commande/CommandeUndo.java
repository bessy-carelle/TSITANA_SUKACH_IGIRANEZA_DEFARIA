/**
 *
 * @author defaria241
 */

public class CommandeUndo implements Commande {
    private Modele modele;

    /**
     * Constructeur de la commande "undo".
     *
     * @param modele Le modèle sur lequel exécuter l'action
     */

    public CommandeUndo(Modele modele) {
        this.modele = modele;
    }

    /**
     * Exécute l'action de revenir en avant via le modèle
     */

    @Override
    public void execute() {
        modele.undo();
    }
}