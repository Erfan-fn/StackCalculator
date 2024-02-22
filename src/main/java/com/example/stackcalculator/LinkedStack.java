package com.example.stackcalculator;

//My Implemented SinglyLinkedList that implement Stack Data structure

public class LinkedStack <E> implements IQueue {

    private Node head;
    private int top=-1;

    //Node class-------------------------------------------------------------------------

    public static class Node<E> {

        private E element;
        private Node next;

        public Node(E element) {
            this.element = element;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    //end of the Node class------------------------------------------------------------------

    public E first() throws IllegalStateException{
        if (isEmpty()){throw new IllegalStateException("Stack is empty");}
        return (E) this.head.element;
    }

    public void addFirst(E element) {
        Node newNode = new Node(element);

        if (this.top == -1) {
            head = newNode;
            newNode.next = null;

        } else {
            newNode.next = head;
            head = newNode;
        }
        top++;
    }

    public E removeFirst() {

        Object first ;

        if (this.top > 0) {

            first=head.element;
            head = this.head.next;
        } else {
            first= head.element;
            this.head.next = null;
            this.head = null;
        }
        top--;

        return (E) first;
    }

    @Override
    public void push(Object value) {
        addFirst((E) value);
    }

    @Override
    public E peek() {
        return first();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public int size() {
        return this.top+1;
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public int getTop() {
        return this.top;
    }

}
