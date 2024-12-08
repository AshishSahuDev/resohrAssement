package com.resohr.wedding.utilty;

import java.util.Base64;

public class ConfigDecryptionUtils {

	private ConfigDecryptionUtils() {
		throw new UnsupportedOperationException("Utility Class");
	}
	
	public static String decrypt(String encryptedValue) {
        if (encryptedValue.startsWith("b64:")) {
            String base64Value = encryptedValue.substring(4);
            return new String(Base64.getDecoder().decode(base64Value));
        }
        throw new IllegalArgumentException("Unsupported encryption format.");
    }
}
