package controller;

import model.GridNumber;
import view.CustomPanel;
import view.GameFrame;
import view.GamePanel;
import view.SelectModelForVistor;

import javax.swing.*;


/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class CustomController {
    private CustomPanel view;
    private GridNumber model;


    public CustomController(CustomPanel view, GridNumber model) {
        this.view = view;
        this.model = model;

    }
    public void restartGame() {
        model.initialNumbers();
        view.updateGridsNumber();
        view.initialGame();
        GameFrame gameFrame = new GameFrame(700, 500);
        gameFrame.setVisible(true);
    }
    public void restartGameForVisitor() {
        SelectModelForVistor selectModelForVistor=new SelectModelForVistor(700,500);
        selectModelForVistor.setVisible(true);
    }
    //todo: add other methods such as loadGame, saveGame...

}
