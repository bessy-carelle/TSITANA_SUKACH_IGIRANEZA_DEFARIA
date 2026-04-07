/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.mycompany.projetpatron.Controller.EtatCreationCercle;
import com.mycompany.projetpatron.Controller.EtatCreationRectangle;
import com.mycompany.projetpatron.Model.Cercle;
import com.mycompany.projetpatron.Model.EcouteurModele;
import com.mycompany.projetpatron.Model.JeuFormes;
import com.mycompany.projetpatron.Model.Point;
import com.mycompany.projetpatron.Model.Rectangle;
import com.mycompany.projetpatron.Model.Forme;


/*****FICHIER A CORRIGER  : PAS ENCORE FINI (CONTIENT DES ERREURS*********/
public class VueJeu2D extends JPanel implements EcouteurModele {
    EtatCreationCercle circle;
    EtatCreationRectangle rectangle;
    private JeuFormes jeu;          
    static final int sizeX = 5;

    public VueJeu2D(EtatCreationCercle var1, EtatCreationRectangle var2) {
        this.circle = var1;
        this.rectangle = var2;
        this.setOpaque(false);
        this.jeu = jeu;
        var1.ajoutEcouteurModele(this);
        var2.ajoutEcouteurModele(this);
        var1.formes.ajoutEcouteurModele(this);
        var2.formes.ajoutEcouteurModele(this);
        jeu.ajoutEcouteurModele(this); 
    }

    public void paintComponent(Graphics var1) {

        /***Partie comportant des erreurs  */
        //dessiner d abord les obstacles(formes en rouge)
        if (jeu != null && jeu.getObstacles() != null) {
            var1.couleur = "ROUGE";
            for (Object f : jeu.getObstacles().getFormes()) {
                if (f instanceof Cercle) {
                    Cercle c = (Cercle) f;
                    int x = (int) c.getCentre().x;
                    int y = (int) c.getCentre().y;
                    int r = (int) c.getRayon();
                    var1.drawOval(x - r, y - r, 2 * r, 2 * r);
                } else if (f instanceof Rectangle) {
                    Rectangle r = (Rectangle) f;
                    var1.drawRect(
                        (int) r.getCoinRectangle().x,
                        (int) r.getCoinRectangle().y,
                        r.getWidth(),
                        r.getHeight()
                    );
                }
            }
            var1.couleur = "BLACK";

        /**jusque ici  ****/

        System.out.println(" here 0000");
        if (this.circle.getCentre() != null) {
        Point var2 = this.circle.getCentre();
        int var3 = (int)var2.x;
        int var4 = (int)var2.y;
        var1.drawLine(var3 - 5, var4 - 5, var3 + 5, var4 + 5);
        var1.drawLine(var3 + 5, var4 - 5, var3 - 5, var4 + 5);
        if (this.circle.getRayon() > (double)0.0F) {
        int var5 = (int)this.circle.getRayon();
        var1.drawOval(var3 - var5, var4 - var5, 2 * var5, 2 * var5);
                }
            }
        ArrayList var10 = this.circle.formes.getFormes();
        for(int var11 = 0; var11 < var10.size(); ++var11) {
        Cercle var14 = (Cercle)var10.get(var11);
        if (var14.active) {
        int var17 = (int)var14.getCentre().x;
        int var6 = (int)var14.getCentre().y;
        int var7 = (int)var14.getRayon();
        var1.drawOval(var17 - var7, var6 - var7, 2 * var7, 2 * var7);
                }
            }
        System.out.println(" here 0");
        System.out.println("Coin vue: " + String.valueOf(this.rectangle.getCoinRectangle()));
        if (this.rectangle.getCoinRectangle() != null) {
        Point var12 = this.rectangle.getCoinRectangle();
        int var15 = (int)var12.x;
        int var18 = (int)var12.y;
        var1.drawLine(var15 - 5, var18 - 5, var15 + 5, var18 + 5);
        var1.drawLine(var15 + 5, var18 - 5, var15 - 5, var18 + 5);
        System.out.println(" here 1");
        if ((double)this.rectangle.getLargeur() > (double)0.0F) {
        System.out.println("Draw here ");
        int var20 = this.rectangle.getLargeur();
        int var22 = this.rectangle.getHauteur();
        var1.drawRect(var15, var18, var20, var22);
        System.out.println("AFTER Draw ");
                }
            }
        ArrayList var13 = this.rectangle.formes.getFormes();
        for(int var16 = 0; var16 < var13.size(); ++var16) {
        Rectangle var19 = (Rectangle)var13.get(var16);
        if (var19.active) {
        int var21 = (int)var19.getCoinRectangle().x;
        int var23 = (int)var19.getCoinRectangle().y;
        int var8 = var19.getWidth();
        int var9 = var19.getHeight();
        var1.drawRect(var21, var23, var8, var9);
            }
        }
    }
    public void modeleMisAJour(Object var1) {
        this.repaint();
    }
}
