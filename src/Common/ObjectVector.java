package Common;

public class ObjectVector<E> extends java.util.Vector<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3294515943655742333L;

	public ObjectVector() {
		super();
	}

	public void Reserve(int s) {
		ensureCapacity(s);
	}

	public E Back() {
		return get(elementCount - 1);
	}

	public E Front() {
		return get(0);
	}

	public void DeleteBack() {
		remove(elementCount - 1);
	}
}
