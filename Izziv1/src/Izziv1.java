import java.util.Random;

public class Izziv1 {
	/// Returns an array of numbers from 1 to n.
	private static int[] generateTable(int n) {
		int[] table = new int[n];
		for (int i = 0; i < n; i++) {
			table[i] = i;
		}
		return table;
	}

	/// Linearly searches a list and returns the index of the first occurrence of
	/// the element in array.
	private static int findLinear(int[] as, int val) {
		for (int i = 0; i < as.length; i++) {
			if (as[i] == val) {
				return i;
			}
		}
		return -1;
	}

	/// Searches the list using bisection and returns the index of the elemnt in the
	/// array. Expects the array to be sorted.
	private static int findBinary(int[] as, int left, int right, int val) {
		if (left > right) {
			return -1;
		}

		int mid = (left + right) / 2;
		if (as[mid] == val) {
			return mid;
		} else if (as[mid] > val) {
			return findBinary(as, left, mid - 1, val);
		} else {
			return findBinary(as, mid + 1, right, val);
		}
	}

	/// Measures the average time of 1000 linear searches in a list of size n.
	private static long timeLinear(int n) {
		int[] table = generateTable(n);
		long start = System.nanoTime();

		Random rand = new Random();

		for (int i = 0; i < 1000; i++) {
			findLinear(table, rand.nextInt(n));

		}

		long end = System.nanoTime();

		return (end - start) / 1000;
	}

	/// Measures the average time of 1000 binary searches in a list of size n.
	private static long timeBinary(int n) {
		int[] table = generateTable(n);
		long start = System.nanoTime();

		Random rand = new Random();

		for (int i = 0; i < 1000; i++) {
			findBinary(table, 0, n - 1, rand.nextInt(n));
		}

		long end = System.nanoTime();

		return (end - start) / 1000;
	}

	public static void main(String[] args) {
		System.out.println("n \t | linear \t | binary");
		for (int i = 1000; i <= 100000; i += 1000) {
			System.out.println(i + " \t | " + timeLinear(i) + " \t | " + timeBinary(i));
		}
	}
}
