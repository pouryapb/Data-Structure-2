package pouryapb;

import java.util.HashMap;
import java.util.Map;

public class Expression {

	protected static final Map<String, Double> variables = new HashMap<>();
	protected static final Map<String[], Value> functions = new HashMap<>();

	class Value {
		String[] vars;
		String expression;

		Value(String[] vars, String expression) {
			this.vars = vars;
			this.expression = expression;
		}
	}

	public void defineVariable(String variable, Double value) {

		variables.put(variable, value);
	}

	public void defineFunction(String functionName, String variablesCount, String[] vars, String expression) {

		functions.put(new String[] { functionName, variablesCount }, new Value(vars, expression));
	}

	public String with(String[] functionKey, String... variables) {

		if (functions.get(functionKey) == null)
			return null;
		if (Integer.parseInt(functionKey[1]) != variables.length)
			return null;

		String exp = functions.get(functionKey).expression;
		String[] vars = functions.get(functionKey).vars;

		for (var i = 0; i < vars.length; i++) {

			exp = exp.replaceAll(vars[i], variables[i]);
		}

		return exp;
	}

	public static double evaluate(String str) {

		return new Object() {
			int pos = -1;
			int ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ')
					nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				nextChar();
				double x = parseExpression();
				if (pos < str.length())
					throw new RuntimeException("Unexpected: " + (char) ch);
				return x;
			}

			// Grammar:
			// expression = term | expression `+` term | expression `-` term
			// term = factor | term `*` factor | term `/` factor
			// factor = `+` factor | `-` factor | `(` expression `)`
			// | number | functionName factor | factor `^` factor

			double parseExpression() {
				double x = parseTerm();
				while (true) {
					if (eat('+'))
						x += parseTerm(); // addition
					else if (eat('-'))
						x -= parseTerm(); // subtraction
					else
						return x;
				}
			}

			double parseTerm() {
				double x = parseFactor();
				while (true) {
					if (eat('*'))
						x *= parseFactor(); // multiplication
					else if (eat('/'))
						x /= parseFactor(); // division
					else
						return x;
				}
			}

			double parseFactor() {
				if (eat('+'))
					return parseFactor(); // unary plus
				if (eat('-'))
					return -parseFactor(); // unary minus

				double x;
				int startPos = this.pos;

				if (eat('(')) { // parentheses
					x = parseExpression();
					eat(')');
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
					while ((ch >= '0' && ch <= '9') || ch == '.')
						nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { // functions
					while (ch >= 'a' && ch <= 'z')
						nextChar();
					var func = str.substring(startPos, this.pos);
					x = parseFactor();

					if (func.equals("sqrt"))
						x = Math.sqrt(x);
					else if (func.equals("sin"))
						x = Math.sin(Math.toRadians(x));
					else if (func.equals("cos"))
						x = Math.cos(Math.toRadians(x));
					else if (func.equals("tan"))
						x = Math.tan(Math.toRadians(x));
					else
						throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char) ch);
				}

				if (eat('^'))
					x = Math.pow(x, parseFactor()); // exponentiation

				return x;
			}
		}.parse();
	}
}
