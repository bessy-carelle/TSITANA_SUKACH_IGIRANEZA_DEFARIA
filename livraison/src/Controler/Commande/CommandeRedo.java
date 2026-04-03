/**
 *
 * @author defaria241
 */

public class CommandeRedo implements Commande {
    private Modele modele;

    /**
     * Constructeur de la commande "redo".
     *
     * @param modele Le modèle sur lequel exécuter l'action
     */

    public CommandeRedo(Modele modele) {
        this.modele = modele;
    }

    /**
     * Exécute l'action de revenir en arriere via le modèle
     */

    @Override
    public void execute() {
        modele.redo();
    }
}