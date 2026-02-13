package rsa;
import java.util.Random;

public class KeyGen {
    private int p;
    private int q;
    private int modulus;
    private int publicKey;
    private int privateKey;
    private int eulers;

    // Constructor
    public KeyGen(int prime1, int prime2){
        this.p = prime1;
        this.q = prime2;
        this.modulus = prime1 * prime2;
        this.eulers = (this.p - 1) * (this.q - 1);
        this.publicKey = generatePublic();
        this.privateKey = generatePrivate();
    }

    // getters
    public int getPrivateKey(){
        return this.privateKey;
    }

    public int getPublicKey(){
        return this.publicKey;
    }

    public int getModulus(){
        return this.modulus;
    }

    // Public/private key generator functions

    /** This function calculates and returns the greatest common divisor (GCD) of two integers a and b using
     * Euclid's algorithm.
     */
    private int gcd(int a, int b){
        int r = a % b;
        while(r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    /** This function generates a public key k, such that the gcd( k, (p-1)*(q-1) ) = 1, where (p-1)*(q-1) is
     * Euler's Totient.
     */
    private int generatePublic(){
        Random randomGen = new Random();
        int k;
        // loop will keep generating until it finds public key k relatively prime with eulers.
        while (true){
            k = randomGen.nextInt(this.eulers - 3) + 3;
            // Filters out even numbers and the original prime numbers used to calculate Euler's Totient
            if (k % 2 == 0 || k == this.p || k == this.q )
                continue;
            // Check if relatively prime
            if (gcd(this.eulers, k) == 1){
                //Ensures k is not its own inverse (so public key != private key)
                if (inverse(k, this.eulers) != k){
                    break;
                }
            }
        }
        return k;
    }

    /** This function finds and returns the multiplicative inverse of an integer k, modulus mod, using the extended
     * Euclidean algorithm.
     */
    private int inverse(int k, int mod){
        int prevRemain = mod;
        int currRemain = k;

        int prevCoeff = 0;
        int currCoeff = 1;

        while (currRemain != 0) {
            int quotient = prevRemain / currRemain;

            int tempRemain = currRemain;
            currRemain = prevRemain - quotient * currRemain;
            prevRemain = tempRemain;

            int tempCoeff = currCoeff;
            currCoeff = prevCoeff - quotient * currCoeff;
            prevCoeff = tempCoeff;
        }
        int inverse = prevCoeff;
        // Ensures inverse is positive
        if (inverse < 0) {
            inverse += mod;
        }
        return inverse;
    }

    /** This function generates a private key s such that it is the inverse of the public key k, where the modulus
     * is Euler's Totient.
     */
    private int generatePrivate(){
        return inverse(this.publicKey, this.eulers);
    }
}
