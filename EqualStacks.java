/*------------------------------------------------------------
Program Name: EqualStacks.java

Problem: Given three different stacks each containing cylinders
of varying height, where the height of a stack can be changed
by removing the topmost cylinder any number of times, find the 
maximum possible height of the stacks such that all of the
stacks are exactly the same height.

Solution: Loop until one of the stacks is empty and pop off the
element from the heighest stack in each iteration of the loop.
After each removal check to see if the stacks have equal height. 
-------------------------------------------------------------*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class EqualStacks {

    public static void main(String[] args) {
        // Data is read into each of the stacks from stdin
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        
        // read in the values to arrays that each stack will hold
        int h1[] = new int[n1];
        for(int h1_i=0; h1_i < n1; h1_i++){
            h1[h1_i] = in.nextInt();
        }
        int h2[] = new int[n2];
        for(int h2_i=0; h2_i < n2; h2_i++){
            h2[h2_i] = in.nextInt();
        }
        int h3[] = new int[n3];
        for(int h3_i=0; h3_i < n3; h3_i++){
            h3[h3_i] = in.nextInt();
        }
        
        //initialize stacks
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        Stack<Integer> s3 = new Stack<Integer>();
        
        //keep track of the heights of each stack
        int s1_height = 0;
        int s2_height = 0;
        int s3_height = 0;
        
        //load data into stacks
        for(int i = h1.length - 1; i >= 0; i-- ){
            s1.push(h1[i]);
            s1_height += h1[i];
        }
        for(int i = h2.length - 1; i >= 0; i-- ){
            s2.push(h2[i]);   
            s2_height += h2[i];
        }
        for(int i = h3.length - 1; i >= 0; i-- ){
            s3.push(h3[i]);   
            s3_height += h3[i];
        }
       
        //variable for detecting if the max height has been found
        boolean height_reached = false;

        //loop while all of the stacks contain elements
        while(!s1.empty() && !s2.empty() && !s3.empty() ){
            
            //check to see if all of the heights are equal
            if(s1_height == s2_height && s1_height == s3_height ){
                System.out.println(s1_height);
                height_reached = true;
                break;
            }
            
            //find the tallest stack
            int max = findMax(s1_height, s2_height, s3_height);
            
            //pop from the tallest stack and adjust the height
            if( max == s1_height ){
                s1_height = s1_height - s1.pop();
            }
            else if( max == s2_height ){
                s2_height = s2_height - s2.pop();
            }
            else if( max == s3_height ){
                s3_height = s3_height - s3.pop();
            }
        }
        //if one of the stacks has a height of zero print 0
        if( !height_reached ){
            System.out.println(0);
        }
        in.close();
    }
    
    //helper function to find the max out of the three stacks
    public static int findMax(int x, int y, int z){
        return Math.max(x,Math.max(y,z));
    }
}