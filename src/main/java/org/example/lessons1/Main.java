package org.example.lessons1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("How are you?");

        Scanner scanner =new Scanner(System.in);
        String answer = scanner.nextLine();

        System.out.println("User answered: "+answer);
    }
}
