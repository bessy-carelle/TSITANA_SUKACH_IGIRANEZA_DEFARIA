package vue;

import controleur.commande.*;
import controleur.commande.Commande;
import modele.*;
import controleur.*;
import javax.swing.*;

public class Vue extends JPanel{
    private Modele modele;
    private JButton btnValider, btnUndo, btnRedo, btnQuitter;
    private Map<String, JButton> boutons = new HashMap<>();
    private Commande commandeUndo;
    private Commande commandeRedo;
    private Commande commandeValider;
    private Commande commandeQuitter;

    public Vue(Modele modele){

    }

    public void setCommandeUndo(Commande c){
        commandeUndo = c; 
    }

    public void setCommandeRedo(Commande c) { 
        commandeRedo = c;
    }

    public void setCommandeValider(Commande c) {
        commandeValider = c;
    }

    public void setCommandeQuitter(Commande c){
        commandeQuitter = c;
    }

    public void setValidationActive(boolean active) {
        btnValider.setEnabled(active);
    }

    public void setUndoActive(boolean active) {
        btnUndo.setEnabled(active);
    }

    public void setRedoActive(boolean active) {
        btnRedo.setEnabled(active);
    }
}