package controller;

import model.GridNumber;
import view.*;

import javax.swing.*;


/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class TimeLimitController {
    private TimeLimitPanel view;
    private GridNumber model;


    public TimeLimitController(TimeLimitPanel view, GridNumber model) {
        this.view = view;
        this.model = model;

    }
    public void restartGame() {
        SelectModel selectModel=new SelectModel(700,500);
        selectModel.setVisible(false);
    }
    public void restartGameForVisitor() {
        SelectModelForVistor selectModelForVistor=new SelectModelForVistor(700,500);
        selectModelForVistor.setVisible(true);
    }
    //todo: add other methods such as loadGame, saveGame...

}
