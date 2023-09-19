public class Main {
    private static ContaPoupanca contaPoupanca = new ContaPoupanca(250);
    private static ContaCorrente contaCorrente = new ContaCorrente();
    private static Relatorio relatorio = new Relatorio();

    public static void main(String[] args) {
        contaPoupanca.sacar(2);
        contaCorrente.sacar(222);
        relatorio.gerarRelatorio(contaCorrente);
        relatorio.gerarRelatorio(contaPoupanca);
    }

}
