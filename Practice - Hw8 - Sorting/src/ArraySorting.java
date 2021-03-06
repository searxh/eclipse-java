import java.util.Random;

public class ArraySorting {
	public static void main(String [] args) {
		
		int n=16000000;
		
		int [] array = new int[n];
		Random rnd = new Random(1234567);
		for (int i=0; i < n; i++) {
			array[i] = rnd.nextInt(n);
		}
		System.out.println("sorting...");
		long startTime = System.nanoTime();
		sort(array);
		long endTime = System.nanoTime();
		//printer(array); //prints sorted
		System.out.println("time taken: "+((endTime-startTime)/1e9)+" seconds");
		
	}
	public static void sort(int [] d) {
		//bubbleSort(d);
		//insertionSort(d);
		//selectionSort(d);
		//quickSort(d);
		mergeSort(d);
	}
	//----------------------------------------------------------------------------//
	public static void bubbleSort(int[] d) {
	    for (int i = d.length - 1; i > 0; i--) {
	    	boolean isSorted = true;
	    	for (int j = 0; j < i; j++) {
		        if (d[j+1] < d[j]) {
		        	isSorted = false;
		        	int t = d[j+1];
		        	d[j+1] = d[j];
		        	d[j] = t;
		        }
	    	}
	    	if (isSorted) break;
	    }
	}
	//----------------------------------------------------------------------------//
	public static void insertionSort(int[] d) {
	    for (int k = 1; k < d.length; k++) {
	      int t = d[k];
	      int j = k-1;
	      while (j >= 0) {
	        if (d[j] <= t) break;
	        d[j+1] = d[j];
	        j--;
	      }
	      d[j+1] = t;
	    }
	  }
	//----------------------------------------------------------------------------//
	public static void selectionSort(int[] d) {
		for (int k = d.length-1; k > 0; k--) {
	      int maxI = 0;
	      for (int i = 1; i <= k; i++) {
	        if (d[maxI] < d[i]) maxI = i;
	      }
	      int t = d[maxI];
	      d[maxI] = d[k];
	      d[k] = t;
		}
	 }
	//----------------------------------------------------------------------------//
	public static void quickSort(int[] d) {
		quickSortR(d, 0, d.length - 1);
	}
	public static void quickSortR(int[] d, int left, int right) {
		if (left < right) {
			int j = partition(d, left, right);
			quickSortR(d, left, j - 1);
			quickSortR(d, j + 1, right);
	    }
	}
	public static int partition(int[] d, int left, int right) {
	    int p = d[left];
	    int i = left, j = right + 1;
	    while (i < j) {
	    	while (p < d[--j]);
	    		while (d[++i] < p) {
	    			if (i == right) break;
	    		}
	    	if (i < j) {
	    		int t = d[i];
	    		d[i] = d[j];
	    		d[j] = t;
	    	}
	    }
	    int t = d[left];
	    d[left] = d[j];
	    d[j] = t;
	    return j;
	}
	//----------------------------------------------------------------------------//
	public static void mergeSort(int[] d) {
	    mergeSortR(d, 0, d.length-1, d.clone());
	  }
	public static void mergeSortR(int[] d, int left, int right, int[] t) {
		if (left < right) {
			int m = left + (right-left) / 2;
			mergeSortR(t, left, m, d);
			mergeSortR(t, m+1, right, d);
			merge(t, left, m, right, d);
	    }
	}
	public static void merge(int[] d, int left, int mid, int right, int[] t) {
		int i = left, j = mid + 1;
		int k = left;
		while (i <= mid && j <= right) {
	      t[k++] = d[i] < d[j] ? d[i++] : d[j++];
	    }
	    while (i <= mid) {
	      t[k++] = d[i++];
	    }
	    while (j <= right) {
	      t[k++] = d[j++];
	    }
	}
	//----------------------------------------------------------------------------//
	public static void printer(int[] arr) {
		System.out.println("Array: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]+",");
		}
		System.out.println("");
	}
}
