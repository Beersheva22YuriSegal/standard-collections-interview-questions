package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

import java.util.*;
import java.util.stream.Collectors;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);

		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);

	}

	@Test
	void displayOccurrencesCount() {
		String[] strings = { "lmn", "abc", "abc", "lmn", "a", "lmn" };
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));

	}

	@Test
	void displayDigitStatistics() {
		new Random().ints(1, Integer.MAX_VALUE).limit(1000000)
		.mapToObj(n -> Integer.toString(n))
		.flatMap(n -> n.chars().boxed())
		.collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
		.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
		.forEach(e -> System.out.printf("%c: %d\n", e.getKey(), e.getValue()));
	}

	@Test
	void stackTest() {
		StackInt stack = new StackInt();
		assertTrue(stack.isEmpty());
		assertThrowsExactly(NoSuchElementException.class, () -> stack.getMax());
		stack.push(6);
		assertFalse(stack.isEmpty());
		stack.push(3);
		stack.push(7);
		stack.push(2);
		assertEquals(7, stack.getMax());
		assertEquals(2, stack.pop());
		assertEquals(7, stack.pop());
		assertEquals(6, stack.getMax());
	}

}