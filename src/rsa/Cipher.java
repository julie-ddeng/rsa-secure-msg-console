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
        int[] wordAsNum = Utils.wordToNum(this.word);
        int[] encrypted = new int[wordAsNum.length];
        // Stores each encrypted letter into an array
        for(int i = 0; i < encrypted.length; i++){
            encrypted[i] = encryptLetter(wordAsNum[i]);
        }
        return encrypted;
    }

    /**
     * This method encrypts a number num representing a letter of the alphabet.
     * It will compute num*publicKey and take its remainder mod modulus.
     */
    private int encryptLetter(int num){
        return Utils.modPow(num, this.publicKey, this.modulus);
    }

    // Decrypt methods

    /**
     * This method decrypts the cipherText (int[]) and returns an array containing the letters of the message.
     */
    public String decryptWord(){
        char[] result = new char[this.cipherText.length];
        for(int i = 0; i < result.length; i++){
            result[i] = decryptLetter(this.cipherText[i]);
        }

        //convert result (char[]) to type String
        String resultAsString = new String(result);
        return resultAsString;
    }

    /**
     * This method returns the encrypted number as the letter it is supposed to represent.
     */
    private char decryptLetter(int num){
        int decryptedNum = Utils.modPow(num, this.privateKey, this.modulus);
        char letter = (char) (decryptedNum -1 + 'A');
        return letter;
    }

}
