package net.savageinter.springpad;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	private static String HEX_DIGITS = "0123456789abcdef";
	private static SecureRandom random = new SecureRandom();
	
	@Id
	private ObjectId id;
	
	private String email;
	private String hashedPassword;
	private String salt;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean authenticate(String password) {
		return encryptPassword(password).equals(hashedPassword);
	}
	
	public static void beforeSave(User user) {
		user.salt = User.makeSalt();
		user.hashedPassword = user.encryptPassword(user.hashedPassword);
	}
	
	private static String makeSalt() {
		return new BigInteger(128, random).toString(16);
	}
	
    private String encryptPassword(String password) {
    	MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
    	md.update(salt.getBytes());
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : byteData) {
			sb.append(HEX_DIGITS.charAt(b >> 4));
			sb.append(HEX_DIGITS.charAt(b & 0xf));
		}
		return sb.toString();
    }
}