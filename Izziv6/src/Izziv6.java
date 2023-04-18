import java.util.Scanner;

class SquareMatrix {

	private int[][] m;
	public int n;

	public SquareMatrix(int n) {
		this.n = n;
		m = new int[n][n];
	}

	// Set value at row i, column j.
	public void setValue(int i, int j, int val) {
		this.m[i][j] = val;
	}

	/// Get value at row i, column j.
	public int getValue(int i, int j) {
		return this.m[i][j];
	}

	// Returns a submatrix from this matrix, starting at row `startRow` and column
	// `startCol`.
	public SquareMatrix getSubmatrix(int startRow, int startCol, int dim) {
		SquareMatrix sub = new SquareMatrix(dim);
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				int val = this.m[startRow + i][startCol + j];
				sub.setValue(i, j, val);
			}
		}
		return sub;
	}

	// Write a given matrix as a submatrix of this matrix, starting at row
	// `startRow` and column `startCol`.
	public void putSubmatrix(int startRow, int startCol, SquareMatrix b) {
		for (int i = 0; i < b.n; i++) {
			for (int j = 0; j < b.n; j++) {
				this.setValue(startRow + i, startCol + j, b.getValue(i, j));
			}
		}
	}

	// Add two matrices using standard matrix addition.
	public SquareMatrix add(SquareMatrix b) {
		if (this.n != b.n) {
			throw new IllegalArgumentException("Matrices must be of the same size.");
		}

		SquareMatrix c = new SquareMatrix(n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int aVal = this.getValue(i, j);
				int bVal = b.getValue(i, j);

				c.setValue(i, j, aVal + bVal);
			}
		}

		return c;

	}

	// Subtract two matrices using standard matrix subtraction.
	public SquareMatrix sub(SquareMatrix b) {
		if (this.n != b.n) {
			throw new IllegalArgumentException("Matrices must be of the same size.");
		}

		SquareMatrix c = new SquareMatrix(n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int aVal = this.getValue(i, j);
				int bVal = b.getValue(i, j);

				c.setValue(i, j, aVal - bVal);
			}
		}

		return c;
	}

	// simple multiplication
	public SquareMatrix mult(SquareMatrix b) {
		if (this.n != b.n) {
			throw new IllegalArgumentException("Matrices must be of the same size.");
		}

		SquareMatrix c = new SquareMatrix(this.n);

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				int sum = 0;
				for (int k = 0; k < this.n; k++) {
					sum += this.getValue(i, k) * b.getValue(k, j);
				}
				c.setValue(i, j, sum);
			}
		}

		return c;
	}

	// Strassen multiplication
	public SquareMatrix multStrassen(SquareMatrix b, int cutoff) {
		if (this.n != b.n) {
			throw new IllegalArgumentException("Matrices must be of the same size.");
		}

		if (this.n <= cutoff) {
			return this.mult(b);
		}

		int dim = this.n / 2;

		SquareMatrix a11 = this.getSubmatrix(0, 0, dim);
		SquareMatrix a12 = this.getSubmatrix(0, dim, dim);
		SquareMatrix a21 = this.getSubmatrix(dim, 0, dim);
		SquareMatrix a22 = this.getSubmatrix(dim, dim, dim);

		SquareMatrix b11 = b.getSubmatrix(0, 0, dim);
		SquareMatrix b12 = b.getSubmatrix(0, dim, dim);
		SquareMatrix b21 = b.getSubmatrix(dim, 0, dim);
		SquareMatrix b22 = b.getSubmatrix(dim, dim, dim);

		// Multiply submatrices using Strassen's algorithm.
		SquareMatrix m1 = a11.add(a22).multStrassen(b11.add(b22), cutoff);
		SquareMatrix m2 = a21.add(a22).multStrassen(b11, cutoff);
		SquareMatrix m3 = a11.multStrassen(b12.sub(b22), cutoff);
		SquareMatrix m4 = a22.multStrassen(b21.sub(b11), cutoff);
		SquareMatrix m5 = a11.add(a12).multStrassen(b22, cutoff);
		SquareMatrix m6 = a21.sub(a11).multStrassen(b11.add(b12), cutoff);
		SquareMatrix m7 = a12.sub(a22).multStrassen(b21.add(b22), cutoff);

		System.out.println("m1: " + m1.sumOfElements());
		System.out.println("m2: " + m2.sumOfElements());
		System.out.println("m3: " + m3.sumOfElements());
		System.out.println("m4: " + m4.sumOfElements());
		System.out.println("m5: " + m5.sumOfElements());
		System.out.println("m6: " + m6.sumOfElements());
		System.out.println("m7: " + m7.sumOfElements());

		// Calculate submatrices of the result matrix.
		SquareMatrix c11 = m1.add(m4).sub(m5).add(m7);
		SquareMatrix c12 = m3.add(m5);
		SquareMatrix c21 = m2.add(m4);
		SquareMatrix c22 = m1.sub(m2).add(m3).add(m6);

		// Put submatrices together to form the result matrix.
		SquareMatrix c = new SquareMatrix(this.n);
		c.putSubmatrix(0, 0, c11);
		c.putSubmatrix(0, dim, c12);
		c.putSubmatrix(dim, 0, c21);
		c.putSubmatrix(dim, dim, c22);

		return c;
	}

	/// Returns the sum of all elements in the matrix.
	public int sumOfElements() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += this.getValue(i, j);
			}
		}
		return sum;
	}

	public void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(this.getValue(i, j));

				if (j < n - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}

public class Izziv6 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int cutoff = scanner.nextInt();

		SquareMatrix a = new SquareMatrix(n);
		SquareMatrix b = new SquareMatrix(n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int val = scanner.nextInt();
				a.setValue(i, j, val);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int val = scanner.nextInt();
				b.setValue(i, j, val);
			}
		}

		scanner.close();

		SquareMatrix c = a.multStrassen(b, cutoff);
		c.print();
	}
}