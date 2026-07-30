class Node {
    int key;
    int value;
    Node previous;
    Node next;

    /**
     * Default constructor for sentinel nodes
     */
    Node() {
    }

    /**
     * Constructor with key and value parameters
     * @param key   The key for this node
     * @param value The value associated with the key
     */
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * LRU (Least Recently Used) Cache implementation
 * Uses a HashMap for O(1) lookup and a doubly linked list for O(1) removal/addition
 * Most recently used items are kept at the head, least recently used at the tail
 */
class LRUCache {
    private int currentSize;
    private int maxCapacity;
    private Node dummyHead;  // Sentinel node for head
    private Node dummyTail;  // Sentinel node for tail
    private Map<Integer, Node> cacheMap;  // HashMap for O(1) access to nodes

    /**
     * Initialize the LRU cache with given capacity
     * @param capacity Maximum number of key-value pairs the cache can hold
     */
    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.currentSize = 0;
      
        // Initialize sentinel nodes
        this.dummyHead = new Node();
        this.dummyTail = new Node();
      
        // Connect sentinel nodes
        dummyHead.next = dummyTail;
        dummyTail.previous = dummyHead;
      
        // Initialize the cache map
        this.cacheMap = new HashMap<>();
    }

    /**
     * Get the value associated with the key
     * Move the accessed node to head (mark as most recently used)
     * @param key The key to look up
     * @return The value if key exists, -1 otherwise
     */
    public int get(int key) {
        // Check if key exists in cache
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
      
        // Get the node from cache
        Node targetNode = cacheMap.get(key);
      
        // Move node to head (mark as most recently used)
        removeNodeFromList(targetNode);
        addNodeToHead(targetNode);
      
        return targetNode.value;
    }

    /**
     * Add or update a key-value pair in the cache
     * @param key   The key to insert or update
     * @param value The value to associate with the key
     */
    public void put(int key, int value) {
        // Case 1: Key already exists - update value and move to head
        if (cacheMap.containsKey(key)) {
            Node existingNode = cacheMap.get(key);
          
            // Remove from current position
            removeNodeFromList(existingNode);
          
            // Update value
            existingNode.value = value;
          
            // Move to head (mark as most recently used)
            addNodeToHead(existingNode);
        } 
        // Case 2: New key - create new node and add to cache
        else {
            // Create new node
            Node newNode = new Node(key, value);
          
            // Add to cache map
            cacheMap.put(key, newNode);
          
            // Add to head of linked list
            addNodeToHead(newNode);
          
            // Increment size
            currentSize++;
          
            // Check if capacity exceeded
            if (currentSize > maxCapacity) {
                // Remove least recently used node (from tail)
                Node lruNode = dummyTail.previous;
              
                // Remove from cache map
                cacheMap.remove(lruNode.key);
              
                // Remove from linked list
                removeNodeFromList(lruNode);
              
                // Decrement size
                currentSize--;
            }
        }
    }

    /**
     * Remove a node from its current position in the doubly linked list
     * @param node The node to remove
     */
    private void removeNodeFromList(Node node) {
        // Update previous node's next pointer
        node.previous.next = node.next;
      
        // Update next node's previous pointer
        node.next.previous = node.previous;
    }

    /**
     * Add a node right after the dummy head (mark as most recently used)
     * @param node The node to add at head
     */
    private void addNodeToHead(Node node) {
        // Set node's pointers
        node.next = dummyHead.next;
        node.previous = dummyHead;
      
        // Update dummy head's next node
        dummyHead.next = node;
      
        // Update the previous pointer of what was the first node
        node.next.previous = node;
    }
}


