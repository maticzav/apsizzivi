# Izziv 7

Napišite program, ki iz standardnega vhoda prebere celo število n in nato poišče najmanjše praštevilo p, tako da v Zp obstaja vsaj en n-ti primitivni koren. 

Kot rezultat naj program izpiše število p, pripadajoče n-te primitivne korene iz Zp ter Vandermondovo matriko, ki pripada najmanjšemu izmed primitivnih korenov.


### Primer (za n = 3)


1. Program prebere število 3.
1. Program predpostavi p=5 in poišče tretje primitivne korene v Z5. Ker jih ni, nadaljuje iskanje. 
1. Program predpostavi p=7 in poišče tretje primitivne korene v Z7. Tu najde dva (2 in 4) in zato zaključi iskanje p.

Program sedaj pripravi Vandermondovo matriko za najmanjši primitivni koren, tem primeru je to 2. Rezultate naj program izpisuje na standardni izhod. V prvi vrstici izhoda naj izpiše p in n-te primitivne korene iz Zp v naraščajočem vrstnem redu, v naslednjih n vrsticah pa vsebino Vandermondove matrike.