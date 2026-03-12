public class CommandeValider implements Commande {
    private Modele modele;

    public CommandeValider(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void execute() {
        modele.validerAction();
    }
}