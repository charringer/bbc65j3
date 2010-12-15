import java.lang.reflect.Method;

public interface TestListener {
	/* called before any test suite is run */
	void beginTestRun();
	/* called before a suite is run */
	void beginSuite(Class<?> suite);
	/* called after a test is executed
	 * t is null if the test was successful,
	 * otherwise t contains the exception */
	void testExcecuted(Method m, Throwable t);
	/* called if a suite cannot be instantiated (called after beginTestRun) */
	void instantiationFailed(Class<?> suite, Throwable t);
	/* called if a @Before method could not be invoked.
	 * testExecuted will not be called, endSuite will be called immediately
	 * after this method. */
	void beforeFailed(Method before, Throwable t);
	/* called if a @Before method could not be invoked.
	 * testExecuted was already called, endSuite will be called immediately
	 * after this method. */
	void afterFailed(Method after, Throwable t);
	/* called after a suite is run (all testExecuted calls have been done
	 * before) */
	void endSuite(Class<?> suite);
	/* called after all test suites are run */
	void endTestRun();
}
