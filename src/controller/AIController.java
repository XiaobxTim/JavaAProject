package controller;

import model.GridNumber;
import view.AIPanel;
import view.SelectModelForVistor;

import javax.swing.*;

public class AIController {
    private AIPanel view;
    private GridNumber model;


    public AIController(AIPanel view, GridNumber model) {
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
