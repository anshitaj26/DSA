class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Initialize a min-heap to store nodes based on their values
        // The heap will always give us the node with the smallest value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
      
        // Add the head of each non-empty linked list to the heap
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
      
        // Create a dummy node to simplify list construction
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
      
        // Process nodes until the heap is empty
        while (!minHeap.isEmpty()) {
            // Extract the node with the smallest value
            ListNode smallestNode = minHeap.poll();
          
            // If this node has a next node, add it to the heap
            // This ensures we continue processing the list this node came from
            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
          
            // Append the smallest node to our result list
            current.next = smallestNode;
            current = current.next;
        }
      
        // Return the merged list, skipping the dummy head
        return dummyHead.next;
    }
}
