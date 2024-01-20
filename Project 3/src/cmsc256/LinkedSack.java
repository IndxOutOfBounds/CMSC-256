package cmsc256;

import java.util.*;
public class LinkedSack<T> implements SackInterface<T>, Iterable<T>{
    protected Node head;
    protected Node tail;
//    protected LinkedSack<T> sack;
    protected int size;
    public LinkedSack() {
        head = null;
        tail = null;
        size = 0;
    }
    public void insertAtEnd(T input) {
        Node a = new Node(input, null);
        size++;
        tail = a;
        if (head == null) {
            head = a;
        } else {
            tail.setNext(a);
        }
    }
    private class Node {
        private T data;
        private Node next;
        Node(T input) {
            data = input;
            next = null;
        }
        Node(T input, Node next) {
            data = input;
            this.next = next;
        }
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
        public boolean hasNext(Node node) {
            return node.next != null;
        }
    }
    private class LinkedSackIterator implements Iterator<T>{
        private Node nextNode;
        private int nextPosition;
        private boolean wasNextCalled;
        private LinkedSackIterator() {
            nextNode = head;
            nextPosition = 0;
            wasNextCalled = false;
        }
        private LinkedSackIterator(LinkedSack sack) {
            nextNode = sack.head;
            nextPosition = 0;
            wasNextCalled = false;
        }
        public boolean hasNext() {
            return nextNode != null;
        }

        public T next() {
            if (hasNext()) {
               Node returnNode = nextNode;
                nextPosition++;
                nextNode = nextNode.getNext();
                return returnNode.getData();
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    public int getCurrentSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean add(T newEntry) {
        insertAtEnd(newEntry);
        return true;
    }

    public T remove() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T temp = head.getData();
        head = head.getNext();
        return temp;
    }

    public boolean remove(T anEntry) {
        if (isEmpty()) {
            return false;
        }
        LinkedSackIterator temp = new LinkedSackIterator();
        if (contains(anEntry)) {

            return true;
        }
        return false;
    }

    public void clear() {
        size = 0;
         head = tail = null;
    }

    public int getFrequencyOf(T anEntry) {
        LinkedSackIterator sack = new LinkedSackIterator();

        return 0;
    }

    public boolean contains(T anEntry) {
        if (isEmpty()) {
            return false;
        }
        LinkedSackIterator sack = new LinkedSackIterator();
        while (sack.hasNext()) {
            if (anEntry == sack.next()) {
                return true;
            }
        }
        return false;
    }

    public T[] toArray() {
        List<T> content;
        content = new ArrayList<>(size);
        LinkedSackIterator sack = (LinkedSackIterator) iterator();
        while (sack.hasNext()) {
            content.add(sack.next());
        }

        return (T[]) content.toArray();
    }

    public boolean equals(LinkedSack<T> otherSack) {
        boolean isSame = true;
        LinkedSackIterator sackOne = new LinkedSackIterator();
        LinkedSackIterator sackTwo = new LinkedSackIterator(otherSack);
        while (sackOne.hasNext() && sackTwo.hasNext()) {
            if (sackOne.next().equals(sackTwo.next())) {
                isSame = false;
            }
        }
        return isSame;
    }

    public boolean duplicateAll() {
        return false;
    }

    public void removeDuplicates() {

    }
    public Iterator<T> iterator() {
        return new LinkedSackIterator();
    }
    public ListIterator<T> getIterator() {
        return (ListIterator<T>) iterator();
    }
    public String toString() {
        String endString = "";
        if(isEmpty()) {
            return endString;
        }
        LinkedSackIterator sack = new LinkedSackIterator();
        while (sack.hasNext()) {
            endString += sack.next();
        }
        return endString;
    }
}
