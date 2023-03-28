# Izziv 4

Napišite program, ki tabelo 32 bitnih celih števil uredi glede na število bitov, ki so enaki 1 v dvojiški predstavitvi tega števila.

Npr. tabela 6 5 4 1 3 bi bila urejena 4 1 6 5 3, ker 4 = 100, 1 = 1, 6 = 110, 5 = 101 in 3 = 11. Števila, ki imajo enako število enic ostanejo v istem vrstnem redu kot pred urejanjem.

Za urejanje uporabite stabilno urejanje s štetjem (counting sort). Na standardnem vhodu boste najprej prejeli dolžino tabele n, za tem pa n celih števil.

Na standardni izhod izpisujete potek vpisovanja elementov v končno (urejeno tabelo). Za vsak zapis zapišete v eni vrstici par (el, pos), kjer je el število, ki ga zapisujete, pos pa indeks v tabeli kamor ta element zapisujete. V zadnji vrstici izpišite še urejeno tabelo ločeno s presledki.