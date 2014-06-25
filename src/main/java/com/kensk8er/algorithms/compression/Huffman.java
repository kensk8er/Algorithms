package main.java.com.kensk8er.algorithms.compression;

import main.java.com.kensk8er.algorithms.heap.Heap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by kensk8er
 *
 * Implementation of Huffman coding (loss-less data compression algorithm).
 */
public class Huffman {

    /**
     * Data structure used in order to store the tree structure of symbols and their coding.
     */
    private static class Symbol {
        Object symbol;
        Symbol left;
        Symbol right;

        Symbol(Object symbol, Symbol left, Symbol right) {
            this.symbol = symbol;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Compute the most efficient binary coding of each symbol given based on their weight
     * (e.g. frequency) using Huffman's algorithm.
     *
     * @param symbolToWeight  mapping from symbol (e.g. character) to its weight (e.g. frequency)
     * @return mapping from symbol to its binary encoding (e.g. 100010)
     */
    public static Map<Object, List<Byte>> getSymbolToCode(Map<Object, Integer> symbolToWeight) {
        Map<Symbol, Integer> symbolObjectToWeight = convertToSymbolObjects(symbolToWeight);

        // create a reverse mapping
        Map<Integer, List<Symbol>> weightToSymbols = new HashMap<>();
        for (Symbol symbol: symbolObjectToWeight.keySet()) {
            int weight = symbolObjectToWeight.get(symbol);
            if (!weightToSymbols.containsKey(weight)) {
                weightToSymbols.put(weight, new ArrayList<>());
            }
            weightToSymbols.get(weight).add(symbol);
        }

        // create a heap to retrieve minimum key in O(log N) time
        Heap heap = new Heap(Heap.HeapType.MIN);
        for (int weight: weightToSymbols.keySet()) {
            for (int i = 0; i < weightToSymbols.get(weight).size(); i++) {
                heap.add(weight);
            }
        }

        // Use the heap to retrieve the minimum 2 keys, merge them and treat it as a new symbol,
        // and add it back to the heap. Iterate until all the symbols are merged.
        while (heap.size() >= 2) {
            // extract minimums from the heap
            int minWeight1 = heap.extractMin();
            int minWeight2 = heap.extractMin();
            Symbol symbol1 = weightToSymbols.get(minWeight1).remove(0);
            Symbol symbol2 = weightToSymbols.get(minWeight2).remove(0);

            // create a new merged symbol
            Symbol newSymbol = new Symbol(null, symbol1, symbol2);
            int newWeight = minWeight1 + minWeight2;
            if (!weightToSymbols.containsKey(newWeight)) {
                weightToSymbols.put(newWeight, new ArrayList<>());
            }
            weightToSymbols.get(newWeight).add(newSymbol);

            // add the new weight to the heap
            heap.add(newWeight);
        }

        // retrieve code for each symbol by traversing the tree
        int rootWeight = heap.extractMin();
        Symbol rootSymbol = weightToSymbols.get(rootWeight).remove(0);
        Map<Object, List<Byte>> symbolToCode = new HashMap<>();
        symbolToCode = getSymbolToCode(rootSymbol, symbolToCode, new ArrayList<>());

        return symbolToCode;
    }

    /**
     * Get the mapping from Symbol to its encoding using Symbol data structure (binary tree).
     *
     * This method is recursively applied for computing the complete mapping from symbol to
     * encoding.
     *
     * @param symbol  current symbol we're looking at in the tree
     * @param symbolToCode  mapping from symbol to encoding (updated in each recursive call)
     * @param code  encoding we've got so far at the node in the tree
     * @return mapping from symbol to encoding
     */
    private static Map<Object, List<Byte>> getSymbolToCode(
            Symbol symbol, Map<Object, List<Byte>> symbolToCode, List<Byte> code) {
        if (symbol.symbol != null) {
            // symbol is a leaf node
            symbolToCode.put(symbol, code);
            return symbolToCode;
        }

        // go down the left node
        List<Byte> leftCode = new ArrayList<>(code);
        leftCode.add((byte) 0);
        symbolToCode = getSymbolToCode(symbol.left, symbolToCode, leftCode);

        // go down the right node
        List<Byte> rightCode = new ArrayList<>(code);
        rightCode.add((byte) 1);
        symbolToCode = getSymbolToCode(symbol.right, symbolToCode, rightCode);

        return symbolToCode;
    }

    /**
     * Convert symbol in the mapping to symbol data structure.
     *
     * @param symbolToWeight  mapping from symbol to weight (symbol is Object type)
     * @return  mapping from symbol to weight (symbol is converted to Symbol type)
     */
    private static Map<Symbol, Integer> convertToSymbolObjects(
            Map<Object, Integer> symbolToWeight) {
        Map<Symbol, Integer> symbolObjectToWeight = new HashMap<>();
        Set<Object> symbols = symbolToWeight.keySet();
        for (Object symbol: symbols) {
            Symbol symbolObject = new Symbol(symbol, null, null);
            symbolObjectToWeight.put(symbolObject, symbolToWeight.get(symbol));
        }
        return symbolObjectToWeight;
    }

    /**
     * Compute the maximum code length (the number of bits required for coding a symbol) for mapping
     * from symbol to its weight (e.g. frequency).
     *
     * @param symbolToWeight  mapping from symbol (e.g. character) to its weight (e.g. frequency)
     * @return the maximum code length (the number of bits required for coding a symbol)
     */
    public static int getMaxCodeLen(Map<Object, Integer> symbolToWeight) {
        return getMaxMinCodeLen(symbolToWeight, true);
    }

    /**
     * Compute the minimum code length (the number of bits required for coding a symbol) for mapping
     * from symbol to its weight (e.g. frequency).
     *
     * @param symbolToWeight  mapping from symbol (e.g. character) to its weight (e.g. frequency)
     * @return the minimum code length (the number of bits required for coding a symbol)
     */
    public static int getMinCodeLen(Map<Object, Integer> symbolToWeight) {
        return getMaxMinCodeLen(symbolToWeight, false);
    }

    private static int getMaxMinCodeLen(Map<Object, Integer> symbolToWeight, boolean max) {
        Map<Object, List<Byte>> symbolToCode = getSymbolToCode(symbolToWeight);
        Integer maxMinCodeLen = null;
        for (List<Byte> code: symbolToCode.values()) {
            if (maxMinCodeLen == null) {
                maxMinCodeLen = code.size();
            } else if (max) {
                maxMinCodeLen = Math.max(maxMinCodeLen, code.size());
            } else {
                maxMinCodeLen = Math.min(maxMinCodeLen, code.size());
            }
        }
        return maxMinCodeLen;
    }

    /**
     * Just for some debug.
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Object, Integer> symbolToWeight = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            line = br.readLine();
            int symbol = 0;

            while (line != null) {
                int weight = Integer.parseInt(line.replace("\n", ""));
                symbolToWeight.put(symbol, weight);
                line = br.readLine();
                symbol++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("max: " + getMaxCodeLen(symbolToWeight));
        System.out.println("min: " + getMinCodeLen(symbolToWeight));
    }
}
