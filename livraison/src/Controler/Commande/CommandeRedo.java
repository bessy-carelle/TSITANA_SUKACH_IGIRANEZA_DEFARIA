public class CommandeRedo implements Commande {
    private Modele modele;

    public CommandeRedo(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void execute() {
        modele.redo();
    }
}