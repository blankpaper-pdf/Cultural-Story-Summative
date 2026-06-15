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
public class Human extends Character {
    private Animal pet;
    
    
    /**
     * Constructor that creates a Human object with position, name, ability,
     * pet, and image.
     *
     * @param p reference to the Processing sketch
     * @param x x-coordinate of the human
     * @param y y-coordinate of the human
     * @param name name of the human
     * @param ability special ability of the human
     * @param pet animal companion of the human
     * @param imagePath file path for the human image
     */
    public Human(PApplet p, int x, int y, String name, String ability,
            Animal pet, String imagePath) {
        super(p, x, y, name, ability, imagePath);
        this.pet = pet;
    }
    
    /**
     * Defines the human's special ability (overridden method).
     * Outputs a message to the console.
     */
    public void ability() {
        System.out.println("Human ability");
    }
}
