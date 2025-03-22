package SistemaCadastro.service;

import java.util.Scanner;

public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);
    CadastroPet cadastrarPet = new CadastroPet();
    public void menu() {
        int resposta;
        try {
            System.out.println("1 - Cadastrar um novo pet\n" +
                    "2 - Alterar os dados do pet cadastrado\n" +
                    "3 - Deletar um pet cadastrado\n" +
                    "4 - Listar todos os pets cadastrados\n" +
                    "5 -  Listar pets por algum critério(idade, nome, raça)\n" +
                    "6 -   Sair" +
                    "Escolha um número referente a opção:");
            resposta = scanner.nextInt();
            scanner.nextLine();

        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite um número.");
            menu();
            return;
        }
        switch (resposta) {
            case 1:

                menu();
                break;
            case 2:
                listaPets();
                menu();
                break;
            case 3:
                adiconarPergunta();

                break;
            case 4:
                listaPets();
                menu();
                break;
            case 5:
                pesquisarNomePet();
                menu();
                break;
            case 6:
                System.out.println("Saindo");
                break;
            default:
                System.out.println("Opção inválida");
                menu();
        }
    }

}
