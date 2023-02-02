class TrasferimentoFondi2 extends Thread {
    private int valueToTransfer;
    private Banca banca;
    public TrasferimentoFondi2(String name, Banca banca, int valueToTransfer) {
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

        banca.contoA = banca.contoA += valueToTransfer;     // step 1
        banca.contoB = banca.contoB -= valueToTransfer;     // step 2

    }
}
