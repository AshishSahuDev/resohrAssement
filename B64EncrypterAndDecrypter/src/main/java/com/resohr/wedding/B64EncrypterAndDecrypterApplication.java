package com.resohr.wedding;

import java.util.Base64;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class B64EncrypterAndDecrypterApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(B64EncrypterAndDecrypterApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            // Process arguments
            for (String arg : args) {
                System.out.println("Encrypted value of \"" + arg + "\": " + encrypt(arg));
            }
        } else {
            // Interactive mode
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Encrypt a value");
                System.out.println("2. Decrypt a value");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.print("Enter value to encrypt: ");
                    String value = scanner.nextLine();
                    System.out.println("Encrypted value: " + encrypt(value));
                } else if (choice == 2) {
                    System.out.print("Enter Base64 value to decrypt: ");
                    String value = scanner.nextLine();
                    try {
                        System.out.println("Decrypted value: " + decrypt(value));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid Base64 value.");
                    }
                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        }
	}
	
	private String encrypt(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private String decrypt(String value) {
        return new String(Base64.getDecoder().decode(value));
    }
}
