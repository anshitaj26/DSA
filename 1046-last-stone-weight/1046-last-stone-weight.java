import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() >= 2) {
            int n1 = pq.poll();
            int n2 = pq.poll();
            if (n1 != n2) {
                pq.offer(n1 - n2);
            }
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }
}