/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysketch;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Ivy Yu
 */
public class Sketch extends PApplet {
    // initialize variables
    // characters
    private Character c1;
    private Character c2;
    private Animal a;
    
    // backgrounds
    private PImage bg;
    private PImage bg1;
    private PImage bg2;
    private PImage menu;
    
    // scene 2 dialogue
    private String[][] s2_dialogue = {
        {
            "Emperor: Houyi, as you know, the heat of the 10 suns have become increasingly unbearable.",
            "A great archer such as yourself must understand the threat it serves to humanity."
        },
        {
            "Houyi: I understand, Your Majesty.",
            "The people suffer every day beneath the scorching light."
        },
        {
            "Emperor: Then I entrust this task to you. The nation's future will be in your hands.",
            "Save humanity from the ten suns with haste and you will be rewarded generously."
        }
    };
    // dialogue count for scene 2
    private int count = 0;
    // check whether e is held to advance dialogue for scene 2
    private boolean eHeld = false;
    // boolean for whether suns are created for scene 2
    private boolean sunsCreated = false;
    
    // stage number static variable
    static int stage = 0;
    
    // display character names in stage 1
    private boolean showInfo = false;
    
    // initialize font variable
    private PFont font;
    
    // initialize item object
    private Item moon;
    
    // track time throughout game
    private long startTime;
    private int elapsedSeconds;
    
    // create array to store suns
    public Item[] suns = new Item[10];
    
    /**
    * The method is called once at the start of the program.
    * Used to configure the sketch size and load all background images
    * before the game begins running.
    */
    public void settings(){
        // load backgrounds for each segment of gameplay
        bg = loadImage("images/background.jpg");
        bg1 = loadImage("images/background1.png");
        bg2 = loadImage("images/background2.png");
        menu = loadImage("images/menu.jpg");
        // set the size of the game window (width, height)
        size(600, 350);
    }
    
    /**
    * The method runs once at the start of the program.
    * Used to initialize game objects, fonts, etc.
    * before the game begins running.
    */
    public void setup(){
	// set background colour using R,G,B (https://rgbcolorpicker.com/)
        background(216, 220, 230);
        
        // set the font used throughout the game
        font = createFont("fonts/pixel_font.ttf", 12);
        textFont(font);
        
        // create objects
        a = new Animal(this, 50, 200, "Rabbit", "null", "null", "images/rabbit.png");
        c1 = new Human(this, 300, 250, "Chang'e", "Moonlight", a, "images/chang-e.png");
        c2 = new Human(this, 400, 200, "Houyi", "Sharpshooter", a, "images/houyi.png");
        moon = new Item(this, 300, 125, "Moon", "images/moon.png");
        
        // timer
        startTime = millis();
        loadGame();
    }
    
    /**
        * Displays different on-screen messages based on the input string.
        *
        * @param select determines which message to display:
        *               "enter" shows instructions to proceed to the next stage,
        *               "controls" shows movement controls,
        *               "e" shows dialogue interaction instructions.
        */
    public void msg(String select) {
        if (select.equals("enter")) {
            text("press 'enter' to proceed stage", 20, 50);
        } else if (select.equals("controls")) {
            text("use arrow keys keys to move character", 20, 25);
        } else if (select.equals("e")) {
            text("press 'e' to advance dialogue", 360, 260);
        }
    }
    
    /**
    * Draws the current dialogue line on the screen.
    * Each entry in the current dialogue array is displayed
    * on a separate line with spacing between them.
    */
    private void drawDialogue() {
        // check if within bounds of dialogue array
        if (count < s2_dialogue.length) {
            // loop through each line of dialogue
            for (int i = 0; i < s2_dialogue[count].length; i++) {
                text(s2_dialogue[count][i], 5, 300 + i * 30);
            }
        }
    }
    
    /**
    * Handles mouse click events in the game for stage 3.
    * Checks if the player clicks on any sun object.
    * If a sun is clicked, it is removed (set to null).
    */
    public void mousePressed() {
        // loop through all sun objects
        for (int i = 0; i < suns.length; i++) {
            // check if sun exists and if it was clicked
            if (suns[i] != null && suns[i].isClicked(mouseX, mouseY)) {
                suns[i] = null; // remove sun from game
                // exit loop
                break;
            }
        }
    }
    
    /**
    * Saves the current game state to a file.
    * Writes a simple confirmation message into "save.txt".
    * If saving fails, an error message is printed to the console.
    */
    public void saveGame() {
        try {
            // create a writer to write to save file
            PrintWriter writer = new PrintWriter("save.txt");
            writer.println("Game Saved"); // save confirmation
            writer.close(); // close file
            println("Game Saved");
            // error handling
        } catch (Exception e) {
            println("Save failed");
        }
    }
    
