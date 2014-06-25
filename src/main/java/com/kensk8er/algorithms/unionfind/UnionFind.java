package main.java.com.kensk8er.algorithms.unionfind;

import java.util.*;

/**
 * Created by kensk8er
 *
 * Implementation of Union Find data structure.
 *
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
public class UnionFind {

    private Map<Object, Object> elementToLeader;
    private Map<Object, List<Object>> leaderToElements;
    private Map<Object, Integer> leaderToCount;

    public UnionFind() {
        this.elementToLeader = new HashMap<>();
        this.leaderToElements = new HashMap<>();
        this.leaderToCount = new HashMap<>();
    }

    /**
     * Instantiate a union find data structure from list of elements.
     *
     * @param elements  list of elements
     */
    public UnionFind(List<Object> elements) {
        this();

        for (Object element: elements) {
            this.elementToLeader.put(element, element);  // set itself as the leader
            this.leaderToElements.put(element, Arrays.asList(element));
            this.leaderToCount.put(element, 1);
        }
    }

    /**
     * Instantiate a union find data structure from set of integers.
     *
     * @param elements  set of integers
     */
    public UnionFind(Set<Integer> elements) {
        this();

        for (Object element: elements) {
            this.elementToLeader.put(element, element);  // set itself as the leader
            List<Object> elementList = new ArrayList<>();
            elementList.add(element);
            this.leaderToElements.put(element, elementList);
            this.leaderToCount.put(element, 1);
        }
    }

    /**
     * Return true if both elements are in the same set. (Run in O(1) time.)
     *
     * @param element1  first element
     * @param element2  second element
     * @return true if elements are in the same set, false otherwise.
     */
    public boolean inSameSet(Object element1, Object element2) {
        return find(element1).equals(find(element2));
    }

    /**
     * Find and return the leader (element that represents a set) of the element.
     *
     * @param element  element which you want to find its leader
     * @return leader element of the set the given element belongs to
     */
    public Object find(Object element) {
        return this.elementToLeader.get(element);
    }

    /**
     * Unify two sets which element1 and element2 belong to.
     *
     * @param element1  1st element
     * @param element2  2nd element
     */
    public void union(Object element1, Object element2) {
        if (this.inSameSet(element1, element2)) {
            // do nothing if they've already belonged to the same set
            return;
        }
        Object leader1 = find(element1);
        Object leader2 = find(element2);
        Object newLeader;
        Object oldLeader;
        if (this.leaderToCount.get(leader1) > this.leaderToCount.get(leader2)) {
            newLeader = leader1;
            oldLeader = leader2;
        } else {
            newLeader = leader2;
            oldLeader = leader1;
        }

        List<Object> elementsToReplace = this.leaderToElements.get(oldLeader);
        this.leaderToElements.remove(oldLeader);
        for (Object element: elementsToReplace) {
            this.elementToLeader.put(element, newLeader);
            this.leaderToElements.get(newLeader).add(element);
        }
        this.leaderToCount.put(
                newLeader, this.leaderToCount.get(newLeader) + this.leaderToCount.get(oldLeader));
        this.leaderToCount.remove(oldLeader);
    }

    public int getSetSize() {
        return this.leaderToCount.size();
    }

    public List<List<Object>> getClusters() {
        List<List<Object>> clusters = new ArrayList<>();
        for (Map.Entry<Object, List<Object>> entry: this.leaderToElements.entrySet()) {
            List<Object> elements = entry.getValue();
            clusters.add(elements);
        }
        return clusters;
    }

    /**
     * Return the number of clusters/sets.
     *
     * @return the number of clusters/sets
     */
    public int getClusterNum() {
        return this.leaderToCount.size();
    }
}
