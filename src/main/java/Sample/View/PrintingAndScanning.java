package Sample.View;

import java.util.Scanner;

public class PrintingAndScanning {
    private static Scanner scanner = new Scanner(System.in);

    public static String scan (){
        String command = scanner.nextLine();
        return command;
    }

    public static void print (String response){
        System.out.println(response);
    }
}
