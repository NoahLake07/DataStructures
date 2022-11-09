package com.prog2.datastructures;

import com.prog2.interfaces.List;

/**
 * in a singly linked list, nodes will only know where the next node
 * in the chain is. This is sometimes preferable because there is less
 * memory overhead as compared to a double linked list.
 *
 * Advantages VS Array/ArrayList
 * - True dynamic Size
 * - Ease of insertion/deletion
 *
 * Disadvantages VS Array/ArrayList:
 * - Random access is not allowed, elements MUST be accessed sequentially.
 * - Extra memory space must be used for a pointer to each item in the list
 * - Not cache friendly, as linked lists do not store data in continuous
 * memory locations.
 */
public class SinglyLinkedList<T> implements List<T> {

    private int count;
    private Node head, tail;

    public SinglyLinkedList(){
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
            n.next = head;
            head = n;
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

        // the information this node represents
        private T data;

        // the next node in the chain
        private Node next;

        Node(T data){
            this.data = data;
            this.next = null;

        }
    }

}
