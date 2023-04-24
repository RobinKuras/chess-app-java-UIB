# Mitt program

Jeg har laget sjakk. Mitt spill fungerer slik at det øverst vises hvem sin tur det er, og om spilleren er satt i sjakk.

Hvordan det spilles:

1. spiller trykker med en musetast på en av brikkene sine. Dermed blir brikken valgt. Deretter kan du flytte brikken i henhold til sine regler.
   https://www.chesshouse.com/pages/chess-rules. 
(Det er dessverre ikke funksjonalitet for spesielle trekk som rokkade eller promotering av bønner 
2. Spillet endrer tur til andre siden av brettet. Og prosedyren gjentas.
3. Dersom en spiller flytter sin brikke og "tar over" feltet til en brikke av motsatt farge, blir den fangede brikken borte fra spillet.
4. Dersom din konge blir satt under angrep, altså at den neste runde kan angripes av din motsdander, vil du gå i det som heter "sjakk".
5. Når du er i sjakk er det kun lov å gjøre trekk som får deg ut av sjakk.
6. Dersom du ikke har flere gyldige trekk på din tur, går spillet over i "sjakkmatt" og motstanderen din har vunnet.


*Mangler*:

Jeg har dessverre i siste liten kommet over problemer med scanningen av hvorvidt ditt nye trekk vil sette deg selv i sjakk.
Kongen kan ikke sette seg selv i sjakk dersom spillet ikke allerede er i sjakk. Dersom kongen allerede står i sjakk, hender det at han får lov å bevege seg til uregelmessige felter.
For andre brikker gjelder i tillegg at dersom de blokkerer kongen fra å stå i sjakk, er det alikvell lovlig å flytte den ut av veien, og dermed sette seg selv i sjakk.

*For å summere*: Brikkene har problemer med å vite om de *står* i blokkering for sjakk, eller om det å bevege seg til en gitt posisjon ville blokkert sjakk.
