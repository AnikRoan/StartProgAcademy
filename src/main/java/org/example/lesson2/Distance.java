package org.example.lesson2;



import java.io.IOException;
import java.util.Scanner;

public class Distance {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        while (true){

        System.out.println("how fast are you going? ");
        var speed = scanner.nextDouble();

        if(speed==0){
            break;
        }
        System.out.println(("how many hours do you drive? "));
        var hours = scanner.nextDouble();

        System.out.println("you will arrive "+(speed*hours));
        }
        System.out.println("you are not moving");

        scanner.close();


    }
}
