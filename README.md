# Værklar

En Androidapp som gir deg klesanbefalinger basert på været

<img src="app/src/main/ic_launcher-playstore.png" width=256>


## Hva er dette prosjektet?

Denne appen er laget i sammenheng med emnet [IN2000 – Software Engineering med prosjektarbeid](https://www.uio.no/studier/emner/matnat/ifi/IN2000/) ved Universitetet i Oslo. Prosjektarbeidet gikk ut på å lage en Androidapp med bruk av vær-API levert av metrologisk institutt. Vi har valgt å bruke disse værdataene til å lage en app som viser klesanbefalinger basert på dette.

Dette repoet er resultatet av gruppe 14 sitt arbeid i emnet våren 2022.


## Kom i gang

### Valgmulighet 1 - Last ned APK-fil

Vi har valgt å kompilere koden til en APK-fil som kan lastes ned [her](https://github.com/IN2000-v22-gruppe14/vaerklar/releases) (ikke tilgjengelig ennå). For å kunne kjøre denne appen må du ha en smarttelefon som kjører Android 8 eller nyere.


### Valgmulighet 2 - Emulér appen med Android Studio

Om du velger å bruke en virtuell enhet må du sørge for at du bruker API-nivå 26 eller høyere. Utifra vår testing fungerer Pixel 5 bra som enhet

Sørg også for at du har satt en lokasjon for enhetens emulatorinstillinger i Android Studio.


## Kjente problemer - og hvordan man evt. løser de.

### Appen er ekstremt treg

Dette er tildels fordi vi har skrevet appen dårlig, men også på grunn av at emulatorer er trege. I følge vår testing fungerer det ganske fint på fysiske enheter. Her er det ikke noe særlig man kan gjøre for å bedre som bruker.

### Værdata for uken og dagen oppdateres ikke ved endring av lokasjon ved for eksempel søk

Dette er en feil vi ikke har klart å løse og er dessverre noe av det som ødelegger brukeropplevelsen av appen mest

### Blank skjerm etter søk

Dette er noe som tilsynelatende bare skjer når man kjører på fysiske enheter. Vi aner ikke hvorfor dette skjer.

### Mangel på lokasjon

* Sørg for at lokasjon er satt i enhetens emulatorinstillinger i Android Studio
* Åpne google maps i bakgrunnen

### Appen min går ikke videre fra splash screen. Den viser bare logoen

* Sørg for at du har gitt appen lokasjonstillatelse


## Bilder

kommer snart™️
