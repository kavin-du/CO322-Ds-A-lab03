package application;
/**
 * 
 * Class for creating a word object
 *
 */
public class Word {
    private String wordName; 
    private int count; // count of the word

    public Word(String wordName){
        this.wordName = wordName;
        this.count = 1; // intial count is 1
    }
    // getters
    public String getName(){
        return this.wordName;
    }
    public int getCount(){
        return this.count;
    }
    // incrementing the count
    public void increment(){
        this.count++;
    }
}