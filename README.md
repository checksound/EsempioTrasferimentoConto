# Esempio Trasferimento Fondi

Esempio sulle sequenze critiche.

## Sequenza critica

[Banca](./src/Banca.java), 
[TrasferimentoFondi](./src/TrasferimentoFondi.java) e applicazione 
[TestTrasferimentoFondi](./src/TestTrasferimentoFondi.java)

con la sezione critica:

```java
int contoLocalA = banca.contoA;  // step 1
int contoLocalB = banca.contoB;  // step 2

contoLocalA += valueToTransfer;
contoLocalB -= valueToTransfer;

banca.contoA = contoLocalA;     // step 3
banca.contoB = contoLocalB;     // step 4

```

Output:

```
START CONTO A: 100 - CONTO B: 200, TOTALE: 300
CONTO A: 110 - CONTO B: 180, TOTALE: 290

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
CONTO A: 110 - CONTO B: 190, TOTALE: 300

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
CONTO A: 120 - CONTO B: 180, TOTALE: 300

START CONTO A: 100 - CONTO B: 200, TOTALE: 300
CONTO A: 130 - CONTO B: 170, TOTALE: 300

```

## Versione corretta

[Banca](./src/Banca.java),
[TrasferimentoFondiSicuro](./src/TrasferimentoFondiSicuro.java),
applicazione [TestTrasferimentoFondiSicuro](./src/TestTrasferimentoFondiSicuro.java)

```java
synchronized (banca) {
    int contoLocalA = banca.contoA;
    int contoLocalB = banca.contoB;

    contoLocalA += valueToTransfer;
    contoLocalB -= valueToTransfer;

    banca.contoA = contoLocalA;
    banca.contoB = contoLocalB;
        }

```
