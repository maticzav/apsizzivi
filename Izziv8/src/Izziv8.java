import java.util.Scanner;

public class Izziv8 {
	public static void main(String[] args) {

		// Read input

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		// NOTE: n tells the number of coefficients meaning that the degree of the
		// polynomial is n - 1. We need k + 1 point values to determine a polynomial of
		// degree k. Since the product of polynomials of power a and b has power a + b,
		// we need 2 * (n - 1 + 1) = 2 * n point values to determine the product of two
		// polynomials of degree n - 1.
		int d = 1;
		while (d < 2 * n) {
			d *= 2;
		}

		Complex[] as = new Complex[d];
		for (int i = 0; i < n; i++) {
			as[i] = new Complex(sc.nextDouble(), 0);
		}
		for (int i = n; i < d; i++) {
			as[i] = new Complex(0, 0);
		}

		Complex[] bs = new Complex[d];
		for (int i = 0; i < n; i++) {
			bs[i] = new Complex(sc.nextDouble(), 0);
		}
		for (int i = n; i < d; i++) {
			bs[i] = new Complex(0, 0);
		}

		sc.close();

		// Calculate FFT of as and bs

		Complex[] asFft = fft(as, d, false);
		Complex[] bsFft = fft(bs, d, false);

		// Calculate FFT of as * bs
		Complex[] asTimesBsFft = new Complex[d];
		for (int i = 0; i < d; i++) {
			asTimesBsFft[i] = asFft[i].times(bsFft[i]);
		}

		// Convert back to polynomial representation
		Complex[] asTimesBs = fft(asTimesBsFft, d, true);

		// Print the result
		for (int i = 0; i < d; i++) {
			System.out.print(asTimesBs[i].times(1 / (double) d).toString() + " ");
		}
	}

	/// Converts from a polynomial to a point-value representation.
	/// NOTE: It expects the polynomial to be padded with zeros to the power of 2.
	private static Complex[] fft(Complex[] xs, int d, boolean inverse) {
		if (d == 1) {
			return xs;
		}

		Complex[] evens = new Complex[d / 2];
		Complex[] odds = new Complex[d / 2];

		for (int i = 0; i < d / 2; i++) {
			evens[i] = xs[2 * i];
			odds[i] = xs[2 * i + 1];
		}

		Complex[] evensFft = fft(evens, d / 2, inverse);
		Complex[] oddsFft = fft(odds, d / 2, inverse);

		double angle = (inverse ? -1 : 1) * 2 * Math.PI / (double) d;
		Complex w = new Complex(Math.cos(angle), Math.sin(angle));
		Complex wk = new Complex(1, 0);

		Complex[] fft = new Complex[d];

		for (int k = 0; k < d / 2; k++) {
			fft[k] = evensFft[k].plus(wk.times(oddsFft[k]));
			fft[k + d / 2] = evensFft[k].minus(wk.times(oddsFft[k]));

			wk = wk.times(w);
		}

		// Print the trace
		for (int i = 0; i < d; i++) {
			System.out.print(fft[i].toString() + " ");
		}
		System.out.println();

		return fft;
	}

}

class Complex {
	double re;
	double im;

	public Complex(double real, double imag) {
		this.re = real;
		this.im = imag;
	}

	public String toString() {
		double tRe = (double) Math.round(re * 100000) / 100000;
		double tIm = (double) Math.round(im * 100000) / 100000;

		if (tIm == 0)
			return tRe + "";
		if (tRe == 0)
			return tIm + "i";
		if (tIm < 0)
			return tRe + "-" + (-tIm) + "i";
		return tRe + "+" + tIm + "i";
	}

	public Complex conj() {
		return new Complex(re, -im);
	}

	public Complex plus(Complex b) {
		Complex a = this;
		double real = a.re + b.re;
		double imag = a.im + b.im;
		return new Complex(real, imag);
	}

	public Complex minus(Complex b) {
		Complex a = this;
		double real = a.re - b.re;
		double imag = a.im - b.im;
		return new Complex(real, imag);
	}

	public Complex times(Complex b) {
		Complex a = this;
		double real = a.re * b.re - a.im * b.im;
		double imag = a.re * b.im + a.im * b.re;
		return new Complex(real, imag);
	}

	public Complex times(double alpha) {
		return new Complex(alpha * re, alpha * im);
	}

	public Complex reciprocal() {
		double scale = re * re + im * im;
		return new Complex(re / scale, -im / scale);
	}

	public Complex divides(Complex b) {
		Complex a = this;
		return a.times(b.reciprocal());
	}

	public Complex exp() {
		return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
	}

	public Complex pow(int k) {
		Complex c = new Complex(1, 0);
		for (int i = 0; i < k; i++) {
			c = c.times(this);
		}
		return c;
	}
}