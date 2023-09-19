public abstract class ContaBancaria implements IImprimivel {
    private int numeroDaConta;
    private double saldo;

    private static int contador;


    public ContaBancaria() {
        contador++;
        this.numeroDaConta = contador;
        this.saldo = 0;
    }

    public abstract boolean sacar(double valor);


    public abstract void depositar(double v);

    @Override
    public String mostrarDados() {
        return "numero da conta - " + numeroDaConta +
                " saldo - " + saldo;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean transferir(double valor, ContaBancaria conta) {
        if (this.sacar(valor)) {
            conta.depositar(valor);
            return true;
        }
        return false;

    }
}
