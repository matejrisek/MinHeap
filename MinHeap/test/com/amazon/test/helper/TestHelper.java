package com.amazon.test.helper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.amazon.MinHeap;

public class TestHelper {

	/**
	 * Gets size field through reflection.
	 * 
	 * @param mh
	 *            MinHeap object
	 * @return Value of the size field from MinHeap object.
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static int getMinHeapSize(MinHeap<?> mh) {
		Field sizeField;
		try {
			sizeField = MinHeap.class.getDeclaredField("size");
			sizeField.setAccessible(true);
			return (Integer) sizeField.get(mh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Gets String representation of array field reflection.
	 * 
	 * @param mh
	 *            MinHeap object
	 * @return String representation of array field.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public static <E> String getMinHeapArrayRepresentation(MinHeap<?> mh) {
		Field arrayField;
		List<E> l = null;
		try {
			arrayField = MinHeap.class.getDeclaredField("array");
			arrayField.setAccessible(true);
			Object array = arrayField.get(mh);
			l = new ArrayList<E>();

			for (int i = 0; i < getMinHeapSize(mh); i++) {
				l.add((E) Array.get(array, i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l.toString();
	}
}
