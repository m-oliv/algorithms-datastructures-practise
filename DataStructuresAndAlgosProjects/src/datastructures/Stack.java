package datastructures;

import exceptions.EmptyException;
import exceptions.OverflowException;

public interface Stack<E> {
    public void push(E o) throws OverflowException;
    public E top() throws EmptyException;
    public E pop() throws EmptyException;
    public int size();
    public boolean empty();
}
