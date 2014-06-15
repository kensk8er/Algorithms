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

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public enum HeapType {
        MIN, MAX,
    }

    public Heap(HeapType heapType) {
        this.list = new ArrayList<>();
        this.heapType = heapType;
    }

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

    public int getMax() {
        assert this.heapType.equals(HeapType.MAX):
                "getMax() is only supported for heapType = HeapType.MAX";
        assert !this.list.isEmpty(): "No element in the heap.";
        return this.list.get(0) * -1;
    }

    public int extractMax() {
        assert this.heapType.equals(HeapType.MAX):
                "getMax() is only supported for heapType = HeapType.MAX";
        return this.extractRoot() * -1;
    }

    public int getMin() {
        assert this.heapType.equals(HeapType.MIN):
                "getMin() is only supported for heapType = HeapType.MIN";
        assert !this.list.isEmpty(): "No element in the heap.";
        return this.list.get(0);
    }

    public int extractMin() {
        assert this.heapType.equals(HeapType.MIN):
                "getMin() is only supported for heapType = HeapType.MIN";
        return this.extractRoot();
    }

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
