import java.util.LinkedList;
import java.util.Scanner;

public class Izziv11 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int W = scanner.nextInt();
		int n = scanner.nextInt();

		Item[] items = new Item[n];

		for (int i = 0; i < n; i++) {
			int weight = scanner.nextInt();
			int value = scanner.nextInt();

			items[i] = new Item(value, weight);
		}

		scanner.close();

		//

		LinkedList<PossibleSolution> options = new LinkedList<PossibleSolution>();

		options.add(new PossibleSolution(0, 0));
		System.out.println("0: (0, 0)");

		// We add each item, one by one.
		for (int i = 0; i < items.length; i++) {

			LinkedList<PossibleSolution> newOptions = new LinkedList<PossibleSolution>();

			for (PossibleSolution option : options) {
				int newWeight = option.weight + items[i].weight;
				int newValue = option.value + items[i].value;

				if (newWeight > W) {
					continue;
				}

				PossibleSolution opt = new PossibleSolution(newValue, newWeight);

				newOptions.add(opt);

			}

			options.addAll(newOptions);

			// Remove dominated options.
			for (int j = 0; j < options.size(); j++) {
				for (int k = j + 1; k < options.size(); k++) {
					PossibleSolution a = options.get(j);
					PossibleSolution b = options.get(k);

					if (a.isDominatedBy(b)) {
						options.remove(j);
						j--;
						break;
					} else if (b.isDominatedBy(a)) {
						options.remove(k);
						k--;
					}
				}
			}

			// Sort options by weight.
			options.sort((a, b) -> a.weight - b.weight);

			System.out.print((i + 1) + ": ");
			for (PossibleSolution option : options) {
				System.out.print(option + " ");
			}
			System.out.println();
		}
	}
}

class PossibleSolution {
	int value;
	int weight;

	public PossibleSolution(int value, int weight) {
		this.weight = weight;
		this.value = value;
	}

	public String toString() {
		return "(" + this.weight + ", " + this.value + ")";
	}

	/// Tells whether this solution may be ignored.
	public boolean isDominatedBy(PossibleSolution other) {
		return this.value <= other.value && this.weight >= other.weight;
	}
}

class Item {
	int value;
	int weight;

	public Item(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
}