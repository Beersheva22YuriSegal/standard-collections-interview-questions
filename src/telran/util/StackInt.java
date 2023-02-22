package telran.util;

import java.util.LinkedList;

public class StackInt {
	LinkedList<Integer> list = new LinkedList<>();
	LinkedList<Integer> stackMax = new LinkedList<>();

	public void push(int num) {
		list.add(num);
		if (stackMax.isEmpty() || num > stackMax.getLast()) {
			stackMax.add(num);
		}
	}

	public int pop() {
		Integer res = list.removeLast();
		if (res.equals(stackMax.getLast())) {
			stackMax.removeLast();
		}
		return res;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int getMax() {
		return stackMax.getLast();
	}
}