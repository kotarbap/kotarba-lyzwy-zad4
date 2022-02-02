# kotarba-lyzwy-zad4, Piotr Kotarba
Wypozyczalnia lyzew, wykorzystujaca Hibernate do operowania na bazie MySQL przez xampp
Nalezy jedynie uruchomic xamppa, a w nim uslugi Apache i MySQL. Program samodzielnie stworzy baze danych, tabele i wypelni je danymi

Kontrolery które nas interesują, znajdują się w paczce pl.edu.wszib.kotarba.ice.skates.controllers.rest
To tam zaszły jedyne zmiany względem zadania 3
Testowanie endpointow, sprawdzane w Insomni:

http://localhost:8080/endpoint1 Wyswietlenie wszystkich lyzew w bazie
http://localhost:8080/endpoint2/{skatesId} Wyswietlenie lyzew o okreslonym ID w bazie
http://localhost:8080/endpoint3 Edytuje rekord w bazie lyzew o określonym ID. Przekazujemy JSON, np.:
{
	"id": 2,
	"brand": "Nowe",
	"model": "Bardzo",
	"price": 120.0,
	"size": 31,
	"quantity": 100
}

http://localhost:8080/endpoint4 Pokazuje aktualnie zalogowanego uzytkownika. Z niewiadomych dla mnie przyczyn działa jedynie w przeglądarce, a w Insomni wcale
http://localhost:8080/endpoint5 Pozwala bardzo latwo zarejestrowac nowego uzytkownika. Przekazujemy JSON, np.:
{
	"id": 1,
	"name": "Piotr",
	"surname": "Piotr",
	"login": "piotr",
	"pass": "piotr"
}

http://localhost:8080/endpoint6 Pokazuje zamowienia aktualnie zalogowanego uzytkownika. Z niewiadomych dla mnie przyczyn również działa jedynie w przeglądarce, a w Insomni wcale
http://localhost:8080/endpoint7 Pokazuje dane uzytkownika którego login przekażemy w nagłówku żądania POST, np.:
login: piotr
