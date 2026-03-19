/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpatron.View;

/**
 *
 * @author tsitana251
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

public class DrawArea extends JLabel{
    Dimension minSize = new Dimension(100, 50);
    
    public DrawArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}
