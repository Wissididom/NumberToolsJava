package tools.number.classes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.databinding.types.UnsignedLong;

public class API {
	
	public enum Language {
		ROMAN, GERMAN, ENGLISH, JAPANESE, ESPERANTO, VAMPIRSCHWESTERN, BINARY;
	}
	
	public static UnsignedLong parseUnsignedLong(String unsignedLong) {
		return new UnsignedLong(unsignedLong);
	}
	
	public static String getNumber(Language language, UnsignedLong number) {
		switch (language) {
			case ROMAN:
				return getRomanNumber(number);
			case GERMAN:
				return getGermanNumber(number);
			case ENGLISH:
				return getEnglishNumber(number);
			case JAPANESE:
				return getJapaneseNumber(number);
			case ESPERANTO:
				return getEsperantoNumber(number);
			case VAMPIRSCHWESTERN:
				return getVampirschwesternNumber(number);
			case BINARY:
				return getBinaryNumber(number);
			default:
				return null;
		}
	}
	
	public static String getRomanNumber(UnsignedLong number) {
		String result = null;
		UnsignedLong m = floorDiv(number, new UnsignedLong("1000"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(m) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "M";
		}
		number = subtract(number, multiply(m, new UnsignedLong("1000")));
		UnsignedLong d = floorDiv(number, new UnsignedLong("500"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(d) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "D";
		}
		number = subtract(number, multiply(d, new UnsignedLong("500")));
		UnsignedLong c = floorDiv(number, new UnsignedLong("100"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(c) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "C";
		}
		number = subtract(number, multiply(c, new UnsignedLong("100")));
		UnsignedLong l = floorDiv(number, new UnsignedLong("50"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(l) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "L";
		}
		number = subtract(number, multiply(l, new UnsignedLong("50")));
		UnsignedLong x = floorDiv(number, new UnsignedLong("10"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(x) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "X";
		}
		number = subtract(number, multiply(x, new UnsignedLong("10")));
		UnsignedLong v = floorDiv(number, new UnsignedLong("5"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(v) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "V";
		}
		number = subtract(number, multiply(v, new UnsignedLong("5")));
		UnsignedLong i = floorDiv(number, new UnsignedLong("1"));
		for (UnsignedLong a = new UnsignedLong("0"); a.compareTo(i) < 0; a = increase(a)) {
			if (result == null)
				result = "";
			result += "I";
		}
		result = result.replace("VIIII", "IX"); // 9
		result = result.replace("IIII", "IV");  // 4
		result = result.replace("LXXXX", "XC"); // 90
		result = result.replace("XXXX", "XL");  // 40
		result = result.replace("DCCCC", "CM"); // 900
		result = result.replace("CCCC", "CD"); // 400
		return result;
	}
	
	public static String getGermanNumber(UnsignedLong number) {
		if (number.equals(new UnsignedLong("0"))) {
			return "Null";
		} else if (number.equals(new UnsignedLong("1"))) {
			return "Eins";
		} else if (number.equals(new UnsignedLong("2"))) {
			return "Zwei";
		} else if (number.equals(new UnsignedLong("3"))) {
			return "Drei";
		} else if (number.equals(new UnsignedLong("4"))) {
			return "Vier";
		} else if (number.equals(new UnsignedLong("5"))) {
			return "Fünf";
		} else if (number.equals(new UnsignedLong("6"))) {
			return "Sechs";
		} else if (number.equals(new UnsignedLong("7"))) {
			return "Sieben";
		} else if (number.equals(new UnsignedLong("8"))) {
			return "Acht";
		} else if (number.equals(new UnsignedLong("9"))) {
			return "Neun";
		} else if (number.equals(new UnsignedLong("10"))) {
			return "Zehn";
		} else if (number.equals(new UnsignedLong("11"))) {
			return "Elf";
		} else if (number.equals(new UnsignedLong("12"))) {
			return "Zwölf";
		} else if (number.compareTo(new UnsignedLong("20")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Sieben", "Sieb") + "zehn";
		} else if (number.equals(new UnsignedLong("20"))) {
			return "Zwanzig";
		} else if (number.compareTo(new UnsignedLong("30")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undzwanzig";
		} else if (number.equals(new UnsignedLong("30"))) {
			return "Dreißig";
		} else if (number.compareTo(new UnsignedLong("40")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undreißig";
		} else if (number.equals(new UnsignedLong("40"))) {
			return "Vierzig";
		} else if (number.compareTo(new UnsignedLong("50")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undvierzig";
		} else if (number.equals(new UnsignedLong("50"))) {
			return "Fünfzig";
		} else if (number.compareTo(new UnsignedLong("60")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undfünfzig";
		} else if (number.equals(new UnsignedLong("60"))) {
			return "Sechzig";
		} else if (number.compareTo(new UnsignedLong("70")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undsechzig";
		} else if (number.equals(new UnsignedLong("70"))) {
			return "Siebzig";
		} else if (number.compareTo(new UnsignedLong("80")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undsiebzig";
		} else if (number.equals(new UnsignedLong("80"))) {
			return "Achtzig";
		} else if (number.compareTo(new UnsignedLong("90")) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undachtzig";
		} else if (number.equals(new UnsignedLong("90"))) {
			return "Neunzig";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 2))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(1))).replace("Eins", "Ein") + "undneunzig";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 2)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))).replace("Eins", "Ein") + "hundert";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))).replace("Eins", "Ein") + "hundert" + getGermanNumber(new UnsignedLong(number.toString().substring(1))).toLowerCase();
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 3)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))).replace("Eins", "Ein") + "tausend";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))).replace("Eins", "Ein") + "tausend" + getGermanNumber(new UnsignedLong(number.toString().substring(1))).toLowerCase();
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 4)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))).replace("Eins", "Ein") + "tausend";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))).replace("Eins", "Ein") + "tausend" + getGermanNumber(new UnsignedLong(number.toString().substring(2))).toLowerCase();
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 5)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))).replace("Eins", "Ein") + "tausend";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))).replace("Eins", "Ein") + "tausend" + getGermanNumber(new UnsignedLong(number.toString().substring(3))).toLowerCase();
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 6)))) {
			return "Eine Million";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 6))) < 0) {
			return "Eine Million " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 6)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Millionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Millionen " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 7)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Millionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Millionen " + getGermanNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 8)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Millionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Millionen " + getGermanNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 9)))) {
			return "Eine Milliarde";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 9))) < 0) {
			return "Eine Milliarde " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 9)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Milliarden";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Milliarden " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 10)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Milliarden";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Milliarden " + getGermanNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 11)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Milliarden";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Milliarden " + getGermanNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 12)))) {
			return "Eine Billion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 12))) < 0) {
			return "Eine Billion " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 12)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Billionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Billionen " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 13)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Billionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Billionen " + getGermanNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 14)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Billionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Billionen " + getGermanNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 15)))) {
			return "Eine Billiarde";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 15))) < 0) {
			return "Eine Billiarde " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 15)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Billionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Billionen " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 16)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Billionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Billionen " + getGermanNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 17)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Billionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Billionen " + getGermanNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 18)))) {
			return "Eine Trillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 18))) < 0) {
			return "Eine Trillion " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 18)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Trillionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 1))) + " Trillionen " + getGermanNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 19)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Trillionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 2))) + " Trillionen " + getGermanNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 20)))) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Trillionen";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0) {
			return getGermanNumber(new UnsignedLong(number.toString().substring(0, 3))) + " Trillionen " + getGermanNumber(new UnsignedLong(number.toString().substring(3)));
		} else {
			return null;
		}
		// Trilliarde; Quadrillion; Quadrilliarde; Quintillion; Quintilliarde; Sextillion; Sextilliarde; Septillion; Septilliarde; Oktillion; Oktilliarde; Nonillion; Nonilliarde;
		// Dezillion; Dezilliarde; Undezillion; Undezilliarde; Dodezillion; Dodezilliarde; Tredezillion; Tredezilliarde; Quattuordezillion; Quattuordezilliarde;
		// Quindezillion; Quindezilliarde; Sedezillion; Sedezilliarde; Septendezillion; Septendezilliarde; Dodevigintillion; Dodevigintilliarde;
		// Undevigintillion; Undevigintilliarde; Vigintillion; Vigintilliarde; Unvigintillion; Unvigintilliarde; Dovigintillion; Dovigintilliarde;
		// Tresvigintillion; Tresvigintilliarde; Quattuorvigintillion; Quattuorvigintilliarde; Quinvigintillion; Quinvigintilliarde; Sevigintillion; Sevigintilliarde;
		// Septenvigintillion; Septenvigintilliarde; Dodetrigintillion; Dodetrigintilliarde; Undetrigintillion; Undetrigintilliarde; Trigintillion; Trigintilliarde;
		// Untrigintillion; Untrigintilliarde; Dotrigintillion; Dotrigintilliarde; Tretrigintillion; Tretrigintilliarde; Quattuortrigintillion; Quattuortrigintilliarde;
		// Quintrigintillion; Quintrigintilliarde; Setrigintillion; Setrigintilliarde; Septentrigintillion; Septentrigintilliarde; Oktotrigintillion; Oktotrigintilliarde;
		// Novemtrigintillion; Novemtrigintilliarde; Quadragintillion; Quadragintilliarde; Unquadragintillion; Unquadragintilliarde; Doquadragintillion; Doquadragintilliarde;
		// Trequadragintillion; Trequadragintilliarde; Quattuorquadragintillion; Quattuorquadragintilliarde; Quinquadragintillion; Quinquadragintilliarde;
		// Sequadragintillion; Sequadragintilliarde; Septenquadragintillion; Septenquadragintilliarde; Oktoquadragintillion; Oktoquadragintilliarde;
		// Novemquadragintillion; Novemquadragintilliarde; Quinquagintillion; Quinquagintilliarde; Zentillion; Zentilliarde;Quinquagintizentillion; Quinquagintizentilliarde;
		// Duzentillion; Duzentilliarde
	}
	
	public static String getEnglishNumber(UnsignedLong number) {
		if (number.equals(new UnsignedLong("0"))) {
			return "Zero";
		} else if (number.equals(new UnsignedLong("1"))) {
			return "One";
		} else if (number.equals(new UnsignedLong("2"))) {
			return "Two";
		} else if (number.equals(new UnsignedLong("3"))) {
			return "Three";
		} else if (number.equals(new UnsignedLong("4"))) {
			return "Four";
		} else if (number.equals(new UnsignedLong("5"))) {
			return "Five";
		} else if (number.equals(new UnsignedLong("6"))) {
			return "Six";
		} else if (number.equals(new UnsignedLong("7"))) {
			return "Seven";
		} else if (number.equals(new UnsignedLong("8"))) {
			return "Eight";
		} else if (number.equals(new UnsignedLong("9"))) {
			return "Nine";
		} else if (number.equals(new UnsignedLong("10"))) {
			return "Ten";
		} else if (number.equals(new UnsignedLong("11"))) {
			return "Eleven";
		} else if (number.equals(new UnsignedLong("12"))) {
			return "Twelve";
		} else if (number.equals(new UnsignedLong("13"))) {
			return "Thirteen";
		} else if (number.equals(new UnsignedLong("14"))) {
			return "Fourteen";
		} else if (number.equals(new UnsignedLong("15"))) {
			return "Fifteen";
		} else if (number.compareTo(new UnsignedLong("20")) < 0) {
			return (getEnglishNumber(new UnsignedLong(number.toString().substring(1))) + "teen").replace("tt", "t");
		} else if (number.equals(new UnsignedLong("20"))) {
			return "Twenty";
		} else if (number.compareTo(new UnsignedLong("30")) < 0) {
			return "Twenty-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("30"))) {
			return "Thirty";
		} else if (number.compareTo(new UnsignedLong("40")) < 0) {
			return "Thirty-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("40"))) {
			return "Fourty";
		} else if (number.compareTo(new UnsignedLong("50")) < 0) {
			return "Fourty-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("50"))) {
			return "Fifty";
		} else if (number.compareTo(new UnsignedLong("60")) < 0) {
			return "Fifty-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("60"))) {
			return "Sixty";
		} else if (number.compareTo(new UnsignedLong("70")) < 0) {
			return "Sixty-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("70"))) {
			return "Seventy";
		} else if (number.compareTo(new UnsignedLong("80")) < 0) {
			return "Seventy-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("80"))) {
			return "Eighty";
		} else if (number.compareTo(new UnsignedLong("90")) < 0) {
			return "Eighty-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("90"))) {
			return "Ninety";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 2))) < 0) {
			return "Ninety-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 2)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Hundred";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Hundred-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 3)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Thousand";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Thousand-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 4)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Thousand";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Thousand-" + getEnglishNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 5)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Thousand";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Thousand-" + getEnglishNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 6)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Million";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Million-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 7)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Million";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Million-" + getEnglishNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 8)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Million";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Million-" + getEnglishNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 9)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Billion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Billion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 10)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Billion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Billion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 11)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Billion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Billion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 12)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Trillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Trillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 13)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Trillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Trillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 14)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Trillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Trillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 15)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Quadrillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Quadrillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 16)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Quadrillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Quadrillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 17)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Quadrillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Quadrillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 18)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Quintillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 1))) + "-Quintillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 19)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Quintillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 2))) + "-Quintillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 20)))) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Quintillion";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0) {
			return getEnglishNumber(new UnsignedLong(number.toString().substring(0, 3))) + "-Quintillion-" + getEnglishNumber(new UnsignedLong(number.toString().substring(3)));
		} else {
			return null;
		}
	}
	
	public static String getJapaneseNumber(UnsignedLong number) {
		if (number.equals(new UnsignedLong("0"))) {
			return "零";
		} else if (number.equals(new UnsignedLong("1"))) {
			return "一";
		} else if (number.equals(new UnsignedLong("2"))) {
			return "二";
		} else if (number.equals(new UnsignedLong("3"))) {
			return "三";
		} else if (number.equals(new UnsignedLong("4"))) {
			return "四";
		} else if (number.equals(new UnsignedLong("5"))) {
			return "五";
		} else if (number.equals(new UnsignedLong("6"))) {
			return "六";
		} else if (number.equals(new UnsignedLong("7"))) {
			return "七";
		} else if (number.equals(new UnsignedLong("8"))) {
			return "八";
		} else if (number.equals(new UnsignedLong("9"))) {
			return "九";
		} else if (number.equals(new UnsignedLong("10"))) {
			return "十";
		} else if (number.compareTo(new UnsignedLong("20")) < 0) {
			return "十" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 2))) < 0 && dividable(number, new UnsignedLong("10"))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "十";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 2))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "十" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 2)))) {
			return "百";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 2))) < 0) {
			return "百" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 2)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "百";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "百" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 3)))) {
			return "千";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 3))) < 0) {
			return "千" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 3)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "千";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "千" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 4)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "万";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "万" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 5)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "万";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "万" + getJapaneseNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 6)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "万";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "万" + getJapaneseNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 7)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "万";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "万" + getJapaneseNumber(new UnsignedLong(number.toString().substring(4)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 8)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "億";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "億" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 9)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "億";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "億" + getJapaneseNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 10)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "億";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "億" + getJapaneseNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 11)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "億";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "億" + getJapaneseNumber(new UnsignedLong(number.toString().substring(4)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 12)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "兆";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "兆" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 13)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "兆";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "兆" + getJapaneseNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 14)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "兆";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "兆" + getJapaneseNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 15)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "兆";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "兆" + getJapaneseNumber(new UnsignedLong(number.toString().substring(4)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 16)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "京";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "京" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 17)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "京";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "京" + getJapaneseNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 18)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "京";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "京" + getJapaneseNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 19)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "京";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "京" + getJapaneseNumber(new UnsignedLong(number.toString().substring(4)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 20)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "垓";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 1))) + "垓" + getJapaneseNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 22))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 21)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "垓";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 22))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 2))) + "垓" + getJapaneseNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 23))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 22)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "垓";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 23))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 3))) + "垓" + getJapaneseNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 24))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 23)))) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "垓";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 24))) < 0) {
			return getJapaneseNumber(new UnsignedLong(number.toString().substring(0, 4))) + "垓" + getJapaneseNumber(new UnsignedLong(number.toString().substring(4)));
		} else {
			return null;
		}
		// 秭; 穣; 溝; 澗; 正; 載; 極; 恒河沙; 阿僧祇; 那由他; 不可思議; 無量大数
		// https://de.wikipedia.org/wiki/Japanisches_Zahlensystem
	}
	
	public static String getEsperantoNumber(UnsignedLong number) {
		if (number.equals(new UnsignedLong("0"))) {
			return "nulo";
		} else if (number.equals(new UnsignedLong("1"))) {
			return "unu";
		} else if (number.equals(new UnsignedLong("2"))) {
			return "du";
		} else if (number.equals(new UnsignedLong("3"))) {
			return "tri";
		} else if (number.equals(new UnsignedLong("4"))) {
			return "kvar";
		} else if (number.equals(new UnsignedLong("5"))) {
			return "kvin";
		} else if (number.equals(new UnsignedLong("6"))) {
			return "ses";
		} else if (number.equals(new UnsignedLong("7"))) {
			return "sep";
		} else if (number.equals(new UnsignedLong("8"))) {
			return "ok";
		} else if (number.equals(new UnsignedLong("9"))) {
			return "naŭ";
		} else if (number.equals(new UnsignedLong("10"))) {
			return "dek";
		} else if (number.compareTo(new UnsignedLong("20")) < 0) {
			return "dek " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 2))) < 0 && dividable(number, new UnsignedLong("10"))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + "dek";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 2))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + "dek " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 2)))) {
			return "cent";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 2))) < 0) {
			return "cent " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 2)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + "cent";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 3))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + "cent " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong(appendMultiple("1", "0", 3)))) {
			return "mil";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("2", "0", 3))) < 0) {
			return "mil " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 3)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " mil";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 4))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " mil " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 4)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " mil";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 5))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " mil " + getEsperantoNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 5)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " mil";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 6))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " mil " + getEsperantoNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 6)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " miliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 7))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " miliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 7)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " miliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 8))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " miliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 8)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " miliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 9))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " miliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 9)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " miliardo";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 10))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " miliardo " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 10)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " miliardo";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 11))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " miliardo " + getEsperantoNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 11)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " miliardo";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 12))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " miliardo " + getEsperantoNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 12)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " biliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 13))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " biliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 13)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " biliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 14))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " biliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 14)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " biliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 15))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " biliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 15)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " biliardo";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 16))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " biliardo " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 16)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " biliardo";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 17))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " biliardo " + getEsperantoNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 17)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " biliardo";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 18))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " biliardo " + getEsperantoNumber(new UnsignedLong(number.toString().substring(3)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 18)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " triliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 19))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 1))) + " triliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 19)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " triliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 20))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 2))) + " triliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(2)));
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0 && dividable(number, new UnsignedLong(appendMultiple("1", "0", 20)))) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " triliono";
		} else if (number.compareTo(new UnsignedLong(appendMultiple("1", "0", 21))) < 0) {
			return getEsperantoNumber(new UnsignedLong(number.toString().substring(0, 3))) + " triliono " + getEsperantoNumber(new UnsignedLong(number.toString().substring(3)));
		} else {
			return null;
		}
	}
	
	public static String getVampirschwesternNumber(UnsignedLong number) {
		if (number.equals(new UnsignedLong("0"))) {
			return null;
		} else if (number.equals(new UnsignedLong("1"))) {
			return "onu";
		} else if (number.equals(new UnsignedLong("2"))) {
			return "zoi";
		} else if (number.equals(new UnsignedLong("3"))) {
			return "trosch";
		} else if (number.equals(new UnsignedLong("4"))) {
			return "ziri";
		} else if (number.equals(new UnsignedLong("5"))) {
			return "fom";
		} else if (number.equals(new UnsignedLong("6"))) {
			return "shoist";
		} else if (number.equals(new UnsignedLong("7"))) {
			return "syto";
		} else if (number.equals(new UnsignedLong("8"))) {
			return "omtje";
		} else if (number.equals(new UnsignedLong("9"))) {
			return "nonce";
		} else if (number.equals(new UnsignedLong("10"))) {
			return "dong";
		} else if (number.compareTo(new UnsignedLong("20")) < 0) {
			return "dong-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("20"))) {
			return "zoing";
		} else if (number.compareTo(new UnsignedLong("30")) < 0) {
			return "zoing-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("30"))) {
			return "trogda";
		} else if (number.compareTo(new UnsignedLong("40")) < 0) {
			return "trogda-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("40"))) {
			return "zirick";
		} else if (number.compareTo(new UnsignedLong("50")) < 0) {
			return "zirick-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("50"))) {
			return "fomsiat";
		} else if (number.compareTo(new UnsignedLong("60")) < 0) {
			return "fomsiat-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("60"))) {
			return "shoszik";
		} else if (number.compareTo(new UnsignedLong("70")) < 0) {
			return "shoszik-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("70"))) {
			return "sytenta";
		} else if (number.compareTo(new UnsignedLong("80")) < 0) {
			return "sytenta-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("80"))) {
			return "omsiat";
		} else if (number.compareTo(new UnsignedLong("90")) < 0) {
			return "omsiat-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("90"))) {
			return "noncenta";
		} else if (number.compareTo(new UnsignedLong("100")) < 0) {
			return "noncenta-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else if (number.equals(new UnsignedLong("100"))) {
			return "bong";
		} else if (number.compareTo(new UnsignedLong("200")) < 0) {
			return "bong-" + getVampirschwesternNumber(new UnsignedLong(number.toString().substring(1)));
		} else {
			return null;
		}
	}
	
	public static String getBinaryNumber(UnsignedLong number) {
		String x = "";
		if (number.equals(new UnsignedLong(0)))
			return "0";
		while (number.compareTo(new UnsignedLong(0)) > 0) { // number > 0
			UnsignedLong a = mod(number, new UnsignedLong("2"));
			x = a.toString() + x;
			number = floorDiv(number, new UnsignedLong("2"));
		}
		return x;
	}
	
	private static UnsignedLong increase(UnsignedLong number) {
		return add(number, new UnsignedLong("1"));
	}
	
	private static UnsignedLong add(UnsignedLong number, UnsignedLong toAdd) {
		return new UnsignedLong(new BigInteger(number.toString()).add(new BigInteger(toAdd.toString())).toString());
	}
	
	private static UnsignedLong subtract(UnsignedLong number, UnsignedLong toSubtract) {
		return new UnsignedLong(new BigInteger(number.toString()).subtract(new BigInteger(toSubtract.toString())).toString());
	}
	
	private static UnsignedLong multiply(UnsignedLong number, UnsignedLong multiplicator) {
		return new UnsignedLong(new BigInteger(number.toString()).multiply(new BigInteger(multiplicator.toString())).toString());
	}
	
	private static UnsignedLong floorDiv(UnsignedLong number, UnsignedLong divider) {
		return new UnsignedLong(new BigInteger(number.toString()).divide(new BigInteger(divider.toString())).toString());
	}
	
	private static UnsignedLong mod(UnsignedLong number, UnsignedLong divider) {
		return new UnsignedLong(new BigInteger(number.toString()).mod(new BigInteger(divider.toString())));
	}
	
	private static boolean dividable(UnsignedLong number, UnsignedLong divider) {
		return new BigInteger(number.toString()).mod(new BigInteger(divider.toString())).equals(new BigInteger("0"));
	}
	
	private static String appendMultiple(String str, String toAppend, int count) {
		String result = str;
		for (int i = 0; i < count; i++) {
			result += toAppend;
		}
		return result;
	}
}
