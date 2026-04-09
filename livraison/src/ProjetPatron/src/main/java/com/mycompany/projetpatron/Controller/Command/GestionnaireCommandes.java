/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Controller.Command;

import java.util.Stack;
import com.mycompany.projetpatron.Model.JeuFormes;

/**
 *
 * @author defaria241@CAMPUS
 */
public class GestionnaireCommandes {
    private static GestionnaireCommandes instance;
    private Stack<Command> historiqueUndo = new Stack<>();
    private Stack<Command> historiqueRedo = new Stack<>();
    private JeuFormes jeu;

    public void setJeu(JeuFormes jeu) {
        this.jeu = jeu;
    }

    private GestionnaireCommandes() {}

    public static GestionnaireCommandes getInstance() {
        if (instance == null) {
            instance = new GestionnaireCommandes();
        }
        return instance;
    }

    public void executerCommande(Command cmd) {
        cmd.execute();
        historiqueUndo.push(cmd);
        historiqueRedo.clear(); // redo effacé après nouvelle action
    }

    public void undo() {
        if (!historiqueUndo.isEmpty()) {
            Command cmd = historiqueUndo.pop();
            cmd.undo();
            historiqueRedo.push(cmd);
        }
    }

    public void redo() {
        if (!historiqueRedo.isEmpty()) {
            Command cmd = historiqueRedo.pop();
            cmd.execute();
            historiqueUndo.push(cmd);
        }
    }

    public boolean canUndo() { return !historiqueUndo.isEmpty(); }
    public boolean canRedo() { return !historiqueRedo.isEmpty(); }
}