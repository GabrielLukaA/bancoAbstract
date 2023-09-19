public class ContaCorrente extends ContaBancaria {
    private double taxaDeOperacao;

    public ContaCorrente() {
        super();
        this.taxaDeOperacao = 1;
    }

    public double getTaxaDeOperacao() {
        return taxaDeOperacao;
    }

    public boolean sacar(double valor) {
        if (this.getSaldo()-(valor+taxaDeOperacao)>=0){
            setSaldo(this.getSaldo()-valor);
            return true;
        }

        return false;
    }

    @Override
    public void depositar(double valor) {
        this.setSaldo(this.getSaldo() + (valor-taxaDeOperacao));
    }

    @Override
    public String mostrarDados() {
        return "Conta Corrente \n"+"taxa de operação - "+this.taxaDeOperacao+"\n"+
        super.mostrarDados()+"\n\n";
    }
}
