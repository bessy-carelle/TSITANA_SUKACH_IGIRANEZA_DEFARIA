public class CommandeQuitter implements Commande {
    private Modele modele;

    /**
     * Constructeur de la commande "Quitter".
     *
     * @param modele Le modèle sur lequel exécuter l'action
     */
    public CommandeQuitter(Modele modele) {
        this.modele = modele;
    }

    /**
     * Exécute l'action de quitter la partie via le modèle
     */
    @Override
    public void execute() {
        modele.quitterAction();
    }
}