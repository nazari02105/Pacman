package Sample.Controller;


import Sample.Graphic.Battlefield;
import Sample.Model.User;

import java.util.ArrayList;
import java.util.Random;

public class BattleField extends Thread{
    User user;
    public String jahat = "hello";
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public String[][] maze = new String[17][21];
    public String[][] helpMaze = new String[17][21];
    public int packManLifePoint;
    public boolean canSoulsMove = true;
    public int score = 0;
    public boolean canSoulEatPackMan = true;
    public long startTime = System.currentTimeMillis();
    public int soulsEatenByPackMan = 0;
    public boolean isGameFinished = false;
    public int currentXP = 10;
    public int currentYP = 11;
    public int currentX1 = 19;
    public int currentY1 = 1;
    public int currentX2 = 1;
    public int currentY2 = 1;
    public int currentX3 = 1;
    public int currentY3 = 15;
    public int currentX4 = 19;
    public int currentY4 = 15;
    public int counter = 0;

    public Battlefield battlefield;
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public BattleField (User user) {
        this.user = user;
        packManLifePoint = this.user.getPackManLife();
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    @Override
    public void run() {
            if (!canSoulEatPackMan && System.currentTimeMillis() - startTime > 10000){
                canSoulEatPackMan = true;
            }


            while (true) {
                if (!jahat.equalsIgnoreCase("hello")) {
                    if (jahat.equals("w") && isScoreThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingScoreSound = true;
                        score += 5;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP - 1][currentXP] = "@";
                        currentYP -= 1;
                        helpMaze[currentYP][currentXP] = " ";
                        counter -= 1;
                    } else if (jahat.equals("s") && isScoreThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingScoreSound = true;
                        score += 5;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP + 1][currentXP] = "@";
                        currentYP += 1;
                        helpMaze[currentYP][currentXP] = " ";
                        counter -= 1;
                    } else if (jahat.equals("a") && isScoreThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingScoreSound = true;
                        score += 5;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP][currentXP - 1] = "@";
                        currentXP -= 1;
                        helpMaze[currentYP][currentXP] = " ";
                        counter -= 1;
                    } else if (jahat.equals("d") && isScoreThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingScoreSound = true;
                        score += 5;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP][currentXP + 1] = "@";
                        currentXP += 1;
                        helpMaze[currentYP][currentXP] = " ";
                        counter -= 1;
                    } else if (jahat.equals("w") && isEnergyBombThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingEnergy = true;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP - 1][currentXP] = "@";
                        currentYP -= 1;
                        helpMaze[currentYP][currentXP] = " ";
                        startTime = System.currentTimeMillis();
                        canSoulEatPackMan = false;
                        soulsEatenByPackMan = 0;
                        counter -= 1;
                    } else if (jahat.equals("s") && isEnergyBombThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingEnergy = true;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP + 1][currentXP] = "@";
                        currentYP += 1;
                        helpMaze[currentYP][currentXP] = " ";
                        startTime = System.currentTimeMillis();
                        canSoulEatPackMan = false;
                        soulsEatenByPackMan = 0;
                        counter -= 1;
                    } else if (jahat.equals("a") && isEnergyBombThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingEnergy = true;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP][currentXP - 1] = "@";
                        currentXP -= 1;
                        helpMaze[currentYP][currentXP] = " ";
                        startTime = System.currentTimeMillis();
                        canSoulEatPackMan = false;
                        soulsEatenByPackMan = 0;
                        counter -= 1;
                    } else if (jahat.equals("d") && isEnergyBombThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.eatingEnergy = true;
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP][currentXP + 1] = "@";
                        currentXP += 1;
                        helpMaze[currentYP][currentXP] = " ";
                        startTime = System.currentTimeMillis();
                        canSoulEatPackMan = false;
                        soulsEatenByPackMan = 0;
                        counter -= 1;
                    } else if (canSoulEatPackMan && jahat.equals("w") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.soulEat = true;
                        packManLifePoint -= 1;
                        canSoulsMove = false;
                    } else if (canSoulEatPackMan && jahat.equals("s") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.soulEat = true;
                        packManLifePoint -= 1;
                        canSoulsMove = false;
                    } else if (canSoulEatPackMan && jahat.equals("a") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.soulEat = true;
                        packManLifePoint -= 1;
                        canSoulsMove = false;
                    } else if (canSoulEatPackMan && jahat.equals("d") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                        battlefield.soulEat = true;
                        packManLifePoint -= 1;
                        canSoulsMove = false;
                    } else if (jahat.equals("w") && isEmpty(maze, currentXP, currentYP, jahat)) {
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP - 1][currentXP] = "@";
                        currentYP -= 1;
                        helpMaze[currentYP][currentXP] = " ";
                    } else if (jahat.equals("s") && isEmpty(maze, currentXP, currentYP, jahat)) {
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP + 1][currentXP] = "@";
                        currentYP += 1;
                        helpMaze[currentYP][currentXP] = " ";
                    } else if (jahat.equals("a") && isEmpty(maze, currentXP, currentYP, jahat)) {
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP][currentXP - 1] = "@";
                        currentXP -= 1;
                        helpMaze[currentYP][currentXP] = " ";
                    } else if (jahat.equals("d") && isEmpty(maze, currentXP, currentYP, jahat)) {
                        maze[currentYP][currentXP] = " ";
                        maze[currentYP][currentXP + 1] = "@";
                        currentXP += 1;
                        helpMaze[currentYP][currentXP] = " ";
                    } else if (!canSoulEatPackMan) {
                        if (jahat.equals("w") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                            battlefield.pacmanEat = true;
                            soulsEatenByPackMan += 1;
                            maze[currentYP][currentXP] = " ";
                            maze[currentYP - 1][currentXP] = "@";
                            currentYP -= 1;
                            score += soulsEatenByPackMan * 200;
                        } else if (jahat.equals("s") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                            battlefield.pacmanEat = true;
                            soulsEatenByPackMan += 1;
                            maze[currentYP][currentXP] = " ";
                            maze[currentYP + 1][currentXP] = "@";
                            currentYP += 1;
                            score += soulsEatenByPackMan * 200;
                        } else if (jahat.equals("a") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                            battlefield.pacmanEat = true;
                            soulsEatenByPackMan += 1;
                            maze[currentYP][currentXP] = " ";
                            maze[currentYP][currentXP - 1] = "@";
                            currentXP -= 1;
                            score += soulsEatenByPackMan * 200;
                        } else if (jahat.equals("d") && isSoulThere(maze, currentXP, currentYP, jahat)) {
                            battlefield.pacmanEat = true;
                            soulsEatenByPackMan += 1;
                            maze[currentYP][currentXP] = " ";
                            maze[currentYP][currentXP + 1] = "@";
                            currentXP += 1;
                            score += soulsEatenByPackMan * 200;
                        }
                        if (currentX1 == currentXP && currentY1 == currentYP) {
                            currentX1 = 19;
                            currentY1 = 1;
                            maze[currentY1][currentX1] = "&";
                        } else if (currentX2 == currentXP && currentY2 == currentYP) {
                            currentX2 = 2;
                            currentY2 = 2;
                            maze[currentY2][currentX2] = "&";
                        } else if (currentX3 == currentXP && currentY3 == currentYP) {
                            currentX3 = 1;
                            currentY3 = 15;
                            maze[currentY3][currentX3] = "&";
                        } else if (currentX4 == currentXP && currentY4 == currentYP) {
                            currentX4 = 19;
                            currentY4 = 15;
                            maze[currentY4][currentX4] = "&";
                        }
                    }


                    if (packManLifePoint == 0) {
                        isGameFinished = true;
                        break;
                    }

                    if (jahat.equals("q") || isGameFinished) {
                        isGameFinished = true;
                        break;
                    }

                    if (jahat.equals("p"))
                        canSoulsMove = false;

                    if (!jahat.equalsIgnoreCase("hello")) {
                        showBoard(maze);
                        jahat = "hello";
                    }
                }

            }
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public void runTheGame (String how) {
        if (how.equalsIgnoreCase("new")){
            //building maze
            String[][] mainMaze = user.getMazes().get(user.getSelectedMaze());
            for (int i = 0;  i<17; ++i){
                for (int j = 0; j<21; ++j){
                    maze[i][j] = mainMaze[i][j];
                    helpMaze[i][j] = mainMaze[i][j];
                }
            }
            //placing pack man
            maze[11][10] = "@";
            //inputting energy bombs
            maze[4][16] = "%";
            maze[13][7] = "%";
            helpMaze[4][16] = "%";
            helpMaze[13][7] = "%";
            counter += 2;
            //building first soul
            maze[1][19] = "&";
            currentX1 = 19;
            currentY1 = 1;
            //building second soul
            maze[1][1] = "&";
            currentX2 = 1;
            currentY2 = 1;
            //building third soul
            maze[15][1] = "&";
            currentX3 = 1;
            currentY3 = 15;
            //building forth soul
            maze[15][19] = "&";
            currentX4 = 19;
            currentY4 = 15;
            //putting scores
            for (int i = 0; i<17; ++i){
                for (int j = 0; j<21; ++j){
                    if (maze[i][j].equals(" ")){
                        maze[i][j] = "-";
                        helpMaze[i][j] = "-";
                        counter += 1;
                    }
                }
            }
        }
        else{
            //assigning values
            maze = user.getSavingTheGame().maze;
            helpMaze = user.getSavingTheGame().helpMaze;
            packManLifePoint = user.getSavingTheGame().packManLifePoint;
            canSoulsMove = user.getSavingTheGame().canSoulsMove;
            score = user.getSavingTheGame().score;
            canSoulEatPackMan = user.getSavingTheGame().canSoulEatPackMan;
            startTime = user.getSavingTheGame().startTime;
            soulsEatenByPackMan = user.getSavingTheGame().soulsEatenByPackMan;
            user.getSavingTheGame().isGameFinished = false;
            isGameFinished = user.getSavingTheGame().isGameFinished;
            currentXP = user.getSavingTheGame().currentXP;
            currentYP = user.getSavingTheGame().currentYP;
            currentX1 = user.getSavingTheGame().currentX1;
            currentY1 = user.getSavingTheGame().currentY1;
            currentX2 = user.getSavingTheGame().currentX2;
            currentY2 = user.getSavingTheGame().currentY2;
            currentX3 = user.getSavingTheGame().currentX3;
            currentY3 = user.getSavingTheGame().currentY3;
            currentX4 = user.getSavingTheGame().currentX4;
            currentY4 = user.getSavingTheGame().currentY4;
            counter = user.getSavingTheGame().counter;
        }
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public void moveTheSouls () throws InterruptedException {
        Thread.sleep(2000);
        String back1 = movingSoul(currentX1, currentY1, maze);
        String[] backArray1 = back1.split(",");
        currentX1 = Integer.parseInt(backArray1[0]);
        currentY1 = Integer.parseInt(backArray1[1]);
        //moving second soul
        String back2 = movingSoul(currentX2, currentY2, maze);
        String[] backArray2 = back2.split(",");
        currentX2 = Integer.parseInt(backArray2[0]);
        currentY2 = Integer.parseInt(backArray2[1]);
        //moving third soul
        String back3 = movingSoul(currentX3, currentY3, maze);
        String[] backArray3 = back3.split(",");
        currentX3 = Integer.parseInt(backArray3[0]);
        currentY3 = Integer.parseInt(backArray3[1]);
        //moving forth soul
        String back4 = movingSoul(currentX4, currentY4, maze);
        String[] backArray4 = back4.split(",");
        currentX4 = Integer.parseInt(backArray4[0]);
        currentY4 = Integer.parseInt(backArray4[1]);
        showBoard(maze);
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public void showBoard (String[][] maze){
        System.out.println("Life Point : " + packManLifePoint + " - Score : " + score);
        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public boolean isEmpty (String[][] maze, int currentX, int currentY, String jahat){
        switch (jahat) {
            case "w":
                if (maze[currentY - 1][currentX].equals(" "))
                    return true;
                break;
            case "s":
                if (maze[currentY + 1][currentX].equals(" "))
                    return true;
                break;
            case "a":
                if (maze[currentY][currentX - 1].equals(" "))
                    return true;
                break;
            case "d":
                if (maze[currentY][currentX + 1].equals(" "))
                    return true;
                break;
        }
        return false;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public boolean isSoulThere (String[][] maze, int currentX, int currentY, String jahat){
        switch (jahat) {
            case "w":
                if (maze[currentY - 1][currentX].equals("&"))
                    return true;
                break;
            case "s":
                if (maze[currentY + 1][currentX].equals("&"))
                    return true;
                break;
            case "a":
                if (maze[currentY][currentX - 1].equals("&"))
                    return true;
                break;
            case "d":
                if (maze[currentY][currentX + 1].equals("&"))
                    return true;
                break;
        }
        return false;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public boolean isPackManThere (String[][] maze, int currentX, int currentY, String jahat){
        switch (jahat) {
            case "w":
                if (maze[currentY - 1][currentX].equals("@"))
                    return true;
                break;
            case "s":
                if (maze[currentY + 1][currentX].equals("@"))
                    return true;
                break;
            case "a":
                if (maze[currentY][currentX - 1].equals("@"))
                    return true;
                break;
            case "d":
                if (maze[currentY][currentX + 1].equals("@"))
                    return true;
                break;
        }
        return false;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public boolean isEnergyBombThere (String[][] maze, int currentX, int currentY, String jahat){
        switch (jahat) {
            case "w":
                if (maze[currentY - 1][currentX].equals("%"))
                    return true;
                break;
            case "s":
                if (maze[currentY + 1][currentX].equals("%"))
                    return true;
                break;
            case "a":
                if (maze[currentY][currentX - 1].equals("%"))
                    return true;
                break;
            case "d":
                if (maze[currentY][currentX + 1].equals("%"))
                    return true;
                break;
        }
        return false;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public boolean isScoreThere (String[][] maze, int currentX, int currentY, String jahat){
        switch (jahat) {
            case "w":
                if (maze[currentY - 1][currentX].equals("-"))
                    return true;
                break;
            case "s":
                if (maze[currentY + 1][currentX].equals("-"))
                    return true;
                break;
            case "a":
                if (maze[currentY][currentX - 1].equals("-"))
                    return true;
                break;
            case "d":
                if (maze[currentY][currentX + 1].equals("-"))
                    return true;
                break;
        }
        return false;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public String movingSoul (int currentX, int currentY, String[][] maze){
        if (!canSoulEatPackMan && System.currentTimeMillis() - startTime > 10000){
            canSoulEatPackMan = true;
        }
        if (!canSoulsMove){
//            try {
//                this.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            canSoulsMove = true;
        }
        else {
            ArrayList<String> jahat = new ArrayList<>();
            jahat.add("w");
            jahat.add("s");
            jahat.add("a");
            jahat.add("d");
            Random random = new Random();
            int whichJahat = random.nextInt(4);
            String x = jahat.get(whichJahat);


            if (x.equals("w") && (isEnergyBombThere(maze, currentX, currentY, x) || isScoreThere(maze, currentX, currentY, x))) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY - 1][currentX] = "&";
                currentY -= 1;
            } else if (x.equals("s") && (isEnergyBombThere(maze, currentX, currentY, x) || isScoreThere(maze, currentX, currentY, x))) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY + 1][currentX] = "&";
                currentY += 1;
            } else if (x.equals("a") && (isEnergyBombThere(maze, currentX, currentY, x) || isScoreThere(maze, currentX, currentY, x))) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY][currentX - 1] = "&";
                currentX -= 1;
            } else if (x.equals("d") && (isEnergyBombThere(maze, currentX, currentY, x) || isScoreThere(maze, currentX, currentY, x))) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY][currentX + 1] = "&";
                currentX += 1;
            } else if (canSoulEatPackMan && x.equals("w") && isPackManThere(maze, currentX, currentY, x)) {
                packManLifePoint -= 1;
                canSoulsMove = false;
            } else if (canSoulEatPackMan && x.equals("s") && isPackManThere(maze, currentX, currentY, x)) {
                packManLifePoint -= 1;
                canSoulsMove = false;
            } else if (canSoulEatPackMan && x.equals("a") && isPackManThere(maze, currentX, currentY, x)) {
                packManLifePoint -= 1;
                canSoulsMove = false;
            } else if (canSoulEatPackMan && x.equals("d") && isPackManThere(maze, currentX, currentY, x)) {
                packManLifePoint -= 1;
                canSoulsMove = false;
            } else if (x.equals("w") && isEmpty(maze, currentX, currentY, x)) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY - 1][currentX] = "&";
                currentY -= 1;
            } else if (x.equals("s") && isEmpty(maze, currentX, currentY, x)) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY + 1][currentX] = "&";
                currentY += 1;
            } else if (x.equals("a") && isEmpty(maze, currentX, currentY, x)) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY][currentX - 1] = "&";
                currentX -= 1;
            } else if (x.equals("d") && isEmpty(maze, currentX, currentY, x)) {
                maze[currentY][currentX] = helpMaze[currentY][currentX];
                maze[currentY][currentX + 1] = "&";
                currentX += 1;
            } else if (!canSoulEatPackMan) {
                if (x.equals("w") && isPackManThere(maze, currentX, currentY, x)) {
                    soulsEatenByPackMan += 1;
                    maze[currentY][currentX] = helpMaze[currentY][currentX];
                    score += soulsEatenByPackMan * 200;
                    //
                    if (currentX1 == currentX && currentY1 == currentY) {
                        currentX1 = 19;
                        currentY1 = 1;
                        maze[currentY1][currentX1] = "&";
                    } else if (currentX2 == currentX && currentY2 == currentY) {
                        currentX2 = 2;
                        currentY2 = 2;
                        maze[currentY2][currentX2] = "&";
                    } else if (currentX3 == currentX && currentY3 == currentY) {
                        currentX3 = 1;
                        currentY3 = 15;
                        maze[currentY3][currentX3] = "&";
                    } else if (currentX4 == currentX && currentY4 == currentY) {
                        currentX4 = 19;
                        currentY4 = 15;
                        maze[currentY4][currentX4] = "&";
                    }
                    //
                } else if (x.equals("s") && isPackManThere(maze, currentX, currentY, x)) {
                    soulsEatenByPackMan += 1;
                    maze[currentY][currentX] = helpMaze[currentY][currentX];
                    score += soulsEatenByPackMan * 200;
                    //
                    if (currentX1 == currentX && currentY1 == currentY) {
                        currentX1 = 19;
                        currentY1 = 1;
                        maze[currentY1][currentX1] = "&";
                    } else if (currentX2 == currentX && currentY2 == currentY) {
                        currentX2 = 2;
                        currentY2 = 2;
                        maze[currentY2][currentX2] = "&";
                    } else if (currentX3 == currentX && currentY3 == currentY) {
                        currentX3 = 1;
                        currentY3 = 15;
                        maze[currentY3][currentX3] = "&";
                    } else if (currentX4 == currentX && currentY4 == currentY) {
                        currentX4 = 19;
                        currentY4 = 15;
                        maze[currentY4][currentX4] = "&";
                    }
                    //
                } else if (x.equals("a") && isPackManThere(maze, currentX, currentY, x)) {
                    soulsEatenByPackMan += 1;
                    maze[currentY][currentX] = helpMaze[currentY][currentX];
                    score += soulsEatenByPackMan * 200;
                    //
                    if (currentX1 == currentX && currentY1 == currentY) {
                        currentX1 = 19;
                        currentY1 = 1;
                        maze[currentY1][currentX1] = "&";
                    } else if (currentX2 == currentX && currentY2 == currentY) {
                        currentX2 = 2;
                        currentY2 = 2;
                        maze[currentY2][currentX2] = "&";
                    } else if (currentX3 == currentX && currentY3 == currentY) {
                        currentX3 = 1;
                        currentY3 = 15;
                        maze[currentY3][currentX3] = "&";
                    } else if (currentX4 == currentX && currentY4 == currentY) {
                        currentX4 = 19;
                        currentY4 = 15;
                        maze[currentY4][currentX4] = "&";
                    }
                    //
                } else if (x.equals("d") && isPackManThere(maze, currentX, currentY, x)) {
                    soulsEatenByPackMan += 1;
                    maze[currentY][currentX] = helpMaze[currentY][currentX];
                    score += soulsEatenByPackMan * 200;
                    //
                    if (currentX1 == currentX && currentY1 == currentY) {
                        currentX1 = 19;
                        currentY1 = 1;
                        maze[currentY1][currentX1] = "&";
                    } else if (currentX2 == currentX && currentY2 == currentY) {
                        currentX2 = 2;
                        currentY2 = 2;
                        maze[currentY2][currentX2] = "&";
                    } else if (currentX3 == currentX && currentY3 == currentY) {
                        currentX3 = 1;
                        currentY3 = 15;
                        maze[currentY3][currentX3] = "&";
                    } else if (currentX4 == currentX && currentY4 == currentY) {
                        currentX4 = 19;
                        currentY4 = 15;
                        maze[currentY4][currentX4] = "&";
                    }
                    //
                }
            }


            if (packManLifePoint == 0) {
                isGameFinished = true;
            }

            if (counter <= 4) {
                packManLifePoint += 1;
                canSoulsMove = false;
                fillTheMaze();
            }

