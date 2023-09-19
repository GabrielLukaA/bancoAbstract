import java.util.Scanner;

public class Executavel {
    private static Banco banco = new Banco();
    private static Relatorio relatorio = new Relatorio();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            telaInicial();
        } while (true);

    }

    private static void telaInicial() {
        System.out.println("""
                1 - Criar conta
                2 - Selecionar conta
                3 - Remover conta
                4 - Gerar relatório
                0 - Finalizar
                """);
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1 -> criarConta();
            case 2 -> selecionarConta();
            case 3 -> removerConta();
            case 4 -> System.out.println("Relatório Geral \n\n" + relatorio.gerarRelatorio(banco));


            case 0 -> System.exit(0);
            default -> System.out.println("Insira um número válido.");
        }
    }


    private static void criarConta() {
        int opcao = -1;
        do {
            System.out.println("""
                    Informe o tipo da sua conta.
                    1 - Poupança
                    2 - Corrente
                    0 - Voltar
                    """);
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("Informe o limite desse usuário:");
                    double limite = sc.nextDouble();
                    if (limite >= 0) {
                        ContaPoupanca conta = new ContaPoupanca(limite);
                        banco.inserir(conta);
                        System.out.println("Conta poupança criada com sucesso.");
                    } else {
                        System.out.println("Não faz sentido dar limite negativo ao usuário.");
                    }
                }
                case 2 -> {
                    ContaCorrente conta = new ContaCorrente();
                    banco.inserir(conta);
                    System.out.println("O custo por transação de sua conta é " + conta.getTaxaDeOperacao());
                    System.out.println("Conta corrente criada com sucesso.");
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Insira um número válido.");
            }
        } while (opcao > 2 || opcao < 0);


    }

    private static void selecionarConta() {
        System.out.println("Informe o número da sua conta:");
        int numeroDaConta = sc.nextInt();
        ContaBancaria conta = banco.procurarConta(numeroDaConta);
        if (conta != null) {
            menu(conta);
        } else {
            System.out.println("Número de conta inserido não existe.");
        }

    }

    private static void removerConta() {
        System.out.println("Informe o número da conta que deseja excluir:");
        int numeroDaConta = sc.nextInt();
        ContaBancaria conta = banco.procurarConta(numeroDaConta);
        if (conta != null) {
            System.out.println("Tem certeza disso? 1 para sim, qualquer outro número para não");
            if (sc.nextInt() == 1) {
                banco.remover(conta);
            } else {
                System.out.println("Obrigado por manter a conta, retornando.");
            }
        } else {
            System.out.println("Número de conta inserido não existe.");
        }
    }


    private static void menu(ContaBancaria conta) {

        int opcao = -2;

        do {
            System.out.println("""
                    Informe o que deseja fazer com essa conta
                    1 - Depositar
                    2 - Sacar
                    3 - Transferir
                    4 - Gerar Relatório
                    5 - Voltar
                                        """);
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> depositar(conta);
                case 2 -> sacar(conta);
                case 3 -> transferir(conta);
                case 4 -> System.out.println(relatorio.gerarRelatorio(conta));
                case 5 -> System.out.println("Voltando");
                default -> System.out.println("Insira um número válido.");
            }
        } while (opcao != 5);
    }

    private static void transferir(ContaBancaria conta) {
        System.out.println("Informe o número da conta que deseja transferir:");
        int numeroDaConta = sc.nextInt();
        ContaBancaria contaAReceber = banco.procurarConta(numeroDaConta);
        if (contaAReceber != null) {
            System.out.println("Informe o quanto deseja transferir");
            double valor = sc.nextDouble();
            if (valor > 0) {
                if (conta instanceof ContaCorrente) {
                    if (valor > ((ContaCorrente) conta).getTaxaDeOperacao()) {
                        if (conta.transferir(valor, contaAReceber)) {
                            System.out.println("Transação realizada com sucesso.");
                        } else {
                            System.out.println("Saldo insuficiente para realizar essa transação.");
                        }

                    } else {
                        System.out.println("O valor inserido é inferior a nossa taxa de operação.");
                    }
                } else {

                    if (conta.transferir(valor, contaAReceber)) {
                        System.out.println("Transação realizada com sucesso.");
                    } else {
                        System.out.println("Saldo insuficiente para realizar essa transação.");
                    }
                }
            }
        } else {
            System.out.println("Esse número não confere com nenhuma conta existente.");
        }

    }

    private static void sacar(ContaBancaria conta) {
        System.out.println("Informe a quantia a sacar:");
        double valor = sc.nextDouble();
        if (valor > 0) {
            if (conta.sacar(valor)) {
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para realizar o saque.");
            }
        } else {
            System.out.println("Saque um valor superior a 0.");
        }

    }

    private static void depositar(ContaBancaria conta) {
        System.out.println("Informe o quanto deseja depositar: ");
        double valor = sc.nextDouble();
        if (valor > 0) {
            if (conta instanceof ContaCorrente) {
                if (valor > ((ContaCorrente) conta).getTaxaDeOperacao()) {
                    conta.depositar(valor);
                    System.out.println("Depósito realizado na conta corrente com sucesso.");
                } else {
                    System.out.println("Você está depositando um valor menor que nossa taxa de operação, operação inválida.");
                }
            } else {
                conta.depositar(valor);
                System.out.println("Depósito realizado na conta poupança com sucesso.");
            }
        } else {
            System.out.println("Deposite um valor superior a 0.");
        }

    }

}
