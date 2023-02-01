class TrasferimentoFondi extends Thread {
    private int valueToTransfer;
    private Banca banca;
    public TrasferimentoFondi(String name, Banca banca, int valueToTransfer) {
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

        int contoLocalA = banca.contoA;  // step 1
        int contoLocalB = banca.contoB;  // step 2

        contoLocalA += valueToTransfer;
        contoLocalB -= valueToTransfer;

        banca.contoA = contoLocalA;     // step 3
        banca.contoB = contoLocalB;     // step 4

    }
}
