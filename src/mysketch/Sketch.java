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
public class Sketch extends PApplet {
    private Character c1;
    private Character c2;
    private Animal a;
    int stage = 0;
    
    public void settings(){
        size(600, 350);
    }
    
    public void setup(){
	// set background colour using R,G,B (https://rgbcolorpicker.com/)
        background(216, 220, 230);
        textSize(15);
        
        a = new Animal(this, 0, 200, "Rabbit", "Spawn", "images/rabbit.png");
        c1 = new Human(this, 0, 200, "Chang'e", "Spawn", a, "images/chang-e.png");
    }
    
    public void draw(){
        if (stage == 0) {
            fill(0);
            text("Main Menu", 20, 50);
            text("Press 'Enter' to Proceed", 20, 80);
        } else if (stage == 1) {
            background(216, 220, 230);
            c1.draw();
        
        if (keyPressed) {
            if (keyCode == LEFT) {
                c1.move(-5, 0);
            } else if (keyCode == RIGHT) {
                c1.move(5, 0);
            } else if (keyCode == UP) {
                c1.move(0, -5);
            } else if (keyCode == DOWN) {
                c1.move(0, 5);
            }
        }
        }
    }
    
    public void keyPressed() {
        if (stage == 0) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 1;
            }
        }
    }
}
