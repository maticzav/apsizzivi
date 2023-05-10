import java.util.Scanner;

public class Izziv10 {
	static public void main(String[] args) {
		// Read the input.
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		sc.close();

		EggDrop ed = new EggDrop(n, k);

		// Header
		System.out.printf("%4s", "");
		for (int j = 1; j <= k; j++) {
			System.out.printf("%4d", j);
		}
		System.out.println();

		// Table

		// Rows 0...n
		for (int i = 0; i <= n; i++) {
			System.out.printf("%4d", i);

			// Columns 1...k
			for (int j = 1; j <= k; j++) {
				int val = ed.calc(i, j);
				System.out.printf("%4d", val);
			}

			System.out.println();
		}

	}

}

class EggDrop {

	private int eggsizes;
	private Integer[] cache;

	public EggDrop(int maxfloors, int eggsizes) {
		this.cache = new Integer[(maxfloors + 1) * eggsizes];
		this.eggsizes = eggsizes;
	}

	public int calc(int n, int k) {
		if (n == 0) {
			// No floors, no need to calcualte anything.
			return 0;
		}

		if (k == 1) {
			// One egg, we neeed to check every floor.
			return n;
		}

		int key = n * eggsizes + k - 1;
		if (this.cache[key] != null) {
			return this.cache[key];
		}

		// NOTE: We need to check all floors and take the best one since we are
		// optimising for the fewest drops.
		int min = Integer.MAX_VALUE;

		for (int x = 1; x <= n; x++) {
			// We need to take the worst case scenario and we don't know which of the two it
			// is.
			int max = Math.max(calc(x - 1, k - 1), calc(n - x, k));

			min = Math.min(min, max);
		}

		int value = min + 1;

		this.cache[key] = value;

		return value;
	}
}