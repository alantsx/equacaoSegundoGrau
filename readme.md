# Quadratic Equation Calculator

Quadratic equation is an essential subject in Math. The objective of this simple project is to receive three different inputs from console (for a, b and c values used in Quadratic formula) and returns the result or exception if that's the case.

But most importantly, this project was used to practice Exceptions events in Java. So I used Try/Catch blocks to capture custom created Exceptions in order to properly treat errors.

So with no further ado, let's get to the explanation!

--------

## 1. Methods Features 🎁

### 1.1 Setting coefficient values through console input
Coefficients _a, b, c_ are declared as double and receives value based on the following method:

```Java
a = coletaDeValores("A");
b = coletaDeValores("B");
c = coletaDeValores("C");
```

The _coletaDeValores_ method is explained below:

```Java
private static int coletaDeValores(String coeficiente) throws EquacaoException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInsira o valor para " + coeficiente);
        int valorCoeficiente = scanner.nextDouble();
        if (coeficiente.equalsIgnoreCase("A") && valorCoeficiente == 0) {
            throw new EquacaoException("O coeficiente A precisa ser diferente de zero");
        }
        return valorCoeficiente;
    }
```

On this case, I created a double variable to storage the next input from console, so this value can go through some validations (like coefficient A must be different from zero in order to be a Quadratic Equation).

Invalid inputs are thrown as an EquacaoException and the program is restarted. After validations succeeded, the method returns input value.

### 1.2 Calulating and validating the Quadratic Equation
The next step is to in fact calculate the Quadratic Equation based on the three coefficient variables. After collecting these inputs, I call a method inside the Final Output:

```Java
a = coletaDeValores("A");
b = coletaDeValores("B");
c = coletaDeValores("C");
System.out.println(calculaSegundoGrau(a, b, c));
```

The method _calculaSegundoGrau(a, b, c)_ is responsible to give me the answer based on Quadratic Equation formula:

```Java
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
```

On the first line, I created a _DecimalFormat_ so I could return the number formatted as my desired pattern.

Note that one of the first steps from this method is to call another method named _calculaDelta_. calculaDelta is responsible to check if __Δ__ value _(b² -4ac)_ is lower than zero. If so, the equation doesn't admit real solution and throws another EquacaoException:

```Java
private static double calculaDelta(double a, double b, double c) throws EquacaoException {
    double delta;
    delta = (b*b) - 4*(a*c);
    if (delta < 0) {
        throw new EquacaoException("A equação não admite solução real, já que o delta é negativo");
    }
    return delta;
}
```

## 2. Other important structures 🧩

### 2.1 Loops
In order to assure that the user is able to correct their inputs, I wraped the whole Try/Catch in a Do/While infinite loop. If the loop is successful, it will break it and finish the program.

```Java
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
```

### 2.2 Customized Exception
At last, the EquacaoException is customized extending Java Exception class. It receives a String Param so any Exception Thrown can receive a specific error message:

```Java
package com.main;

public class EquacaoException extends Exception {
    public EquacaoException(String mensagem) {
        super(mensagem);
    }
}
```

## 3. That's it!
Although the project is quite simple, it uses important concepts from Java and also for Programming in a whole. Be able to treat errors through exceptions is an essential part of good software engineering.

I hope this ReadMe find use for you in some way and I appreciate if you read this far!

Also, if you want to try the program for yourself, just clone the repository and run main class in console,
