public class CommandeUndo implements Commande {
    private Modele modele;

    public CommandeUndo(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void execute() {
        modele.undo();
    }
}