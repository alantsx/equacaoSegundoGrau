package com.main;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraEquacao {
    public static void main(String[] args) {
        double a, b, c;
        System.out.print("CALCULADORA EQUACAO SEGUNDO GRAU  \n");

        do {
            try {
                a = coletaDeValores("A");
                b = coletaDeValores("B");
                c = coletaDeValores("C");
                System.out.println(calculaSegundoGrau(a, b, c));
                break;
            } catch(EquacaoException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("O valor inserido não é um número válido");
            }
        } while(true);
    }

    private static double coletaDeValores(String coeficiente) throws EquacaoException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInsira o valor para " + coeficiente);
        double valorCoeficiente = scanner.nextDouble();
        if (coeficiente.equalsIgnoreCase("A") && valorCoeficiente == 0) {
            throw new EquacaoException("O coeficiente A precisa ser diferente de zero");
        }
        return valorCoeficiente;
    }

    private static String calculaSegundoGrau(double a, double b, double c) throws EquacaoException {
        DecimalFormat df = new DecimalFormat("0.00");
        double delta = calculaDelta(a, b, c);
        double x1 = (-b + Math.sqrt(delta))/(2*a);
        double x2 = (-b - Math.sqrt(delta))/(2*a);

        if (delta == 0) {
            return "Delta calculado é igual a zero, raízes são iguais\n" +
                    "O valor da raíz é " + df.format(x1);
        }

        if (delta > 0) {
            return "Delta calculado é maior que zero, raízes são diferentes\n" +
                    "O valor da primeira raíz é " + df.format(x1) + "\n" +
                    "O valor da segunda raíz é " + df.format(x2);
        }

        throw new EquacaoException("Valores dos coeficientes inválidos, tente novamente");
    }

    private static double calculaDelta(double a, double b, double c) throws EquacaoException {
        double delta;
        delta = (b*b) - 4*(a*c);
        if (delta < 0) {
            throw new EquacaoException("A equação não admite solução real, já que o delta é negativo");
        }
        return delta;
    }
}
