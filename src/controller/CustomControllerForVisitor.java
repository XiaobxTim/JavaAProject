package controller;

import model.GridNumber;
import view.CustomPanelForVisitor;
import view.SelectModelForVistor;

import javax.swing.*;

public class CustomControllerForVisitor {
    private CustomPanelForVisitor view;
    private GridNumber model;
    public CustomControllerForVisitor(CustomPanelForVisitor view, GridNumber model) {
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
