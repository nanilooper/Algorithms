package com.practice.algorithms.general;

import java.math.BigInteger;

public class Karatsuba {

	public static String multiplyIntegers(String x, String y) throws NumberFormatException {

		if (x.replaceFirst("^0+(?!$)", "").length() < 3 && y.replaceFirst("^0+(?!$)", "").length() < 3) {
			long ans = Long.parseLong(x.replaceFirst("^0+(?!$)", "")) * Long.parseLong(y.replaceFirst("^0+(?!$)", ""));
			return ans + "";
		}

		if (x.length() < y.length()) {
			x = makeStringsEqualLengths(x, y);
		} else if (x.length() > y.length()) {
			y = makeStringsEqualLengths(x, y);
		} else {

		}

		int n = Math.max(x.length(), y.length());
		// System.out.println(x);

		String x0 = x.substring(0, n / 2);
		String x1 = x.substring(n / 2, n);

		String y0 = y.substring(0, n / 2);
		String y1 = y.substring(n / 2, n);

		String val1 = multiplyIntegers(x0, y0);
		String val2 = multiplyIntegers(x1, y1);
		String val3 = multiplyIntegers(x0, y1);
		String val4 = multiplyIntegers(x1, y0);

		String k = multiplyIntegers(val1, convertPowertoString(n));
		String m = multiplyIntegers(addStrings(val3, val4), convertPowertoString(n / 2));

		String result0 = addStrings(k, m);
		String result = addStrings(result0, val2);

		// BigInteger ans = (long) Math.pow(10, n) * Long.parseLong(val1) + (long) Math.pow(10, n / 2) * Long.parseLong(val3 + val4)
		// + Long.parseLong(val2);
		// return ans + "";
		return result;
	}

	public static String makeStringsEqualLengths(String s1, String s2) {
		if (s1.length() > s2.length()) {
			int n = s1.length() - s2.length();
			for (int i = 0; i < n; i++) {
				s2 = "0" + s2;
			}
			return s2;
		} else if (s1.length() < s2.length()) {
			int n = s2.length() - s1.length();
			for (int i = 0; i < n; i++) {
				s1 = "0" + s1;
			}
			return s1;
		} else {
			return "";
		}
	}

	public static String addStrings(String x, String y) {
		StringBuilder s = new StringBuilder("");
		if (x.length() < y.length()) {
			x = makeStringsEqualLengths(x, y);
		} else if (x.length() > y.length()) {
			y = makeStringsEqualLengths(x, y);
		} else {

		}
		//
		// System.out.println(x);
		// System.out.println(y);

		int carry = 0;
		for (int i = x.length() - 1; i >= 0; i--) {
			int a = Character.getNumericValue(x.charAt(i));
			int b = Character.getNumericValue(y.charAt(i));
			s.append((a + b + carry) % 10);
			carry = (a + b + carry) / 10;
		}
		s.append(carry);
		s.reverse();
		return s.toString();
	}

	public static String convertPowertoString(int n) {
		StringBuilder s = new StringBuilder("");
		s.append(1);
		for (int i = 0; i < n; i++) {
			s.append(0);
		}
		return s.toString();

	}

	public static void main(String[] args) {
		BigInteger b1 = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
		BigInteger b2 = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
		BigInteger b3 = b1.multiply(b2);
		System.out.println(b3.toString());
	}

}
