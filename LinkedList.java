package LinkedList;

public class LinkedList {
    public static class Node {
        int data;
        Node next;

        public Node(int data) { //Constructor
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data) {
        //step1 -> Create new Node
        Node newNode = new Node(data);
        size++;
        if(head == null) { //if there is no node
            head = tail = newNode;
            return;
        }
        //step2 -> newNode next = head
        newNode.next = head; //link

        //step3 -> head = newNode
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if(head == null) { //empty linkedlist
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void printLL() {
        if(head == null) {
            System.out.println("LL is empty");
            return;
        }
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data +"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addMiddle(int idx, int data) {
        if(idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while(i < idx-1) {
            temp = temp.next;
            i++;
        }

        //i = idx-1; temp -> prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst() {
        if(size == 0) {
            System.out.println("LL is empty");
            return Integer.MAX_VALUE;
        }
        else if(size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data; 
        head = head.next; //the second node now becomes the head
        size--;
        return val;
    }

    public int removeLast() {
        if(size == 0) {
            System.out.println("LL is empty");
            return Integer.MAX_VALUE;
        }
        else if(size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        //prev node: idx = size-2
        Node prev = head;
        for(int i = 0; i<size-2; i++) { //loop to get prev
            prev = prev.next;
        }
        int val = tail.data;
        prev.next = null;
        prev = tail;
        size--;
        return val;
    }

    public int linearSearch(int key) { //TC-> O(N)
        Node temp = head;
        int index = 0;
        while(temp!=null) {
            if(temp.data == key) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }

    public int helper(Node head, int key) { //helper fxn for recursive search
        if(head == null) {
            return -1;
        }
        if(head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        if(idx == -1) {
            return -1;
        }
        return idx+1;
    } //TC-> O(N), SC-> O(N) {bcz of call stack}
    public int recursiveSearch(int key) {
        return helper(head, key);
    }
    
     public void reverse() {
        Node prev = null; //head ke pehle null hota hai
        Node curr = tail = head;
        Node next;

        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
     } //TC-> O(N), SC-> O(1)

    public void deleteNthfromEnd(int n) {
        if(head == null) {
            return;
        }
        // Calculate size
        int size = 0;
        Node temp = head;
        while(temp!=null) {
            temp = temp.next;
            size++;
        }

        if(n == size) { //Corner Case
            head = head.next; //removeFirst
            return;
        }

        //to reach (size-n)th idx {prev}
        int i = 1;
        int idxToFind = size-n; //finding prev node 
        Node prev = head;
        while(i < idxToFind) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next; //prev ke next ka next
        return;
    } //TC-> O(L) {L is the no of nodes}, SC-> O(1) 

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        ll.addFirst(2);
        ll.addFirst(1);
        // TC -> O(1) - while adding elements 
        ll.addLast(4);
        ll.addLast(5);
        // TC -> O(1) - while adding Last Elements

        ll.addMiddle(2, 3);

        ll.printLL();
        //System.out.println(ll.size);

        // ll.removeFirst();
        // ll.printLL();

        // ll.removeLast();
        // System.out.println(ll.removeLast());
        // ll.printLL();
        // System.out.println(ll.size);

        //System.out.println(ll.linearSearch(3));

        // System.out.println(ll.recursiveSearch(5));

        // ll.reverse();
        // ll.printLL();

        ll.deleteNthfromEnd(3);
        ll.printLL();
    } 
}
