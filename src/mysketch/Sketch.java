/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysketch;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Ivy Yu
 */
public class Sketch extends PApplet {
    private Character c1;
    private Character c2;
    private Animal a;
    private PImage bg;
    private PImage menu;
    int stage = 0;
    private boolean showInfo = false;
    
    public void settings(){
        bg = loadImage("images/background.jpg");
        menu = loadImage("images/menu.jpg");
        size(600, 350);
    }
    
    public void setup(){
	// set background colour using R,G,B (https://rgbcolorpicker.com/)
        background(216, 220, 230);
        textSize(15);
        
        a = new Animal(this, 50, 200, "Rabbit", "Spawn", "images/rabbit.png");
        c1 = new Human(this, 300, 250, "Chang'e", "Spawn", a, "images/chang-e.png");
        c2 = new Human(this, 400, 200, "Houyi", "Spawn", a, "images/houyi.png");
    }
    
    public void draw(){
        if (stage == 0) {
            image(menu, 0, 0, width, height);
            text("Main Menu", 20, 50);
            text("Press 'Enter' to Proceed", 20, 80);
        } else if (stage == 1) {
            background(216, 220, 230);
            image(bg, 0, 0, width, height);
            a.draw();
            c2.draw();
            c1.draw();
            if (c1.isCollidingWith(a)) {
                fill(255);
                this.text("Press 'E' to interact", a.x, a.y);
                if (keyPressed) {
                    if (key == 'e' || key == 'E') {
                        strokeWeight(1);
                        stroke(255);
                        fill(0);
                        rect(0, 300, width-1, 50);
                        fill(255);
                        text("[intelligible bunny noises ... what could it be trying to communicate]", 5, 330);
                    }
                }
            }
            if (c1.isCollidingWith(c2)) {
                fill(255);
                this.text("Press 'E' to interact", c2.x, c2.y);
                if (keyPressed) {
                    if (key == 'e' || key == 'E') {
                        strokeWeight(1);
                        stroke(255);
                        fill(0);
                        rect(0, 300, width-1, 50);
                        fill(255);
                        text("Greetings Chang'e. The Emperor summoned ... [idk]", 5, 330);
                    }
                }
            }
            fill(255);
            text("Use arrow keys keys to move character", 20, 50);
        
        if (keyPressed) {
            if (keyCode == LEFT) {
                c1.move(-3, 0);
            } else if (keyCode == RIGHT) {
                c1.move(3, 0);
            } else if (keyCode == UP) {
                c1.move(0, -3);
            } else if (keyCode == DOWN) {
                c1.move(0, 3);
//            } else if (key == 'a' || key == 'A') {
//                a.move(-5, 0);
//            } else if (key == 'd' || key == 'D') {
//                a.move(5, 0);
//            } else if (key == 'w' || key == 'W') {
//                a.move(0, -5);
//            } else if (key == 's' || key == 'S') {
//                a.move(0, 5);
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
