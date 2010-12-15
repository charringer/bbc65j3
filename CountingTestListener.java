import java.lang.reflect.Method;

public class CountingTestListener implements TestListener {
	private int failed = 0, finished = 0, errors = 0;
	private int totalFailed = 0, totalFinished, totalErrors = 0;

	/* number of exectuted tests (whether they failed or not) for the current
	 * suite */
	public int getTestsRun() {
		return failed + finished;
	}

	/* number of failed tests for the current suite */
	public int getFailed() {
		return failed;
	}

	/* number of successfully finished tests for the current suite */
	public int getFinished() {
		return finished;
	}

	/* number of errors for the current suite */
	public int getErrors() {
		return errors;
	}

	/* false iff there have been errors and/or failed tests in the current
	 * suite */
	public boolean isSuccess() {
		return failed + errors == 0;
	}

	/* number of all executed tests that have been run */
	public int getTotalTestsRun() {
		return totalFailed + totalFinished;
	}

	/* number of all failed tests that have been run */
	public int getTotalFailed() {
		return totalFailed;
	}

	/* number of all successfully finished tests that have been run */
	public int getTotalFinished() {
		return totalFinished;
	}

	/* number of all errors that have happened */
	public int getTotalErrors() {
		return totalErrors;
	}

	/* false iff there have been errors and/or failed tests */
	public boolean isTotalSuccess() {
		return totalFailed + totalErrors == 0;
	}

	@Override
	public void beginTestRun() {}
	
	@Override
	public void beginSuite(Class<?> suite) {}

	/* increments finished and totalFinished if t == null
	 * otherwise increments failed and totalFailed */
	@Override
	public void testExcecuted(Method test, Throwable t) {
		if (t == null) {
			finished++; totalFinished++;
		} else {
			failed++; totalFailed++;
		}
	}

	/* increments errors and totalErrors */
	@Override
	public void instantiationFailed(Class<?> suite, Throwable t) {
		errors++; totalErrors++;
	}

	/* increments errors and totalErrors */
	@Override
	public void beforeFailed(Method before, Throwable t) {
		errors++; totalErrors++;
	}

	/* increments errors and totalErrors */
	@Override
	public void afterFailed(Method after, Throwable t) {
		errors++; totalErrors++;
	}

	/* resets failed, finished and errors to 0 */
	@Override
	public void endSuite(Class<?> suite) {
		failed = 0; finished = 0; errors = 0;
	}

	@Override
	public void endTestRun() {}

}
