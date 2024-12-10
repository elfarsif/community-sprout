package org.frank.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener {
    GamePanel gp;

    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        System.out.println("Mouse Dragged");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
//        System.out.println("Mouse Moved");
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("Mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
