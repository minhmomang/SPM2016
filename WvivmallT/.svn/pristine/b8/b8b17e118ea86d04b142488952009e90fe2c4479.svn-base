package Helper;

import java.io.UnsupportedEncodingException;

import org.apache.axis.encoding.Base64;

public class EncrypterDecrypter {
	public static String encodeString(String text) {
		byte[] bytes = null;
		try {
			bytes = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encodeString = Base64.encode(bytes);
		return encodeString;
	}

	// Giải mã hóa một đoạn text (Đã mã hóa trước đó).
	// Decode
	public static String decodeString(String encodeText) throws UnsupportedEncodingException {
		byte[] decodeBytes = Base64.decode(encodeText);
		String str = new String(decodeBytes, "UTF-8");
		return str;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String text = "001-02";

		////System.out.println("Text before encode: " + text);

		String encodeText = encodeString(text);
		////System.out.println("Encode text: " + encodeText);

		String decodeText = decodeString(encodeText);
		////System.out.println("Decode text: " + decodeText);
	}

}
