import java.util.Scanner;

public class Izziv4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Figure out parameters.
		int n = scanner.nextInt();
		int[] as = new int[n];

		for (int i = 0; i < n; i++) {
			as[i] = scanner.nextInt();
		}

		scanner.close();

		// Find the range of ones.
		int max = 0;
		for (int i = 0; i < n; i++) {
			int key = value(as[i]);
			if (key > max) {
				max = key;
			}
		}

		// Initialize the counts array.
		int[] counts = new int[max + 1];
		for (int i = 0; i < n; i++) {
			int key = value(as[i]);
			counts[key]++;
		}

		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}

		// Sort the numbers.
		int sorted[] = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			int e = as[i];
			int key = value(e);

			counts[key]--;

			int pos = counts[key];
			sorted[pos] = e;

			System.out.println("(" + e + "," + pos + ")");
		}

		for (int i = 0; i < n; i++) {
			System.out.print(sorted[i]);

			if (i < n - 1) {
				System.out.print(" ");
			}
		}
	}

	/// Returns the number of ones in the binary representation of the given number.
	private static int value(int i) {
		int count = 0;

		while (i > 0) {
			if ((i & 1) == 1) {
				count++;
			}

			i >>= 1;
		}

		return count;
	}

}
