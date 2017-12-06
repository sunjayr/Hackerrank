import java.util.*;
import java.io.*;

/*##############################################################################
Program Name: TrieFunctions.java
Purpose: This program is an implementation of a trie for autocomplete functionality.
It implements functions to add and find elements, and also implements functions 
that given a prefix, will find the number of words containing the prefix in the 
trie as well as the number of words containing the prefix. The program reads from
a file of queries and executes them on the trie. These queries include adding 
a word, finding a word, retrieving matches to a prefix, and printing the trie

Classes: TrieFunctions - main driver class with main method
         Trie - contains all of the functionality of the trie 
         TrieNode - an individual node that is inserted into the trie
##############################################################################*/

/*----------------------------------------------------------------------------
Class Name: TrieFunctions
Purpose: A driver class that holds the main method of the code
----------------------------------------------------------------------------*/
public class TrieFunctions{
    public static void main(String[] args) throws FileNotFoundException, IOException{
        //open file containing queries
        File inputFile = new File("trieInput.txt");
        FileReader reader = new FileReader(inputFile);
        BufferedReader buffReader = new BufferedReader(reader);
        
        //create trie
        Trie trie = new Trie();

        //read from queries and execute functions on trie
        String line;
        while((line = buffReader.readLine())!= null){
            if(line.split(" ")[0].equals("add")){
                trie.add(line.split(" ")[1]);
            }
            else if( line.split(" ")[0].equals("print")){
                trie.printTrie();
            }
            else if( line.split(" ")[0].equals("retrieve")){
                int matches = trie.findMatches(line.split(" ")[1]);
                System.out.println("There are " + matches + " matches:");
                trie.printMatches(line.split(" ")[1]);
            }
            else if(line.split(" ")[0].equals("find")){
                String word = line.split(" ")[1];
                boolean result = trie.find(word);
                if( result ){
                    System.out.println(word + " found in the trie");
                }
                else{
                    System.out.println(word + " is not found in the trie");
                }
            }
        }
        buffReader.close();
    }
}
/*----------------------------------------------------------------------------
Class Name: Trie
Purpose: This class contains all the functionality of the trie in its definition.
Attributes: root - type TrieNode -> a pointer to the root node of the trie
Functions: add(), find(), findMatches(), printMatches(), printTrie()
----------------------------------------------------------------------------*/
class Trie{
    public TrieNode root;

/*----------------------------------------------------------------------------
Constructor Name: Trie()
Purpose: Initializes the root node upon creation of the Trie object  
----------------------------------------------------------------------------*/
    public Trie(){
        root = new TrieNode('\0');
    }
/*----------------------------------------------------------------------------
Function Name: add()
Purpose: Adds a word into the trie
Parameters: word - String type
Return: void
----------------------------------------------------------------------------*/
    public void add(String word){
        //set currentNode to root
        TrieNode currentNode = this.root;
        
        //starting at root loop through word and traverse through trie adding nodes when needed
        for( int i = 0; i < word.length(); i++){
            //increment the number of words in the subtree of each node as we add to the trie
            currentNode.wordCounter++;
            
            //check if there is a child otherwise add a node to the children 
            if( currentNode.hasChild(word.charAt(i))){
                currentNode = currentNode.children.get(word.charAt(i));
            }
            else{
                TrieNode newChild = new TrieNode(word.charAt(i));
                currentNode.addChild(word.charAt(i), newChild);
                currentNode = newChild;
            }
            
        }
        //increment the count of the final node and set the isWord flag to true storing the word
        currentNode.wordCounter++;
        currentNode.isWord = true;
        currentNode.wordStored = word;
    }
/*----------------------------------------------------------------------------
Function Name: find
Purpose: determines if a word is inside the trie
Parameters: word - type String
Return: boolean   
----------------------------------------------------------------------------*/
    public boolean find(String word){
        TrieNode currentNode = this.root;
        for( int i = 0; i < word.length(); i++){
            if( currentNode.hasChild(word.charAt(i))){
                currentNode = currentNode.children.get(word.charAt(i));
            }
            else{
                return false;
            }
        }
        return true;
    }
/*----------------------------------------------------------------------------
Function Name: printMatches()
Purpose: prints out the words in the trie starting with the prefix
Parameters: prefix - type String
Return: void
----------------------------------------------------------------------------*/    
    public void printMatches(String prefix){
        //initialize the matches arraylist to hold all the matches
        ArrayList<String> matches = new ArrayList<String>();
        TrieNode currentNode = this.root;
        
        //flag to see if a match has not been found
        boolean noMatch = false;
        
        //get to the node holding the last character of the prefix
        for( int i = 0; i < prefix.length(); i++){
            if(currentNode.hasChild(prefix.charAt(i))){
                currentNode = currentNode.children.get(prefix.charAt(i));
            }
            else{
                noMatch = true;
                break;
            }

        }

        //if the prefix is not in the trie return
        if( noMatch ){
            System.out.println("The prefix " + prefix + " has no matches in the Trie");
            return;
        }

        //if the prefix is a word return
        if( currentNode.children.isEmpty()){
            System.out.println("The prefix " + prefix + " is not a prefix of any words in the Trie");
            return;
        }
        
        //perform a depth first search starting from the end node of the trie
        Stack<TrieNode> stack = new Stack<TrieNode>();
        TrieNode traverseNode;
        stack.push(currentNode);
        while( !stack.isEmpty()){
            traverseNode = stack.pop();
            if( traverseNode.isWord ){
                matches.add(traverseNode.wordStored);
            }
            for( TrieNode temp : traverseNode.children.values()){
                stack.push(temp);
            }
        }

        //print out the matches to the prefix
        for( int i = 0; i < matches.size(); i++){
            System.out.println(matches.get(i));
        }
        
    }
/*----------------------------------------------------------------------------
Function Name: findMatches()
Purpose: returns the number of words in the trie that start with the prefix
Parameters: String prefix
Return: int 
----------------------------------------------------------------------------*/
    public int findMatches(String prefix){
        TrieNode currentNode = this.root;
        for( int i = 0; i < prefix.length(); i++){
            if( currentNode.hasChild(prefix.charAt(i))){
                currentNode = currentNode.children.get(prefix.charAt(i));
            }
            else{
                return 0;
            }
        }
        return currentNode.wordCounter;
    }
/*----------------------------------------------------------------------------
Function Name: printTrie()
Purpose: Prints the characters in the trie out using depth first search
Parameters: None
Return: void
----------------------------------------------------------------------------*/
    public void printTrie(){
        Stack<TrieNode> stack = new Stack<TrieNode>();
        stack.push(this.root);
        TrieNode currentNode;
        while(!stack.isEmpty()){
            currentNode = stack.pop();
            System.out.print(currentNode.letter + " ");
            for( TrieNode node : currentNode.children.values()){
                stack.push(node);
            }
        }
        System.out.println();

    }
}
/*----------------------------------------------------------------------------
Class Name: TrieNode
Purpose: Contains the functionality for a TrieNode that gets inserted into
the trie. 
Attributes: children - type HashMap<Character, Node> -> holds the information
of the children in a hashmap for easy access to get to the next node
letter - type char -> holds the letter of the node
wordCounter - type int -> how many words are in subtree of the node
isWord - type boolean -> a flag for if the currentNode is a word
wordStored - type String -> if the node is the end of a word it holds the 
word that was entered into the trie.
----------------------------------------------------------------------------*/
class TrieNode{
    public HashMap<Character, TrieNode> children;
    public char letter;
    public int wordCounter;
    public boolean isWord;
    public String wordStored;

/*----------------------------------------------------------------------------
Constructor Name: TrieNode()
Purpose: initializes the TrieNode
Parameters: ltr - type char
----------------------------------------------------------------------------*/
    public TrieNode(char ltr){
        children = new HashMap<Character, TrieNode>();
        this.letter = ltr;
        this.wordCounter = 0;
        this.isWord = false;
        this.wordStored = "";
    }
/*----------------------------------------------------------------------------
Function Name: hasChild()
Purpose: determines if a given character is a child of the node
Parameters: test - type char
Return: boolean
----------------------------------------------------------------------------*/
    public boolean hasChild(char test){
        if( children.containsKey(test)){
            return true; 
        }
        else{
            return false;
        }
    }
/*----------------------------------------------------------------------------
Function Name: addChild()
Purpose: adds a child to this node's pointers to children
Parameters: test - type char
            newChild - type TrieNode
Return: void
----------------------------------------------------------------------------*/
    public void addChild(char test, TrieNode newChild){
        children.put(test, newChild);
    }
} 