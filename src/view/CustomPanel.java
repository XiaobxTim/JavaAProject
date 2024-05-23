package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class CustomPanel extends ListenerPanel {
    private GridComponent[][] grids;
    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private int steps;
    private int score;
    private final int GRID_SIZE;
    private String account;

    public CustomPanel(int size,int COUNT,String account) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.GRID_SIZE = size / COUNT;
        this.grids = new GridComponent[COUNT][COUNT];
        this.model = new GridNumber(COUNT, COUNT);
        initialGame();
        this.account=account;
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
    public void doMoveRight() throws IOException {
        if (model.gameEnd()) {
            File CustomFile = new File("src/" + account + "_CustomMode.txt");
            if (CustomFile.exists()){
                try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_CustomMode.txt";
                List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                Collections.sort(numbers,Collections.reverseOrder());
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                    for (Integer num : numbers) {
                        writer.write(num.toString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureFrame failureFrame=new FailureFrame(400,500,model.getScore(),account);
            failureFrame.setVisible(true);
        }else {
            System.out.println("Click VK_RIGHT");
            this.model.moveRight();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=model.getAim()){
                File CustomFile = new File("src/" + account + "_CustomMode.txt");
                if (CustomFile.exists()){
                    try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                        fileWriter.write(Integer.toString(model.getScore()));
                        fileWriter.write(System.lineSeparator());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    String filePath = "src/" + account + "_ClassicMode.txt";
                    List<Integer> numbers = null;
                    try {
                        numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Collections.sort(numbers,Collections.reverseOrder());
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                        for (Integer num : numbers) {
                            writer.write(num.toString());
                            writer.newLine();
                        }
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
                JFrame gameframe = findParentFrame(this);
                gameframe.setVisible(false);
                SuccessFrame successFrame=new SuccessFrame(400,500,model.getScore(),account);
                successFrame.setVisible(true);
            }
        }
    }
    @Override
    public void doMoveLeft() throws IOException {
        if (model.gameEnd()) {
            File CustomFile = new File("src/" + account + "_CustomMode.txt");
            if (CustomFile.exists()){
                try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_CustomMode.txt";
                List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                Collections.sort(numbers,Collections.reverseOrder());
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                    for (Integer num : numbers) {
                        writer.write(num.toString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureFrame failureFrame=new FailureFrame(400,500,model.getScore(),account);
            failureFrame.setVisible(true);
        }else {
            System.out.println("Click VK_Left");
            this.model.moveLeft();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=model.getAim()){
                File CustomFile = new File("src/" + account + "_CustomMode.txt");
                if (CustomFile.exists()){
                    try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                        fileWriter.write(Integer.toString(model.getScore()));
                        fileWriter.write(System.lineSeparator());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    String filePath = "src/" + account + "_ClassicMode.txt";
                    List<Integer> numbers = null;
                    try {
                        numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Collections.sort(numbers,Collections.reverseOrder());
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                        for (Integer num : numbers) {
                            writer.write(num.toString());
                            writer.newLine();
                        }
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
                JFrame gameframe = findParentFrame(this);
                gameframe.setVisible(false);
                SuccessFrame successFrame=new SuccessFrame(400,500,model.getScore(),account);
                successFrame.setVisible(true);
            }
        }
    }
    @Override
    public void doMoveUp() throws IOException {
        if (model.gameEnd()) {
            File CustomFile = new File("src/" + account + "_CustomMode.txt");
            if (CustomFile.exists()){
                try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_CustomMode.txt";
                List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                Collections.sort(numbers,Collections.reverseOrder());
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                    for (Integer num : numbers) {
                        writer.write(num.toString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureFrame failureFrame=new FailureFrame(400,500,model.getScore(),account);
            failureFrame.setVisible(true);
        }else {
            System.out.println("Click VK_UP");
            this.model.moveUp();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=model.getAim()){
                File CustomFile = new File("src/" + account + "_CustomMode.txt");
                if (CustomFile.exists()){
                    try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                        fileWriter.write(Integer.toString(model.getScore()));
                        fileWriter.write(System.lineSeparator());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    String filePath = "src/" + account + "_ClassicMode.txt";
                    List<Integer> numbers = null;
                    try {
                        numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Collections.sort(numbers,Collections.reverseOrder());
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                        for (Integer num : numbers) {
                            writer.write(num.toString());
                            writer.newLine();
                        }
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
                JFrame gameframe = findParentFrame(this);
                gameframe.setVisible(false);
                SuccessFrame successFrame=new SuccessFrame(400,500,model.getScore(),account);
                successFrame.setVisible(true);
            }
        }
    }
    @Override
    public void doMoveDown() throws IOException {
        if (model.gameEnd()) {
            File CustomFile = new File("src/" + account + "_CustomMode.txt");
            if (CustomFile.exists()){
                try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_CustomMode.txt";
                List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                Collections.sort(numbers,Collections.reverseOrder());
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                    for (Integer num : numbers) {
                        writer.write(num.toString());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JFrame gameframe = findParentFrame(this);
            gameframe.setVisible(false);
            FailureFrame failureFrame=new FailureFrame(400,500,model.getScore(),account);
            failureFrame.setVisible(true);
        }else {
            System.out.println("Click VK_DOWN");
            this.model.moveDown();
            this.updateGridsNumber();
            this.afterMove();
            int number=model.FindMaxNumber();
            if (number>=model.getAim()){
                File CustomFile = new File("src/" + account + "_CustomMode.txt");
                if (CustomFile.exists()){
                    try (FileWriter fileWriter = new FileWriter(CustomFile,true)) {
                        fileWriter.write(Integer.toString(model.getScore()));
                        fileWriter.write(System.lineSeparator());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    String filePath = "src/" + account + "_ClassicMode.txt";
                    List<Integer> numbers = null;
                    try {
                        numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Collections.sort(numbers,Collections.reverseOrder());
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                        for (Integer num : numbers) {
                            writer.write(num.toString());
                            writer.newLine();
                        }
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
                JFrame gameframe = findParentFrame(this);
                gameframe.setVisible(false);
                SuccessFrame successFrame=new SuccessFrame(400,500,model.getScore(),account);
                successFrame.setVisible(true);
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
