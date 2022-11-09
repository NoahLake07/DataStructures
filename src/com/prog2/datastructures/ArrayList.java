package com.prog2.datastructures;

import com.prog2.interfaces.List;

/**
 * An array list uses a dynamic array to store elements
 * The only reason an Array List can behave like a list is because it implements the 'List' interface.
 * ArrayList uses less memory* than other lists and is faster to access
 *
 * ArrayLists are, however, slower to manipulate.
 * @param <T>
 */
public class ArrayList<T> implements List<T> {


    private T[] list;
    private int count;


    public ArrayList(){
        this(20);
    }

    public ArrayList(int maxSize){
        count = 0;
        list = (T[])(new Object[maxSize]);
    }



    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int getLength() {
        return count;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= count){
            return null;

        }
        // pass back the T at index
        return list[index];
    }

    @Override
    public List prepend(T element) {

        if(isEmpty()){
            list[0] = element;
        }
        // looping backwards
        for (int i = count-1; i >= 0 ; i--) {
            list[i + 1] = list[i];
        }

        // set the beginning of the array to the given element
        list[0] = element;
        count++;


        return this;
    }

    @Override
    public List append(T element) {

        if(isEmpty()){
            list[0] = element;
        }

        list[count] = element;
        count++;

        return this;
    }

    @Override
    public List insert(int index, T element) {

        if(index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }

        // looping backwards
        for (int i = count-1; i >= index ; i++) {
            list[i + 1] = list[i];
        }

        list[index] = element;
        count++;

        return this;
    }

    @Override
    public List remove(int index) {

        if (index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < count; i++) {
            list[i] = list[i+1];
        }

        return this;
    }

    @Override
    public int indexOf(T element) {

        if(isEmpty()){
            return 0;
        }

        for (int i = 0; i < count; ++i) {
            if(list[i] == element){
                return i;
            }
        }

        return -1;
    }

    private void checkForGrowth(){
        // the array is full
        if(count == list.length){
            // make a new bigger array
            T[] temp = (T[])(new Object[count*2]);

            // copy the old array into the new one
            for (int i = 0; i < list.length; i++) {
                temp[i] = list[i];
            }

            // reassign the old array to the new array
            list = temp;
        }
    }
}
