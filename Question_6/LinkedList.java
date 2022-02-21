package com.code.DSA.Question_6;

public class LinkedList {
    Node head;
    
    public void addNode(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void deletepDulicates(){
        Node current = head;

        while(current != null) {
            Node temp = current;
            while(temp != null && temp.data == current.data) {
                temp = temp.next;
            }
            current.next = temp;
            current = current.next;
        }
    }
}