//        showBoard(maze);

            return String.valueOf(currentX) + "," + String.valueOf(currentY);
        }
        return String.valueOf(currentX) + "," + String.valueOf(currentY);
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public void fillTheMaze (){
        counter = 0;
        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                if (!maze[i][j].equals("#")){
                    maze[i][j] = " ";
                    helpMaze[i][j] = " ";
                }
            }
        }

        maze[1][19] = "&";
        currentX1 = 19;
        currentY1 = 1;
        maze[1][1] = "&";
        currentX2 = 1;
        currentY2 = 1;
        maze[15][1] = "&";
        currentY3 = 15;
        currentX3 = 1;
        maze[15][19] = "&";
        currentY4 = 15;
        currentX4 = 19;

        maze[11][10] = "@";
        currentXP = 10;
        currentYP = 11;

        maze[4][16] = "%";
        maze[13][7] = "%";
        helpMaze[4][16] = "%";
        helpMaze[13][7] = "%";
        counter += 2;

        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                if (maze[i][j].equals(" ")){
                    maze[i][j] = "-";
                    helpMaze[i][j] = "-";
                    counter += 1;
                }
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------

}



class HW
{
    // `M × N` matrix
    private final int M = 17;
    private final int N = 21;

    // Check if it is possible to go to `(x, y)` from the current position. The
    // function returns false if the cell has value 0 or already visited
    private boolean isSafe(String mat[][], int visited[][], int x, int y) {

        return !(mat[x][y].equals("#") || visited[x][y] != 0);
    }

