# Pacman

A Pac-Man game written in **Java** with **JavaFX**. It has user accounts, a score board, random and hand-made mazes, saving and loading a game, music and sound effects.

User data is saved as JSON files with **Gson**. The project is built with **Maven**.

## Features

- **Accounts:** sign up, log in, change password, delete account, or **play as guest**
- **Score board:** a pie chart of the top 10 high scores
- **Mazes:** every new user gets 3 random mazes. You can also:
  - make a new random maze (**New Maze**, then *Yes* to keep it or *No* for another one)
  - draw your own maze (**Build Maze**: click cells to switch between *wall* and *road*, then *finish*)
- **Pac-Man lives:** choose 2, 3, 4 or 5 (default 5) with **Set PacMan Life**
- **Save and load:** leaving a game with **Exit** saves it, and **Load Game** continues it
- Background music, sound effects, and Pac-Man drawn as a small animated video

## How to play

- Move Pac-Man with the **arrow keys**. (W, A, S, D don't work.)
- **Dots** are worth **5 points** each.
- Each maze has **2 energy items**. After you eat one, you can eat ghosts for **10 seconds**. The first ghost gives 200 points, the second 400, the third 600 and the fourth 800. An eaten ghost goes back to (near) its starting corner.
- The **4 ghosts** move randomly every 2 seconds. If Pac-Man and a ghost meet, you lose a life and the ghosts stop until you press a key again.
- When only **4 or fewer** dots are left, you get **1 extra life** and the maze is filled again.
- When you have **no lives left**, the game ends. Your score becomes your high score if it is higher than your old one.
- **Pause** stops the ghosts until you press a key. **Mute** turns the music off and on. **Exit** saves the game and goes back to the menu. A game you leave with Exit doesn't count for the score board.

**Note:** the board is only redrawn when you press a key. The ghosts keep moving, but you see their new places only after your next move.

## Requirements

- **JDK 11 or newer.** The project was made with JDK 16.
- **Maven**, or **IntelliJ IDEA**, which has Maven built in
- An internet connection the first time, so Maven can download JavaFX and Gson

## How to run

The game starts from the class **`Sample.Graphic.LoginMenu`**.

### Option 1: IntelliJ IDEA (easiest)

1. **File → Open** and choose the project folder. IntelliJ reads `pom.xml` and downloads the libraries.
2. Open `src/main/java/Sample/Graphic/LoginMenu.java` and click the green **Run** arrow next to `main`.

### Option 2: Maven on the command line

The `javafx-maven-plugin` in `pom.xml` points to a class that doesn't exist (`org.openjfx.App`), so `mvn javafx:run` fails as it is. First change this line in `pom.xml`:

```xml
<mainClass>org.openjfx.App</mainClass>
```

to:

```xml
<mainClass>HW3/Sample.Graphic.LoginMenu</mainClass>
```

(`HW3` is the module name from `module-info.java`.) Then run, from the project folder:

```bash
mvn clean javafx:run
```

### First steps in the game

1. Click **SIGNUP**, enter a username and password, click **SIGNUP**, then **Back**.
2. Log in. Or click **Play as guest** (this uses the built-in user `Admin`, password `1111`).
3. **New Game → Select Maze**, and click **number1**, **number2** or **number3** under a maze.
4. Click **Start game** and use the arrow keys.

**Important: always close the game with the Exit button on the login screen.** Users, scores and saved games are written to disk **only** then. If you close the window with ✕, everything from that session is lost.

## Where data is saved

The files are written in the folder the game runs from (the project folder when you use IntelliJ or Maven):

- `allUsers.txt`: the list of usernames
- one file per user, named after the username (no file extension). It is JSON with the password, high score, mazes, chosen number of lives and saved game.

Passwords are stored as plain text.

## Known issues

These come from testing the code:

- **Moving up crashes the drawing on Linux.** The code loads `Video/pacUp.mp4`, but the file is named `pacup.mp4`. On Windows and macOS the names match anyway. On Linux the screen stops updating correctly after you press the **Up** arrow. To fix it, rename `src/main/resources/Sample/Video/pacup.mp4` to `pacUp.mp4`.
- **"Play as guest" can fail** if the game was closed without the Exit button. Then `allUsers.txt` is empty, the `Admin` user is not created, and *New Game → Select Maze* does nothing (there is a `NullPointerException` in the console). To fix it, delete `allUsers.txt`.
- **The guest's progress is not kept.** Every Exit re-creates `Admin` with a score of 0 and no saved game.
- After **Load Game**, the score and lives at the top show 0 until you press a key, and some cells may look empty until then.
- `pom.xml` mixes JavaFX versions: `javafx-controls` and `javafx-fxml` are version 16, `javafx-media` is `17-ea+11` (an early-access build).
- `Sample.Controller.Main` is an older text-only version of the game (commands like `user login --username ...`). The graphic game uses its logic, but it doesn't work as a game by itself: after login it doesn't open the main menu.

## Project structure

| Folder | What it is |
|---|---|
| `src/main/java/Sample/Graphic/` | The JavaFX screens: `LoginMenu` (start), `Signup`, `MainMenu`, `StartNewGame`, `SelectMaze`, `SelectNewMaze`, `BuildMazeByUser`, `ScoreBoard`, `Battlefield` (the game screen) |
| `src/main/java/Sample/Controller/` | Game logic: `BattleField` (Pac-Man and ghost moves), `ThreadForBattlefield` (moves the ghosts), `GeneratingMaze` (random mazes), `LoadUsers` (save and load users), `SavingTheGame`, and the older text version in `Main` |
| `src/main/java/Sample/Model/User.java` | A user: password, score, mazes, lives, saved game |
| `src/main/resources/Sample/` | Images (`IMG`), music and sounds (`Music`) and Pac-Man videos (`Video`) |
| `pom.xml` | Maven build file |
| `target/` | Old build output (can be deleted) |

## Testing

I couldn't use Maven here, because the Maven package server wasn't reachable. Instead, I compiled the code with JavaFX 11 and Gson 2.10 and ran it on a virtual screen. JavaFX on this test machine couldn't play MP4 videos, so for the test only, I replaced the videos with still images.

I tested:
- sign up and log in
- play as guest
- selecting a maze and playing with the arrow keys
- Exit and Load Game
- saving users to disk and loading them after a restart
- the score board

The issues listed above come from these tests.
