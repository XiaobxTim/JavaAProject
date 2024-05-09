package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;


public class CustomPanel extends ListenerPanel {
    private GridComponent[][] grids;

    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private int steps;
    private int score;
    private final int GRID_SIZE;

    public CustomPanel(int size,int COUNT) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.GRID_SIZE = size / COUNT;
        this.grids = new GridComponent[COUNT][COUNT];
        this.model = new GridNumber(COUNT, COUNT);
        initialGame();

    }

    public GridNumber getModel() {
        return model;
    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public void updateGridsNumber() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
            }
        }
        repaint();
    }


    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {
        if (model.gameEnd()) {
            JOptionPane.showMessageDialog(null,"You fail");
        }else {
            System.out.println("Click VK_RIGHT");
            this.model.moveRight();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=2048){
                JOptionPane.showMessageDialog(null,"You win");
            }
        }
    }
    @Override
    public void doMoveLeft() {
        if (model.gameEnd()) {
            JOptionPane.showMessageDialog(null,"You fail");
        }else {
            System.out.println("Click VK_Left");
            this.model.moveLeft();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=2048){
                JOptionPane.showMessageDialog(null,"You win");
            }
        }
    }
    @Override
    public void doMoveUp() {
        if (model.gameEnd()) {
            JOptionPane.showMessageDialog(null,"You fail");
        }else {
            System.out.println("Click VK_UP");
            this.model.moveUp();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=2048){
                JOptionPane.showMessageDialog(null,"You win");
            }
        }
    }
    @Override
    public void doMoveDown() {
        if (model.gameEnd()) {
            JOptionPane.showMessageDialog(null,"You fail");
        }else {
            System.out.println("Click VK_DOWN");
            this.model.moveDown();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=2048){
                JOptionPane.showMessageDialog(null,"You win");
            }
        }
    }

    public void afterMove() {
        if (!model.gameEnd()) {
            this.steps++;
            this.stepLabel.setText(String.format("Step: %d", this.steps));
            this.scoreLabel.setText(String.format("Score: %d", model.getScore()));
        }
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }
    public void setScoreLabel(JLabel scoreLabel){ this.scoreLabel = scoreLabel; }
    public void setMaxscoreLabel(JLabel maxscoreLabel){ this.maxscoreLabel = maxscoreLabel;}
}