    // Returns false if not a valid position
    private boolean isValid(int x, int y) {
        return (x < M && y < N && x >= 0 && y >= 0);
    }

    // Find the shortest possible route in a matrix `mat` from source cell `(0, 0)`
    // to destination cell `(x, y)`.
    // `min_dist` stores the length of the longest path from source to a destination
    // found so far, and `dist` maintains the length of the path from a source cell
    // to the current cell `(i, j)`.
    public int findShortestPath(String mat[][], int visited[][],
                                       int i, int j, int x, int y, int min_dist, int dist)
    {
        // if the destination is found, update `min_dist`
        if (i == x && j == y) {
            return Integer.min(dist, min_dist);
        }

        // set `(i, j)` cell as visited
        visited[i][j] = 1;

        // go to the bottom cell
        if (isValid(i + 1, j) && isSafe(mat, visited, i + 1, j))
        {
            min_dist = findShortestPath(mat, visited, i + 1, j, x, y,
                    min_dist, dist + 1);
        }

        // go to the right cell
        if (isValid(i, j + 1) && isSafe(mat, visited, i, j + 1))
        {
            min_dist = findShortestPath(mat, visited, i, j + 1, x, y,
                    min_dist, dist + 1);
        }

        // go to the top cell
        if (isValid(i - 1, j) && isSafe(mat, visited, i - 1, j))
        {
            min_dist = findShortestPath(mat, visited, i - 1, j, x, y,
                    min_dist, dist + 1);
        }

        // go to the left cell
        if (isValid(i, j - 1) && isSafe(mat, visited, i, j - 1))
        {
            min_dist = findShortestPath(mat, visited, i, j - 1, x, y,
                    min_dist, dist + 1);
        }


        // backtrack: remove `(i, j)` from the visited matrix
        visited[i][j] = 0;

        return min_dist;
    }

    public void main(String[][] maze, int currentYP, int currentXP)
    {
        String mat[][] = maze;

        // construct a matrix to keep track of visited cells
        int[][] visited = new int[M][N];

        int min_dist = findShortestPath(mat, visited, 1, 1, currentYP, currentXP,
                Integer.MAX_VALUE, 0);
    }
}