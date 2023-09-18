public class Main {
    private static ContaPoupanca contaPoupanca = new ContaPoupanca();
    private static ContaCorrente contaCorrente = new ContaCorrente();
    private static Relatorio relatorio = new Relatorio();

    public static void main(String[] args) {
        contaPoupanca.sacar();
        contaCorrente.sacar();
        relatorio.gerarRelatorio(contaCorrente);
        relatorio.gerarRelatorio(contaPoupanca);
    }

}
