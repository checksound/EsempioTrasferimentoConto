# Esempio Trasferimento Fondi

Esempio sulle sequenze critiche. 
Vedi [Esempio trasferimento fondi](https://checksound.gitbook.io/corsojava/i-thread-e-concorrenza#esempio-trasferimento-fondi)


## Sequenza critica

[Banca](./src/Banca.java), 
[TrasferimentoFondi](./src/TrasferimentoFondi.java) e applicazione 
[GestoreConti](./src/GestoreConti.java)

con la sezione critica:

```java
// in TrasferimentoFondi.run()

int contoLocalA = banca.contoA;  // step 1
int contoLocalB = banca.contoB;  // step 2

contoLocalA += valueToTransfer;
contoLocalB -= valueToTransfer;

banca.contoA = contoLocalA;     // step 3
banca.contoB = contoLocalB;     // step 4

```

Output di esecuzioni successive:

```
START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 130 - CONTO B: 170, TOTALE: 300

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 110 - CONTO B: 180, TOTALE: 290

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 110 - CONTO B: 170, TOTALE: 280

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 120 - CONTO B: 170, TOTALE: 290

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 110 - CONTO B: 190, TOTALE: 300

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 120 - CONTO B: 180, TOTALE: 300

```

L'output corretto è solo quello con conto A = 130 e conto B = 170, gli altri non sono 
risultati corretti.

## Versione due

[Banca](./src/Banca.java),
[TrasferimentoFondi2](./src/TrasferimentoFondi2.java),
applicazione [GestoreConti2](./src/GestoreConti2.java)

```java
// in TrasferimentoFondi2.run()

banca.contoA = banca.contoA += valueToTransfer;     // step 1
banca.contoB = banca.contoB -= valueToTransfer;     // step 2
```

Output di esecuzioni successive come nel caso precedente:

```
START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 130 - CONTO B: 170, TOTALE: 300

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 110 - CONTO B: 170, TOTALE: 280

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 120 - CONTO B: 180, TOTALE: 300

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 120 - CONTO B: 170, TOTALE: 290
```
## Versione corretta

[Banca](./src/Banca.java),
[TrasferimentoFondiMutuaEsclusione](./src/TrasferimentoFondiMutuaEsclusione.java),
applicazione [GestoreContiSicuro](./src/GestoreContiSicuro.java)

```java
// in TrasferimentoFondiMutuaEsclusione.run()

synchronized (banca) {
    int contoLocalA = banca.contoA;
    int contoLocalB = banca.contoB;

    contoLocalA += valueToTransfer;
    contoLocalB -= valueToTransfer;

    banca.contoA = contoLocalA;
    banca.contoB = contoLocalB;
        }

```
L'output dell'esecuzione ora risulta sempre corretto:

```

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
TRASFERIAMO 10 B --> A
TRASFERIAMO 20 B --> A
CONTO A: 130 - CONTO B: 170, TOTALE: 300

```
