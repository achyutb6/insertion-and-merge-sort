/** Implementation of insertion sort and various modifications of MergeSort
 *  @author @Mythri Thippareddy, Achyut Bhandiwad
 */

package mxt172530;

import java.util.Random;

public class SP9 {
	public static Random random = new Random();
	public static int numTrials = 100; //Number of Trials
	static final int Threshold = 20; // Threshold value for take 2 and 3 of mergeSort

	public static void main(String[] args) {
		int n = 10;  
		int choice = 1 + random.nextInt(4);
		if(args.length > 0) { 
			n = Integer.parseInt(args[0]); 
		}
		if(args.length > 1) {
			choice = Integer.parseInt(args[1]); 
		}

		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			choice = Integer.parseInt(args[1]);
		}
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		
		//Timers for each sorting algorithm
		Timer timer = new Timer();
		Timer timerInsertSort = new Timer();
		Timer mergeSort1 = new Timer();
		Timer mergeSort2 = new Timer();
		Timer mergeSort3 = new Timer();

		long insertionSortTime = 0;
		long mergeSort1Time = 0;
		long mergeSort2Time = 0;
		long mergeSort3Time = 0;

		int countInsertionSort = 0;
		int countMergeSort1 = 0;
		int countMergeSort2 = 0;
		int countMergeSort3 = 0;

		switch (choice) {
		case 1:
			Shuffle.shuffle(arr);
			numTrials = 1;
			timerInsertSort.start();
			insertionSort(arr);
			timerInsertSort.end();
			insertionSortTime +=timerInsertSort.elapsedTime;
			countInsertionSort++;
			System.out.println("===================Average time for insertionSort:"+insertionSortTime/countInsertionSort);
			break;
		case 2:
			System.out.println("MergeSort1");
			for (int i = 0; i < numTrials; i++) {
				System.out.println("Trial:"+i);
				Shuffle.shuffle(arr);
				mergeSort1.start();
				mergeSort1(arr);
				mergeSort1.end();
				mergeSort1Time += mergeSort1.elapsedTime;
				countMergeSort1++;
			}
			System.out.println("===================Average time for mergeSort1:"+mergeSort1Time/countMergeSort1);
			break; // etc
		case 3:
			System.out.println("MergeSort2");
			for (int i = 0; i < numTrials; i++) {
				System.out.println("Trial:"+i);
				Shuffle.shuffle(arr);
				mergeSort2.start();
				mergeSort2(arr);
				mergeSort2.end();
				mergeSort2Time += mergeSort2.elapsedTime;
				countMergeSort2++;
			}
			System.out.println("===================Average time for mergeSort2:"+mergeSort2Time/countMergeSort2);
			System.out.println("Threshold:"+Threshold);
			break; // etc
		case 4:
			System.out.println("MergeSort3");
			for (int i = 0; i < numTrials; i++) {
				System.out.println("Trial:"+i);
				Shuffle.shuffle(arr);
				mergeSort3.start();
				mergeSort3(arr);
				mergeSort3.end();
				mergeSort3Time += mergeSort3.elapsedTime;
				countMergeSort3++;
			}
			System.out.println("====================Average time for mergeSort3:"+mergeSort3Time/countMergeSort3);
			System.out.println("Threshold:"+Threshold);
			break; // etc
		}
		timer.end();
		timer.scale(numTrials);

