package controller;

import model.GridNumber;
import view.GamePanel;
import view.GamePanelForVisitor;
import view.SelectModelForVistor;

import javax.swing.*;

public class GameControllerForVisitor {
    private GamePanelForVisitor view;
    private GridNumber model;
    public GameControllerForVisitor(GamePanelForVisitor view,GridNumber model){
        this.view = view;
        this.model = model;
    }
    public void restartGame(JFrame jFrame) {
        jFrame.setVisible(true);
    }
    public void restartGameForVisitor() {
        SelectModelForVistor selectModelForVistor=new SelectModelForVistor(700,500);
        selectModelForVistor.setVisible(true);
    }
}
