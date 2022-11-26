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
import java.awt.*;
public class BallComponent implements MyComponent {
    private Image ball;
    private String name;
    private double x;
    private double y;
    private double speed;
    private double degree;
    private BallGame frame;
    public BallComponent() {
    }
    public BallComponent(String name, double x, double y, double speed, double degree, BallGame frame) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.degree = degree;
        this.frame = frame;
    }
    public Image getBall() {
        return ball;
    }
    public void setBall(String ball) {
        this.ball = Toolkit.getDefaultToolkit().getImage(ball);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getDegree() {
        return degree;
    }
    public void setDegree(double degree) {
        this.degree = degree;
    }
    public BallGame getFrame() {
        return frame;
    }
    public void setFrame(BallGame frame) {
        this.frame = frame;
    }
    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, 30, 30);
    }
    public void checkList(java.util.List<MyComponent> list) {
        for (MyComponent ball : list) {
            if (ball instanceof BallComponent) {
                if (ball == this) {
                    continue;
                }
                BallComponent temp = ((BallComponent) ball);
                if (this.getRectangle().intersects(temp.getRectangle())) {
                    if (GameUtils.map.containsKey(this) && GameUtils.map.containsKey(temp)) {
                        continue;
                    } else {
                        double xab = GameUtils.xab(temp.getX(), temp.getY(), this.getX(), this.getY());
                        temp.setDegree(xab);
                        double fab = GameUtils.fab(this.getDegree(), xab);
                        if (fab > 0) {
                            this.setDegree(xab + 1.57);
                        } else if (fab < 0) {
                            this.setDegree(xab - 1.57);
                        } else if (fab == 0) {
                            this.setDegree(xab);
                        }
                        double Speed1 = Math.abs(Math.cos(fab)) * this.speed;
                        double Speed2 = Math.abs(Math.sin(fab)) * this.speed;
                        this.setSpeed(Speed2);
                        temp.setSpeed(Speed1);
                        GameUtils.map.put(this, temp);
                        GameUtils.map.put(temp, this);
                    }
                } else {
                    if (GameUtils.map.containsKey(this) && GameUtils.map.containsKey(temp)) {
                        GameUtils.map.remove(this);
                        GameUtils.map.remove(temp);
                    }
                }
            }
        }
    }
    public boolean isScore(BallComponent ball) {
        if (x < 45 && y < 80) {
            System.out.println("左上角进球");
            return true;
        }
        if (x < 45 && y + 30 > 450) {
            System.out.println("左下角进球");
            return true;
        }
        if (x + 30 > 800 && y < 80) {
            System.out.println("右上角进球");
            return true;
        }
        if (x + 30 > 800 && y + 30 > 450) {
            System.out.println("右下角进球");
            return true;
        }
        if (x > 400 && x + 30 < 440 && y < 65) {
            System.out.println("上中进球");
            return true;
        }
        if (x > 400 && x + 30 < 440 && y > 428) {
            System.out.println("下中进球");
            return true;
        }
        return false;
    }
    @Override
    public void paintSelf(Graphics g) {
        System.out.println("Ball Drawed...");
        g.drawImage(ball, (int) x, (int) y, null);
        if (speed > 0) {
            speed -= 0.15;
            x += speed * Math.cos(degree);
            y += speed * Math.sin(degree);
        }
        if (isScore(this)) {
            this.frame.removeList.add(this);
            if (this == this.frame.ballwhite) {
                BallGame.result = "GAME OVER";
            } else {
                BallGame.score++;
                BallGame.result = BallGame.score + " pt.";
            }
        }
        if (x < 30 || x > 800) {
            degree = Math.PI - degree;
        }
        if (y < 60 || y > 440) {
            degree = -degree;
        }
        this.checkList(this.frame.componentList);
    }
}
