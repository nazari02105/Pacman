package Sample.Controller;

import Sample.Model.User;
import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class LoadUsers {


    public static void loadAllUsers () throws IOException {
        File file = new File("allUsers.txt");
        if (file.exists()){
            FileReader xxx = new FileReader("allUsers.txt");
            String x = "";
            int ch;
            while ((ch=xxx.read()) != -1)
                x += (char)ch;
            xxx.close();


            if (!x.equals("")) {
                String[] users = x.split(",");
                for (int i = 0; i < users.length; ++i) {
                    FileReader xxx1 = new FileReader(users[i]);
                    String x1 = "";
                    int ch1;
                    while ((ch1 = xxx1.read()) != -1)
                        x1 += (char) ch1;
                    xxx1.close();

                    Gson gson = new Gson();
                    User user = gson.fromJson(x1, User.class);
                    User.allUsers.put(user.getName(), user);
                }
            }
        }
        else{
            new FileWriter("allUsers.txt");
            new User("Admin", "1111");
        }
    }


    public static void saveAllUsers () throws IOException {
        new User("Admin", "1111");
        String allUsersToSave = "";
        for (Map.Entry<String, User> entry : User.allUsers.entrySet()){
            allUsersToSave += entry.getKey();
            allUsersToSave += ",";
            Gson gson = new Gson();
            String everyTime = gson.toJson(entry.getValue());


            FileWriter fw = new FileWriter(entry.getKey());
            fw.write(everyTime);
            fw.close();
        }

        FileWriter fw = new FileWriter("allUsers.txt");
        fw.write(allUsersToSave);
        fw.close();
    }


}
