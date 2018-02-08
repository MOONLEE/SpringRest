package com.moon.rest.main.utiil;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.validation.constraints.NotNull;

/**
 * @author moonlee
 *	
 */
public class CryptUtil {
	
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	
	/**
	 * 평문을 암호화
	 * 
	 * @param algorithm
	 * @param plainText
	 * @param charSet
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getEncrptString( String algorithm, String plainText, String charSet) throws NoSuchAlgorithmException {
		
		// 알고리즘으로 암호화 byte로 전환
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte[] textBytes = plainText.getBytes(Charset.forName(charSet));
		md.update(textBytes);
		byte[] digestData = md.digest();

		// byte To Hex String
		String encryptedString = Base64.getEncoder().encodeToString(digestData);
		
		return encryptedString;
	}
	
	
	/**
	 * 
	 * SHA-512로 평문을 암호화
	 * @param plainText
	 * @param charSet
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha512Encrpt(String plainText, @NotNull String charSet) throws NoSuchAlgorithmException {
		return getEncrptString("SHA-512", plainText, charSet);
	}
	
	
	/**
	 * 
	 * SHA-512로 평문을 암호화
	 * @param plainText
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSha512Encrpt(String plainText) throws NoSuchAlgorithmException {
		return getSha512Encrpt(plainText, DEFAULT_CHARSET);
	}
	
	
	
	
	
}
