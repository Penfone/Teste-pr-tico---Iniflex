import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.math.RoundingMode;


// Classe principal
public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dataF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            // Adicionando funcionarios
            LocalDate dataMaria = LocalDate.parse("18/10/2000", dataF);
            Funcionario funcionario1 = new Funcionario("Maria", dataMaria, BigDecimal.valueOf(2009.44), "Operador");
            funcionarios.add(funcionario1);

            LocalDate dataJoao = LocalDate.parse("12/05/1990", dataF);
            Funcionario funcionario2 = new Funcionario("João", dataJoao, BigDecimal.valueOf(2284.38), "Operador");
            funcionarios.add(funcionario2);

            LocalDate dataCaio = LocalDate.parse("02/05/1961", dataF);
            Funcionario funcionario3 = new Funcionario("Caio", dataCaio, BigDecimal.valueOf(9836.14), "Coordenador");
            funcionarios.add(funcionario3);

            LocalDate dataMiguel = LocalDate.parse("10/10/1988", dataF);
            Funcionario funcionario4 = new Funcionario("Miguel", dataMiguel, BigDecimal.valueOf(19119.14), "Diretor");
            funcionarios.add(funcionario4);

            LocalDate dataAlice = LocalDate.parse("05/01/1995", dataF);
            Funcionario funcionario5 = new Funcionario("Alice", dataAlice, BigDecimal.valueOf(2234.68), "Recepcionista");
            funcionarios.add(funcionario5);

            LocalDate dataHeitor = LocalDate.parse("19/11/1999", dataF);
            Funcionario funcionario6 = new Funcionario("Heitor", dataHeitor, BigDecimal.valueOf(1582.72), "Operador");
            funcionarios.add(funcionario6);

            LocalDate dataArthur = LocalDate.parse("31/03/1993", dataF);
            Funcionario funcionario7 = new Funcionario("Arthur", dataArthur, BigDecimal.valueOf(4071.84), "Contador");
            funcionarios.add(funcionario7);

            LocalDate dataLaura = LocalDate.parse("08/07/1994", dataF);
            Funcionario funcionario8 = new Funcionario("Laura", dataLaura, BigDecimal.valueOf(3017.45), "Gerente");
            funcionarios.add(funcionario8);

            LocalDate dataHeloisa = LocalDate.parse("24/05/2003", dataF);
            Funcionario funcionario9 = new Funcionario("Heloísa", dataHeloisa, BigDecimal.valueOf(1606.85), "Eletricista");
            funcionarios.add(funcionario9);

            LocalDate dataHelena = LocalDate.parse("02/09/1996", dataF);
            Funcionario funcionario10 = new Funcionario("Helena", dataHelena, BigDecimal.valueOf(2799.93), "Gerente");
            funcionarios.add(funcionario10);


        } catch (DateTimeParseException e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
        }

        funcionarios.remove(1);//removendo João

        //fazendo a troca de . para ,
        DecimalFormatSymbols troca = new DecimalFormatSymbols(Locale.getDefault());
        troca.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", troca);

        //imprimindo funcionarios geral
        System.out.println("\n Imprimindo funcionários Geral:");
        for (Funcionario f : funcionarios) {
            System.out.println("-----------------------------------------");
            System.out.println("Nome: " + f.nome);
            System.out.println("Salário: " + df.format(f.salario));
            System.out.println("Data de nascimento: " + f.dataNascimento.format(dataF));
            System.out.println("Função: " + f.funcao + "\n");
        }

        //Cauculando aumento de salário
        BigDecimal aumento = new BigDecimal("0.10");

        for (Funcionario f : funcionarios) {
            BigDecimal aumentoValor = f.getSalario().multiply(aumento);
            f.setSalario(f.getSalario().add(aumentoValor));
        }

        //imprimindo funcionarios com aumento
        System.out.println("\n Imprimindo funcionários com aumento de salário em 10%:");
        for (Funcionario f : funcionarios) {
            System.out.println("-----------------------------------------");
            System.out.println("Nome: " + f.nome);
            System.out.println("Salário: " + df.format(f.salario));
            System.out.println("Data de nascimento: " + f.dataNascimento.format(dataF));
            System.out.println("Função: " + f.funcao + "\n");
        }

        //MAP
        Map<String, List> mapafuncoes = new HashMap<>();
        mapafuncoes.put("Operador", funcionarios);
        mapafuncoes.put("Coordenador", funcionarios);
        mapafuncoes.put("Diretor", funcionarios);
        mapafuncoes.put("Recepcionista", funcionarios);
        mapafuncoes.put("Contador", funcionarios);
        mapafuncoes.put("Gerente", funcionarios);
        mapafuncoes.put("Eletricista", funcionarios);

        //não consegui realizar o 3.6

        //Imprimindo funcionários que fazem aniversário nos messes 10 E 12
        System.out.println("\n Imprimindo funcionários que fazem aniversário nos messes 10 E 12");
        for (Funcionario f : funcionarios) {
            LocalDate dataNascimento = f.getDataNascimento();
            int mes = dataNascimento.getMonthValue();
            if(mes == 10 || mes == 12) {
                System.out.println("-----------------------------------------");
                System.out.println("Nome: " + f.nome);
                System.out.println("Salário: " + df.format(f.salario));
                System.out.println("Data de nascimento: " + f.dataNascimento.format(dataF));
                System.out.println("Função: " + f.funcao + "\n");
            }
        }

        //Imprimindo funcionário com maior idade
        Funcionario maisVelho = funcionarios.get(0);

        System.out.println("\n Imprimindo funcionário com a maior idade");
        for (Funcionario f : funcionarios) {
            if(f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = f;
            }
        }

        LocalDate hoje = LocalDate.now();
        hoje.format(dataF);
        int idade = Period.between(maisVelho.getDataNascimento(), hoje).getYears();
        System.out.println("-----------------------------------------");
        System.out.println("Nome: " + maisVelho.nome);
        System.out.println("Idade: " + idade + "\n");


        //imprimindo funcionarios em ordem alfabética
        Collections.sort(funcionarios, Comparator.comparing(f -> f.nome));
        System.out.println("\n Imprimindo funcionários em ordem alfabética:");
        for (Funcionario f : funcionarios) {
            System.out.println("-----------------------------------------");
            System.out.println("Nome: " + f.nome);
            System.out.println("Salário: " + df.format(f.salario));
            System.out.println("Data de nascimento: " + f.dataNascimento.format(dataF));
            System.out.println("Função: " + f.funcao + "\n");
        }

        //imprimindo o salário total dos funcionários
        System.out.println("\n Imprimindo o salário total dos funcionários:");
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : funcionarios){
            total = total.add(f.salario);
        }
        System.out.println("Salário total dos funcionários: " + df.format(total));

        //Imprimindo quantos salários minimos cada funcionário recebe
        BigDecimal minimo = BigDecimal.valueOf(1212.00);
        for (Funcionario f : funcionarios) {
            System.out.println("-----------------------------------------");
            System.out.println("Nome: " + f.nome);
            System.out.println("Salários minimos: " + f.salario.divide(minimo, 2, RoundingMode.HALF_UP));
        }

    }
}
