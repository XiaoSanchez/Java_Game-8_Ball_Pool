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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class DirectionComonent implements MyComponent {
    private JFrame frame;
    private double start_x;
    private double start_y;
    private double end_x;
    private double end_y;
    private Double degree;
    private BallComponent whiteBall;
    public DirectionComonent(JFrame frame, BallComponent whiteBall) {
        this.frame = frame;
        this.whiteBall = whiteBall;
        this.frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == 1) {
                    BallGame.isShow = true;
                    start_x = whiteBall.getX() + 15;
                    System.out.println(start_x);
                    start_y = whiteBall.getY() + 15;
                    System.out.println(start_y);
                    end_x = e.getX();
                    end_y = e.getY();
                    System.out.println(end_x);
                    System.out.println(end_y);
                    degree = GameUtils.getAngle(start_x, start_y, end_x, end_y);
                    whiteBall.setDegree(degree);
                }
                if (e.getButton() == 3) {
                    BallGame.isShow = false;
                    whiteBall.setSpeed(20);
                }
            }
        });
    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public double getStart_x() {
        return start_x;
    }
    public void setStart_x(double start_x) {
        this.start_x = start_x;
    }
    public double getStart_y() {
        return start_y;
    }
    public void setStart_y(double start_y) {
        this.start_y = start_y;
    }
    public double getEnd_x() {
        return end_x;
    }
    public void setEnd_x(double end_x) {
        this.end_x = end_x;
    }
    public double getEnd_y() {
        return end_y;
    }
    public void setEnd_y(double end_y) {
        this.end_y = end_y;
    }
    public Double getDegree() {
        return degree;
    }
    public void setDegree(Double degree) {
        this.degree = degree;
    }
    public BallComponent getWhiteBall() {
        return whiteBall;
    }
    public void setWhiteBall(BallComponent whiteBall) {
        this.whiteBall = whiteBall;
    }
    @Override
    public void paintSelf(Graphics g) {
        g.drawLine((int) start_x, (int) start_y, (int) end_x, (int) end_y);
    }
}
