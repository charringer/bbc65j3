import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/* annotates a method that will be run as a testcase by TestRunner */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tst {
	Class<? extends Throwable> expected() default NoExpectedException.class;

	public static final class NoExpectedException extends Throwable {
		private static final long serialVersionUID = 1L;

		private NoExpectedException() {}
	}
}
