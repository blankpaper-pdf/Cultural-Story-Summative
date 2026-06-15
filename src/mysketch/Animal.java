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
    
    /**
     * Constructor that creates an Animal object with position, name, ability,
     * species type, and image.
     *
     * @param p reference to the Processing sketch
     * @param x x-coordinate of the animal
     * @param y y-coordinate of the animal
     * @param name name of the animal
     * @param ability special ability of the animal
     * @param species species/type of the animal
     * @param imagePath file path for the animal image
     */
    public Animal(PApplet p, int x, int y, String name, String ability, String species,
            String imagePath) {
        super(p, x, y, name, ability, imagePath);
        this.species = species;
    }
    
    /**
     * Defines the animal's ability (overridden method).
     * Prints a message to the console.
     */
    public void ability() {
        System.out.println("Animal ability");
    }
    

    /**
     * Overrides move method to prevent user-controlled movement.
     * Animals cannot be controlled by the player.
     *
     * @param dx change in x-direction
     * @param dy change in y-direction
     */
    public void move(int dx, int dy) {
        System.out.print("null - animals are not user controlled");
    }
}
