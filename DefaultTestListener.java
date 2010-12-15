import java.lang.reflect.Method;
import java.io.PrintStream;

public class DefaultTestListener extends CountingTestListener {
	private PrintStream out;

	public DefaultTestListener(PrintStream w) {
		setOutput(w);
	}

	public void setOutput(PrintStream w) {
		out = w;
	}

	public PrintStream getOutput() {
		return out;
	}
	
	@Override
	public void beginSuite(Class<?> suite) {
		super.beginSuite(suite);
		out.println("Testsuite " + suite.getName() + ":");
	}

	private void failure(String msg, Throwable t) {
		out.println(msg + " FAILED:");
		t.printStackTrace(out);
		out.println();
	}

	private String getTestName(Method test) {
		Desc desc = test.getAnnotation(Desc.class);
		if (desc != null) {
			return desc.value();
		} else {
			return test.getName();
		}
	}

	@Override
	public void testExcecuted(Method test, Throwable t) {
		super.testExcecuted(test, t);
		out.print(" - ");
		if (t == null) {
			out.println(getTestName(test) + " OK");
		} else {
			failure(test.getName(), t);
		}
	}

	@Override
	public void instantiationFailed(Class<?> suite, Throwable t) {
		super.instantiationFailed(suite, t);
		failure("Instantiation of " + suite.getDeclaringClass(), t);
	}

	@Override
	public void beforeFailed(Method test, Throwable t) {
		super.beforeFailed(test, t);
		failure("@Before " + test.getDeclaringClass().getName()+"."+test.getName(), t);
	}

	@Override
	public void afterFailed(Method test, Throwable t) {
		super.afterFailed(test, t);
		failure("@After " + test.getDeclaringClass().getName()+"."+test.getName(), t);
	}

	@Override
	public void endSuite(Class<?> suite) {
		if (isSuccess()) {
			out.print("Suite succeeded: ");
		} else {
			out.print("SUITE FAILED: ");
		}
		out.println(getTestsRun() + " tests run, " + getFailed() + " failures, " + getErrors() + " errors.");
		out.println();
		super.endSuite(suite);
	}

	@Override
	public void endTestRun() {
		super.endTestRun();
		if (isTotalSuccess()) {
			out.print("Success: ");
		} else {
			out.print("FAILURE: ");
		}
		out.println(getTotalTestsRun() + " tests run, " + getTotalFailed() + " failures, " + getTotalErrors() + " errors.");
	}
}
