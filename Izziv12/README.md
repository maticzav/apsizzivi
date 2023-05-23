# Izziv 12

Napišite program, ki iz standardnega vhoda prebere podatke o usmerjenem grafu, in z algoritmom Bellman-Ford izračuna najkrajše poti od vozlišča 0 do vseh ostalih vozlišč.

Na standardnem vhodu bo najprej podano število vozlišč grafa, nato pa vse povezave tega grafa. Povezava bo podana kot trojka celih števil. Prvo število predstavlja začetno vozlišče, drugo število pa končno vozlišče povezave. Tretje število predstavlja dolžino povezave.

Na izhod izpisujte vrednost  najkrajših poti od vozlišča 0 do vseh ostalih za vsako vrednost h.

### Primer

#### Vhod

```
6 
0 1 1
1 3 2 
3 0 2 
1 2 5
1 5 7 
3 2 1 
3 4 4 
2 5 1 
5 4 1 
4 3 3
```


#### Izhod

```
h0: 0 Inf Inf Inf Inf Inf
h1: 0 1 Inf Inf Inf Inf
h2: 0 1 6 3 Inf 8
h3: 0 1 4 3 7 7
h4: 0 1 4 3 7 5
h5: 0 1 4 3 6 5
```