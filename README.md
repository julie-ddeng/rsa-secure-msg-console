# RSA Secure Messaging Console Project

This project displays a simple console and uses the RSA cryptosystem to allow users to generate public/private keys and encrypt/decrypt one word messages. 

## How does it work?

- To generate keys, the program asks the user to input 2 prime numbers. It then calculates and returns the public and private keys.

- To encrypt the messages, the program converts each letter to its corresponding order in the alphabet('a' = 1, ... , 'z' = 26). It then uses the public key to produce a ciphertext of each of the letters that the user can then transmit to their counterpart.

- To decrypt, the programs uses the private key to decipher the message and convert it back into letters the intended recipient can read.

### Note on security

- This is a very basic implementation of the RSA cryptosystem and is in no way meant to be truly secure. 
  - Designed mostly for learning, experimenting, and curiosity :)

### Future Improvements

- Offer prime number generation
- Replace int with BigInteger
- Implement more robust user input validation
- Add GUI interface
