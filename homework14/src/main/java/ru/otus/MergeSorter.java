package ru.otus;

public class MergeSorter {

    private int[] total = new int[0];

    public void add(int[] array) {
        if (total.length == 0) {
            total = array;
        } else if (array.length > 0) {
            int length = array.length + total.length;
            int[] mergeResult = new int[length];

            int totalCounter = 0, arrayCounter = 0;
            for (int mergeCounter = 0; mergeCounter < length; mergeCounter++) {
                if (totalCounter < total.length && arrayCounter < array.length) {
                    if (total[totalCounter] < array[arrayCounter]) {
                        mergeResult[mergeCounter] = total[totalCounter];
                        totalCounter++;
                    } else {
                        mergeResult[mergeCounter] = array[arrayCounter];
                        arrayCounter++;
                    }
                } else if (totalCounter < total.length) {
                    mergeResult[mergeCounter] = total[totalCounter];
                    totalCounter++;
                } else {
                    mergeResult[mergeCounter] = array[arrayCounter];
                    arrayCounter++;
                }
            }

            total = mergeResult;
        }
    }

    public int[] getTotal() {
        return total;
    }
}
