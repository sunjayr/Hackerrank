public class LinkedList{
    public static void main(String[] args){
        List test = new List();
        test.append(5);
        test.append(4);
        test.append(6);
        test.insert(3,1);
        
        test.printList();
    }
}
class List{
    ListNode head;
    public List(){
        head = null;
    }

    public void insert(int x, int position){
        int counter = 0;
        ListNode insert = new ListNode(x);
        if( this.head == null){
            return;
        }
        ListNode currentNode = this.head;
        if( position == 0 ){
            insert.next = this.head;
            this.head = insert;
        }
        while( currentNode.next != null && counter < position - 1 ){
            currentNode = currentNode.next;
            counter++;
        }
        if( currentNode.next == null ){
            currentNode.next = new ListNode(x);
        }
        else{
            ListNode temp = currentNode.next;
            currentNode.next = new ListNode(x);
            currentNode.next.next = temp;
        }
    }
    public void append(int x){
        if( this.head == null ){
            this.head = new ListNode(x);
        }
        else{
            ListNode currentNode = this.head;
            while( currentNode.next != null ){
                currentNode = currentNode.next;
            }
            currentNode.next = new ListNode(x);
        }
    }

    public void printList(){
        if( this.head == null ){
            return;
        }
        else{
            ListNode currentNode = this.head;
            while(currentNode != null ){
                System.out.print(currentNode.data + " -> ");
                currentNode = currentNode.next;
            }
            System.out.println("NULL");
        }
    }
}
class ListNode{
    public int data;
    public ListNode next;
    
    public ListNode(int data){
        this.data = data;
    }
}