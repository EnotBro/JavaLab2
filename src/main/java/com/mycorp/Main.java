package com.mycorp;

import com.mycorp.exceptions.ExpressionException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isAllOk=false;
        Scanner input = new Scanner(System.in);
        System.out.println("Введите выражение");
        while(!isAllOk)
        {
            String expression = input.nextLine();
            try
            {
                double result = Solver.getSolution(expression);
                System.out.println("Результат выражения " + result);
                isAllOk = true;
            }
            catch (ExpressionException e)
            {
                System.out.println("В выражении есть ошибки: " + e.getMessage() + "\nВведите выражение заново");
            }
        }

    }
}