    /**
    * Loads the saved game state from a file.
    * Reads the saved stage value from "save.txt" and restores the game progress.
    * If the file cannot be read, an error message is displayed.
    */
    public void loadGame() {
        try {
            // open save file to read
            Scanner fileInput = new Scanner(new File("save.txt"));
            // check data
            if (fileInput.hasNext()) {
                stage = Integer.parseInt(fileInput.nextLine());
            }
            fileInput.close(); // close file
            println("Game loaded: stage " + stage);
            // error handling
            } catch (IOException e) {
                println("Error loading file");
            }
    }
    
    /**
    * Displays an in-game timer showing how long the player has been playing.
    * The timer is calculated using millis() and converted into minutes and seconds.
    */
    public void drawTimer() {
        // calculate total elapsed time in seconds since game started
        elapsedSeconds = (millis() - (int)startTime) / 1000;

        // convert seconds into minutes
        int minutes = elapsedSeconds / 60;
        int seconds = elapsedSeconds % 60;
        
        // display formatted time in minutes:seconds format
        fill(255);
        text("Time: " + nf(minutes, 2) + ":" + nf(seconds, 2), 500, 20);
    }
    
    /**
    * The main game loop that runs continuously.
    * Responsible for rendering the correct game stage,
    * updating objects, handling input, and drawing UI elements.
    * This method is called automatically every frame.
    */
    public void draw(){
        
        if (stage == 0) {
            // set main menu
            image(menu, 0, 0, width, height);
            text("myth of the moon festival", 20, 50);
            text("press 'enter' to begin", 20, 80);
        } else if (stage == 1) {
            // set background image, text
            background(216, 220, 230);
            image(bg, 0, 0, width, height);
            // add characters
            a.draw();
            c2.draw();
            c1.draw();
            fill(255);
            // add instructions
            msg("controls");
            msg("enter");
            // check for collision
            if (c1.isCollidingWith(a)) {
                fill(255);
                this.text("White Rabbit (pet)", a.x, a.y); // display information if collision detected
//                if (keyPressed) {
//                    if (key == 'e' || key == 'E') {
                        // dialogue box
                        strokeWeight(1);
                        stroke(255);
                        fill(0);
                        rect(0, 300, width-1, 50);
                        fill(255);
                        text("White Rabbit: [intelligible bunny noises]", 5, 330);
//                    }
//                }
            }
            // check for collision
            if (c1.isCollidingWith(c2)) {
                fill(255);
                this.text("Houyi (husband)", c2.x, c2.y); // display information if collision detected
//                if (keyPressed) {
//                    if (key == 'e' || key == 'E') {
                        // dialogue box
                        strokeWeight(1);
                        stroke(255);
                        fill(0);
                        rect(0, 300, width-1, 50);
                        fill(255);
                        text("Houyi: Greetings Chang'e. The Emperor has requested for my presence.", 5, 330);
//                    }
//                }
            }
        
        // check if key is pressed for user controls
        // change sprite direction to correspond
        if (keyPressed) {
            if (keyCode == LEFT) {
                c1.move(-3, 0);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e_L.png");
            } else if (keyCode == RIGHT) {
                c1.move(3, 0);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e_R.png");
            } else if (keyCode == UP) {
                c1.move(0, -3);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e_B.png");
            } else if (keyCode == DOWN) {
                c1.move(0, 3);
                c1 = new Human(this, c1.x, c1.y, "Chang'e", "Spawn", a, "images/chang-e.png");
//            } else if (key == 'a' || key == 'A') {
//                c1.move(-3, 0);
//            } else if (key == 'd' || key == 'D') {
//                c1.move(3, 0);
//            } else if (key == 'w' || key == 'W') {
//                c1.move(0, -3);
//            } else if (key == 's' || key == 'S') {
//                c1.move(0, 3);
            }
        }
        } else if (stage == 2) {
            // set background
            image(bg1, 0, 0, width, height);
            
            // add character
            c2.draw();
            
            // add instructions
            fill(0);
            msg("controls");
            msg("enter");
            msg("e");
            
            // create dialogue box
            strokeWeight(1);
            stroke(255);
            rect(0, 270, width-1, 80);
            fill(255);
            
            // initiate dialogue sequence
            drawDialogue();
            
            // check if key is pressed for user controls
            // change sprite direction to correspond
            if (keyPressed) {
                if (keyCode == LEFT) {
                    c2.move(-3, 0);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi_L.png");
                } else if (keyCode == RIGHT) {
                    c2.move(3, 0);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi_R.png");
                } else if (keyCode == UP) {
                    c2.move(0, -3);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi_B.png");
                } else if (keyCode == DOWN) {
                    c2.move(0, 3);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi.png");
                }
                
                // use e to proceed dialogue by checking if pressed
                if ((key == 'e' || key == 'E') && !eHeld) {
                    if (count < s2_dialogue.length - 1) {
                        count++;
                        System.out.println("count = " + count); // print count
                    }
                    eHeld = true;
                }
            }
            // change to false if not pressed to prevent it from automatically running all dialogue immediately
            if (!keyPressed) {
                eHeld = false;
            }
            
        } else if (stage == 3) {
            // set background
            image(bg2, 0, 0, width, height);
            
            // draw suns at random coordinates
            if (!sunsCreated) {
                for (int i = 0; i < suns.length; i++) {
                    int randX = (int) random(20, width - 50);
                    int randY = (int) random(20, 220); // keeps it above dialogue box
                    // each sun object is an item
                    suns[i] = new Item(this, randX, randY, "Sun", "images/sun.png");
                }
                // after complete, variable is true
                sunsCreated = true;
            }
            
            //  check each sun and draws if not null
            for (int i = 0; i < suns.length; i++) {
                if (suns[i] != null) {
                    image(suns[i].image, suns[i].x, suns[i].y);
                }
            }
            
            // sun counter
            int sunsLeft = 0;
            
            // cycles through array to check suns
            for (int i = 0; i < suns.length; i++) {
                if (suns[i] != null) {
                    sunsLeft++; // increments
                }
            }
            
            // add instructions
            fill(0);
            text("Suns Remaining: " + sunsLeft, 20, 20);
            msg("enter");
            
            // automatically proceed if complete
            if (sunsLeft == 0) {
                stage = 4;
            }
            
        } else if (stage == 4) {
            // set background
            image(bg1, 0, 0, width, height);
            // add instructions
            fill(0);
            msg("controls");
            msg("enter");
            // add character
            c2.draw();
            // dialogue box
            strokeWeight(1);
            stroke(255);
            fill(0);
            rect(0, 300, width-1, 50);
            fill(255);
            text("[elixir of immortality obtained]", 5, 330);
            
            // check if key is pressed for user controls
            // change sprite direction to correspond
            if (keyPressed) {
                if (keyCode == LEFT) {
                    c2.move(-3, 0);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi_L.png");
                } else if (keyCode == RIGHT) {
                    c2.move(3, 0);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi_R.png");
                } else if (keyCode == UP) {
                    c2.move(0, -3);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi_B.png");
                } else if (keyCode == DOWN) {
                    c2.move(0, 3);
                    c2 = new Human(this, c2.x, c2.y, "Houyi", "Spawn", a, "images/houyi.png");
                }
            }
        } else if (stage == 5) {
            // set background
            image(bg, 0, 0, width, height);
            // add characters
            a.draw();
            c1.draw();
            // move characters upward
            c1.y -= 2;
            a.y -= 1;
            // dialogue box
            strokeWeight(1);
            stroke(255);
            fill(0);
            rect(0, 300, width-1, 50);
            fill(255);
            text("A day later, Chang'e's curiosity gets the best of her and she consumes the elixir.", 5, 330);
            
            // automatically proceed to next stage if both sprites reach the top
            if (c1.y < -50 && a.y < -50) {
                stage = 6;
            }
        } else if (stage == 6) {
            // set background
            image(menu, 0, 0, width, height);
            image(moon.image, moon.x, moon.y); // add moon object (item)
            
            // dialogue box
            strokeWeight(1);
            stroke(255);
            fill(0);
            rect(0, 300, width-1, 50);
            fill(255);
            text("They reside on the moon for the rest of eternity as punishment.", 5, 330);
        
            // option to save game
            text("press 's' to save game", 20, 50);
        
            // press 's' to save game
            if (keyPressed) {
                if (key == 's' || key == 'S') {
                    saveGame(); // method used to save game
                }
        }
        
            // timer disp;layed at the very end
            drawTimer();
        }
    }
    
    // check if key pressed to advance each stage ('enter')
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
        } else if (stage == 2) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 3;
            }
        } else if (stage == 3) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 4;
            }
        } else if (stage == 4) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 5;
            }
        } else if (stage == 5) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 6;
            }
        } else if (stage == 6) {
            if (keyCode == ENTER) {
                // proceed to next stage
                stage = 7;
            }
        }
    }
}
