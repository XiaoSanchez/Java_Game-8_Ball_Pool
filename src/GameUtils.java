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
import java.math.BigDecimal;
import java.util.HashMap;
public class GameUtils {
    public static HashMap map = new HashMap();
    public static double getAngle(double startx, double starty, double endx, double endy) {
        double tempx = endx - startx;
        double tempy = endy - starty;
        double z = Math.sqrt(tempx * tempx + tempy * tempy);
        double angle = (double) (Math.asin(Math.abs(tempy) / z));
        if (tempx > 0 && tempy < 0) {
            angle = 6.28 - angle;
        } else if (tempx < 0 && tempy < 0) {
            angle = 3.14 + angle;
        } else if (tempx < 0 && tempy > 0) {
            angle = 3.14 - angle;
        } else if (tempx > 0 && tempy > 0) {
        }
        return angle;
    }
    public static double xab(double x, double y, double bx, double by) {
        double xab = 0;
        x = x - bx;
        y = y - by;
        if (y > 0) {
            xab = Math.atan2(y, x);
        } else if (y < 0) {
            xab = Math.atan2(y, x) + 6.28;
        } else if (y == 0 && x > 0) {
            xab = 0;
        } else if (y == 0 && x < 0) {
            xab = 3.14;
        }
        BigDecimal b = new BigDecimal(xab);
        double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
    public static double fab(double m, double xab) {
        double fab = m - xab;
        if (fab > 3.14) {
            fab = fab - 6.28;
        } else if (fab <= 3.14 && fab >= -3.14) {
            fab = fab;
        } else if (fab < -3.14) {
            fab = fab + 6.28;
        }
        BigDecimal b = new BigDecimal(fab);
        double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
}
