package com.prog2.stack;

/**
 * A first in last out (FILO) or last in first out (LIFO) data structure.
 *
 * @param <T>
 */

public class Stack<T> {

    static final int MAX = 1000;
    private int top;
    T[] arr;

    public Stack(){
        // when the array is empty, top is not valid
        top = -1;

        // indices are 0-999. A total of 1000 slots
        arr = (T[]) new Object[MAX];

    }

    // isEmpty : boolean
    public boolean isEmpty() {
        return top < 0;
    }

    // isFull : boolean
    public boolean isFull(){
        return top >= MAX -1;
    }

    // push : void
    public void push(T item){
        if(isFull()){
            System.out.println("Stack is full");
            return;
        }

        arr[++top] = item;
    }

    // pop : T
    public T pop(){
        if(isEmpty()){
            System.out.println("Stack is empty");
            return null;
        }

        return arr[top--];
    }

    // peek : T
    public T peek(){

        if(isEmpty()){
            System.out.println("Stack is empty");
            return null;
        }

        return arr[top];

    }


}