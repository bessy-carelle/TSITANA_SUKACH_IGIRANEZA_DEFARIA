public class MainMemory{
    public static void main(String[] args){
        Modele modele = new Modele();

        Vue vue = new Vue(modele);
        vue.setCommandeUndo(new CommandeUndo(modele));
        vue.setCommandeRedo(new CommandeRedo(modele));
        vue.setCommandeValider(new CommandeValider(modele));
        vue.setCommandeQuitter(new CommandeQuitter(modele));
    }

    gameMode.demarrer(modele);
}