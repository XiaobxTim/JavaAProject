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
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Stream;

public class AIPanel extends ListenerPanel{
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
    private String account;
    public AIPanel(int size,String account) {
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
    public void updateScore(int score) {
        model.setScore(model.getS());
        this.scoreLabel.setText(String.format("Score: %d", score));
    }
    public void updateStep(int steps) {
        model.setScore(model.getS());
        this.stepLabel.setText(String.format("Step: %d", steps));
    }
    @Override
    public void doMoveRight() throws IOException {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            File AIFile = new File("src/" + account + "_AIMode.txt");
            if (AIFile.exists()){
                try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_AIMode.txt";
                java.util.List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
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
                        File AIFile = new File("src/" + account + "_AIMode.txt");
                        if (AIFile.exists()){
                            try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                                fileWriter.write(Integer.toString(model.getScore()));
                                fileWriter.write(System.lineSeparator());
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                            String filePath = "src/" + account + "_AIMode.txt";
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
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
        }
    }
    @Override
    public void doMoveLeft() throws IOException {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            File AIFile = new File("src/" + account + "_AIMode.txt");
            if (AIFile.exists()){
                try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_AIMode.txt";
                java.util.List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
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
            final boolean[] isFirst = {true};
            Timer timer = new Timer(DELAY, e -> {
                System.out.println("!");
                boolean moved = getModel().moveLeftStep(isFirst[0]);
                isFirst[0] = false;
                updateGridsNumber();
                if (!moved) {
                    System.out.println("...");
                    getModel().addNewPiece("Left");
                    this.updateGridsNumber();
                    System.out.println("here!");
                    this.afterMove();
                    int number=model.FindMaxNumber();
                    if (number>=model.getAim()){
                        File AIFile = new File("src/" + account + "_AIMode.txt");
                        if (AIFile.exists()){
                            try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                                fileWriter.write(Integer.toString(model.getScore()));
                                fileWriter.write(System.lineSeparator());
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                            String filePath = "src/" + account + "_AIMode.txt";
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
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();

        }
    }
    @Override
    public void doMoveUp() throws IOException {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            File AIFile = new File("src/" + account + "_AIMode.txt");
            if (AIFile.exists()){
                try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_AIMode.txt";
                java.util.List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
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
                        File AIFile = new File("src/" + account + "_AIMode.txt");
                        if (AIFile.exists()){
                            try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                                fileWriter.write(Integer.toString(model.getScore()));
                                fileWriter.write(System.lineSeparator());
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                            String filePath = "src/" + account + "_AIMode.txt";
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
                    model.setLock(false);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
        }
    }
    @Override
    public void doMoveDown() throws IOException {
        if (model.getLock())
            return ;
        model.setLock(true);
        if (model.gameEnd()) {
            File AIFile = new File("src/" + account + "_AIMode.txt");
            if (AIFile.exists()){
                try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                String filePath = "src/" + account + "_AIMode.txt";
                java.util.List<Integer> numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
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
                        File AIFile = new File("src/" + account + "_AIMode.txt");
                        if (AIFile.exists()){
                            try (FileWriter fileWriter = new FileWriter(AIFile,true)) {
                                fileWriter.write(Integer.toString(model.getScore()));
                                fileWriter.write(System.lineSeparator());
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                            String filePath = "src/" + account + "_AIMode.txt";
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
            String filePath = "src/" + account + "_AIMode.txt"; // 替换为你的文件路径
            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                String firstLine = lines.findFirst().orElse("0");
                this.maxscoreLabel.setText(String.format("MaxScore: %s",firstLine));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }
    public void setScoreLabel(JLabel scoreLabel){ this.scoreLabel = scoreLabel; }
    public void setMaxscoreLabel(JLabel maxscoreLabel){ this.maxscoreLabel = maxscoreLabel;}
    public void setStep(int steps){this.steps=steps;}
    public int getStep(){return steps;}
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
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
