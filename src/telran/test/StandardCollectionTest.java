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
	void SubListTest() {
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
	
	@Test
	void maxNumberWithNegativeImageTest() {
		int ar[] = {10000000, 3, -2, -200, 200, -3, 2};
		int ar1[] = {1000000, -1000000000, 3, -4};
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
	}
	
	int maxNumberWithNegativeImage(int[] array) {
		//return maximal positive number having it negative image or -1 if none such numbers
		
		HashSet<Integer> set = new HashSet<>();
		int res = -1;
		for(int num:array) {
			set.add(num);
			if(set.contains(-num) && Math.abs(num) > res) {
				res = num;
			}
		}
		return res;
	}
	@Test
	void treeIteratingTest() {
		int array[] = {1, 11, 111, 32, 9, 1234, 99, 992};
		createAndIterateTreeInOrder(array);
	}

	private void createAndIterateTreeInOrder(int[] array) {
		//create tree, add in tree numbers from a given array
		//and iterate in the order of array defined in 93
		
		TreeSet<Integer> tree = new TreeSet<>((m, n) -> Integer.compare(sumOfDigits(m), sumOfDigits(n)));
		for (int num : array) {
			tree.add(num);
		}
	}

	private int sumOfDigits(int num) {
		 int res = 0;
		 while (num != 0) {
			 res += num % 10;
			 num /= 10;
		 }
		 return res;
	}
}