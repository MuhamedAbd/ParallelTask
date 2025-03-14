import java.util.Arrays;

class MergeSortThread extends Thread {
    private int[] array;

    public MergeSortThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        mergeSort(array);
    }

    private void mergeSort(int[] arr) {
        if (arr.length < 2) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        MergeSortThread leftThread = new MergeSortThread(left);
        MergeSortThread rightThread = new MergeSortThread(right);

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(arr, left, right);
    }

    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public int[] getSortedArray() {
        return array;
    }
}

class MergeSortMultithreaded {
    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 2, 9, 1, 7, 6, 4};
        System.out.println("Unsorted: " + Arrays.toString(arr));

        MergeSortThread sorter = new MergeSortThread(arr);
        sorter.start();

        try {
            sorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sorted: " + Arrays.toString(sorter.getSortedArray()));
    }
}


