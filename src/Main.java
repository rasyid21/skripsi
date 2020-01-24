import regex.Regex;

public class Main {

    public static void main(String[] args) {
        Regex regex = Regex.use();
        regex.match("sample.txt","sample2.txt");
        regex.most(3);
    }

}
