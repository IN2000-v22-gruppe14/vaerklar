# Værklar

En Androidapp som gir deg klesanbefalinger basert på været

<img src="app/src/main/ic_launcher-playstore.png" width=256>

## Hva er dette prosjektet?

Denne appen er laget i sammenheng med emnet [IN2000 – Software Engineering med prosjektarbeid](https://www.uio.no/studier/emner/matnat/ifi/IN2000/) ved Universitetet i Oslo. Prosjektarbeidet gikk ut på å lage en Androidapp med bruk av vær-API levert av meteorologisk institutt. Vi har valgt å bruke disse værdataene til å lage en app som viser klesanbefalinger basert på dette.

Dette repoet er resultatet av gruppe 14 sitt arbeid i emnet våren 2022.

## Kom i gang

### Valgmulighet 1 - Last ned APK-fil

Vi har valgt å kompilere koden til en APK-fil som kan lastes ned [her](https://github.com/IN2000-v22-gruppe14/vaerklar/releases) (ikke tilgjengelig før karakter er satt). For å kunne kjøre denne appen må du ha en smarttelefon som kjører Android 8 eller nyere.

### Valgmulighet 2 - Emulér appen med Android Studio

Om du velger å bruke en virtuell enhet må du sørge for at du bruker API-nivå 26 eller høyere. Utifra vår testing fungerer Pixel 5 bra som enhet

Sørg også for at du har satt en lokasjon for enhetens emulatorinstillinger i Android Studio.

## Kjente problemer - og hvordan man evt. løser de.

### Appen er ekstremt treg

Dette er tildels fordi vi har skrevet appen dårlig, men også på grunn av at emulatorer er trege. I følge vår testing fungerer det ganske fint på fysiske enheter. Her er det ikke noe særlig man kan gjøre for å bedre som bruker.

### Værdata for uken og dagen oppdateres ikke ved endring av lokasjon ved for eksempel søk

Dette er en feil vi ikke har klart å løse og er dessverre noe av det som ødelegger brukeropplevelsen av appen mest

### Trykk på today/weektiles mer enn én gang etter søk kræsjer appen

Dette er også et problem vi ikke har klart å løse og har heller ikke funnet årsaken til. Vår hypotese er at det er relatert til problemet ovenfor.

### Blank skjerm etter søk

Dette er noe som tilsynelatende bare skjer når man kjører på _noen_ fysiske enheter. Om det oppstår problemer anbefaler vi å prøve emulator eller omvendt om man allerede bruker det fra før.

### Mangel på lokasjon

Dette skjer trolig fordi splash screen venter bare på respons fra APIet som gir oss værdata. Siden lokasjonsnavnet hentes separat hender det at det ikke blir hentet i tide. Rerendering skjer ikke ved henting av data og brukeren vil dermed ikke se noe når/hvis APIet svarer.

Det finnes dog noen løsninger man kan prøve seg på:

- Sørg for at lokasjon er satt i enhetens emulatorinstillinger i Android Studio
- Åpne google maps i bakgrunnen før åpning av appen

### Appen min går ikke videre fra splash screen. Den viser bare logoen

Sørg for at du har gitt appen lokasjonstillatelse

### "Hjem"-knappen i sidemenyen gjør ikke noe

Nei, det gjør den ikke :)

Den har ingen hensikt egentlig.

### Instillngs/søkesiden har ingen tilbakeknapp! Hva gjør jeg?

Det er sant, og er dårlig av oss å ikke implementere med tanke på UX. Redningen din er derimot Android sin innebygde tilbakeknapp!

### Annet

Som alle med mer enn ett minutt med erfaring som tech vet så fungerer prinsippet om å skru av og på ting godt for å løse problemer. Vi vil strekke dette prinsippet til følgende:

- Invalidate cache i android studio
- Lag en ny emulatorenhet

## Bilder

Man kan også se en videodemo av appen [her](https://www.youtube.com/watch?v=OVF0suAyR18)

<img src="https://user-images.githubusercontent.com/24893890/169403188-58673c3b-23b8-4c63-b227-023c940191ee.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403415-496d5ce8-406c-453c-8e50-3135fe7f2292.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403499-8025402c-2179-44b3-b98e-c6a9dd8c1193.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403673-9b3a61d1-597d-4e21-a2ea-ffed3155e490.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403717-01cda031-9bca-4578-a027-2990fd9dffc5.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403762-8d6a2384-b46a-4b0f-8524-d3aed151a8f0.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403822-8919967e-11ab-4012-ab9f-98256f605a89.png" width=256>

<img src="https://user-images.githubusercontent.com/24893890/169403888-9b6e0e2e-db3c-480d-93b2-0ec210eb80bc.png" width=256>
