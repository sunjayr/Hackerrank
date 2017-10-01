/*-----------------------------------------------------------------
Program Name: BalancedBrackets.java
Problem: Given a string of braces,parantheses, and brackets how do
you determine if it is balanced, meaning the braces and brackets 
match up correctly similar to programming syntax.

Solution: Use a Stack to hold opening braces and given the appearance
of a closing brace pop from the stack and see if the opening brace is
returned. If not return "NO". If the stack is empty, return "NO".
Upon exit from looping over the string, if the stack is not empty,
return "NO". If each of these conditions have passed, return "YES"
------------------------------------------------------------------*/
import java.util.*;

public class BalancedBrackets{
    public static void main(String[] args){
        //read string of braces from stdin
        Scanner input = new Scanner(System.in);
        String in = input.next();
        System.out.println(isBalanced(in));
    }

    static String isBalanced(String s){
        //if its an odd length string its not balanced
        if( s.length() % 2 != 0 ){
            return "NO";
        }
        //initialize stack
        Stack<Character> stack = new Stack<Character>();
        
        //loop through the entire string
        for( int i = 0; i < s.length(); i++){
            //push all opening braces onto the stack
            if( s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }
            //checks to see if the braces match up coming off of the stack
            else{
                if( stack.isEmpty() ){
                    return "NO";
                }
                
                if( s.charAt(i) == '}' && stack.pop() != '{'){
                    return "NO";
                }
                else if( s.charAt(i) == ']' && stack.pop() != '['){
                    return "NO";
                }
                else if( s.charAt(i) == ')' && stack.pop() != '('){
                    return "NO";
                }
            }
        }
        //check to make sure all braces have been matched
        if(!stack.isEmpty()){
            return "NO";
        }
        else{
            return "YES";   
        }
    } 
}