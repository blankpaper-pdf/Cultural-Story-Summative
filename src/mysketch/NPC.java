/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysketch;

import processing.core.PApplet;

/**
 *
 * @author 343033031
 */
public class NPC extends Character {
    
    public NPC(PApplet p, int x, int y, String name, String imagePath) {
        super(p, x, y, name, imagePath);
    }
    
    public void move() {
        // override to cancel movement capability
    }
}
