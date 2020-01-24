package regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class In {

    private static In instance;

    private In(){}

    private static void init(){
        if (instance == null)
            instance = new In();
    }

    private static void deallocate(){
        if (instance != null)
            instance = null;
    }

    static In use(){
        init();
        return instance;
    }

    static In close(){
        deallocate();
        return instance;
    }

    String getText(String url){
        File file = new File(url);
        StringBuilder toReturn = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String temp;
            while ((temp = reader.readLine()) != null){
                toReturn.append(temp).append(" ");
            }
            reader.close();
            fr.close();
            return toReturn.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
