public class ContaPoupanca extends ContaBancaria {
    private double limite;

    public ContaPoupanca(double limite) {
        super();
        this.limite = -limite;
    }


    @Override
    public boolean sacar(double valor) {
        if (this.getSaldo() - valor > this.limite) {
            setSaldo(this.getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public String mostrarDados() {
        return "Conta Poupança\n" + "limite - " + this.limite + "\n" +
                super.mostrarDados() + "\n\n";
    }

    @Override
    public void depositar(double valor) {
        this.setSaldo(this.getSaldo() + valor);
    }

}
