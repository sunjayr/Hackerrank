/*---------------------------------------------------------
Program Name: BinarySearchTree.java

Purpose: A simple implementation of a binary search tree
with an insert function and functions to print the tree 
in level order and in order
----------------------------------------------------------*/
import java.util.*;

public class BinarySearchTree{
    public static void main(String[] args){
        Tree test = new Tree();
        test.insert(3);
        test.insert(1);
        test.insert(2);
        test.insert(4);
        test.insert(5);
        test.printLevelOrder();
        test.printInOrder();
    }
}
class Tree{
    public TreeNode root;

    public void insert(int x){
        if( root == null ){
            root = new TreeNode(x);
        }
        else{
            TreeNode currentNode = root;
            while( true ){
                if( x > currentNode.data ){
                    if( currentNode.right == null ){
                        currentNode.right = new TreeNode(x);
                        break;
                    }
                    else{
                        currentNode = currentNode.right;
                    }
                }
                else{
                    if( currentNode.left == null ){
                        currentNode.left = new TreeNode(x);
                        break;
                    }
                    else{
                        currentNode = currentNode.left;
                    }
                }
            }
        }

    }

    public void printLevelOrder(){
        ArrayList<TreeNode> toVisit = new ArrayList<TreeNode>();
        if(this.root == null ){
            System.out.println("No elements in Tree!");
            return;
        }
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            TreeNode currentNode = toVisit.get(0);
            toVisit.remove(0);
            System.out.print(currentNode.data + " ");
            if( currentNode.left != null ){
                toVisit.add(currentNode.left);
            }
            if( currentNode.right != null ){
                toVisit.add(currentNode.right);
            }
            
        }
        System.out.println();
    }
    public void printInOrder(){
        printInOrder(this.root);
    }
    
    public void printInOrder(TreeNode node){
        if( node == null ){
            return;
        }
        printInOrder(node.left);
        System.out.println(node.data);
        printInOrder(node.right);

    }
}
class TreeNode{
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x){
        this.data = x;
    }
}