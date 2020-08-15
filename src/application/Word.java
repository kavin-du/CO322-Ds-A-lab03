package application;
public class Word {
    private String wordName;
    private int count;

    public Word(String wordName){
        this.wordName = wordName;
        this.count = 1;
    }
    public String getName(){
        return this.wordName;
    }
    public int getCount(){
        return this.count;
    }
    public void increment(){
        this.count++;
    }
}