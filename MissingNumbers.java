/*------------------------------------------------------------------------
Program Name: MissingNumbers.java
Problem: Numeros, the Artist, had two lists A and B, such that B was a permutation of A.
Numeros was very proud of these lists. Unfortunately, while transporting them
from one exhibition to another, some numbers were left out of B.
Can you find the missing numbers?

Solution: Use two hashtables to keep track of the numbers and the frequency
of each number. Search through the second hashtable for keys with different
frequencies or missing values in the first hashtable.
------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MissingNumbers {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        File f = new File("Input.txt");
        FileReader r = new FileReader(f);
        BufferedReader reader = new BufferedReader(r);

        int n = Integer.parseInt(reader.readLine());
        String numbers = reader.readLine();
        String[] numbersArray = numbers.split(" ");
        
        int[] firstList = new int[n];
        for(int i = 0; i < numbersArray.length; i++){
            firstList[i] = Integer.parseInt(numbersArray[i]);
        }

        int m = Integer.parseInt(reader.readLine());
        
        int[] secondList = new int[m];
        String[] line2 = reader.readLine().split(" ");
        for( int i = 0; i < line2.length; i++){
            secondList[i] = Integer.parseInt(line2[i]);
        }
        
        findNumbers(firstList, secondList);
        reader.close();
    }

    public static void findNumbers(int[] firstList, int[] secondList){
        HashMap<Integer, Integer> firstMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> secondMap = new HashMap<Integer, Integer>();
        ArrayList<Integer> missingNumbers = new ArrayList<Integer>();
        
        firstMap = createHashMap(firstList);
        secondMap = createHashMap(secondList);

        for( int key : secondMap.keySet() ){
            if( !firstMap.containsKey(key)){
                System.out.println("Condition entered");
                missingNumbers.add(key);
            }
            else{
                if( (int)firstMap.get(key) != (int)secondMap.get(key)){
                    System.out.println("Second Condition entered");
                    missingNumbers.add(key);
                }
            }
        }
        Collections.sort(missingNumbers);
        for(int element : missingNumbers){
            System.out.print(element + " ");
        }
    }

    public static HashMap<Integer, Integer> createHashMap(int[] numbers){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for( int i = 0; i < numbers.length; i++){
            if( map.containsKey(numbers[i])){
                map.put(numbers[i], map.get(numbers[i])+1);
            }
            else{
                map.put(numbers[i], 1);
            }
        }
        return map;
    }
}