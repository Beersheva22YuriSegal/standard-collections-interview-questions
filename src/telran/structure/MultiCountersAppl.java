package telran.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MultiCountersAppl implements MultiCounters {
	
	private TreeMap<Integer, HashSet<Object>> treeMap = new TreeMap<>();
	private HashMap<Object, Integer> hashMap = new HashMap<>();
	

	@Override
	public Integer addItem(Object item) {
		Integer res = hashMap.get(item);
		if (res == null) {
			res = 1;
		} else {
			res++;
		}
		hashMap.put(item, res);
		
		if (!treeMap.containsKey(res)) {
			treeMap.put(res, new HashSet<>());
		}
		treeMap.get(res).add(item);
		
		return res;
	}

	@Override
	public Integer getValue(Object item) {

		return hashMap.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer counter = hashMap.get(item);
		if (hashMap.containsKey(item)) {
			treeMap.get(counter).remove(item);
		}
		hashMap.remove(item);
		return counter != null;
	}

	@Override
	public Set<Object> getMaxItems() {

		return treeMap.lastEntry().getValue();
	}

}
