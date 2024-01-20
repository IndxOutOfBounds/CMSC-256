package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack<T> implements StackInterface<T> {
    private T[] data;
    private int topOfStack;
    private int INITIAL_CAPACITY = 5;
    private int currentCapacity;
    ArrayBasedStack(int userCapacity) {
        if (userCapacity <= 0) {
            throw new IllegalArgumentException("Array intial size error.");
        }
        data = (T[])new Object[currentCapacity];
        topOfStack = -1;
        currentCapacity = userCapacity;
        INITIAL_CAPACITY = userCapacity;
    }
    public ArrayBasedStack() {
        clear();
    }
    private void expandArray() {
        data = Arrays.copyOf(data,currentCapacity * 2);
        currentCapacity *= 2;
    }
    private boolean isArrayFull() {
        return topOfStack >= data.length - 1;
    }
    /** Adds a new entry to the
     top of this stack. @param newEntry An object to be added to the stack. */

    public void push(T newEntry) {
        if (isArrayFull()) {
            expandArray();
        }
        topOfStack++;
        data[topOfStack] = newEntry;
    }
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException("No elements in stack");
        }
        T topElement = data[topOfStack];
        data[topOfStack] = null;
        topOfStack--;
        return topElement;

    }
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException("No elements in stack");
        }
        return data[topOfStack];
    }
    public boolean isEmpty() {
        return topOfStack < 0;
    }
    public void clear() {
        T[] tempArray = (T[]) new Object[INITIAL_CAPACITY];
        topOfStack = -1;
        currentCapacity = INITIAL_CAPACITY;
        data = tempArray;
    }

}