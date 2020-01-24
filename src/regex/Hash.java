package regex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

class Hash {

    private static Hash instance;

    private HashMap<String,Integer> word_hash;
    private ArrayList<Compare> list;
    private int totalWords;

    private Hash(){
        word_hash  = new HashMap<>();
        list       = new ArrayList<>();
        totalWords = 0;
    }

    private static void init(){
        if (instance == null)
            instance = new Hash();
    }

    private static void deallocate(){
        if (instance != null){
            instance.word_hash    .clear();
            instance.word_hash  = null;
            instance.totalWords = 0;
            instance.list         .clear();
            instance.list       = null;
            instance            = null;
        }
    }

    static Hash use(){
        init();
        return instance;
    }

    static Hash close(){
        deallocate();
        return instance;
    }

    public void add(String key){
        totalWords++;
        if (word_hash.containsKey(key)){
            int val = word_hash.get(key);
            val++;
            word_hash.replace(key,val);
            return;
        }
        word_hash.put(key,1);
    }

    public boolean hasSearched(String key){
        return word_hash.containsKey(key);
    }

    public void print(){
        Collection<String> keys = word_hash.keySet();
        System.out.println("total kata : "+totalWords+" kata");
        System.out.println("frequensi  :");
        Compare cmp;
        for (String key:keys){
            cmp = new Compare(key,(word_hash.get(key)*100)/totalWords);
            sort(cmp);
            System.out.println(key+"\t:\t"+word_hash.get(key)+"\t:\t"+cmp.getFrekuensi()+"%");
        }
    }

    private void sort(Compare node){
        if (list.isEmpty()){
            list.add(node);
            return;
        }
        for (int i = 0; i < list.size();i++){
            if (node.getFrekuensi() > list.get(i).getFrekuensi()){
                list.add(i,node);
                break;
            }
            if (i == list.size()-1){
                list.add(list.size(),node);
                break;
            }
        }
    }

    public void most(int index){
        System.out.println();
        System.out.println(index+" kata yang paling sering muncul.");
        for (int i = 0; i < index;i++){
            System.out.println(list.get(i).getKata()+"\t:\t"+list.get(i).getFrekuensi()+"%");
        }
    }
}

class Compare{
    private String kata;
    private int frekuensi;

    Compare(String kata,int frekuensi){
        this.kata = kata;
        this.frekuensi = frekuensi;
    }

    public String getKata() {
        return kata;
    }

    public int getFrekuensi() {
        return frekuensi;
    }
}
