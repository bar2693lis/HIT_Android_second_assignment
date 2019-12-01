package BarLis;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int answer = 1;

        while(answer == 1) {

            System.out.println("\nHow many threads do you want? Choose from 2 to 20!");
            int threadsNumber = scan.nextInt();

            while(threadsNumber < 2 || threadsNumber > 20) {
                System.out.println("The number you entered does not match! Please choose between 2 and 20\nTry Again");
                threadsNumber = scan.nextInt();
            }

            ExecutorService exService = Executors.newFixedThreadPool(threadsNumber);

            System.out.println("How many square matrices would you like? Choose at least number 2!");
            int matricesNumber = scan.nextInt();

            while(matricesNumber < 2) {
                System.out.println("The number you entered does not match! Please choose at least number 2!\nTry Again");
                matricesNumber = scan.nextInt();
            }

            System.out.println("Select the dimension of the square matrices.");
            int matricesDimension = scan.nextInt();

            for(int i = 0 ; i < matricesNumber ; i++) {
                exService.execute(new ThreadPoolClass(matricesDimension));
            }

            exService.shutdown();
            while(!exService.isTerminated());

            ThreadPoolClass.print();

            System.out.println("If you want to play again, press 1 for YES or 0 for NO");
            answer = scan.nextInt();
        }
    }
}
