package com.prog2.datastructures;

import com.prog2.interfaces.List;

/**
 * A doubly linked list implementation. Nodes have a previous connection AND
 * a next connection.
 *
 * There is more memory overhead in a doubly linked list than a singly linked list.
 * This is useful in only particular cases where forward AND backward traversal
 * are necessary.
 *
 * For example, a browser uses a doubly linked list in order to allow you to have a
 * forward and a backward button.
 *
 * @param <T>
 */
public class DoublyLinkedList<T> implements List<T> {

    private int count;
    private Node head, tail;

    public DoublyLinkedList(){
        count = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean isEmpty() {
        return count ==0;
    }

    @Override
    public int getLength() {
        return count;
    }

    @Override
    public T get(int index) {

        // is the index valid?
        if (index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node ptr = head;

        while(i < index && ptr.next != null){
            ptr = ptr.next;
            i++;
        }

        return ptr.data;
    }

    @Override
    public List prepend(T element) {

        Node n = new Node(element);

        if(count == 0){
            head = n;
            tail = n;
        } else {
            Node oldhead = head;
            n.next = head;
            head = n;
            oldhead.prev = head;
        }

        count++;

        return this;
    }

    @Override
    public List append(T element) {

        // make the new node
        Node n = new Node(element);

        // what if the list is empty
        if(isEmpty()){
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }

        // increment
        count++;

        return this;
    }

    @Override
    public List insert(int index, T element) {

        if(index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node ptr = head;
        Node newNode = new Node(element);

        for (int j = 0; j < index-1; j++) {
            ptr = ptr.next;
        }

        newNode.next = ptr.next;
        ptr.next = newNode;

        count++;
        return this;
    }

    @Override
    public List remove(int index) {

        if(index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }

        // handle cases if the list is empty or if inserting at the end of list
        if(count==0){
            // removing head
            head = head.next;
        } else if (index == count-1) {
            // removing tail
            Node ptr = head;

            for (int i = 0; i < getLength()-1; i++) {
                ptr = ptr.next;
            }

            tail = ptr;
            ptr.next = null;

        } else {
            // removing an index between head and tail
            int i = 0;
            Node ptr = head;
            Node target;

            while (i < index - 1 && ptr.next != null) {
                ptr = ptr.next;
                i++;
            }

            target = ptr.next;
            ptr.next = target.next;
            count--;
        }

        return this;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < getLength(); i++) {
            if(get(i) == element){
                return i;
            }
        }
        return -1;
    }
    private class Node{

        private T data;
        private Node next;
        private Node prev;

        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }

    }
}
