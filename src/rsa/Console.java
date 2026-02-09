package rsa;

import java.util.Scanner;

public class Console {

    // string to char array
    // str.toCharArray();

    // char array to string
    // String str = new String(charArray);

    private boolean primeInputValidation(int p1, int p2){
        /**
         * This method returns true if p1 and p2 are two distinct prime numbers greater than 5.
         */
        if(p1 != p2 && p1 > 5 && p2 > 5){
            //check if both are prime.
        }
        return false;
    }

    // Console option methods



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to this RSA encrypted messaging console!");
        System.out.println("This Console allows you to encrypt/decrypt one word messages.");
        System.out.print("Press enter to start!");
        String enter = sc.nextLine();

        while(true){
            // console options
            System.out.println("***************************************");
            System.out.println("* CONSOLE OPTIONS:                    *");
            System.out.println("* 1. Generate public and private keys *");
            System.out.println("* 2. Encrypt a message with your keys *");
            System.out.println("* 3. Decrypt a message with your keys *");
            System.out.println("* 4. Exit                             *");
            System.out.println("***************************************");
            System.out.print("What shall we do? (type a number and press enter) ");
            int choice = sc.nextInt();

            if(choice == 1){
                System.out.println("PUBLIC/PRIVATE KEY GENERATION");
                System.out.println("Instructions:");
                System.out.println("Please enter two distinct prime numbers over 5.");
                System.out.println("First prime: ");
                int prime1 = sc.nextInt();
                System.out.println("Second prime: ");
                int prime2 = sc.nextInt();

            }
            else if(choice == 2){
                System.out.println("ENCRYPTION");
            }
            else if(choice == 3){
                System.out.println("DECRYPTION");
            }
            else if(choice == 4){
                System.out.println("Thank you for using this console!");
                System.out.print("See you soon! :)");
                break;
            }

        }

        sc.close();
    }
}
