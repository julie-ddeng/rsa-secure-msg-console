package rsa;

public class Cipher {
    // fields
    private int publicKey; // public key k
    private int privateKey; // private key s
    private int modulus; // modulus n
    private char[] word; // word to encrypt
    private int[] cipherText; //cipherText to decrypt

    // Encrypt constructor
    public Cipher(int publicKey, int modulus, String word){
        this.publicKey = publicKey;
        this.modulus = modulus;
        this.word = word.toCharArray();
    }
    // Decrypt constructor
    public Cipher(int privateKey, int modulus, int[] cipherText){
        this.privateKey = privateKey;
        this.modulus = modulus;
        // Stores copy of the cipherText array
        int[] cipherCopy = new int[cipherText.length];
        for (int i = 0; i < cipherText.length; i++){
            cipherCopy[i] = cipherText[i];
        }
        this.cipherText = cipherCopy;
    }

    // Encrypt methods
    public int[] encryptWord(){
        /**
         * This method returns an array containing an encryption of each letter of the word to be encrypted.
         */
        // transforms each letter of the word into its number representation
        int[] wordAsNum = wordToNum(this.word);
        int[] encrypted = new int[wordAsNum.length];
        // Stores each encrypted letter into an array
        for(int i = 0; i < encrypted.length; i++){
            encrypted[i] = encryptLetter(wordAsNum[i]);
        }
        return encrypted;
    }

    // Helper methods to encryptWord
        // Unicode info for testing purposes: 'A' == 65, 'Z' == 90.
    private int[] wordToNum(char[] word){
        /**
         * This method returns an array of the numbers corresponding to the order of the letters in the alphabet.
         */
        int[] result = new int[word.length];
        for(int i = 0; i < result.length; i++){
            result[i] = word[i] - 'A' + 1;
        }
        return result;
    }
    private int encryptLetter(int num){
        /**
         * This method encrypts a number num representing a letter of the alphabet.
         * It will compute num*publicKey and take its remainder mod modulus.
         */
        return modPow(num, this.publicKey, this.modulus);
    }

    // Decrypt methods
    public String decryptWord(){
        /**
         * This method decrypts the cipherText (int[]) and returns an array containing the letters of the message.
         */
        char[] result = new char[this.cipherText.length];
        for(int i = 0; i < result.length; i++){
            result[i] = decryptLetter(this.cipherText[i]);
        }

        //convert result (char[]) to type String
        String resultAsString = new String(result);
        return resultAsString;
    }

    private char decryptLetter(int num){
        /**
         * This method returns the encrypted number as the letter it is supposed to represent.
         */
        int decryptedNum = modPow(num, this.privateKey, this.modulus);
        char letter = (char) (decryptedNum -1 + 'A');
        return letter;
    }

    // other helper method
    private int modPow(int base, int exponent, int modulus){
        /**
         * This method implements repeated squaring trick to compute an exponent, modulo n.
         * Consistently reduces the base (mod n) to avoid integers that are too large.
         */
        int result = 1;
        base = base % modulus;
        while (exponent > 0){
            if(exponent % 2 == 1){
                result = (result * base) % modulus;
                exponent--;
            }
            exponent = exponent/2;
            base = (base*base) % modulus;
        }
        return result;
    }
}
