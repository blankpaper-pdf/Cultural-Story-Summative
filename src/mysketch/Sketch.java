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
    private Character c3;
    private Animal a;
    private PImage bg;
    private PImage bg1;
    private PImage menu;
    static int stage = 0;
    private boolean showInfo = false;
    
    public void settings(){
        bg = loadImage("images/background.jpg");
        bg1 = loadImage("images/background1.jpg");
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
    
    public void msg(String select) {
        if (select.equals("proceed")) {
            text("Press 'Enter' to Proceed", 20, 80);
        } else if (select.equals("controls")) {
            text("Use arrow keys keys to move character", 20, 25);
        }
    }
    
    public void draw(){
        if (stage == 0) {
            image(menu, 0, 0, width, height);
            text("Main Menu", 20, 50);
            msg("proceed");
        } else if (stage == 1) {
            background(216, 220, 230);
            image(bg, 0, 0, width, height);
            a.draw();
            c2.draw();
            c1.draw();
            fill(255);
            msg("controls");
            if (c1.isCollidingWith(a)) {
                fill(255);
                this.text("White Rabbit (pet)", a.x, a.y);
//                if (keyPressed) {
//                    if (key == 'e' || key == 'E') {
                        strokeWeight(1);
                        stroke(255);
                        fill(0);
                        rect(0, 300, width-1, 50);
                        fill(255);
                        text("White Rabbit: [intelligible bunny noises]", 5, 330);
//                    }
//                }
            }
            if (c1.isCollidingWith(c2)) {
                fill(255);
                this.text("Houyi (husband)", c2.x, c2.y);
//                if (keyPressed) {
//                    if (key == 'e' || key == 'E') {
                        strokeWeight(1);
                        stroke(255);
                        fill(0);
                        rect(0, 300, width-1, 50);
                        fill(255);
                        text("Houyi: Greetings Chang'e. The Emperor has requested for my presence.", 5, 330);
//                    }
//                }
            } 
        
        if (keyPressed) {
            if (keyCode == LEFT) {
                c1.move(-3, 0);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e_L.png");
            } else if (keyCode == RIGHT) {
                c1.move(3, 0);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e_R.png");
            } else if (keyCode == UP) {
                c1.move(0, -3);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e.png");
            } else if (keyCode == DOWN) {
                c1.move(0, 3);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e.png");
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
        } else if (stage == 2) {
            image(bg1, 0, 0, width, height);
            int [] s2_dialogue = new int [6];
            int count = 0;
            strokeWeight(1);
            stroke(255);
            fill(0);
            rect(0, 270, width-1, 80);
            fill(255);
            text("Emperor: Houyi, as you know, the heat of the 10 suns have "
                    + "become increasingly unbearable.", 5, 300);
            text("                     A great archer such as yourself must "
                    + "understand the threat it serves to humanity.", 5, 330);
            while (count >= s2_dialogue.length) {
                // play cutscene
                // enter to continue to next stage
            }
        } else if (stage == 3) {
            // sun shooting minigame
            // reward drops, 'elixir of immortality'
        } else if (stage == 4) {
            // chang'e at home, activate ability
            // trying to find lost object
            // acccidentally stumbles upon elixir
        } else if (stage == 5) {
            // float up while dodging obstacles
        } else if (stage == 6) {
            // play as random kid setting up lantern
            // end by dialogue w/ houyi
        } else if (stage == 7) {
            // end 'credits' game complete
        }
    }

    public void keyPressed() {
        if (stage == 0) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 1;
            }
        } else if (stage == 1) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 2;
            }
        }
    }
}
