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
    public int x, y; // position of the person
    private String name; // name of person
    private PImage image; // image of the person
    private PApplet app; // the canvas used to display graphical elements
    private String ability; // ability of character

    /**
     * Default constructor that creates an unnamed character.
     */
    public Character() {
        this.name = "null";
    }
    
    /**
     * Constructor that initializes a character with position, name, ability, and image.
     *
     * @param p reference to the Processing sketch
     * @param x x-coordinate of the character
     * @param y y-coordinate of the character
     * @param name name of the character
     * @param ability special ability of the character
     * @param imagePath file path of the character image
     */
    public Character(PApplet p, int x, int y, String name, String ability, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.ability = ability;
        this.image = app.loadImage(imagePath);
    }
    
    /**
     * Moves the character by a given amount in x and y directions.
     *
     * @param dx change in x position
     * @param dy change in y position
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    /**
     * Draws the character image at its current position.
     */
    public void draw() {
        app.image(image, x, y);
    }
    
    /**
     * Checks if this character is colliding with another character
     * using distance between their centers.
     *
     * @param other the other character to check collision with
     * @return true if the characters are close enough to be considered colliding
     */
    public boolean isCollidingWith(Character other) {
        // calculate center of this character
        int centerX = x+(image.pixelWidth/2);
        int centerY = y+(image.pixelWidth/2);
        // calculate center of other character
        int otherCenterX = other.x+(other.image.pixelWidth/2);
        int otherCenterY = other.y+(other.image.pixelHeight/2);
         // distance between centers
        float d = PApplet.dist(otherCenterX, otherCenterY, centerX, centerY);
        return d < 32;
    }
}
