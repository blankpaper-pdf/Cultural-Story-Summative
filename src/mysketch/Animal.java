/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysketch;

import processing.core.PApplet;

/**
 *
 * @author Ivy Yu
 */
public class Animal extends Character {
    private String species;
    
    public Animal(PApplet p, int x, int y, String name, String species,
            String imagePath) {
        super(p, x, y, name, imagePath);
        this.species = species;
    }
    
    public void ability() {
        
    }
}
