/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetpatron;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Controller.VueControlleurJeu;
import com.mycompany.projetpatron.View.FenetreGlobale;

/**
 *
 * @author tsitana251
 */
public class ProjetPatron {

     public static void main(String[] args) {
        System.out.println("___Project Start____");
        new FenetreGlobale(new VueControlleurJeu(), new EtatCreationCercle(), new EtatCreationRectangle());
     }
}
