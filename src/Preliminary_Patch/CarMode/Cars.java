package Preliminary_Patch.CarMode;

import Preliminary_Patch.Vehicle;

import javax.swing.*;
import java.awt.*;

public class Cars extends Vehicle {

    public Cars(int x, int y, ImageIcon imageIcon) {
        super(x, y, imageIcon);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
