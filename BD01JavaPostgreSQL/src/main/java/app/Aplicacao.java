package app;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import model.X;
import dao.XDAO;

public class Aplicacao {
	static XDAO XDAO = new XDAO();
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * Insert: Funcao para inserir um X no banco de dados. Para isso pede a entrada de um id(tem que ser um valor distinto dos ja usados) e um boolean
	 */
	public static void insert() {
		System.out.println("\n\n==== Inserir X ==== ");
		System.out.println("Qual id desejado? ");
		int id = sc.nextInt();
		System.out.println("True or false? ");
		boolean b = sc.nextBoolean();
		X x = new X(id,b);
		if(XDAO.insert(x)) {
			System.out.println("Inserção com sucesso -> " + x.toString());
		}
	}
	
	/**
	 * Show: Mostra em ordem de criacao todos os X da tabela
	 */
	public static void show () {
		System.out.println("\n\n==== Mostrar X ==== ");
		List <X> xs = XDAO.getAllX();
		for (X u: xs) {
			System.out.println(u.toString());
		}
	}
	/**
	 * ShowOrdered: Mostra em ordem crescente do id os X da tabela
	 */
	public static void showOrdered() {
		System.out.println("\n\n==== Mostrar usuários ordenados por código ==== ");
		List <X> xs = XDAO.getOrderByID();
		for (X u: xs) {
			System.out.println(u.toString());
		}
	}
	
	/**
	 * Exclude: exclui um X da tabela, precisa do id para realizar a acao (Id nao valido nao da problema)
	 */
	public static void exclude() {
		System.out.println("Qual id desejado? ");
		int id = sc.nextInt();
		System.out.println("\n\n==== Excluir X (ID " + id + ") ==== ");
		XDAO.delete(id);
	}
	
	/**
	 * Att: funcao para atualizar os dados de um X ja existente
	 */
	public static void att() {
		System.out.println("Qual id desejado? ");
		int id = sc.nextInt();
		System.out.println("True or false? ");
		boolean b = sc.nextBoolean();
		
		X updatedX = new X(id,b);
		System.out.println("\n\n==== Atualizar X (ID " + id + ") ==== ");
		XDAO.updateX(updatedX);
	}
	
	public static void main(String[] args) throws Exception {
		int opcao = 0;
		do {
			System.out.println("===================\n Escolha uma opcao");
			System.out.println(" 0:Parar\n 1:Incluir\n 2:Listar\n 3:Listar Ordenado\n 4:Excluir\n 5:Atualizar");
			opcao = sc.nextInt();
			switch (opcao) {
			case 0:
				System.out.println(" Encerrando programa ...");
				break;
			case 1:
				insert();
				break;
			case 2:
				show();
				break;
			case 3:
				showOrdered();
				break;
			case 4:
				exclude();
				break;
			case 5:
				att();
				break;
			default:
				System.out.println(" Opcao Invalida");
			}
		}while(opcao != 0);
	}
}