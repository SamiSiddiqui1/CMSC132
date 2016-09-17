package util;

public class InversionCount {

    public static int count(int[] array) {

	return mergeSort(array, 0, array.length);
    }

    private static int mergeSort(int[] array, int low, int high) {

	if (low == high - 1)
	    return 0;
	int mid = (low + high) / 2;
	return mergeSort(array, low, mid) + mergeSort(array, mid, high)
	       + merge(array, low, mid, high);
    }

    private static int merge(int[] array, int low, int mid, int high) {

	int count = 0;
	int[] buff = new int[array.length];
	for (int i = low, lb = low, hb = mid; i < high; i++) {
	    if (hb >= high || lb < mid && array[lb] <= array[hb]) {
		buff[i] = array[lb++];
	    } else {
		count = count + (mid - lb);
		buff[i] = array[hb++];
	    }
	}
	System.arraycopy(buff, low, array, low, high - low);
	return count;
    }
}
