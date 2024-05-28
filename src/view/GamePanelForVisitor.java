package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanelForVisitor extends ListenerPanel {
    private final int COUNT = 4;
    private final int DELAY = 100;
    private GridComponent[][] grids;

    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private int steps;
    private int score;
    private final int GRID_SIZE;
    public GamePanelForVisitor(int size){

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
    public void updateScoreAndStep() {
        model.setScore(model.getS());
        this.stepLabel.setText(String.format("Step: %d", this.steps-1));
        this.scoreLabel.setText(String.format("Score: %d", model.getScore()));
    }

    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureForVisitor failureFrameForVisitor=new FailureForVisitor(400,500,model.getScore());
            failureFrameForVisitor.setVisible(true);
        }else {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            System.out.println("Click VK_RIGHT");
            final boolean[] isFirst = {true};
            Timer timer = new Timer(DELAY, e -> {
                boolean moved = getModel().moveRightStep(isFirst[0]);
                isFirst[0] = false;
                updateGridsNumber();
                if (!moved) {
                    getModel().addNewPiece("Right");
                    this.updateGridsNumber();
                    this.afterMove();
                    int number=model.FindMaxNumber();
                    if (number>=model.getAim()){
                        JFrame gameframe = findParentFrame(this);
                        gameframe.setVisible(false);
                        SuccessForVisitor successForVisitor=new SuccessForVisitor(400,500,model.getScore());
                        successForVisitor.setVisible(true);
                    }
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
        }
    }
    @Override
    public void doMoveLeft() {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureForVisitor failureFrameForVisitor=new FailureForVisitor(400,500,model.getScore());
            failureFrameForVisitor.setVisible(true);
        }else {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            System.out.println("Click VK_Left!");
            final boolean[] isFirst = {true};
            Timer timer = new Timer(DELAY, e -> {
                System.out.println("`!");
                boolean moved = getModel().moveLeftStep(isFirst[0]);
                isFirst[0] = false;
                updateGridsNumber();
                if (!moved) {
                    System.out.println("...");
                    getModel().addNewPiece("Left");
                    this.updateGridsNumber();
                    System.out.println("here");
                    this.afterMove();
                    int number=model.FindMaxNumber();
                    if (number>=model.getAim()){
                        JFrame gameframe = findParentFrame(this);
                        gameframe.setVisible(false);
                        SuccessForVisitor successForVisitor=new SuccessForVisitor(400,500,model.getScore());
                        successForVisitor.setVisible(true);
                    }
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();

        }
    }
    @Override
    public void doMoveUp() {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureForVisitor failureFrameForVisitor=new FailureForVisitor(400,500,model.getScore());
            failureFrameForVisitor.setVisible(true);
        }else {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            System.out.println("Click VK_UP");
            final boolean[] isFirst = {true};
            Timer timer = new Timer(DELAY, e -> {
                boolean moved = getModel().moveUpStep(isFirst[0]);
                isFirst[0] = false;
                updateGridsNumber();
                if (!moved) {
                    getModel().addNewPiece("Up");
                    this.updateGridsNumber();
                    this.afterMove();
                    int number=model.FindMaxNumber();
                    if (number>=model.getAim()){
                        JFrame gameframe = findParentFrame(this);
                        gameframe.setVisible(false);
                        SuccessForVisitor successForVisitor=new SuccessForVisitor(400,500,model.getScore());
                        successForVisitor.setVisible(true);
                    }
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
        }
    }
    @Override
    public void doMoveDown() {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureForVisitor failureFrameForVisitor=new FailureForVisitor(400,500,model.getScore());
            failureFrameForVisitor.setVisible(true);
        }else {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            System.out.println("Click VK_DOWN");
            final boolean[] isFirst = {true};
            Timer timer = new Timer(DELAY, e -> {
                boolean moved = getModel().moveDownStep(isFirst[0]);
                isFirst[0] = false;
                updateGridsNumber();
                if (!moved) {
                    getModel().addNewPiece("Down");
                    this.updateGridsNumber();
                    this.afterMove();
                    int number=model.FindMaxNumber();
                    if (number>=model.getAim()){
                        JFrame gameframe = findParentFrame(this);
                        gameframe.setVisible(false);
                        SuccessForVisitor successForVisitor=new SuccessForVisitor(400,500,model.getScore());
                        successForVisitor.setVisible(true);
                    }
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
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
    private JFrame findParentFrame(Component comp) {
        if (comp == null) {
            return null;
        }
        if (comp instanceof JFrame) {
            return (JFrame) comp;
        }
        return findParentFrame(comp.getParent());
    }
}
