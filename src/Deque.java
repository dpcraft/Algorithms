/**
* 不识庐山真面目，只缘身在此山中。
*/


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author dpcraft
 * @date 2018-06-18
 * @time 11:00
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> end;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can not add a null into deque");
        }
        Node<Item> firstItem = new Node<>(null, item, head);
        if (head == null) {
            end = firstItem;
        } else {
            head.prev = firstItem;
        }
        head = firstItem;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can not add a null into deque");
        }
        Node<Item> lastItem = new Node<>(end, item, null);
        if (end == null) {
            head = lastItem;
        } else {
            end.next = lastItem;
        }
        end = lastItem;
        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is empty");
        }
        Node<Item> tmp = head;
        //head = null;
        head = tmp.next;
        size--;
        if(size == 0){
            head = null;
            end = null;
        }
        return tmp.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is empty");
        }
        Node<Item> tmp = end;
        // end = null;
        end = tmp.prev;
        size--;
        if(size == 0){
            head = null;
            end = null;
        }
        return tmp.item;

    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {

        return new DeqItr<>();

    }

    private class DeqItr<Item> implements Iterator<Item> {

        private int nextIndex;
        private Node<Item> next;
        private Node<Item> lastReturned;

        public DeqItr() {
            next = (Node<Item>) head;
            nextIndex = 0;

        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;

        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

//        Deque<String> deque = new Deque<>();
//        deque.addLast("hello,");
//        deque.addLast("deque ");
//        deque.addLast("and ");
//        deque.addLast("what ");
//        deque.addLast("? ");
//        deque.removeFirst();
//        deque.removeLast();
//        Iterator<String> iterator = deque.iterator();
//        while (iterator.hasNext()) {
//            iterator.remove();
//            System.out.println(iterator.next());
//        }
//        System.out.println(deque.size);
        Deque<Integer> deque = new Deque<>();
//        System.out.println(deque.isEmpty());         //==> true
//        deque.addLast(1);
//        deque.addLast(2);
//        System.out.println(deque.removeFirst());    // ==> 1
//        deque.addLast(4);
//        System.out.println(deque.removeFirst());     //==> 2
//        System.out.println(deque.removeFirst());     //==> 4

        deque.addLast(1);
        deque.addLast(2);
        System.out.println(deque.removeLast()); //==>0;
        System.out.println(deque.removeFirst()); //==>1;
        deque.addFirst(5);
        deque.addFirst(6);
        System.out.println(deque.isEmpty());
        System.out.println(deque.isEmpty());
        System.out.println(deque.removeLast()); //==>1;


    }
}