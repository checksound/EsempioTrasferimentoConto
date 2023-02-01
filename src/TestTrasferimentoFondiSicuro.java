public class TestTrasferimentoFondiSicuro {
    public static void main(String[] args) throws InterruptedException {

        // trasferire da contoB --> contoA;

        int transfer1 = 10;
        int transfer2 = 20;

        Banca banca = new Banca(100, 200);

        TrasferimentoFondiSicuro threadA = new TrasferimentoFondiSicuro("threadA", banca, transfer1);
        TrasferimentoFondiSicuro threadB = new TrasferimentoFondiSicuro("threadB", banca, transfer2);

        System.out.println("START CONTO A: " + banca.contoA + " - CONTO B: " + banca.contoB +
                ", TOTALE: " + (banca.contoA + banca.contoB));

        System.out.println("TRASFERIAMO " + transfer1 + " B --> A");
        System.out.println("TRASFERIAMO " + transfer2 + " B --> A");
        
        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("CONTO A: " + banca.contoA + " - CONTO B: " + banca.contoB +
                ", TOTALE: " + (banca.contoA + banca.contoB));
    }
}