package ex3;

public class Counter {
    private int count;

    public Counter(int count){
        this.count = count;
    }
    // add number to current count.
    public void increase(int number){
        this.count = count+number;

    }
    // subtract number from current count.
    public void decrease(int number){
        this.count = count - number;
    }

    // get current count.
    public int getValue(){
        return this.count;
    }
}
