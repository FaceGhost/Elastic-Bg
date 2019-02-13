package com.faceghost.elasticbg.base.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * <p>
 * Base58 压缩算法辅助类
 * </p>
 * 
 * @Date 2016-04-21
 */
public class Base58Helper {

	public static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();

	private static final int[] INDEXES = new int[128];


	static {
		for ( int i = 0 ; i < INDEXES.length ; i++ ) {
			INDEXES[i] = -1;
		}
		for ( int i = 0 ; i < ALPHABET.length ; i++ ) {
			INDEXES[ALPHABET[i]] = i;
		}
	}


	/** Encodes the given bytes in base58. No checksum is appended. */
	public static String encode( byte[] value ) {
		if ( value.length == 0 ) {
			return "";
		}
		byte[] input = copyOfRange(value, 0, value.length);
		// Count leading zeroes.
		int zeroCount = 0;
		while ( zeroCount < input.length && input[zeroCount] == 0 ) {
			++zeroCount;
		}
		// The actual encoding.
		byte[] temp = new byte[input.length * 2];
		int j = temp.length;

		int startAt = zeroCount;
		while ( startAt < input.length ) {
			byte mod = divmod58(input, startAt);
			if ( input[startAt] == 0 ) {
				++startAt;
			}
			temp[--j] = (byte) ALPHABET[mod];
		}

		// Strip extra '1' if there are some after decoding.
		while ( j < temp.length && temp[j] == ALPHABET[0] ) {
			++j;
		}
		// Add as many leading '1' as there were leading zeros.
		while ( --zeroCount >= 0 ) {
			temp[--j] = (byte) ALPHABET[0];
		}

		byte[] output = copyOfRange(temp, j, temp.length);
		try {
			return new String(output, "US-ASCII");
		} catch ( UnsupportedEncodingException e ) {
			throw new RuntimeException(e); // Cannot happen.
		}
	}


	public static String encode( long id ) {
		return encode(long2Bytes(id));
	}


	public static long decode( String input ) {
		return bytes2Long(decodeInner(input));
	}


	public static byte[] decodeInner( String input ) {
		if ( input.length() == 0 ) {
			return new byte[0];
		}
		byte[] input58 = new byte[input.length()];
		// Transform the String to a base58 byte sequence
		for ( int i = 0 ; i < input.length() ; ++i ) {
			char c = input.charAt(i);
			int digit58 = -1;
			if ( c >= 0 && c < 128 ) {
				digit58 = INDEXES[c];
			}
			if ( digit58 < 0 ) {
				throw new RuntimeException("Illegal character " + c + " at " + i);
			}
			input58[i] = (byte) digit58;
		}
		// Count leading zeroes
		int zeroCount = 0;
		while ( zeroCount < input58.length && input58[zeroCount] == 0 ) {
			++zeroCount;
		}
		// The encoding
		byte[] temp = new byte[input.length()];
		int j = temp.length;

		int startAt = zeroCount;
		while ( startAt < input58.length ) {
			byte mod = divmod256(input58, startAt);
			if ( input58[startAt] == 0 ) {
				++startAt;
			}

			temp[--j] = mod;
		}
		// Do no add extra leading zeroes, move j to first non null byte.
		while ( j < temp.length && temp[j] == 0 ) {
			++j;
		}
		return copyOfRange(temp, j - zeroCount, temp.length);
	}


	public static byte[] doubleDigest( byte[] input, int offset, int length ) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(input, offset, length);
			byte[] first = digest.digest();
			return digest.digest(first);
		} catch ( NoSuchAlgorithmException e ) {
			throw new RuntimeException(e); // Cannot happen.
		}
	}


	//
	// number -> number / 58, returns number % 58
	//
	private static byte divmod58( byte[] number, int startAt ) {
		int remainder = 0;
		for ( int i = startAt ; i < number.length ; i++ ) {
			int digit256 = number[i] & 0xFF;
			int temp = remainder * 256 + digit256;
			number[i] = (byte) (temp / 58);
			remainder = temp % 58;
		}
		return (byte) remainder;
	}


	//
	// number -> number / 256, returns number % 256
	//
	private static byte divmod256( byte[] number58, int startAt ) {
		int remainder = 0;
		for ( int i = startAt ; i < number58.length ; i++ ) {
			int digit58 = number58[i] & 0xFF;
			int temp = remainder * 58 + digit58;
			number58[i] = (byte) (temp / 256);
			remainder = temp % 256;
		}
		return (byte) remainder;
	}


	private static byte[] copyOfRange( byte[] source, int from, int to ) {
		byte[] range = new byte[to - from];
		System.arraycopy(source, from, range, 0, range.length);
		return range;
	}


	/*
	 * 压缩 UUID
	 */
	public static synchronized String compressedUUID() {
		UUID uuid = UUID.randomUUID();
		byte[] byUuid = new byte[16];
		long least = uuid.getLeastSignificantBits();
		long most = uuid.getMostSignificantBits();
		long2bytes(most, byUuid, 0);
		long2bytes(least, byUuid, 8);
		return Base58Helper.encode(byUuid);
	}


	/*
	 * 针对正数的int
	 */
	public static byte[] long2Bytes( long num ) {
		byte[] byteNum = new byte[8];
		for ( int ix = 0 ; ix < 8 ; ++ix ) {
			int offset = 64 - (ix + 1) * 8;
			byteNum[ix] = (byte) ((num >> offset) & 0xff);
		}
		return byteNum;
	}


	public static long bytes2Long( byte[] byteNum ) {
		long num = 0;
		for ( int ix = 0 ; ix < 8 ; ++ix ) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}


	public static void long2bytes( long value, byte[] bytes, int offset ) {
		int offsetLocal = offset;
		for ( int i = 7 ; i > -1 ; i-- ) {
			bytes[offsetLocal++] = (byte) ((value >> 8 * i) & 0xFF);
		}
	}

}
