package com.amazon.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.amazon.MinHeap;
import com.amazon.test.helper.TestHelper;

public class CorrectnessTest_String {

	String[] testArray = new String[] { "Test", "AAA", "aaa", "sxsxsx", "Leaf", "BinaryHeap" };

	/**
	 * Test if initial order is correct.
	 */
	@Test
	public void testOrder() {
		MinHeap<String> mh = new MinHeap<String>(testArray);
		assertTrue("[AAA, Leaf, BinaryHeap, sxsxsx, Test, aaa]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));
	}

	/*
	 * Three consecutive tests of peek method. All three should return same
	 * value.
	 */
	@Test
	public void testPeek() {
		MinHeap<String> mh = new MinHeap<String>(testArray);
		assertTrue(mh.peek().equals("AAA"));
		assertTrue(mh.peek().equals("AAA"));
		assertTrue(mh.peek().equals("AAA"));
		assertTrue("[AAA, Leaf, BinaryHeap, sxsxsx, Test, aaa]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));

		mh = new MinHeap<String>();
		assertNull(mh.peek());

	}

	/*
	 * Three consecutive tests of pop method.
	 */
	@Test
	public void testPop() {
		MinHeap<String> mh = new MinHeap<String>(testArray);
		assertTrue(mh.pop().equals("AAA"));
		assertFalse(mh.peek().equals("AAA"));

		assertTrue(mh.pop().equals("BinaryHeap"));
		assertFalse(mh.peek().equals("BinaryHeap"));

		assertTrue(mh.pop().equals("Leaf"));
		assertFalse(mh.peek().equals("Leaf"));
		assertTrue("[Test, sxsxsx, aaa]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));

		mh = new MinHeap<String>();
		assertNull(mh.pop());
	}

	/*
	 * Adds four new element to heap.
	 */
	@Test
	public void testAdd() {
		MinHeap<String> mh = new MinHeap<String>(testArray);
		mh.add("12");
		mh.add("_jjj");
		mh.add("#$#%%");
		mh.add("8912");
		assertTrue("[#$#%%, 12, AAA, Leaf, 8912, aaa, BinaryHeap, sxsxsx, _jjj, Test]".equals(TestHelper
				.getMinHeapArrayRepresentation(mh)));
	}

	/*
	 * Tries to delete few elements.
	 */
	@Test
	public void testDelete() {
		MinHeap<String> mh = new MinHeap<String>(testArray);
		assertTrue(mh.delete("BinaryHeap"));
		assertFalse(mh.delete("BinaryHeap"));
		assertFalse(mh.delete("NonExistant"));
		assertTrue(mh.delete("Leaf"));
		assertTrue("[AAA, aaa, Test, sxsxsx]".equals(TestHelper.getMinHeapArrayRepresentation(mh)));

		mh = new MinHeap<String>();
		assertFalse(mh.delete("Leaf"));
	}

	/*
	 * Test all methods of the BinaryHeap interface implemented by MinHeap.
	 */
	@Test
	public void testAll() {
		MinHeap<String> mh = new MinHeap<String>(testArray);
		mh.add("XXXX");
		assertTrue(mh.pop().equals("AAA"));
		assertTrue(mh.peek().equals("BinaryHeap"));

		mh.add("xzc");
		mh.add("||}");
		mh.add("*-/*+");
		mh.add("pn");
		mh.add("new test");

		assertTrue(mh.pop().equals("*-/*+"));
		assertFalse(mh.delete("}}{"));
		assertTrue(mh.delete("BinaryHeap"));
		assertFalse(mh.delete("BinaryHeap"));

		assertTrue(mh.peek().equals("Leaf"));
		assertTrue(mh.delete("Leaf"));
		assertFalse(mh.peek().equals("Leaf"));

		assertTrue("[Test, aaa, XXXX, new test, xzc, ||}, sxsxsx, pn]".equals(TestHelper
				.getMinHeapArrayRepresentation(mh)));
	}
}
