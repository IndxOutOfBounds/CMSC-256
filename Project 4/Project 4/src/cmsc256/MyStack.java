/*
 * Custom Stack Implementation for Tag Matching
 * This code implements a stack in order to check HTML files to make sure that the tags within the file are balanced.
 */
package cmsc256;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

public class MyStack<E> implements StackInterface {
    private class Node {
        private E data; // Entry in stack
        private Node next; // Link to next node

        private Node(E dataPortion) {
            this(dataPortion, null);
        }

        private Node(E dataPortion, Node linkPortion) {
            data = dataPortion;
            next = linkPortion;
        }

        private E getData() {
            return data;
        }

        private void setData(E newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }

    private E[] stack;
    private int topValue = -1;
    private static final int size = 5;

    public MyStack() {
        topValue = -1;
        stack = (E[]) new Object[size];
    }

    public MyStack(int size) {
        E[] tempStack = (E[]) new Object[size];
        stack = tempStack;
    }

    private boolean isFull() {
        if (topValue == stack.length - 1) {
            return true;
        }
        return false;
    }

    private void increaseSize() {
        if (isFull()) {
            stack = Arrays.copyOf(stack, stack.length * 2);
        }
    }

    @Override
    public void push(Object newEntry) {
        if (newEntry != null) {
            increaseSize();
            topValue++;
            stack[topValue] = (E) newEntry;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Object oldObject = stack[topValue];
        stack[topValue] = null;
        topValue--;
        return oldObject;
    }

    @Override
    public Object peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[topValue];
    }

    @Override
    public boolean isEmpty() {
        if (topValue >= 0) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        topValue = -1;
    }

    public static boolean isBalanced(File webpage) throws FileNotFoundException {
        try (Scanner in = new Scanner(webpage)){
            MyStack validLines = new MyStack();
            while (in.hasNext()) {
                String line = in.nextLine();
                int stringIndex = 0;
                while (stringIndex < line.length()) {
                    int openingBracket = line.indexOf("<", stringIndex);
                    int closingBracket = line.indexOf(">", stringIndex);
                    if (openingBracket != -1 && closingBracket != -1) { //if either int is -1, a bracket wasn't found
                        String tag = line.substring(openingBracket, closingBracket + 1);
                        if (!tag.contains("/")) {
                            validLines.push(tag);
                        } else if (validLines.isEmpty()) { //if stack is empty, there is not a opening tag
                            return false;
                        }
                        String recentTag = (String) validLines.pop();
                        String closingTag = "</" + recentTag.substring(1);
                        if (!recentTag.equals(closingTag)) {
                            return false;
                        }
                        stringIndex = closingBracket + 1;
                    } else {
                        break;
                    }
                }
            }
            return validLines.isEmpty();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
