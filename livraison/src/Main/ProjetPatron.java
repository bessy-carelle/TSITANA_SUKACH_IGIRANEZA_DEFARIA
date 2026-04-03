/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Main;

import Controller.EtatCreationCercle;
import Controller.VueControlleurJeu;
import View.FenetreGlobale;

/**
 *
 * @author tsitana251
 */
public class ProjetPatron {

     public static void main(String[] args) {
        System.out.println("___Project Start____");
        new FenetreGlobale(new VueControlleurJeu(), new EtatCreationCercle());
     }
}
