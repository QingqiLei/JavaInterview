package stack;

import java.util.Stack;

public class MiniStack {
    Stack<Integer> elem;
    Stack<Integer> min;
    public MiniStack(){
        elem = new Stack<>();
        min = new Stack<>();
    }

    public void push(int data){
        elem.push(data);
        if(min.isEmpty())
            min.push(data);
        if(data < min.peek())
            min.push(data);
    }

    public int pop(){
        int topData = elem.peek();
        elem.pop();
        if(topData == this.min())
            min.pop();
        return topData;
    }

    public int min(){
        if(min.isEmpty())
            return Integer.MAX_VALUE;
        else return min.peek();
    }
}
