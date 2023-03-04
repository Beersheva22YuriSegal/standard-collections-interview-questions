package telran.structure;

import java.util.HashMap;
import java.util.LinkedList;

public class ConnectionsPoolImpl implements ConnectionsPool {

	private HashMap<Integer, Object> map = new HashMap<>();
	private LinkedList<Integer> list = new LinkedList<>();
	private int listMaxSize = 5;
	
	@Override
	public boolean addConnection(Connection connection) {
		boolean res = false;
		int id = connection.getId();
		if (!map.containsKey(id)) {
			if (map.size() >= listMaxSize) {
				map.remove(list.remove());
			}
			list.addLast(id);
			map.put(id, connection);
			res = true;
		}
		return res;
	}


	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Connection getConnection(int id) {
		Connection res = (Connection) map.get(id);
			list.remove(res);
			list.addLast(id);
		return res == null ? null : res;
		
	}


}
