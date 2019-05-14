package com.mt.member.api.util;

import java.util.Random;

public class Uid {

	private static Uid singleton = new Uid();

	private static String key;

	public Uid() {
		key = generateString(36);
	}

	public String generateString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
		Random rnd = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rnd.nextInt(characters.length()));
		}
		return new String(text);
	}

	public static Uid getInstance() {
		return singleton;
	}
}
