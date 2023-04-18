import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Izziv7 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		scanner.close();

		// Find the first Z_p that has a n-th primitive root.
		int p = n;
		int w = 1;

		while (true) {
			if (!isPrime(p)) {
				p++;
				continue;
			}

			List<Integer> prs = new LinkedList<Integer>();

			// We manually check all the numbers from 2 to p - 1.
			for (int i = 2; i < p; i++) {
				int acc = i;
				int pow = 1;

				while (acc != 1 && pow <= n) {
					acc = (acc * i) % p;
					pow++;
				}

				if (pow == n) {
					prs.add(i);
				}
			}

			if (prs.size() > 0) {
				System.out.print(p + ": ");
				for (int pr : prs) {
					System.out.print(pr + " ");
				}
				System.out.println();

				w = prs.get(0);

				break;
			}

			p++;
		}

		// Once we've found the first Z_p that has a n-th primitive root,
		// we construct the DFT matrix.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// NOTE: Brackets here are important because of the modulo!
				int val = (int) (Math.pow(w, i * j % n) % p);
				System.out.printf("%2d ", val);
			}
			System.out.println();
		}
	}

	/// Tells whether a given number is a prime.
	private static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}

		int sqrt = (int) Math.sqrt(n);

		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}