/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.Controller.Command;
import com.mycompany.projetpatron.Model.Forme;
import com.mycompany.projetpatron.Model.GroupeForme;

/**
 *
 * @author defaria241@CAMPUS
 */
public class CommandAjoutForme implements Command {
    private GroupeForme groupe;
    private Forme forme;

    public CommandAjoutForme(GroupeForme groupe, Forme forme) {
        this.groupe = groupe;
        this.forme = forme;
    }

    @Override
    public void execute() {
        forme.active = true;
        groupe.ajoutForme(forme);  // ou juste fireChangement si déjà ajoutée
    }

    @Override
    public void undo() {
       forme.active = false;
       groupe.notifierChangement(); // redessine sans cette forme
    }
}
