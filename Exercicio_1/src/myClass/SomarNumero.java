package myClass;

import java.util.*;

class SomarNumero {
	public static Scanner sc = new Scanner(System.in);
	public static void main (String args[]) {
		// Variaveis
		int num1,num2,soma;
		
		// Leituras
		System.out.print("Digite um numero: ");
		num1 = sc.nextInt();
		System.out.print("Digite mais um numero: ");
		num2 = sc.nextInt();
		
		// Soma
		soma = num1 + num2;
		
		// Saida
		System.out.println("A soma de "+ num1 +" + "+ num2 +" = "+ soma);
	}
}
