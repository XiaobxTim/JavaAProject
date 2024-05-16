package controller;

import model.GridNumber;
import view.EntertainingFrame;
import view.EntertainingPanel;
import view.GamePanel;
import view.SelectModelForVistor;

import javax.swing.*;

public class EntertainingController {
    private EntertainingPanel view;
    private GridNumber model;

    public EntertainingController(EntertainingPanel view, GridNumber model) {
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
    //todo: add other methods such as loadGame, saveGame...
}
