# Izziv 8

Napiši program, ki na vhod dobi dva polinoma (podana kot vektorja), ter ju zmnoži s pomočjo rekurzivne hitre Fourierjeve transformacije.

Vhodni podatki:

Prvi argument programa je dolžina polinomov, oz. število koeficientov v podanih polinomih (oba polinoma bosta iste stopnje).
Koeficiente obeh polinomov nato preberete s standarnega vhoda. Vsi koeficienti so realna števila (Java tip double).
Zaradi končne natančnosti tipa double, lahko dobite rezultate, ki odstopajo od rezultatov, ki so podani v primerih.

Sled
Najprej izpišite sled izvajanja FFT na prvem polinomu.
Nato na drugem polinomu.
Nazadnje pa še sled izvajanja inverznega FFT.
V zadnji vrstici izpišite še končni rezultat, tj. vektor dobljen iz prejšnjega koraka pomnožen z 1/n.
Sled izvajanja enega FFT
Kot sled izvajanja izpišite dobljeni vektor v vsakem klicu FFT.

Če dobimo na vhod vektor (polinom):

 2 -3 -5 6  

potem je sled izvajanja:

-3.0 7.0 
3.0 -9.0 
0.0 7.0-9.0i -6.0 7.0+9.0i

Glej tudi primer 1 s prosojnic.

## Pomožni razredi

```java
class Complex{
	double re;
	double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
    	double tRe = (double)Math.round(re * 100000) / 100000;
    	double tIm = (double)Math.round(im * 100000) / 100000;
        if (tIm == 0) return tRe + "";
        if (tRe == 0) return tIm + "i";
        if (tIm <  0) return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

	public Complex conj() {
		return new Complex(re, -im);
	}

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }


    //potenca komplesnega stevila
    public Complex pow(int k) {

    	Complex c = new Complex(1,0);
    	for (int i = 0; i <k ; i++) {
			c = c.times(this);
		}
    	return c;
    }

}
```