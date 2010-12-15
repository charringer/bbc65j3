
public class Assert {
	/* throws an exception if expected is not equal to actual */
	public static void assertEquals(String message, Object expected, Object actual) {
		if ((expected == null && actual != null) || (expected != null && !expected.equals(actual))) {
			throw new ComparisonFailure(message, String.valueOf(expected), String.valueOf(actual));
		}
	}

	/* throws an exception if expected is not equal to actual */
	public static void assertEquals(Object expected, Object actual) {
		assertEquals(null, expected, actual);
	}

	/* throws an exception if bool is not true */
	public static void assertTrue(String message, boolean bool) {
		assertEquals(message, true, bool);
	}

	/* throws an exception if bool is not true */
	public static void assertTrue(boolean bool) {
		assertTrue(null, bool);
	}

	/* throws an exception if bool is not false */
	public static void assertFalse(String message, boolean bool) {
		assertEquals(message, false, bool);
	}

	/* throws an exception if bool is not false */
	public static void assertFalse(boolean bool) {
		assertFalse(null, bool);
	}
}
