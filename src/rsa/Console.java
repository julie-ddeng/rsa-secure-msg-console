package rsa;

import java.util.Arrays;
import java.util.Scanner;

public class Console {
    private int publicKey;
    private int privateKey;
    private int modulus;

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

    // Console methods

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

    // Console option methods

    private void generateKeys(Scanner sc){
        System.out.println("PUBLIC/PRIVATE KEY GENERATION");
        System.out.println("Instructions:");
        System.out.println("Please enter two distinct prime numbers over 5.");
        System.out.print("First prime: ");
        int p1 = sc.nextInt();
        System.out.print("Second prime: ");
        int p2 = sc.nextInt();
        sc.nextLine();

        // asks for user input until primes are valid
        while( ! primeInputValidation(p1, p2)){
            System.out.println("Invalid primes, try again!");
            System.out.print("First prime: ");
            p1 = sc.nextInt();
            System.out.print("Second prime: ");
            p2 = sc.nextInt();
            sc.nextLine();
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
    }

    private void encrypt(Scanner sc){
        System.out.println("ENCRYPTION");
        System.out.println("This console will encrypt single word messages, case insensitive.");
        // ask for user input
        System.out.print("Please enter the word you want to encrypt: ");
        String word = sc.nextLine();
        System.out.print("Please enter the public key k: ");
        int pubKey = sc.nextInt();
        System.out.print("Please enter the modulus n: ");
        int mod = sc.nextInt();
        sc.nextLine();

        // Ensure public key and modulus makes sense

        // ecrypt and return cipher text to user
        Cipher encrypt = new Cipher(pubKey, mod, word.toUpperCase());
        System.out.println("Here is your encrypted message: " + Arrays.toString(encrypt.encryptWord()));

    }

    private void decrypt(Scanner sc){
        System.out.println("DECRYPTION");
        System.out.println("This console will decrypt an encoded single word message.");

        // user input
        System.out.print("Please enter your private key: ");
        int privKey = sc.nextInt();
        System.out.print("Please enter your modulus: ");
        int mod = sc.nextInt();
        sc.nextLine();
        // insert private key and modulus validation?

        System.out.println("Please enter the encoded message to decrypt (Format: 13, 24, 35, 43): ");
        String codedMsg = sc.nextLine();

        // transform coded message into an array of integers
        String[] tempMsg = codedMsg.split(", ");
        int[] cipherText = new int[tempMsg.length];
        for(int i = 0; i < cipherText.length; i++){
            cipherText[i] = Integer.parseInt(tempMsg[i]);
        }

        // actual decryption
        Cipher decrypt = new Cipher(privKey, mod, cipherText);
        System.out.println("This is your secret message: " + decrypt.decryptWord());

    }


    public void run(){
        Scanner sc = new Scanner(System.in);
        welcomeMsg(sc);

        while(true) {
            consoleMenu();
            int choice = sc.nextInt();
            sc.nextLine();

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

            // return to console menu
            System.out.print("Press enter to go back to the console menu! ");
            sc.nextLine();
        }
        sc.close();
    }

    public static void main(String[] args) {
        Console testing = new Console();
        testing.run();
    }
}
