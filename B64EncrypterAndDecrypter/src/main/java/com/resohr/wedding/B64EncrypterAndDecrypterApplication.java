package com.resohr.wedding;

import java.util.Base64;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class B64EncrypterAndDecrypterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("Application started successfully! Ready for encryption and decryption.");
		SpringApplication.run(B64EncrypterAndDecrypterApplication.class, args);
		String activeProfile = System.getProperty("spring.profiles.active");
        if ("test".equalsIgnoreCase(activeProfile)) {
            runInteractiveMenu();
        }
	}

	private String key;

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			// Process arguments

			if (args[0].equalsIgnoreCase("encrypt")) {
				for (int i = 1; i < args.length; i++) {
	                System.out.println("Encrypted value of \"" + args[i] + "\": " + encrypt(args[i]));
	            }
			} else if (args[0].equalsIgnoreCase("decrypt")) {
				for (int i = 1; i < args.length; i++) {
	                System.out.println("Decrypted value of \"" + args[i] + "\": " + decrypt(args[i]));
	            }
			} else {
				if (!args[0].equalsIgnoreCase("encrypt") || !args[0].equalsIgnoreCase("decrypt"))
					System.out.println("first argumet must be 'encrypt' for encrption or 'decrypt' for decryption");
				do {
					terminalEncryption();
				} while (key.equals(null));
			}
		} else {
			do {
				terminalEncryption();
			} while (key.equals(null));
		}
	}

	private String terminalEncryption() {

		try (// Interactive mode
				Scanner scanner = new Scanner(System.in)) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Encrypt a value");
			System.out.println("2. Decrypt a value");
			System.out.println("3. Exit");

			String choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("1")) {
				System.out.print("Enter value to encrypt: ");
				String value = scanner.nextLine();
				try {
					key = encrypt(value);
				} catch (IllegalArgumentException e) {
					System.out.println("Error: Invalid Base64 value.");
				}
			} else if (choice.equalsIgnoreCase("2")) {
				System.out.print("Enter Base64 value to decrypt: ");
				String value = scanner.nextLine();
				try {
					key = decrypt(value);
				} catch (IllegalArgumentException e) {
					System.out.println("Error: Invalid Base64 value.");
				}
			} else if (choice.equalsIgnoreCase("3")) {
				System.out.println("Exiting...");
				key = null;
				System.exit(0);

			} else {
				System.out.println("Invalid choice. Try again.");
				key = "";
			}
		}
		return key;

	}

	private static String encrypt(String value) {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}

	private static String decrypt(String value) {
		return new String(Base64.getDecoder().decode(value));
	}
	
	private static void runInteractiveMenu() {
	    try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    System.out.println("Choose an option:");
			    System.out.println("1. Encrypt a value");
			    System.out.println("2. Decrypt a value");
			    System.out.println("3. Exit");
			    
			    int choice = scanner.nextInt();
			    scanner.nextLine(); // Consume newline character
			    switch (choice) {
			        case 1:
			            System.out.print("Enter value to encrypt: ");
			            String valueToEncrypt = scanner.nextLine();
			            System.out.println("Encrypted: " + encrypt(valueToEncrypt));
			            break;
			        case 2:
			            System.out.print("Enter value to decrypt: ");
			            String valueToDecrypt = scanner.nextLine();
			            System.out.println("Decrypted: " + decrypt(valueToDecrypt));
			            break;
			        case 3:
			            System.out.println("Exiting...");
			            return;
			        default:
			            System.out.println("Invalid choice. Try again.");
			    }
			}
		}
	}
}
