package SvsQ;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		int[] arr1 = RandomizedArray(10000000);
		int[] arr2 = copyArr(arr1);
		
		long start = System.currentTimeMillis();
		QuickSort(arr1, 0, arr1.length - 1);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Sorting a random array size of " + arr1.length + " took Quick Sort " + timeElapsed + "ms to complete.");
	
		start = System.currentTimeMillis();
		ShellSort(arr2);
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("Sorting a random array size of " + arr1.length + " took Shell Sort " + timeElapsed + "ms to complete.");
	}

	public static int Partition(int[] arr, int low, int high) {
		int start = low;
		int end = high;
		int pivot = arr[(low + high)/2];
		boolean done = false;
		int temp = -1;
		while(!done) {
			while(arr[start] < pivot && start <= high) {
				start++;
			}
			while(arr[end] > pivot && end > low) {
				end--;
			}
			if(end > start) {
				temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				start++;
				end--;
			} else {
				done = true;
			}
			
		}
		return end;
	}
	
	public static void QuickSort(int[] arr, int low, int high) {
		if(low >= high) {
			return;
		} else {
			int mid = Partition(arr, low, high);
			QuickSort(arr, low, mid);
			QuickSort(arr, mid + 1, high);
		}
	}
	
	public static void ShellPartition(int[] arr, int start, int gap) {
		for(int i = start; i < arr.length; i = i + gap) {
			int pos = i;
			while(pos - gap >= start && arr[pos - gap] > arr[pos]) {
				int temp = arr[pos];
				arr[pos] = arr[pos - gap];
				arr[pos - gap] = temp;
				pos = pos - gap;
 			}
		}
	}
	
	public static void ShellSort(int[] arr) {
		int length = arr.length;
        int n = (int)Math.floor(Math.log(length + 1));
        for(int i = n; i > 0; i--){
            int gap = (int)Math.pow(2, i) - 1;
            for(int start = 0; start < gap; start++){
                ShellPartition(arr, start, gap);
            }
        }
	}
	
	public static int[] RandomizedArray(int size){
        Random r = new Random();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = i + 1;
        }
        int temp = -1;
        int ran = -1;
        for(int i = 0; i < size - 1; i++){
            temp = arr[i];
            ran = r.nextInt(size - i) + i;
            arr[i] = arr[ran];
            arr[ran] = temp;
        }
        return arr;
    }
	
	
	public static int[] copyArr(int[] arr1){
        int[] arr2 = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++){
            arr2[i] = arr1[i];
        }
        return arr2;
    }
	
}
