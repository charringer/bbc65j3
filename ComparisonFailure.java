
public class ComparisonFailure extends AssertionError {
	private static final long serialVersionUID = 1L;
	private String message, expected, actual;

	public ComparisonFailure(String message, String expected, String actual) {
		this.message = message;
		this.expected = expected;
		this.actual = actual;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		if (message != null) {
			sb.append(message).append(": ");
		}
		sb.append("expected ").append(escape(getExpected()))
			.append(", got ").append(escape(getActual()));
		return sb.toString();
	}

	/* acts as the identity if s !~ /[ \n\t]/
	 * otherwise escapes whitespace and adds quotes.
	 */
	private String escape(String s) {
		if (s.isEmpty() || s.contains(" ") || s.contains("\n") || s.contains("\t")) {
			String res = s;
			res = res.replace("\n", "\\n");
			res = res.replace("\t", "\\n");
			return '"'+res+'"';
		} else {
			return s;
		}
	}

	public String getExpected() {
		return expected;
	}

	public String getActual() {
		return actual;
	}
}
