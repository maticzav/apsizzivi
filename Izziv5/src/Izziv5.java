import java.util.LinkedList;
import java.util.Scanner;

public class Izziv5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Read a series of integers from the standard input.
		LinkedList<Integer> llist = new LinkedList<Integer>();
		while (scanner.hasNextInt()) {
			llist.add(scanner.nextInt());
		}

		scanner.close();

		// Find the largest joint subsequence.
		int[] arr = new int[llist.size()];
		for (int i = 0; i < llist.size(); i++) {
			arr[i] = llist.get(i);
		}

		findSumOfTheLargestJointSubsequence(arr, 0, arr.length - 1);
	}

	/// Finds the largest subsequence in the list from index l up to incl. index r.
	private static int findSumOfTheLargestJointSubsequence(int[] list, int l, int r) {
		if (l > r) {
			throw new Error("Invalid range.");
		}

		// BASE CASE: The list contains only one element.
		if (l == r) {
			print(list, l, r, list[l]);
			return list[l];
		}

		// Split the list into two sublists.
		// NOTE: We want the left sublist to be larger than the right one.
		// By adding one one of two things might happen:
		// 1. size is odd and the left half is going to be larger.
		// 2. size is even and 1/2 won't change the result in integer
		// division.
		int mid = (l + r) / 2;

		int left_sum = findSumOfTheLargestJointSubsequence(list, l, mid);
		int right_sum = findSumOfTheLargestJointSubsequence(list, mid + 1, r);

		// Find the largest joint subsequence that crosses the middle.
		// NOTE: There has to be at least one element from the left and right sublist.
		// Because of that we start with the sum of the last item from the left sublist
		// and the first item from the right sublist.
		int sum = list[mid] + list[mid + 1];
		int max = sum;

		int i = mid - 1; // We have already added the last item from the left sublist.
		while (l <= i) {
			sum += list[i];
			if (sum > max) {
				max = sum;
			}

			i--;
		}

		sum = max;

		int j = mid + 2; // We have already added the first item from the right sublist.
		while (j <= r) {
			sum += list[j];
			if (sum > max) {
				max = sum;
			}

			j++;
		}

		int largest_sum = Math.max(max, Math.max(left_sum, right_sum));

		print(list, l, r, largest_sum);

		return largest_sum;
	}

	private static void print(int[] list, int l, int r, int sum) {
		String series = "";
		for (int i = l; i <= r; i++) {
			series += list[i];
			if (i < r) {
				series += ", ";
			}
		}

		String out = String.format("[%s]: %d", series, sum);
		System.out.println(out);
	}

}
