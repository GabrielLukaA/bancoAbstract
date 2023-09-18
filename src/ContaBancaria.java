public abstract class ContaBancaria implements IImprimivel {
    private int numeroDaConta;
    private double saldo;

    public abstract void sacar();


    public abstract void depositar();

    @Override
    public void mostrarDados() {

    }



    public void transferir(double valor, ContaBancaria conta) {
        sacar();
        depositar();
    }
}
