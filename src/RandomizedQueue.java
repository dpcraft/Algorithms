import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


/**
 * @author dpcraft
 * @date 2018-06-20
 * @time 10:51
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {

        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {

        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= items.length / 2) {
            enlarge();
        }
        items[size++] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        Item returnValue = items[index];
        if (size > 1) {
            items[index] = items[--size];
        }else{
            size--;
        }
        return returnValue;
    }


    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandItr<>();

    }

    private class RandItr<Item> implements Iterator<Item> {


        private int[] order;
        private int index;

        public RandItr() {

            order = randomArray(size);
            index = 0;

        }

        @Override
        public boolean hasNext() {
            return (index < order.length);

        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return (Item) items[order[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void enlarge() {
        Item[] tmp = (Item[]) new Object[items.length * 2];
        for (int i = 0; i < size; i++) {
            tmp[i] = items[i];
        }
        items = tmp;
    }

    private static int[] randomArray(int num) {
        int len = num;

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = 0; i < len; i++) {
            source[i] = i;
        }

        int[] result = new int[num];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }


    // unit testing (optional)
    public static void main(String[] args) {

//        Set<Integer> set = getRandomSet(10);
//
//        Iterator<Integer> iterator = set.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        for (int i = 0; i < 2; i++) {
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
            randomizedQueue.enqueue("zero ");
            randomizedQueue.enqueue("one ");
            randomizedQueue.enqueue("two ");
            randomizedQueue.enqueue("three ");
            randomizedQueue.enqueue("four ");
            System.out.println("size: " + randomizedQueue.size);
            randomizedQueue.dequeue();
            System.out.println("size: " + randomizedQueue.size);
            randomizedQueue.dequeue();
            System.out.println("size: " + randomizedQueue.size);
            randomizedQueue.dequeue();
            System.out.println("size: " + randomizedQueue.size);
            randomizedQueue.dequeue();
            System.out.println("size: " + randomizedQueue.size);
            randomizedQueue.dequeue();
            System.out.println("size: " + randomizedQueue.size);
            randomizedQueue.dequeue();
            System.out.println("size: " + randomizedQueue.size);

            Iterator<String> iterator = randomizedQueue.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            System.out.println("**********************************");
        }

    }
}

