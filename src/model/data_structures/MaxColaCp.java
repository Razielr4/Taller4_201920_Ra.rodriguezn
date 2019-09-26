package model.data_structures;

import java.util.Iterator;

public class MaxColaCp<Key extends Comparable<Key>> implements IMaxCP<Key>,Iterable<Key> {
	
	private Node<Key> first;
	private Node<Key> last;
	private int tamano;
	
	public MaxColaCp(){
		first = null;
        last  = null;
        tamano = 0;
	}
	
	public int size() {
		return tamano;
	}

	public void insert(Key key) {
		Node<Key> add = new Node<Key>(key);
		if(isEmpty()){
			first = add;
			last = first;
			tamano ++;
			
		}else{
			if(last.darElemento().compareTo(key) >= 0) {
				last.cambiarSiguiente(add);
				last = last.darSiguiente();
				tamano++;
			}else {
				if(first.darElemento().compareTo(key) == -1) {
					add.cambiarSiguiente(first);
					first = add;
					tamano++;
				}else{
					boolean fin = false;
					Node<Key> actual = first;
				while(actual.darSiguiente() != null && !fin) {
					if(actual.darSiguiente().darElemento().compareTo(key) == -1) {
						add.cambiarSiguiente(actual.darSiguiente());
						actual.cambiarSiguiente(add);
						fin = true;
						tamano++;
					}else {
						actual = actual.darSiguiente();
					}
				}
				
			}
			
		}
			
	}
	}

	public Key delMax() {
		Key ans = first.darElemento();
		first = first.darSiguiente();
		return ans;
	}

	public Key max() {
		return first.darElemento();
	}

	public boolean isEmpty() {
		return tamano == 0;
	}

	public Iterator<Key> iterator() {
		return new iterator<Key>(first);
	}
	
	private class iterator<E> implements Iterator<E>{
		
		Node<E> actual;
		
		public iterator(Node<E> primero) {
            actual = primero;
        }

		public boolean hasNext() {
			return actual.darSiguiente() != null;
		}
		public E next() {
			if(hasNext()){
				E dar = actual.darElemento();
				actual = actual.darSiguiente();
				return dar;
			}else{
				return null;
			}
		}
		
		
	}

}
