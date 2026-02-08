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
    private int gcd(int a, int b){
        /** This function calculates and returns the greatest common divisor (GCD) of two integers a and b using
         * Euclid's algorithm.
         */
        int r = a % b;
        while(r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    private int generatePublic(){
        /** This function generates a public key k, such that the gcd( k, (p-1)*(q-1) ) = 1, where (p-1)*(q-1) is
         * Euler's Totient.
         */

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
                break;
            }
        }
        return k;
    }

    private int inverse(int n, int k){
        /** This function finds and returns the multiplicative inverse of an integer k, modulus n, using the extended
         * Euclidean algorithm.
         */
        int r = n % k;
        int q = (n - r) / k;
        int t1 = 0;
        int t2 = 1;
        int t3 = t1 - q * t2;
        while(r != 0) {
            n = k;
            k = r;
            r = n % k;
            q = (n - r) / k;
            t1 = t2;
            t2 = t3;
            t3 = t1 - q * t2;
        }
        return t2;
    }

    private int generatePrivate(){
        /** This function generates a private key s such that it is the inverse of the public key k, where the modulus
         * is Euler's Totient.
         */
        int s;
        s = inverse(this.eulers, this.publicKey);

        // Ensures s is in the range [0, modulus]
        if (s < 0)
            s = s + this.eulers;
        return s;
    }



    public static void main(String[] args){
        KeyGen newKey = new KeyGen(13, 11);
        System.out.println(newKey.publicKey);
        System.out.println(newKey.privateKey);

    }
}
