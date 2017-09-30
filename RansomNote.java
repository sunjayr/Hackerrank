/*--------------------------------------------------------------------
Program Name: Ransom Note.java
Problem: Given a string of words in a magazine, and given a string of
words in a ransom note, determine if the ransom note can be made 
from the words in the magazine.

Solution: Use two Hashtables to hold the words and the counts of 
each word for the magazine and the ransom note. Determine if each 
word in the ransom note appears in the magazine and has a count less
than that of the word in the magazine. If any of these conditions 
are not met return false, otherwise return true.
--------------------------------------------------------------------*/

import java.util.*;

public class RansomNote{
    public static void main(String[] args){
        //use scanner to read from stdin
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        String magazine = in.nextLine();
        String note = in.nextLine();

        in.close();

        RansomSolution test = new RansomSolution(magazine, note);
        boolean result = test.solve();
        
        //output
        if( result ){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
class RansomSolution{
    //declare hashtables;
    private HashMap<String, Integer> magazineMap;
    private HashMap<String, Integer> noteMap;

    public RansomSolution(String magazine,String ransom){
       //initialize hashtables
        magazineMap = new HashMap<String,Integer>();
        noteMap = new HashMap<String,Integer>();
        
        //load magazine hashtable
        for (String var : magazine.split(" ")) {
            if( magazineMap.containsKey(var)){
                magazineMap.put(var, magazineMap.get(var)+1);
            }
            else{
                magazineMap.put(var, 1);
            }
        }

        //load ransom note hashtable
        for( String word : ransom.split(" ") ){
            if( noteMap.containsKey(word)){
                noteMap.put(word, noteMap.get(word)+1);
            }
            else{
                noteMap.put(word, 1);
            }
        }
    }

    //function to solve the cases
    public boolean solve(){
        
        //iterate through each distinct word in the ransom note
        for( String ransomWord : noteMap.keySet()){
            if( !magazineMap.containsKey(ransomWord)){
                return false;
            }
            else{
                if( noteMap.get(ransomWord) > magazineMap.get(ransomWord)){
                    return false;
                }
            }
        }
        return true;
    }
}