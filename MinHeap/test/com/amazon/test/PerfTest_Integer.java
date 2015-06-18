package com.amazon.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.amazon.MinHeap;
import com.amazon.test.helper.TestHelper;

public class PerfTest_Integer {

	private final int _20MILLION = 20000000;
	
	@Test
	public void test20millionConsecutiveAdds() {
		
		Random rand = new Random();
		int value = rand.nextInt();
		int min = value;
		
		MinHeap<Integer> mh = new MinHeap<Integer>();
		for (int i = 0; i < _20MILLION; i++) {
			mh.add(value);
			value = rand.nextInt();
			if (value < min) 
				min = value;
		}

		assertTrue(TestHelper.getMinHeapSize(mh) == _20MILLION);
		assertTrue(mh.pop().equals(min));
	}
	
	@Test
	public void test20millionAddsAndPops() {
		Random rand = new Random();
		int value = rand.nextInt();
		int min = value;
		
		MinHeap<Integer> mh = new MinHeap<Integer>();
		for (int i = 0; i < _20MILLION; i++) {
			mh.add(value);
			value = rand.nextInt();
			if (value < min) 
				min = value;
		}
		
		for (int i = 0; i < _20MILLION; i++) {
			mh.pop();
		}
		
		assertTrue(TestHelper.getMinHeapSize(mh) == 0);
		assertNull(mh.pop());
	}
}
