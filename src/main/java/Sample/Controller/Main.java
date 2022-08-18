package Sample.Controller;

import Sample.Model.User;
import Sample.View.PrintingAndScanning;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //----------------------------------------------------------------------------nothing to have for graphic
    public static boolean doesTheCommandMatches (String command, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        return matcher.find();
    }
    //----------------------------------------------------------------------------nothing to have for graphic
    public static Matcher returnMatcher (String command, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }
    //----------------------------------------------------------------------------nothing to have for graphic
    public static void main(String[] args) throws IOException, InterruptedException {
        LoadUsers.loadAllUsers();
        loginMenu();
    }
    //----------------------------------------------------------------------------
    public static void loginMenu () throws IOException, InterruptedException {
        while (true){
            String command = PrintingAndScanning.scan();
            if (doesTheCommandMatches(command, "^user signup --username (.+)? --password (.+)?$"))
                signup(command);
            else if (doesTheCommandMatches(command, "^user login --username (.+)? --password (.*)?$"))
                login(command);
            else if (doesTheCommandMatches(command, "^exit$")) {
                LoadUsers.saveAllUsers();
                System.exit(0);
            }
            else if (doesTheCommandMatches(command, "^play as guest$"))
                mainMenu("Admin");
            else if (doesTheCommandMatches(command, "^help$"))//nothing to have for graphic
                helpInLoginMenu();
            else
                PrintingAndScanning.print("Invalid command.");//nothing to have for graphic
        }
    }
    //----------------------------------------------------------------------------nothing to have for graphic
    public static void helpInLoginMenu (){
        PrintingAndScanning.print("^user signup --username (.+)? --password (.+)?$");
        PrintingAndScanning.print("^user login --username (.+)? --password (.*)?$");
        PrintingAndScanning.print("^play as guest$");
        PrintingAndScanning.print("^exit$");
        PrintingAndScanning.print("^help$");
    }
    //----------------------------------------------------------------------------
    public static String signup (String command) throws IOException {
        Matcher matcher = returnMatcher(command, "^user signup --username (.+)? --password (.+)?$");
        String username = "";
        String password = "";
        if (matcher.find()){
            username = matcher.group(1);
            password = matcher.group(2);
        }
        if (User.allUsers.containsKey(username)) {
            PrintingAndScanning.print("User with this Username already exists.");
            return "User with this Username already exists.";
        }
        else{
            new User(username, password);
            System.out.println("User created successfully.");
            return "User created successfully.";
        }
    }
    //----------------------------------------------------------------------------
    public static String login (String command) throws IOException, InterruptedException {
        Matcher matcher = returnMatcher(command, "^user login --username (.+)? --password (.*)?$");
        String username = "";
        String password = "";
        if (matcher.find()){
            username = matcher.group(1);
            password = matcher.group(2);
        }
        if (!User.allUsers.containsKey(username)) {
            PrintingAndScanning.print("There is no user with this username.");
            return "There is no user with this username.";
        }
        else if (!User.allUsers.get(username).getPassword().equals(password)) {
            PrintingAndScanning.print("Wrong password.");
            return "Wrong password.";
        }
        else{
            PrintingAndScanning.print("Logged in successfully.");
//            mainMenu(username);
            return "Logged in successfully.";
        }
    }
    //----------------------------------------------------------------------------
    public static void mainMenu (String username) throws IOException, InterruptedException {
        while (true){
            String command = PrintingAndScanning.scan();
            if (doesTheCommandMatches(command, "^change password --currentPassword (.+)? --newPassword (.+)?$"))
                changePassword(username, command);
            else if (doesTheCommandMatches(command, "^delete account$")) {
                deleteAccount(username);
                if (!User.allUsers.containsKey(username))
                    break;
            }
            else if (doesTheCommandMatches(command, "^back$"))
                break;
            else if (doesTheCommandMatches(command, "^scoreBoard$"))
                showScoreBoard();
            else if (doesTheCommandMatches(command, "^start new game$"))
                startNewGame(username);
            else if (doesTheCommandMatches(command, "^load game$")){//hhheeerrreee
                if (User.allUsers.get(username).getSavingTheGame() == null)
                    PrintingAndScanning.print("You dont have any game to load.");
                else
                    loadGame(username);
            }
            else if (doesTheCommandMatches(command, "^help$"))//nothing to do for graphic
                helpInMainMenu();
            else
                PrintingAndScanning.print("Invalid command.");//nothing to do for graphic
        }
    }
    //----------------------------------------------------------------------------//nothing to do for graphic
    public static void helpInMainMenu (){
        PrintingAndScanning.print("^change password --currentPassword (.+)? --newPassword (.+)?$");
        PrintingAndScanning.print("^delete account$");
        PrintingAndScanning.print("^back$");
        PrintingAndScanning.print("^scoreBoard$");
        PrintingAndScanning.print("^start new game$");
        PrintingAndScanning.print("^load game$");
    }
    //----------------------------------------------------------------------------
    public static String changePassword (String username, String command){
        Matcher matcher = returnMatcher(command, "^change password --currentPassword (.+)? --newPassword (.+)?$");
        String currentPassword = "";
        String newPassword = "";
        if (matcher.find()){
            currentPassword = matcher.group(1);
            newPassword = matcher.group(2);
        }
        if (!User.allUsers.get(username).getPassword().equals(currentPassword)) {
            PrintingAndScanning.print("Wrong password.");
            return "Wrong password.";
        }
        else if (currentPassword.equals(newPassword)) {
            PrintingAndScanning.print("Current and new password are the equal.");
            return "Current and new password are the same.";
        }
        else {
            User.allUsers.get(username).setPassword(newPassword);
            return "Password changed successfully.";
        }
    }
    //----------------------------------------------------------------------------
    public static void deleteAccount (String username) throws IOException {
        PrintingAndScanning.print("Account deleted successfully.");
        User.allUsers.remove(username);
        File file = new File(username);
        file.delete();

        FileReader xxx = new FileReader("allUsers.txt");
        String x = "";
        int ch;
        while ((ch=xxx.read()) != -1)
            x += (char)ch;
        xxx.close();

        String[] allUsers = x.split(",");
        ArrayList<String> lastedUsers = new ArrayList<>();
        for (String allUser : allUsers) {
            if (!allUser.equals(username))
                lastedUsers.add(allUser);
        }
        String atLast = "";
        for (String lastedUser : lastedUsers) {
            atLast += lastedUser;
            atLast += ",";
        }

        FileWriter fw = new FileWriter("allUsers.txt");
        fw.write(atLast);
        fw.close();
    }
    //----------------------------------------------------------------------------
    public static String showScoreBoard (){
        ArrayList<User> users = new ArrayList<>();
        for (Map.Entry<String, User> entry : User.allUsers.entrySet()){
            users.add(entry.getValue());
        }

        for (int i = 0; i<users.size(); ++i){
            for (int j = 0; j<users.size(); ++j){
                if (users.get(i).getScore() > users.get(j).getScore()){
                    User temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }

        String response = "";

        int rank = 1;
        for (int i = 0; i<users.size(); ++i){
            if (i == 10)
                break;
            if (i != 0 && users.get(i).getScore() != users.get(i-1).getScore())
                rank = i + 1;
            PrintingAndScanning.print(rank + ":" + users.get(i).getName() + "-" + users.get(i).getScore());
            response += rank + ":" + users.get(i).getName() + "-" + users.get(i).getScore() + "\n";
        }
//        PrintingAndScanning.print("Press enter to continue.");
//        PrintingAndScanning.scan();
        return response;
    }
    //----------------------------------------------------------------------------
    public static void startNewGame (String username) throws IOException, InterruptedException {
        while (true){
            String command = PrintingAndScanning.scan();
            if (doesTheCommandMatches(command, "^back$"))
                break;
            else if (doesTheCommandMatches(command, "^select maze$"))
                selectingBoard(username);
            else if (doesTheCommandMatches(command, "^start game")){
                if (User.allUsers.get(username).getSelectedMaze() == -1)
                    PrintingAndScanning.print("You did not select any maze.");
                else
                    startGame(username);
            }
            else if (doesTheCommandMatches(command, "^help$"))//nothing to do for graphic
                helpInStartNewGame();
            else if (doesTheCommandMatches(command, "^set packman life (\\d)$"))
                setPackManLife(command, username);
            else
                PrintingAndScanning.print("Invalid command.");//nothing to do for graphic
        }
    }
    //----------------------------------------------------------------------------//nothing to do for graphic
    public static void helpInStartNewGame (){
        PrintingAndScanning.print("^select maze$");
        PrintingAndScanning.print("^start game$");
        PrintingAndScanning.print("^set packman life (\\d)$");
        PrintingAndScanning.print("^back$");
    }
    //----------------------------------------------------------------------------
    public static String selectingBoard (String username) throws IOException {
        String mazes = "";
        for (int i = 0; i<User.allUsers.get(username).getMazes().size(); ++i){
            System.out.println("number " + (i + 1));
            for (int j = 0; j<17; ++j){
                for (int k = 0; k<21; ++k){
                    System.out.print(User.allUsers.get(username).getMazes().get(i)[j][k]);
                    mazes += User.allUsers.get(username).getMazes().get(i)[j][k];
                }
                System.out.println();
                mazes += "\n";
            }
            System.out.println();
            mazes += "*";
        }

        return mazes;

//        System.out.println("Select one of them or enter (new maze) to create a new maze or (back) to cancel process.");
//        while (true){
//            String command = PrintingAndScanning.scan();
//            try {
//                int x = Integer.parseInt(command);
//                if (x > 0 && x < User.allUsers.get(username).getMazes().size() + 1){
//                    User.allUsers.get(username).setSelectedMaze(x-1);
//                    break;
//                }
//                else
//                    PrintingAndScanning.print("Invalid number.");
//            }catch (Exception e){
//                if (command.equalsIgnoreCase("back"))
//                    break;
//                else if (command.equalsIgnoreCase("new maze")){
//                    generateNewMaze(username);
//                    break;
//                }
//                else
//                    PrintingAndScanning.print("Invalid command.");
//            }
//        }
    }
    //----------------------------------------------------------------------------
    public static String[][] generateNewMaze (String username) throws IOException {
        String[][] maze = GeneratingMaze.returningMaze();
        for (int i = 0; i<17; ++i){
            for (int j = 0; j<21; ++j){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        return maze;

//        PrintingAndScanning.print("Do you like this?");
//        while (true){
//            String command = PrintingAndScanning.scan();
//            if (command.equalsIgnoreCase("yes")){
//                User.allUsers.get(username).addToMazes(maze);
//                User.allUsers.get(username).setSelectedMaze(User.allUsers.get(username).getMazes().size() - 1);
//                break;
//            }
//            else if (command.equalsIgnoreCase("no")){
//                generateNewMaze(username);
//                break;
//            }
//        }
    }
    //----------------------------------------------------------------------------
    public static void setPackManLife (String command, String username){
        Matcher matcher = returnMatcher(command, "^set packman life (\\d)$");
        int lifePoint = 0;
        if (matcher.find())
            lifePoint = Integer.parseInt(matcher.group(1));

        if (lifePoint > 1 && lifePoint < 6){
            PrintingAndScanning.print("Life point updated successfully.");
            User.allUsers.get(username).setPackManLife(lifePoint);
        }
        else
            PrintingAndScanning.print("Invalid number.");
    }
    //----------------------------------------------------------------------------
    public static void startGame (String userName) throws InterruptedException {
        User.allUsers.get(userName).setSavingTheGame(null);
        BattleField battleField = new BattleField(User.allUsers.get(userName));
        battleField.runTheGame("new");
        afterGame(battleField, userName);
    }
    //----------------------------------------------------------------------------//hhheeelllppp
    public static void loadGame (String username) throws InterruptedException {
        BattleField battleField = new BattleField(User.allUsers.get(username));
        battleField.runTheGame("load");
        afterGame(battleField, username);
    }
    //----------------------------------------------------------------------------//hhheeelllppp
    public static String afterGame (BattleField battleField, String userName){
        if (battleField.packManLifePoint != 0){
            User user = User.allUsers.get(userName);
            user.setSavingTheGame(new SavingTheGame());
            //
            user.getSavingTheGame().maze = battleField.maze;
            user.getSavingTheGame().helpMaze = battleField.helpMaze;
            user.getSavingTheGame().packManLifePoint = battleField.packManLifePoint;
            user.getSavingTheGame().canSoulsMove = battleField.canSoulsMove;
            user.getSavingTheGame().score = battleField.score;
            user.getSavingTheGame().canSoulEatPackMan = battleField.canSoulEatPackMan;
            user.getSavingTheGame().startTime = battleField.startTime;
            user.getSavingTheGame().soulsEatenByPackMan = battleField.soulsEatenByPackMan;
            user.getSavingTheGame().isGameFinished = battleField.isGameFinished;
            user.getSavingTheGame().currentXP = battleField.currentXP;
            user.getSavingTheGame().currentYP = battleField.currentYP;
            user.getSavingTheGame().currentX1 = battleField.currentX1;
            user.getSavingTheGame().currentY1 = battleField.currentY1;
            user.getSavingTheGame().currentX2 = battleField.currentX2;
            user.getSavingTheGame().currentY2 = battleField.currentY2;
            user.getSavingTheGame().currentX3 = battleField.currentX3;
            user.getSavingTheGame().currentY3 = battleField.currentY3;
            user.getSavingTheGame().currentX4 = battleField.currentX4;
            user.getSavingTheGame().currentY4 = battleField.currentY4;
            user.getSavingTheGame().counter = battleField.counter;
            //
            return  "Your game saved successfully.";
        }
        else{
            if (User.allUsers.get(userName).getScore() > battleField.score){
                return "Your score in this round is " + battleField.score + " but it is " +
                        "lower than your high score.";
            }
            else{
                User.allUsers.get(userName).setScore(battleField.score);
                return "Your score in this round is " + battleField.score + " and you " +
                        "set a new record";
            }
        }
    }
    //----------------------------------------------------------------------------
}
