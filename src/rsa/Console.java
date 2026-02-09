package rsa;

import java.util.Scanner;

public class Console {
    private int publicKey;
    private int privateKey;
    private int modulus;

    // string to char array
    // str.toCharArray();

    // char array to string
    // String str = new String(charArray);

    // helper methods
    private boolean primeInputValidation(int p1, int p2){
        /**
         * This method returns true if p1 and p2 are two distinct prime numbers greater than 5.
         */
        if(p1 != p2 && p1 > 5 && p2 > 5){
            //check if both are prime
            return true;
        }
        return false;
    }

    // Console option methods

    private void welcomeMsg(Scanner sc){
        System.out.println("Welcome to this RSA encrypted messaging console!");
        System.out.println("This Console allows you to encrypt/decrypt one word messages.");
        System.out.print("Press enter to start!");
        sc.nextLine();
    }

    private void consoleMenu(){
        System.out.println("***************************************");
        System.out.println("* CONSOLE OPTIONS:                    *");
        System.out.println("* 1. Generate public and private keys *");
        System.out.println("* 2. Encrypt a message with your keys *");
        System.out.println("* 3. Decrypt a message with your keys *");
        System.out.println("* 4. Exit                             *");
        System.out.println("***************************************");
        System.out.print("What shall we do? (type a number and press enter) ");
    }

    private void generateKeys(Scanner sc){
        System.out.println("PUBLIC/PRIVATE KEY GENERATION");
        System.out.println("Instructions:");
        System.out.println("Please enter two distinct prime numbers over 5.");
        System.out.print("First prime: ");
        int p1 = sc.nextInt();
        System.out.print("Second prime: ");
        int p2 = sc.nextInt();

        // asks for user input until primes are valid
        while( ! primeInputValidation(p1, p2)){
            System.out.println("Invalid primes, try again!");
            System.out.print("First prime: ");
            p1 = sc.nextInt();
            System.out.print("Second prime: ");
            p2 = sc.nextInt();
        }

        // generate keys and initializing fields
        KeyGen newKeys = new KeyGen(p1, p2);
        this.publicKey = newKeys.getPublicKey();
        this.privateKey = newKeys.getPrivateKey();
        this.modulus = newKeys.getModulus();

        // outputs key to user
        System.out.println("Success! Here are your keys: ");
        System.out.println("Public Key k: " + this.publicKey);
        System.out.println("Private Key s: " + this.privateKey);
        System.out.println("Modulus n: " + this.modulus);

        sc.nextLine();
        System.out.print("Press enter to go back to the console menu! ");
        sc.nextLine();
    }

    private void encrypt(Scanner sc){
        System.out.println("ENCRYPTION");

    }

    private void decrypt(Scanner sc){
        System.out.println("DECRYPTION");
    }


    public void run(){
        Scanner sc = new Scanner(System.in);
        welcomeMsg(sc);

        while(true) {
            consoleMenu();
            int choice = sc.nextInt();

            if (choice == 1) {
                //key gen
                generateKeys(sc);
            }
            else if(choice == 2){
                // encryption
                encrypt(sc);
            }
            else if(choice == 3){
                // decryption
                decrypt(sc);
            }
            else if(choice == 4){
                // exits console
                System.out.println("Thank you for using this console!");
                System.out.print("See you soon! :)");
                break;
            }
        }
        sc.close();
    }



    public static void main(String[] args) {
        Console testing = new Console();
        testing.run();

    }
}
