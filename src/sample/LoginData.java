package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginData {

    private static final LoginData instance = new LoginData();
    private final List<LoginItem> loginList = new ArrayList<>();


    public static LoginData getInstance() {
        return instance;
    }

    public List<LoginItem> getLoginList() {
        return loginList;
    }

    public void loadData() throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("loginDetails.txt"))) {
            String input;
            while ((input = bufferedReader.readLine()) !=null) {
                String[] strings = input.split(" ");
                LoginItem item = new LoginItem(strings[0],strings[1]);
                loginList.add(item);
            }
        }catch (IOException e) {
            throw new IOException("Input/Output Exception");
        }
    }

    public void saveData() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("loginDetails.txt"))) {
            for (LoginItem item : loginList) {
                bufferedWriter.write(item.getUsername() + " " + item.getPassword());
                bufferedWriter.newLine();
            }
        }
    }
}
