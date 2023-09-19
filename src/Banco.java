import java.util.ArrayList;

public class Banco implements IImprimivel {
    private static ArrayList<ContaBancaria> contas = new ArrayList<>();

    @Override
    public String mostrarDados() {
        String dadosContas = "";
        for (ContaBancaria conta : contas) {
            dadosContas+="\n"+conta.mostrarDados();
        }
        return dadosContas;
    }

    public void inserir(ContaBancaria conta) {
        contas.add(conta);

    }

    public void remover(ContaBancaria conta) {
contas.remove(conta);
    }

    public ContaBancaria procurarConta(int numeroDaConta) {
        for (ContaBancaria conta : contas) {
            if (numeroDaConta == conta.getNumeroDaConta()) {
                return conta;
            }
        }
        return null;
    }

    public static ArrayList<ContaBancaria> getContas() {
        return contas;
    }
}
