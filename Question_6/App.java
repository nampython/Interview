package com.code.DSA.Question_6;


public class App {
    public static void main(String[] args) {
        LinkedList llist = new LinkedList();
        llist.addNode(6);
        llist.addNode(6);
        llist.addNode(1);
        llist.addNode(1);
        llist.addNode(3);
        llist.addNode(4);
        llist.addNode(4);
        llist.addNode(5);
        
        

        System.out.println("List before removal of duplicates");
        llist.printList();


        llist.deletepDulicates();
        System.out.println("List after removal of elements");
        llist.printList();
    }
}
