import java.util.*;
public class MyDoubleLinkedList<E extends Comparable<E>> implements MyList<E> {
    private Node<E> head, tail;
    private int size;
    /** Create a default list */
    public MyDoubleLinkedList() {
        head = null;
        tail = null;
        size=0;
    }

    /** Create a list from an array of objects */
    public MyDoubleLinkedList(E[] objects) {
        for (E e : objects)
            addLast(e);
    }

    public MyDoubleLinkedList(Collection<? extends E> c) {
        try {
            for (E e : c)
                addLast(e);
        }
        catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e);
            newNode.next = current.next;
            newNode.previous = current;
            current.next.previous = newNode;
            current.next = newNode;
            size++;
        }
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            addLast(e);
        }
        return true;
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    /** Clear the list */
    public void clear() {
        head = tail = null;
    }

    /** Make deep copy of this list */
    public Object clone()
    {
        MyDoubleLinkedList<E> clone = new MyDoubleLinkedList<>();
        Node<E> current = head;
        while (current != null) {
            clone.addLast(current.element);
            current = current.next;
        }
        return clone;
    }

    /** Check to see if this list contains element e */
    public boolean contains(Object e) {
        return indexOf(e) != -1;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
        try{ 
            checkIndex(index);  
                Node<E> current = head;
            for (int i = 0; i < index; i++) {
            current = current.next;
            }
            return current.element;
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
            return null;
        }

    }

    /** Return the head element in the list */
    public E getFirst() {
        if (head == null) 
            throw new NoSuchElementException("List is empty.");
        return head.element;
    }

    /** Return the last element in the list */
    public E getLast() {
        if (tail == null) 
            throw new NoSuchElementException("List is empty.");
        return tail.element;    
    }

    /**
     * Return the index of the head matching element in this list. Return -1 if
     * no match.
     */
    public int indexOf(Object e) {
        int index = 0;
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1; 
    }

    /**
     * Return the index of the last matching element in this list Return -1 if
     * no match.
     */
    public int lastIndexOf(Object e) {
        int index = size - 1;
        Node<E> currentNode = tail;
        while (currentNode != null) {
            if (currentNode.element.equals(e)) {
                return index;
            }
            currentNode = currentNode.previous;
            index--;
        }
        return -1;
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     */
    public E remove(int index) {
        checkIndex(index); 
        Node<E> current;
        if (index == 0) { 
            try
            {
                return removeFirst();
            }
            catch (Exception e)
            {
                throw new NoSuchElementException();
            }
        } 
        else if (index == size - 1) {
            return removeLast();
        } 
        else { 
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return current.element;
        }
    }

    /** Remove the first occurrence of the element e
     * from this list. Return true if the element is removed.
     */
    public boolean remove(Object o)
    {
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(o)) {
                if (current == head) {
                    try
                    {
                        removeFirst();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                } else if (current == tail) {
                    removeLast();
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false; 
    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() throws Exception {
        if (head == null){ 
            throw new NoSuchElementException("List is empty.");
        }
        E element = head.element;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }
        size--;
        return element;
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        if (tail == null){
            throw new NoSuchElementException("List is empty.");
        }
        E element = tail.element;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return element;
    }

    /**
     * Replace the element at the specified position in this list with the
     * specified element.
     */
    public E set(int index, E e) {
        try{ 
        checkIndex(index);  
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = e;
        return oldElement;
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
            return null;
        }

    }

    /** Return the number of elemnts in this list */
    public int size() {
        return size;
    }

    /** Split the original list in half. The original
     * list will continue to reference the
     * front half of the original list and the method
     * returns a reference to a new list that stores the
     * back half of the original list. If the number of
     * elements is odd, the extra element should remain
     * with the front half of the list. */
    public MyDoubleLinkedList<E> split(){
        if (this.size < 2) {
            return new MyDoubleLinkedList<>();
        }
        MyDoubleLinkedList<E> backHalf = new MyDoubleLinkedList<>(); 
        Node<E> middle = head;
        int steps = (size - 1) / 2; 
        for (int i = 0; i < steps; i++) {
            middle = middle.next;
        }
        backHalf.head = middle.next; 
        backHalf.tail = tail;
        backHalf.size = size - (steps + 1);
        if (middle.next != null) {
            middle.next.previous = null;
        }
        tail = middle; 
        middle.next = null; 
        size = steps + 1;
        return backHalf;
    }

    /** Returns an array containing all of the elements in this list in proper
    sequence
     * (from first to last element).
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> currNode = head;
        int i = 0;
        while (currNode != null) {
            array[i++] = currNode.element;
            currNode = currNode.next;
        }
        return array;
    }
    
    //project wont compile without the overriding of this method.
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /** Returns an array containing all of the elements in this list in proper
    sequence
    * (from first to last element); the runtime type of the returned array is that
    * of the specified array.
    */
    @SuppressWarnings("unchecked")
    public Object[] toArray(E[] a) {
        Object[] array = a;
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }

    /** Add a new element at the specified index in this list in ascending order */
    public void addInOrder(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } 
        else if (e.compareTo(head.element) <= 0) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } 
        else{
            Node<E> current = head;
            while (current.next != null && e.compareTo(current.next.element) > 0) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.previous = current;
            if (current.next != null) {
                current.next.previous = newNode;
            } 
            else {
                tail = newNode;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Return true if this list contains no elements */
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndex(int index) {
        if(index<0 || index >=size){
            throw new IndexOutOfBoundsException("Index "+index +" out of bound for length "+size);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); 
            } else {
                result.append("]");
            }
        }
        return result.toString();
    }

    /** Check to see if this list is identical to the list o */
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof MyDoubleLinkedList<?>)){ 
            return false;
        }
        MyDoubleLinkedList<E> otherList = (MyDoubleLinkedList<E>) o;
        if (this.size != otherList.size) {
            return false;
        }
        Node<E> currentNode = this.head;
        Node<E> otherNode = otherList.head;
        while (currentNode != null) {
            if (currentNode.element == null) {
                if (otherNode.element != null) return false;
            } else if (!currentNode.element.equals(otherNode.element)) {
                return false;
            }
            currentNode = currentNode.next;
            otherNode = otherNode.next;
        }
        return true;
    }

    /** Return the element at the middle of this list */
    public E middleElement() {
        if (head == null) {
            throw new IllegalStateException("List is empty.");
        }
        int count = 0;
        Node<E> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        int middleIndex = count / 2;
        current = head;
        for (int i = 0; i < middleIndex; i++) {
            current = current.next;
        }
        return current.element; 
    }

    /** Reverse the elements of this list without creating an extra list. */
    public void reverse() {
        Node<E> current = head;
        Node<E> temp = null;
        while (current != null) {
            temp = current.previous; 
            current.previous = current.next; 
            current.next = temp; 
            current = current.previous; 
        }
        if (temp != null) {
            tail = head; 
            head = temp.previous; 
        }
    }

    /** Returns true if this collection contains all of the elements
     * in the specified collection.
     */
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /** Removes all of this collection's elements that are also contained in the
     * specified collection.
     */
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Node<E> current = head;
        while (current != null) {
            Node<E> nextNode = current.next;
            if (c.contains(current.element)) {
                if (current.previous != null) {
                    current.previous.next = current.next;
                } 
                else {
                    head = current.next; 
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                } 
                else {
                    tail = current.previous; 
                }

                size--;
                modified = true;
            }
            current = nextNode;
        }
        return modified;
    }

    /** Retains only the elements in this collection that are contained in the
     * specified collection
     */
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node<E> current = head;
        while (current != null) {
            Node<E> nextNode = current.next; 
            if (!c.contains(current.element)) {
                if (current.previous != null) {
                    current.previous.next = current.next;
                } 
                else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                } 
                else {
                    tail = current.previous; 
                }
                size--;
                modified = true;
            }
            current = nextNode;
        }
        return modified;
    }

    /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new DoubleLinkedListIterator();
    }
    private class DoubleLinkedListIterator 
    implements java.util.Iterator<E> {
        private Node<E> current = head;
        public boolean hasNext() {
            return current != null; 
        }
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements in the list.");
            }
            E element = current.element; 
            current = current.next; 
            return element;
        }
        public void remove() {
            if (current == null || current.previous == null) {
                throw new IllegalStateException("No element to remove, or remove already called.");
            }
            Node<E> nodeToRemove = current.previous;
            if (nodeToRemove.previous != null) {
                nodeToRemove.previous.next = current;
            } else {
                head = current; 
            }
            if (current != null) {
                current.previous = nodeToRemove.previous;
            } else {
                tail = nodeToRemove.previous; 
            }
            size--; 
        }
    }
    public class Node<T> {
        T element;
        Node<T> next;
        Node<T> previous;
        public Node(T o) {
            element = o;
        }
    }
}

