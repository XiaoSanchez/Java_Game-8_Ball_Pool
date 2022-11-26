/*
 * @Description: 
 * @Logo:                                                               ▄   ▄
 * ░██████╗██╗░░██╗░█████╗░░██╗░░░░░░░██╗███╗░░██╗  ░█████╗░░█████╗░██╗ █▀█▀█
 * ██╔════╝██║░░██║██╔══██╗░██║░░██╗░░██║████╗░██║  ██╔══██╗██╔══██╗██║ █▄█▄█
 * ╚█████╗░███████║███████║░╚██╗████╗██╔╝██╔██╗██║  ██║░░╚═╝███████║██║ ███  ▄▄
 * ░╚═══██╗██╔══██║██╔══██║░░████╔═████║░██║╚████║  ██║░░██╗██╔══██║██║ ████▐█ █
 * ██████╔╝██║░░██║██║░░██║░░╚██╔╝░╚██╔╝░██║░╚███║  ╚█████╔╝██║░░██║██║ ████   █
 * ╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═╝░░╚══╝  ░╚════╝░╚═╝░░╚═╝╚═╝ ▀▀▀▀▀▀▀
 * @Author: Shawn C
 * Copyright (c) 2022 by Shawn C., All Rights Reserved. 
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class BallGame extends JFrame {
    List<MyComponent> componentList = new ArrayList<>();
    List<MyComponent> removeList = new ArrayList<>();
    DeskComponent desk = new DeskComponent();
    BallComponent ballwhite = new BallComponent("ballWhite", 200, 200, 0, 0, this);
    BallComponent ballRed = new BallComponent("ballRed", 130, 300, 0, 0, this);
    BallComponent ballOrange = new BallComponent("ballOrange", 60, 400, 0, 0, this);
    DirectionComonent directionComonent = new DirectionComonent(this, ballwhite);
    static boolean isShow = false;
    Integer MY_WIDTH = 1056;
    Integer MY_HEIGHT = 501;
    static Integer score = 0;
    static String result = "0   分";
    Image offScreenImage = null;
    public BallGame() {
        ballwhite.setBall("img/ball.png");
        ballRed.setBall("img/ball_red.png");
        ballOrange.setBall("img/ball_orange.png");
        componentList.add(desk);
        componentList.add(ballwhite);
        componentList.add(ballRed);
        componentList.add(ballOrange);
    }
    public void launch() {
        setVisible(true);
        setSize(MY_WIDTH, MY_HEIGHT);
        setLocation(200, 200);
        setTitle("8 Ball Pool");
        while (true) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(MY_WIDTH, MY_HEIGHT);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(Color.darkGray);
        gImage.fillRect(0, 0, MY_WIDTH, MY_HEIGHT);
        for (MyComponent component : componentList) {
            component.paintSelf(gImage);
        }
        componentList.removeAll(removeList);
        gImage.setColor(Color.red);
        if (isShow) {
            directionComonent.paintSelf(gImage);
        }
        gImage.setColor(Color.red);
        gImage.setFont(new Font("Cursive", Font.BOLD, 30));
        gImage.drawString("Score: ", 890, 100);
        gImage.drawString(BallGame.result, 880, 200);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    public static void main(String[] args) {
        BallGame bg = new BallGame();
        bg.launch();
    }
}
