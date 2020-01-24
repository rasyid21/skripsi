package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    private static Regex instance;

    private In input;
    private Hash hash;

    private Regex(){
        input = In.use();
        hash  = Hash.use();
    }

    private static void init(){
        if (instance == null)
            instance = new Regex();
    }

    private static void deallocate(){
        if (instance != null){
            instance.input = In.close();
            instance.hash  = Hash.close();
            instance = null;
        }
    }

    public static Regex use(){
        init();
        return instance;
    }

    public static Regex close(){
        deallocate();
        return instance;
    }

    public void match(String...file_urls){
        String text;
        for (String file:file_urls){
            text = input.getText(file);
            regex(text);
        }
        hash.print();
    }

    private void regex(String text){
        String[] words = text.split("\\s+");
        Pattern pattern;
        Matcher matcher;
        for (String word:words){
            if (!hash.hasSearched(word)){
                pattern = Pattern.compile("\\b("+word+")\\b");
                matcher = pattern.matcher(text);
                while (matcher.find()){
                    hash.add(matcher.group(1));
                }
            }
        }
    }

    public void most(int index){
        hash.most(index);
    }

}
