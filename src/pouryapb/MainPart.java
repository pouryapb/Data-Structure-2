package pouryapb;

import java.util.Iterator;
import java.util.regex.Pattern;

public class MainPart {

	private static Expression expressions = new Expression();

	static void define(String str) {

		var p1 = Pattern.compile("[a-zA-Z]+\\s+=\\s+");
		var p2 = Pattern.compile("[a-zA-Z]+[(][a-zA-Z]+(,[a-zA-Z]+)*[)]\\s+=\\s+");
		var m1 = p1.matcher(str);
		var m2 = p2.matcher(str);

		if (m1.find()) {
			// a variable
			int pos = str.indexOf("=");
			int ch;
			ch = str.charAt(++pos);

			while (ch == ' ') {
				pos++;
				ch = str.charAt(pos);
			}

			expressions.defineVariable(m1.group(0).substring(0, m1.group(0).indexOf(' ')),
					Double.valueOf(str.substring(pos, str.length())));
		} else if (m2.find()) {
			// a function
			int pos = str.indexOf("=");
			int ch;
			ch = str.charAt(++pos);

			while (ch == ' ') {
				pos++;
				ch = str.charAt(pos);
			}

			var count = 0;

			for (var i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ')')
					break;
				if (str.charAt(i) == ',')
					count++;

			}

			expressions.defineFunction(str.substring(0, str.indexOf('(')), String.valueOf(++count),
					(str.substring(str.indexOf('(') + 1, str.indexOf(')')).split(",")),
					str.substring(pos, str.length()));
		} else {
			System.out.println(">>err1 : wrong exp");
		}
	}

	public double print(String str, String[] args) {

		var p = Pattern.compile("[a-zA-Z]+[(][\\d+(.\\d+){0,1}]+(,[\\d+(.\\d+){0,1}]+)*[)]\\s+=\\s+");
		var m = p.matcher(str);

		if (m.find()) {

			Iterator<String[]> it = expressions.functions.keySet().iterator();
			while (it.hasNext()) {
				// String s = it.next()[0];
				// BORIDAM...
			}
		}

		return -1d;
	}

	// public static void main(String[] args) {
	// Pattern p = Pattern.compile("[(][a-zA-Z]+(,[a-zA-Z]+)*[)]");
	// Matcher m = p.matcher("(ag,b,pi,)");
	// m.find();
	// System.out.println(m.group(0));
	// }
}
