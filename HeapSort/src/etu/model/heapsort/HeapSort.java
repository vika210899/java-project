package etu.model.heapsort;

import etu.model.heapsort.BinTree.BinTree;

public class HeapSort{
    private int arr[];
    private BinTree[] binArr;

    public HeapSort(int[] arr){
        this.arr = arr;
//        System.out.print("Unsorted array: ");
//        printArray();
        BinTree bt = new BinTree(arr);
        bt.createBinTree();
        SortProcess sp = new SortProcess(arr);
        bt.getRoot().traverse(sp);
        this.binArr = sp.getBinArr();
    }

    public int[] getArray() {
        return  arr;
    }

    public BinTree[] getBinArr() {
        return binArr;
    }

    public void printArray(){
        for (Integer i : arr){
            System.out.print(" " + i.intValue());
        }
        System.out.println();
    }
}
