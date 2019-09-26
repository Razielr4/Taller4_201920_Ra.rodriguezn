package model.data_structures;

public interface IMaxCP <Key extends Comparable<Key>>{
	
	int size();
	void insert(Key key);
	Key delMax();
	Key max();
	boolean isEmpty();
	
}
