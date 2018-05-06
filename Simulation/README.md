Program tworzy symulacje, wykorzystujac wielowatkowosc.

Symulacja: 
Plansza do symulacji jest prostokątem w na h pol. Rozmiar planszy ( width i height ),
szybkosc dzialnia (v- velocity) i prawdopodobienstwo (p- probability) zmiany koloru sa 
podane jako parametry. Poczatkowe kolory pol sa losowe.
Kazde pole jest watkiem, ktory co pewien czas (opoznienie jest równe losowo wybranej
liczbie milisekund z przedzialu [0.5v, 1.5v] ) wykonuje nastepujace czynnosci:
  -z prawdopodobienstwem p zmienia swój kolor na losowy;
  -z prawdopodobienstwem 1 − p sprawdza kolory swoich czterech sasiadów (plansza
  jest traktowana jako dwuwymiarowy torus) i przyjmuje jako kolor sredni z nich.

Aplikacja moze zmieniac rozmiar. 
