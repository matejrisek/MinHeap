package com.amazon.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.amazon.MinHeap;
import com.amazon.test.helper.TestHelper;

public class CorrectnessTest_Integer {
	
	Integer[] testArray = new Integer[] { 3, 7, 8, 9, 5, 1 };
	
	/**
	 * Test if initial order is correct.
	 */
	@Test
	public void testOrder() {
		MinHeap<Integer> mh = new MinHeap<Integer>(testArray);
		assertTrue("[1, 5, 3, 9, 7, 8]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));
	}

	/*
	 * Three consecutive tests of peek method. All three should return same
	 * value.
	 */
	@Test
	public void testPeek() {
		MinHeap<Integer> mh = new MinHeap<Integer>(testArray);
		assertTrue(mh.peek().equals(1));
		assertTrue(mh.peek().equals(1));
		assertTrue(mh.peek().equals(1));
		assertTrue("[1, 5, 3, 9, 7, 8]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));
		
		mh = new MinHeap<Integer>();
		assertNull(mh.peek());

	}

	/*
	 * Three consecutive tests of pop method.
	 */
	@Test
	public void testPop() {
		MinHeap<Integer> mh = new MinHeap<Integer>(testArray);
		assertTrue(mh.pop().equals(1));
		assertFalse(mh.peek().equals(1));

		assertTrue(mh.pop().equals(3));
		assertFalse(mh.peek().equals(3));

		assertTrue(mh.pop().equals(5));
		assertFalse(mh.peek().equals(5));
		assertTrue("[7, 9, 8]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));

		mh = new MinHeap<Integer>();
		assertNull(mh.pop());
	}

	/*
	 *	Adds four new element to heap.
	 */
	@Test
	public void testAdd() {
		MinHeap<Integer> mh = new MinHeap<Integer>(testArray);
		mh.add(1);
		mh.add(-1);
		mh.add(-3);
		mh.add(76);
		assertTrue("[-3, -1, 1, 1, 7, 8, 3, 9, 5, 76]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));
	}
	
	/*
	 * Tries to delete few elements.
	 */
	@Test
	public void testDelete() {
		MinHeap<Integer> mh = new MinHeap<Integer>(testArray);
		assertTrue(mh.delete(5));
		assertFalse(mh.delete(5));
		assertFalse(mh.delete(4));
		assertTrue(mh.delete(8));
		assertTrue("[1, 3, 9, 7]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));
		
		mh = new MinHeap<Integer>();
		assertFalse(mh.delete(1));
	}
	
	/*
	 * Test all methods of the BinaryHeap interface implemented by MinHeap.
	 */
	@Test
	public void testAll() {
		MinHeap<Integer> mh = new MinHeap<Integer>(testArray);
		mh.add(-16);
		assertTrue(mh.pop().equals(-16));
		assertTrue(mh.peek().equals(1));
		
		mh.add(45);
		mh.add(19);
		mh.add(7);
		mh.add(-1);
		mh.add(-2);
		
		assertTrue(mh.pop().equals(-2));
		assertFalse(mh.delete(15));
		assertTrue(mh.delete(-1));
		assertFalse(mh.delete(-1));
		
		assertTrue(mh.peek().equals(1));
		assertTrue(mh.delete(1));
		assertFalse(mh.peek().equals(1));
		
		assertTrue("[3, 7, 5, 7, 45, 19, 9, 8]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));
	}
}
