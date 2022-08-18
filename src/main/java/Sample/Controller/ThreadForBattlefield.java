package Sample.Controller;

public class ThreadForBattlefield extends Thread{
    public BattleField battleField;
    public int finished = 1;
    @Override
    public void run() {
        while (finished == 1){
            try {
                battleField.moveTheSouls();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
