package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface
interface IPredicate<T> {
	boolean test(T t);
	
	default IPredicate<T> and(IPredicate<? super T> other) {
		Objects.requireNonNull(other);
		return (t) -> test(t) && other.test(t);
	}
}
public class PredicateExample {

	public static void main(String[] args) {
		Predicate<String> p1 = s -> s.length() < 20;
		Predicate<String> p2 = s -> s.length() > 10;
		
		Predicate<String> p3 = p1.and(p2);
		List<String> result = Arrays.asList("one","Two");
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = System.out::println;
		
		result.forEach(c1);
	}

}
