import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
	private TestListener listener;

	public TestRunner(TestListener listener) {
		setListener(listener);
	}

	public void setListener(TestListener listener) {
		this.listener = listener;
	}

	public TestListener getListener() {
		return listener;
	}

	/* calls beginTestRun, runSuite() for all suites, and then endTestRun */
	public void runSuites(Class<?>... suites) {
		listener.beginTestRun();
		for (Class<?> suite : suites) {
			runSuite(suite);
		}
		listener.endTestRun();
	}

	/* runs a suite and calls listener methods as specified in TestListener */
	public void runSuite(Class<?> suite) {
		listener.beginSuite(suite);

		Object obj;
		try {
			obj = suite.newInstance();
		} catch (Throwable t) {
			listener.instantiationFailed(suite, t);
			return;
		}

		for (Method test : suite.getMethods()) {
			Tst annotation = test.getAnnotation(Tst.class);
			if (annotation == null) {
				continue;
			}

			for (Method m : suite.getMethods()) {
				if (m.isAnnotationPresent(Before.class)) {
					try {
						m.invoke(obj);
					} catch (InvocationTargetException ex) {
						listener.beforeFailed(m, ex.getCause());
						return;
					} catch (Throwable t) {
						listener.beforeFailed(m, t);
						return;
					}
				}
			}

			try {
				test.invoke(obj);
				if (annotation.expected() == Tst.NoExpectedException.class) {
					listener.testExcecuted(test, null);
				} else {
					throw new AssertionError(
							"Did not catch expected exception: "
							+ annotation.expected().getName());
				}
			} catch (InvocationTargetException ex) {
				Throwable t = ex.getCause();
				if (annotation.expected().isInstance(t)) {
					listener.testExcecuted(test, null);
				} else {
					listener.testExcecuted(test, t);
				}
			} catch (Throwable t) {
				listener.testExcecuted(test, t);
			}

			for (Method m : suite.getMethods()) {
				if (m.isAnnotationPresent(After.class)) {
					try {
						m.invoke(obj);
					} catch (InvocationTargetException ex) {
						listener.afterFailed(m, ex.getCause());
						return;
					} catch (Throwable t) {
						listener.afterFailed(m, t);
						return;
					}
				}
			}
		}

		listener.endSuite(suite);
	}

	/* runs the suites, prints info to System.out, and exists with status 1 if
	 * there's any problem */
	public static void main(Class<?>... suites) {
		DefaultTestListener listener = new DefaultTestListener(System.out);
		new TestRunner(listener).runSuites(suites);
		
		if (!listener.isTotalSuccess()) {
			System.exit(1);
		}
	}

	/* runs the suites given as classnames in args, prints info to System.out,
	 * and exists with status 1 if there's any problem */
	public static void main(String[] args) {
		List<Class<?>> suites = new ArrayList<Class<?>>();
		for (String className : args) {
			try {
				suites.add(Class.forName(className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		main(suites.toArray(new Class<?>[0]));
	}
}
