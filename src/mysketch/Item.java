/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysketch;

/**
 *
 * @author Ivy Yu
 */
import processing.core.PApplet;
import processing.core.PImage;

public class Item {
    public int x, y; // position of the item
    private String name; // name of item
    public PImage image; // image of the object
    private PApplet app; // the canvas used to display graphical elements
    
    public Item(PApplet p, int x, int y, String name, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
    }
    
    public boolean isClicked(int mousex, int mousey) {
        int centerX = x + 25;
        int centerY = y + 25;
        float d = PApplet.dist(mousex, mousey, centerX, centerY);
        return d < 16;
    }
}
