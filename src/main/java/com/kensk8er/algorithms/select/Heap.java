package main.java.com.kensk8er.algorithms.select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kensk8er
 *
 * Heap class is an implementation of heap data structure.
 *
 * Currently only support int elements.
 * Internally, heap is always implemented as min heap. When constructing a max heap, simply multiply
 * by -1 when adding/returning numbers.
 */
public class Heap {

    private List<Integer> list;
    private HeapType heapType;

    /**
     * Return true if the heap is empty.
     *
     * @return true if empty, else false
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Type of the heap.
     *
     * MIN heap can do min() operation with O(log N) time, whereas MAX heap can do max() operation
     * with O(log N) time.
     */
    public enum HeapType {
        MIN, MAX,
    }

    /**
     * Initialize heap data structure for the given heap type.
     *
     * @param heapType  type of the heap (MIN/MAX)
     */
    public Heap(HeapType heapType) {
        this.list = new ArrayList<>();
        this.heapType = heapType;
    }

    /**
     * Add an element to the heap
     *
     * @param element  an element to add
     */
    public void add(int element) {
        if (this.heapType.equals(HeapType.MAX)) {
            element = element * -1;  // multiply by -1 when working on max heap
        }
        this.list.add(element);
        this.bubbleUp(this.list.size() - 1);
    }

    public int size() {
        return this.list.size();
    }

    /**
     * Get the max element in the heap
     *
     * @return max element in the heap
     */
    public int getMax() {
        assert this.heapType.equals(HeapType.MAX):
                "getMax() is only supported for heapType = HeapType.MAX";
        assert !this.list.isEmpty(): "No element in the heap.";
        return this.list.get(0) * -1;
    }

    /**
     * Extract (i.e. get and remove) the max element in the heap
     *
     * @return max element in the heap
     */
    public int extractMax() {
        assert this.heapType.equals(HeapType.MAX):
                "getMax() is only supported for heapType = HeapType.MAX";
        return this.extractRoot() * -1;
    }

    /**
     * Get the min element in the heap
     *
     * @return min element in the heap
     */
    public int getMin() {
        assert this.heapType.equals(HeapType.MIN):
                "getMin() is only supported for heapType = HeapType.MIN";
        assert !this.list.isEmpty(): "No element in the heap.";
        return this.list.get(0);
    }

    /**
     * Extract (i.e. get and remove) the min element in the heap
     *
     * @return min element in the heap
     */
    public int extractMin() {
        assert this.heapType.equals(HeapType.MIN):
                "getMin() is only supported for heapType = HeapType.MIN";
        return this.extractRoot();
    }

    /**
     * Extract (i.e. get and remove) the root element of the heap.
     *
     * @return root element of the heap
     */
    private int extractRoot() {
        int returnValue;
        if (this.list.size() == 1) {
            returnValue = this.list.remove(0);
        } else {
            returnValue = this.list.get(0);
            int newRoot = this.list.remove(this.list.size() - 1);
            this.list.set(0, newRoot);
            this.bubbleDown(0);
        }
        return returnValue;
    }

    /**
     * Bubble down operation in order to maintain the heap.
     *
     * @param parentId  ID of parent of the subtree (starting point of bubble down)
     */
    private void bubbleDown(int parentId) {
        int parent = this.list.get(parentId);
        int child1Id = parentId * 2;
        int child2Id = parentId * 2 + 1;
        int smallChild;
        int smallChildId;

        if (child1Id >= this.list.size()) {
            // finish bubble down when there's no child node anymore
            return;
        } else if (child2Id >= this.list.size()) {
            // candidate for replacement is always child1 if it's the only child
            smallChild = this.list.get(child1Id);
            smallChildId = child1Id;
        } else {
            int child1 = this.list.get(child1Id);
            int child2 = this.list.get(child2Id);
            if (child1 <= child2) {
                smallChild = child1;
                smallChildId = child1Id;
            } else {
                smallChild = child2;
                smallChildId = child2Id;
            }
        }

        // recursively bubble down if parent is larger than the small child
        if (parent > smallChild) {
            // swap parent and smallChild
            this.list.set(parentId, smallChild);
            this.list.set(smallChildId, parent);
            this.bubbleDown(smallChildId);
        }
    }

    /**
     * Bubble up operation in order to maintain the heap.
     *
     * @param childId  ID of child of the subtree (starting point of bubble up)
     */
    private void bubbleUp(int childId) {
        if (childId == 0) {
            // finish bubble up if it's already at the root
            return;
        }

        int child = this.list.get(childId);
        int parentId = childId / 2;
        int parent = this.list.get(parentId);
        if (parent > child) {
            // swap parent and child
            this.list.set(parentId, child);
            this.list.set(childId, parent);
            this.bubbleUp(parentId);
        }
    }

    /**
     * Return copy of all the elements in the heap.
     *
     * @return copy of all the elements in the heap
     */
    public List<Integer> getElements() {
        List<Integer> elements = new ArrayList<>();
        for (int element: this.list) {
            if (this.heapType.equals(HeapType.MAX)) {
                element *= -1;
            }
            elements.add(element);
        }
        return elements;
    }

}
