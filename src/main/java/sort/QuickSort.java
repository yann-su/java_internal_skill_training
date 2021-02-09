package sort;

public class QuickSort {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {10, 20, 15 , 16, 18 , 17, 12, 13, 19, 11, 14};
        QuickSort(array, 0, array.length - 1);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+" ");
        }

    }

    public static void QuickSort(int[] array, int low, int high) {
        if(low < high){
            int position = Partition(array, low, high);
            QuickSort(array, low, position-1);
            QuickSort(array, position+1, high);
        }
    }

    private static int Partition(int[] array, int low, int high) {
        int start = low;
        int end = high + 1;
        int compare = array[low];
        int tmp;
        while(true){
            while(array[++start] < compare && start < high);
            while(array[--end] > compare);
            if(start >= end) break;
            tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
        }
        array[low] = array[end];
        array[end] = compare;
        return end;
    }


}
