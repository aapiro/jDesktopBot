package com.example;

import com.opencsv.bean.CsvToBeanBuilder;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;

@QuarkusMain
public class Main {
    public static void main(String... args) {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        public static final int FIVE_SECONDS = 5000;

        @Override
        public int run(String... args) throws Exception {
           /* System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            return 0;*/
            Robot robot = new Robot();
//            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//            int width = gd.getDisplayMode().getWidth();
//            int height = gd.getDisplayMode().getHeight();
//            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
//            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

            while (true) {

                List<Tracking> beans = new CsvToBeanBuilder(new FileReader("mousemotion.csv"))
                        .withType(Tracking.class)
                        .build()
                        .parse();

                beans.forEach(point -> {
                    moveMouse(robot,Integer.parseInt(point.getHorizontal()),Integer.parseInt(point.getVertical()));
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                    Thread.sleep(5000);

            }

        }
        //
        // Move mouse at coordinates
        //


        public static void moveMouseRandom(Robot r, int x, int y){
            Random random = new Random();
            r.mouseMove(random.nextInt(x), random.nextInt(y));
        }
        public static void moveMouse(Robot r, int X, int Y){
            r.mouseMove(X, Y);
        }

        //
        // Click mouse button twice
        //

        public static void  doubleClickMouse(Robot r){
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            r.delay(1000);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }

        //
        // Click mouse buttons n - times
        //

        public static void  clickMouse(String button,Robot r, int number){
            int mouse;
            switch (button) {
                case "right": mouse = InputEvent.BUTTON3_DOWN_MASK;break;
                case "middle": mouse = InputEvent.BUTTON2_DOWN_MASK;break;
                case "left":
                default: mouse = InputEvent.BUTTON1_DOWN_MASK;break;
            }
            for(int i =0;i<number;i++){
                r.mousePress(mouse);
                r.delay(1000);
                r.mouseRelease(mouse);
                r.delay(1000);
            }
        }

        //
        // Press Enter
        //

        public static void  pressEnter(Robot r){
            r.delay(1000);
            r.keyPress(KeyEvent.VK_ENTER);
            r.delay(1000);
        }

        //
        // Press Tab
        //
        public static void  pressTab(Robot r){
            r.delay(1000);
            r.keyPress(KeyEvent.VK_TAB);
            r.delay(1000);
        }

        //
        // Copy CTRL + C
        //
        public static void copy(Robot r){
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_C);
            r.delay(1000);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_C);
        }

        //
        // Paste CTRL + V
        //
        public static void paste(Robot r){
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.delay(1000);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
        }

        //
        // Paste CTRL + V
        //
        public static void pasteText(String text, Robot r){
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            //Paste attached file paste
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
        }

        //
        // Select All CTRL + A
        //
        public static void  selectAll(Robot r){
            //Select all
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_A);
            r.delay(1000);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_A);
        }

        //
        // Select press buttons
        //
        public static void  pressButton(Robot r, String button, int number){
            for(int i =0;i<number;i++){
                r.delay(1000); //set the delay
                type(r, button);
                r.delay(1000); //set the delay
                type(r, button);
            }
        }

        public static void   type(Robot r, String character) {
            switch (character) {
                //letters
                case "a": r.keyPress(KeyEvent.VK_A); break;
                case "b": r.keyPress(KeyEvent.VK_B); break;
                case "c": r.keyPress(KeyEvent.VK_C); break;
                case "d": r.keyPress(KeyEvent.VK_D); break;
                case "e": r.keyPress(KeyEvent.VK_E); break;
                case "f": r.keyPress(KeyEvent.VK_F); break;
                case "g": r.keyPress(KeyEvent.VK_G); break;
                case "h": r.keyPress(KeyEvent.VK_H); break;
                case "i": r.keyPress(KeyEvent.VK_I); break;
                case "j": r.keyPress(KeyEvent.VK_J); break;
                case "k": r.keyPress(KeyEvent.VK_K); break;
                case "l": r.keyPress(KeyEvent.VK_L); break;
                case "m": r.keyPress(KeyEvent.VK_M); break;
                case "n": r.keyPress(KeyEvent.VK_N); break;
                case "o": r.keyPress(KeyEvent.VK_O); break;
                case "p": r.keyPress(KeyEvent.VK_P); break;
                case "q": r.keyPress(KeyEvent.VK_Q); break;
                case "r": r.keyPress(KeyEvent.VK_R); break;
                case "s": r.keyPress(KeyEvent.VK_S); break;
                case "t": r.keyPress(KeyEvent.VK_T); break;
                case "u": r.keyPress(KeyEvent.VK_U); break;
                case "v": r.keyPress(KeyEvent.VK_V); break;
                case "w": r.keyPress(KeyEvent.VK_W); break;
                case "x": r.keyPress(KeyEvent.VK_X); break;
                case "y": r.keyPress(KeyEvent.VK_Y); break;
                case "z": r.keyPress(KeyEvent.VK_Z); break;
                //special
                case "alt": r.keyPress(KeyEvent.VK_ALT); break;
                case "tab": r.keyPress(KeyEvent.VK_TAB); break;
                case "enter": r.keyPress(KeyEvent.VK_ENTER); break;
                case "shift": r.keyPress(KeyEvent.VK_SHIFT); break;
                case "windows": r.keyPress(KeyEvent.VK_WINDOWS); break;
                case "control": r.keyPress(KeyEvent.VK_CONTROL); break;
                case "open_bracket": r.keyPress(KeyEvent.VK_OPEN_BRACKET); break;//[
                case "escape": r.keyPress(KeyEvent.VK_ESCAPE); break;//[

                default:
                    throw new IllegalArgumentException("Cannot type character " + character);
            }
        }
    }
}