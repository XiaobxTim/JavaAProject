package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;


public class AdventurePanel extends ListenerPanel {
    private final int COUNT = 4;
    private GridComponent[][] grids;

    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private int steps;
    private int score;
    private final int GRID_SIZE;
    private int AimScore=30;
    private int CurrentScore;

    public AdventurePanel(int size) {
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
            if (model.getScore()>=AimScore){
                CurrentScore=model.getScore();
                setVisible(false);
                AdventureModeForVisitor adventureModeForVisitor=new AdventureModeForVisitor(700,500);
                adventureModeForVisitor.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null,"You fail");
            }
        }else {
            System.out.println("Click VK_RIGHT");
            this.model.moveRight();
            this.updateGridsNumber();
            this.afterMove();
        }
    }
    @Override
    public void doMoveLeft() {
        if (model.gameEnd()) {
            if (model.getScore()>=AimScore){
                CurrentScore=model.getScore();
                setVisible(false);
                AdventureModeForVisitor adventureModeForVisitor=new AdventureModeForVisitor(700,500);
                adventureModeForVisitor.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null,"You fail");
            }
        }else {
            System.out.println("Click VK_Left");
            this.model.moveLeft();
            this.updateGridsNumber();
            this.afterMove();
        }
    }
    @Override
    public void doMoveUp() {
        if (model.gameEnd()) {
            if (model.getScore()>=AimScore){
                CurrentScore=model.getScore();
                setVisible(false);
                AdventureModeForVisitor adventureModeForVisitor=new AdventureModeForVisitor(700,500);
                adventureModeForVisitor.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null,"You fail");
            }
        }else {
            System.out.println("Click VK_UP");
            this.model.moveUp();
            this.updateGridsNumber();
            this.afterMove();
        }
    }
    @Override
    public void doMoveDown() {
        if (model.gameEnd()) {
            if (model.getScore()>=AimScore){
                CurrentScore=model.getScore();
                setVisible(false);
                AdventureModeForVisitor adventureModeForVisitor=new AdventureModeForVisitor(700,500);
                adventureModeForVisitor.setVisible(true);
                this.model.setScore(CurrentScore);
            }else {
                JOptionPane.showMessageDialog(null,"You fail");
            }
        }else {
            System.out.println("Click VK_DOWN");
            this.model.moveDown();
            this.updateGridsNumber();
            this.afterMove();
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
