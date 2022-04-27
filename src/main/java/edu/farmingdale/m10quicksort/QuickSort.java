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
     * use the quicksort algorithm to sort the array between firstIndex
     * and lastIndex (inclusive)
     * @param theData The array
     * @param firstIndex The first index of the portion of the array to sort
     * @param lastIndex The last index of the portion of the array to sort
     */
    private void quickSort(T theData[], int firstIndex, int lastIndex) {
        
        if(firstIndex < lastIndex){
            int pi = partition(theData, firstIndex, lastIndex);
            quickSort(theData, firstIndex, pi - 1);
            quickSort(theData, pi + 1, lastIndex);
        }
        
        
    } // quickSort()

    private int partition(T theData[], int low, int high){
        
      T pivot = theData[low];
      int first = low + 1;
      int last = high;
      
      while(first > last){
          while(first <= last && (theData[first].compareTo(pivot) <= 0)){
              low++;
          }
          while(low <= high && (theData[last].compareTo(pivot) >0)){
              high--;
          }
          
          if(last > first){
              T temp = theData[last];
             theData[last] = theData[first];
        theData[first] = temp;
          }
      }
      
      while(first > high && (theData[last].compareTo(pivot) >= 0)){
          high--;
      }
      if(pivot.compareTo(theData[last]) > 0){
          theData[low] = theData[last];
          theData[last] = pivot;
          return last;
      } else {
          return low;
      }
        
        /*
        T pivot = theData[high];
        int i = (low - 1);
        
        for(int j = low; j <= high - 1; j++){
            if(theData[j].compareTo(pivot) < 0){
                i++;
                swap(theData, i, j);
            }
        }
        swap(theData, i+1, high);
        return (i + 1);
        */
    }
    
    private void swap(T theData[], int i, int j){
        T temp = theData[i];
        theData[i] = theData[j];
        theData[j] = temp;
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
