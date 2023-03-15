import java.util.Scanner;

public class Izziv2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Figure out the size of the array.
		int n = scanner.nextInt();

		// Populate the array.
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = scanner.nextInt();
		}

		scanner.close();

		// Sort the array using heap sort.
		sort(array);
	}

	private static void sort(int[] array) {
		// Prepare the initial heap.
		for (int i = array.length / 2; i >= 0; i--) {
			heapify(array, i, array.length);
		}

		print(array, array.length);

		int size = array.length;
		while (size > 1) {
			// Swap the first and the last element.
			int temp = array[0];
			array[0] = array[size - 1];
			array[size - 1] = temp;

			// Decrease the size of the heap.
			size--;

			// Heapify the heap.
			heapify(array, 0, size);

			print(array, size);
		}
	}

	private static void heapify(int[] array, int i, int size) {
		// We are on the last level of the heap or below.
		if (i >= size / 2) {
			return;
		}

		// We start with the left son and check the right one if possible.
		int indexOfLargerSon = 2 * i + 1;

		if (indexOfLargerSon + 1 < size) {
			// Check if right son is larger and adjust the index of the larger son.
			if (array[indexOfLargerSon + 1] > array[indexOfLargerSon]) {
				indexOfLargerSon++;
			}
		}

		if (array[i] < array[indexOfLargerSon]) {
			// Swap the elements.
			int temp = array[i];
			array[i] = array[indexOfLargerSon];
			array[indexOfLargerSon] = temp;

			// Recursively heapify the subtree.
			heapify(array, indexOfLargerSon, size);
		}
	}

	/// Prints the heap by levels.
	private static void print(int[] array, int size) {
		int batch = 1; // Number of nodes that are on the current level.
		int printed = 0; // Number of printed nodes.

		for (int i = 0; i < size; i++) {
			System.out.print(array[i]);

			if (i < size - 1) {
				System.out.print(" ");
			}

			printed++;

			if (printed == batch && i < size - 1) {
				System.out.print("| ");
				batch *= 2;
				printed = 0;
			}
		}

		System.out.println();
	}
}
