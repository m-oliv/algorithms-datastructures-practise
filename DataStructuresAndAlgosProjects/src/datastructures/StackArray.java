package datastructures;

import exceptions.EmptyException;
import exceptions.OverflowException;

public class StackArray<E> implements Stack<E> {
    private int top;
    private int maxSize;
    private E[] stack;

    public StackArray(){
        this.top = -1;
        this.maxSize = 100;
        this.stack = (E[]) new Object[this.maxSize];
    }

    public StackArray(int maxSize){
        this.top = -1;
        this.maxSize = maxSize;
        this.stack = (E[]) new Object[this.maxSize];
    }

    public void push(E o) throws OverflowException {
        if(this.top == this.maxSize) {
            throw new OverflowException("The stack is full!");
        }

        this.top++;
        this.stack[this.top] = o;
    }

    public E top() throws EmptyException {

        if(empty()) {
            throw new EmptyException("The stack is empty!");
        }

        return this.stack[top];
    }

    public E pop() throws EmptyException {
        if(empty()) {
            throw new EmptyException("The stack is empty!");
        }

        E temp = top();
        this.top--;

        return temp;
    }

    public int size() {
        return this.top + 1;
    }

    public boolean empty() {
        return this.top == -1;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int i;

        for(i=0; i<size();i++) {
            builder.append(this.stack[i]);
            builder.append(" ");
        }

        builder.append("]");

        return builder.toString();
    }
}
