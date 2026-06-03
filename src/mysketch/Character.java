package mysketch;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ivy Yu
 */
import processing.core.PApplet;
import processing.core.PImage;

public class Character {
    private int x, y; // position of the person
    private String name; // name of person
    private PImage image; // image of the person
    private PApplet app; // the canvas used to display graphical elements
    
    public Character(PApplet p, int x, int y, String name, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
    }
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public void draw() {
        app.image(image, x, y);
    }
}
