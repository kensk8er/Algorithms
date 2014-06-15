package main.java.com.kensk8er.algorithms.select;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by kensk8er
 *
 * Median class implements selection of median value from a collection.
 * This is implemented using 2 heap data structure.
 */
public class Median {

    private final Heap lowHeap;
    private final Heap highHeap;

    public Median() {
        this.lowHeap = new Heap(Heap.HeapType.MAX);
        this.highHeap = new Heap(Heap.HeapType.MIN);
    }

    public int streamMedian(int element) {
        int returnValue;
        if (this.lowHeap.isEmpty()) {
            returnValue = element;
            this.lowHeap.add(element);
        } else if (this.highHeap.isEmpty()) {
            if (element > this.lowHeap.getMax()) {
                returnValue = this.lowHeap.getMax();
                this.highHeap.add(element);
            } else {
                returnValue = element;
                int lowMax = this.lowHeap.extractMax();
                this.lowHeap.add(element);
                this.highHeap.add(lowMax);
            }
        } else if (this.lowHeap.size() == this.highHeap.size()) {
            if (element <= this.lowHeap.getMax()) {
                // element is less than or equal to max of lowHeap
                returnValue = this.lowHeap.getMax();
                this.lowHeap.add(element);
            } else if (element < this.highHeap.getMin()){
                // element is between max of lowHeap and min of highHeap
                returnValue = element;
                this.lowHeap.add(element);  // always add to lowHeap when it can be added to either
            } else {
                // element is higher than or equal to min of highHeap
                returnValue = this.highHeap.extractMin();
                this.lowHeap.add(returnValue);
                this.highHeap.add(element);
            }
        } else {
            assert this.lowHeap.size() > this.highHeap.size():
                    "this.lowHeap.size() >= this.highHeap.size()";
            if (element <= this.lowHeap.getMax()) {
                // element is less than or equal to max of lowHeap
                // move the max value of lowHeap to highHeap
                int lowMax = this.lowHeap.extractMax();
                this.highHeap.add(lowMax);
                if (element < this.lowHeap.getMax()) {
                    returnValue = this.lowHeap.getMax();
                    this.lowHeap.add(element);
                } else {
                    returnValue = element;
                    this.lowHeap.add(element);
                }
            } else {
                returnValue = this.lowHeap.getMax();
                this.highHeap.add(element);
            }
        }
        return returnValue;
    }

    public static int getMedian(List<Integer> numbers) {
        assert numbers.size() > 0: "numbers.size == 0";
        Median median = new Median();
        int medianNum = 0;
        for (int number: numbers) {
            medianNum = median.streamMedian(number);
        }
        return medianNum;
    }

    public static void main (String[] args) {
        int medianSum = 0;
        try {

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            Median median = new Median();

            while (line != null) {
                int number = Integer.parseInt(line.replace("\n", ""));
                medianSum += median.streamMedian(number);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(medianSum % 10000);
    }
}
