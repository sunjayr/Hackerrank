import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
This program implements a queue using two stacks. It takes in user input from stdin.
The first line of user input is an integer which represents the number of queries to
be made on the queue. The types of queries that can be made are an insert represented by
1 followed by x, the value to insert. Dequeue is represented by 2, and peek is represented
by 3.
*/

public class TwoStack {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TwoStackQueue queue = new TwoStackQueue();
        int n = input.nextInt();
        for( int i = 0; i < n; i++){
            int query = input.nextInt();
            if( query == 1){
                queue.enqueue(input.nextInt());
            }
            else if( query == 2){
                queue.dequeue();
            }
            else if(query == 3){
                queue.printElement();
            }
        }
        
    }
}
class TwoStackQueue {
    
    public Stack<Integer> stack1;
    public Stack<Integer> stack2;
    
    public TwoStackQueue(){
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    
    public void enqueue(int x){
        stack1.add(x);
    }
    
    public void dequeue(){
        if(stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        
        if(!stack2.empty()){
            stack2.pop();
        }
    }
    
    public void printElement(){
        if(stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        
        if(!stack2.empty()){
            System.out.println(stack2.peek());
        }
        
    }
    
}