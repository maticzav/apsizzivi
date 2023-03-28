import java.util.Scanner;

public class Izziv3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Figure out the size of the array.
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		/// Two arrays of increasing integers.
		int[] as = new int[n];
		int[] bs = new int[m];

		for (int i = 0; i < n; i++) {
			as[i] = scanner.nextInt();
		}

		for (int j = 0; j < m; j++) {
			bs[j] = scanner.nextInt();
		}

		scanner.close();

		// Sort the array using heap sort.
		MergeResult result = merge(as, bs);

		System.out.println(result.pattern);
		for (int i = 0; i < result.list.length; i++) {
			System.out.print(result.list[i]);

			if (i < result.list.length - 1) {
				System.out.print(" ");
			}
		}
	}

	static class MergeResult {
		int[] list;
		String pattern;

		public MergeResult(int[] list, String pattern) {
			this.list = list;
			this.pattern = pattern;
		}
	}

	private static MergeResult merge(int[] as, int[] bs) {
		String pattern = "";
		int[] list = new int[as.length + bs.length];

		int ai = 0;
		int bi = 0;

		// Both lists still have remaining items.
		while (ai < as.length && bi < bs.length) {
			if (as[ai] <= bs[bi]) {
				pattern += "a";
				list[ai + bi] = as[ai];
				ai++;
			} else {
				pattern += "b";
				list[ai + bi] = bs[bi];
				bi++;
			}
		}

		// Check whether as have some leftover elements.
		if (ai < as.length) {
			for (; ai < as.length; ai++) {
				pattern += "a";
				list[ai + bi] = as[ai];
			}
		}

		// Check whether bs have some leftover elements.
		if (bi < bs.length) {
			for (; bi < bs.length; bi++) {
				pattern += "b";
				list[ai + bi] = bs[bi];
			}
		}

		return new MergeResult(list, pattern);
	}

}
