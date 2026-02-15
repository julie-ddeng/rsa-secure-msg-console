package rsa;

public class Utils {

    // Key Generation helper functions

    /** This function calculates and returns the greatest common divisor (GCD) of two integers a and b using
     * Euclid's algorithm.
     */
    public static int gcd(int a, int b){
        int r = a % b;
        while(r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    /** This function finds and returns the multiplicative inverse of an integer k, modulus mod, using the extended
     * Euclidean algorithm.
     */
    public static int inverse(int k, int mod){
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

    // Cipher helper methods

    /**
     * This method returns an array of the numbers corresponding to the order of the letters in the alphabet.
     */
    public static int[] wordToNum(char[] word){
        int[] result = new int[word.length];
        for(int i = 0; i < result.length; i++){
            result[i] = word[i] - 'A' + 1;
        }
        return result;
    }

    /**
     * This method implements repeated squaring trick to compute an exponent, modulo n.
     * Consistently reduces the base (mod n) to avoid integers that are too large.
     */
    public static int modPow(int base, int exponent, int modulus){
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


    // User input validation methods

    /**
     * This method returns true if p1 and p2 are two distinct prime numbers greater than 5.
     */
    public static boolean primeInputValidation(int p1, int p2){
        if(p1 == p2 || p1 < 7 || p2 < 7){
            return false;
        }
        //check if both are prime
        return (isPrime(p1) && isPrime(p2));
    }

    /**
     * This method checks if a given integer is prime
     */
    public static boolean isPrime(int i){
        if ( i < 2) return false;
        int d = 2;
        while (d*d <= i){
            if (i % d == 0){
                return false;
            }
            d++;
        }
        return true;
    }
}
