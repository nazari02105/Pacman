package Sample.Model;

import Sample.Controller.BattleField;
import Sample.Controller.GeneratingMaze;
import Sample.Controller.SavingTheGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public static HashMap<String, User> allUsers = new HashMap<>();
    public String name;
    private String password;
    private int score = 0;
    private ArrayList<String[][]> mazes = new ArrayList<>();
    private Integer selectedMaze = -1;
    private int packManLife = 5;
    private SavingTheGame savingTheGame = null;


    public User (String name, String password) throws IOException {
        this.name = name;
        this.password = password;
        allUsers.put(name, this);
        fillTheMaze();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void fillTheMaze () throws IOException {
        for (int i = 0; i<3; ++i)
            mazes.add(GeneratingMaze.returningMaze());
    }

    public ArrayList<String[][]> getMazes() {
        return mazes;
    }

    public void addToMazes (String[][] maze){
        mazes.add(maze);
    }

    public Integer getSelectedMaze() {
        return selectedMaze;
    }

    public void setSelectedMaze(Integer selectedMaze) {
        this.selectedMaze = selectedMaze;
    }

    public int getPackManLife() {
        return packManLife;
    }

    public void setPackManLife(int packManLife) {
        this.packManLife = packManLife;
    }

    public SavingTheGame getSavingTheGame() {
        return savingTheGame;
    }

    public void setSavingTheGame(SavingTheGame savingTheGame) {
        this.savingTheGame = savingTheGame;
    }
}
