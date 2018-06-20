import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * @author dpcraft
 * @date 2018-06-20
 * @time 12:39
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        Integer k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {

            randomizedQueue.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = randomizedQueue.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            if (count == k) {
                break;
            }
            System.out.println(iterator.next());
            count++;
        }
    }

}
