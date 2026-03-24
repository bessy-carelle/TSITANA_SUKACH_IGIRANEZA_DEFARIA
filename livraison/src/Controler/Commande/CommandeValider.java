/**
 *
 * @author defaria241
 */

public class CommandeValider implements Commande {
    private Modele modele;

    /**
     * Constructeur de la commande "Valider".
     *
     * @param modele Le modèle sur lequel exécuter l'action
     */

    public CommandeValider(Modele modele) {
        this.modele = modele;
    }

    /**
     * Exécute l'action de Valider nos actions via le modèle
     */

    @Override
    public void execute() {
        modele.validerAction();
    }
}