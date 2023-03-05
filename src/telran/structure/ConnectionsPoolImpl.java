package telran.structure;

import java.util.*;

public class ConnectionsPoolImpl implements ConnectionsPool{
	public static class Node {
		
		public Connection connection;
		Node next;
		Node prev;
		 Node(Connection connection) {
		
			this.connection = connection;
		}
	}
	public static class ListConnections {
		public Node head;
		public Node tail;
		 void addHead(Node nodeConnection) {
			nodeConnection.next = head;
			if (head != null) {
				head.prev = nodeConnection;
			} else {
				tail = nodeConnection;
			}
			head = nodeConnection;
			
		}
		void removeTail() {
			tail = tail.prev;
			if (tail != null) {
				tail.next = null;
			}
			
		}
		public void removeNode(Node nodeConnection) {
			if (nodeConnection == tail) {
				removeTail();
			} else {
				Node before = nodeConnection.prev;
				Node after = nodeConnection.next;
				before.next = after;
				after.prev = before;
			}
			
		}
	}
	HashMap<Integer, Node> connectionsMap = new HashMap<>();
	int connectionsPoolLimit;
	public ListConnections listConnections = new ListConnections();

	public ConnectionsPoolImpl(int connectionsPoolLimit) {
		this.connectionsPoolLimit = connectionsPoolLimit;
	}

	@Override
	public boolean addConnection(Connection connection) {
		if (connectionsMap.containsKey(connection.getId())) {
			return false;
		}
		Node nodeConnection = new Node(connection);
		addMapList(connection, nodeConnection);
		if (connectionsMap.size() > connectionsPoolLimit) {
			removeListTailMap();
		}
		
		return true;
	}

	private void removeListTailMap() {
		connectionsMap.remove(listConnections.tail.connection.getId());
		listConnections.removeTail();
	}

	private void addMapList(Connection connection, Node nodeConnection) {
		connectionsMap.put(connection.getId(), nodeConnection);
		listConnections.addHead(nodeConnection);
	}

	@Override
	public Connection getConnection(int id) {
		Node nodeConnection = connectionsMap.get(id);
		if (nodeConnection == null) {
			return null;
		}
		moveConnectionToHead(nodeConnection);
		return nodeConnection.connection;
	}

	private void moveConnectionToHead(Node nodeConnection) {
		if (nodeConnection != listConnections.head) {
		listConnections.removeNode(nodeConnection);
		listConnections.addHead(nodeConnection);
		}
		
	}

}