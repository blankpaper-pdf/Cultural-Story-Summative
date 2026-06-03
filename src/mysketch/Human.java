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
    private String ability;
    private Animal pet;
    
    public Human(PApplet p, int x, int y, String name, String ability,
            Animal pet, String imagePath) {
        super(p, x, y, name, imagePath);
        this.ability = ability;
        this.pet = pet;
    }
}
