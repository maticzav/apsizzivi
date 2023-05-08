# Izziv 9

Implementirajte program, ki izpiše sled izvajanja Ford-Fulkersonovega algoritma za maksimalne pretoke. 

Kot prvi argument na standardnem vhodu dobite število vozlišč v omrežju (vozlišča od 0 do n-1). Nato preberite na standardnem vhodu povezave v tem omrežju. Povezava je podana kot tri cela števila, prvi dve števili označujeta vozlišči na tej usmerjeni povezavi, tretje število pa predstavlja kapaciteto povezave. 

Izračunajte maksimalni pretok v podanem omrežju, med izvajanjem algoritma pa izpišite vsako nezasičeno pot, ki ste jo našli. Primer izpisa ene nezasičene poti : 

```
1: 5+  4+  1- 3+  2+  0
```

> najprej je izpisana količina pretoka (1:), ki ga lahko spravimo po tej poti, nato pa je zaporedje vozlišč na poti (*od ponora proti izvoru*). Vsako vozlišče (razen izvora) ima še oznako, ali smo do njega prišli po pozitivni (+) ali po negativni (-) povezavi.

Pri izbiranju, katero označeno vozlišče obiskati naslednje, se držite pravila, da najprej izberemo vozlišče z najmanjšim indeksom.