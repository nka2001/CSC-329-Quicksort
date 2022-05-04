/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m10quicksort;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gerstl
 */
public class QuickSort<T extends Comparable<T>> implements SortTestable<T> {

    ArrayList<T> theData = new ArrayList<T>();

    @Override
    public void append(T newItem) {
        theData.add(newItem);
    }

    /**
     * use the quicksort algorithm to sort the array between firstIndex and
     * lastIndex (inclusive)
     *
     * @param theData The array
     * @param firstIndex The first index of the portion of the array to sort
     * @param lastIndex The last index of the portion of the array to sort
     */
    private void quickSort(T theData[], int firstIndex, int lastIndex) {

        if (firstIndex < lastIndex) {

            int partiton = partition(theData, firstIndex, lastIndex);//get the partition location to pass in the recursive calls
            quickSort(theData, firstIndex, partiton - 1);//recursivly sort left side
            quickSort(theData, partiton + 1, lastIndex);//recursivly sort right side 
        }

    } // quickSort()

    /**
     * this is lumotos partition algorithm, helps partition theData array
     *
     * @param theData
     * @param firstIndex
     * @param lastIndex
     * @return
     */
    private int partition(T theData[], int firstIndex, int lastIndex) {
        T pivot = theData[firstIndex];//the pivot is always going to be at first index
        int firstBig = firstIndex;//firstbig moves as we move through the array
        for (int i = firstIndex; i <= lastIndex; i++) {
            if (pivot.compareTo(theData[i]) >= 0) {//if the pivot value is greater than the data at i, we swap it
                swap(theData, firstBig, i);
                firstBig++;

            }
        }

        swap(theData, firstBig - 1, firstIndex);//finally, pivot is swapped with "last small" or firstbig - 1

        return firstBig - 1;//return the location of the pivot

    }

    /**
     * normal swap method, used by partition, and median methods 
     * @param theData
     * @param i
     * @param j 
     */
    private void swap(T theData[], int i, int j) {
        T temp = theData[i];
        theData[i] = theData[j];
        theData[j] = temp;
    }

    /**
     * median of three approach for choosing pivot for quicksort 
     * @param theData
     * @param begin
     * @param end 
     */
    private void median(T theData[], int begin, int end) {

        int middle = (begin + end) / 2;//get the center for median of three
        /*
        a = begin
        b = middle
        c = end
         */

        //a < b < c case:
        if (theData[begin].compareTo(theData[middle]) < 0 && theData[middle].compareTo(theData[end]) < 0) {
            // b < m < e -> swap b and m
            swap(theData, begin, middle);
        } //a < c < b case:
        else if (theData[begin].compareTo(theData[end]) < 0 && theData[end].compareTo(theData[middle]) < 0) {
            swap(theData, begin, end);
        } /*
        //b < a < c case:
        else if(theData[middle].compareTo(theData[begin]) < 0 && theData[begin].compareTo(theData[end]) < 0){
            //do nothing
        }
        //c < a < b case:
        else if(theData[end].compareTo(theData[begin]) < 0 && theData[begin].compareTo(theData[middle]) < 0){
            //do nothing
        }
         */ //b < c < a case:
        else if (theData[middle].compareTo(theData[end]) < 0 && theData[end].compareTo(theData[begin]) < 0) {
            swap(theData, begin, end);
        } //c < b < a case:
        else if (theData[end].compareTo(theData[middle]) < 0 && theData[middle].compareTo(theData[begin]) < 0) {
            swap(theData, middle, begin);
        }
    }

    /**
     * Public sort() method. Calls quicksort.
     */
    public void sort() {
        T theDataAsArray[] = (T[]) theData.toArray(new Comparable[theData.size()]);
        quickSort(theDataAsArray, 0, theDataAsArray.length - 1);
        // now put the data back
        theData.clear();
        Collections.<T>addAll(theData, theDataAsArray);
    }

    public int getCount() {
        return theData.size();
    }

    @Override
    public T getItemAt(int index) {
        return theData.get(index);
    }
}