		System.out.println("Choice: " + choice + " n:"+n+ "\n" + timer);
		
	}

	public static void insertionSort(int[] arr) {
		insertionSort(arr, 0, arr.length - 1);
	}

	private static void insertionSort(int[] arr, int p, int r) {
		int arrLength = r - p + 1;
		if (arrLength >= 2) {
			int i = p + 1;
			int temp, j;
			while (i <= r) {
				temp = arr[i];
				j = i - 1;
				while (j >= p && arr[j] > temp) {
					arr[j + 1] = arr[j];
					j = j - 1;
				}
				arr[j + 1] = temp;
				i++;
			}
		}
	}

	public static void mergeSort1(int[] arr) {
		mergeSort1(arr, 0, arr.length - 1);
	}

	private static void mergeSort1(int[] arr, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort1(arr, p, q);
			mergeSort1(arr, q + 1, r);
			merge1(arr, p, q, r);
		}
	}

	private static void merge1(int[] A, int p, int q, int r) {
		int[] L = new int[q - p + 1];
		int[] R = new int[r - q];
		System.arraycopy(A, p, L, 0, q - p + 1);
		System.arraycopy(A, q + 1, R, 0, r - q);
		int i = 0, j = 0;
		for (int k = p; k <= r; k++) {
			if ((j >= R.length) || (i < L.length && L[i] <= R[j])) {
				A[k] = L[i++];
			} else {
				A[k] = R[j++];
			}
		}
	}

	public static void mergeSort2(int[] A) {
		int[] B = new int[A.length];
		mergeSort2(A, B, 0, A.length);
	}

	private static void mergeSort2(int[] A, int[] B, int left, int n) {
		if (n < Threshold) {
			insertionSort(A, left, left + n - 1);
		} else {
			int Ln = n / 2;
			mergeSort2(A, B, left, Ln);
			mergeSort2(A, B, left + Ln, n - Ln);
			merge2(A, B, left, left + Ln - 1, left + n - 1);
		}
	}

	private static void merge2(int[] A, int[] B, int p, int q, int r) {
		System.arraycopy(A, p, B, p, r - p + 1);
		int i = p, j = q + 1;
		for (int k = p; k <= r; k++) {
			if ((j > r) || (i <= q && B[i] <= B[j])) {
				A[k] = B[i++];
			} else {
				A[k] = B[j++];
			}
		}
	}

	public static void mergeSort3(int[] A) {
		int[] B = new int[A.length];
		System.arraycopy(A, 0, B, 0, A.length);
		mergeSort3(A, B, 0, A.length);
	}

	private static void mergeSort3(int[] A, int[] B, int left, int n) {
		if (n < Threshold) {
			insertionSort(A, left, left + n - 1);
		} else {
			int Ln = n / 2;
			mergeSort3(B, A, left, Ln);
			mergeSort3(B, A, left + Ln, n - Ln);
			merge3(A, B, left, left + Ln - 1, left + n - 1);
		}
	}

	private static void merge3(int[] A, int[] B, int p, int q, int r) {
		int i = p;
		int j = q + 1;
		int k = p;
		while (i <= q && j <= r) {
			if (B[i] <= B[j]) {
				A[k++] = B[i++];
			} else {
				A[k++] = B[j++];
			}
		}
		while (i <= q) {
			A[k++] = B[i++];
		}
		while (j <= r) {
			A[k++] = B[j++];
		}
	}

	/**
	 * Timer class for roughly calculating running time of programs
	 * 
	 * @author rbk Usage: Timer timer = new Timer(); timer.start(); timer.end();
	 *         System.out.println(timer); // output statistics
	 */

	public static class Timer {
		long startTime, endTime, elapsedTime, memAvailable, memUsed;
		boolean ready;

		public Timer() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public void start() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public Timer end() {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			memAvailable = Runtime.getRuntime().totalMemory();
			memUsed = memAvailable - Runtime.getRuntime().freeMemory();
			ready = true;
			return this;
		}

		public long duration() {
			if (!ready) {
				end();
			}
			return elapsedTime;
		}

		public long memory() {
			if (!ready) {
				end();
			}
			return memUsed;
		}

		public void scale(int num) {
			elapsedTime /= num;
		}

		public String toString() {
			if (!ready) {
				end();
			}
			return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / "
					+ (memAvailable / 1048576) + " MB.";
		}
	}

	/**
	 * @author rbk : based on algorithm described in a book
	 */

	/* Shuffle the elements of an array arr[from..to] randomly */
	public static class Shuffle {

		public static void shuffle(int[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static <T> void shuffle(T[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static void shuffle(int[] arr, int from, int to) {
			int n = to - from + 1;
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		public static <T> void shuffle(T[] arr, int from, int to) {
			int n = to - from + 1;
			Random random = new Random();
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		static void swap(int[] arr, int x, int y) {
			int tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		static <T> void swap(T[] arr, int x, int y) {
			T tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		public static <T> void printArray(T[] arr, String message) {
			printArray(arr, 0, arr.length - 1, message);
		}

		public static <T> void printArray(T[] arr, int from, int to, String message) {
			System.out.print(message);
			for (int i = from; i <= to; i++) {
				System.out.print(" " + arr[i]);
			}
			System.out.println();
		}
	}
}
