class TrasferimentoFondiMutuaEsclusione extends Thread {
    private int valueToTransfer;
    private Banca banca;
    public TrasferimentoFondiMutuaEsclusione(String name, Banca banca, int valueToTransfer) {
        super(name);
        this.banca = banca;
        this.valueToTransfer = valueToTransfer;
    }

    public void run() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (banca) {
            int contoLocalA = banca.contoA;
            int contoLocalB = banca.contoB;

            contoLocalA += valueToTransfer;
            contoLocalB -= valueToTransfer;

            banca.contoA = contoLocalA;
            banca.contoB = contoLocalB;
        }
    }
}
