package test;

import hello.Range;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static junit.framework.TestCase.*;
import static org.junit.Assert.fail;

public class RangeTest {
    // START HERE: Range(0) should contain no values, [ ]
    @Test
    public void RangeTestZero() {
        Iterable<Integer> i = new Range(0);
        Iterator<Integer> rangeIterator = i.iterator();

        assertFalse(rangeIterator.hasNext());
    }

    // Range(5) should contain five values, [0, 1, 2, 3, 4]
    @Test
    public void RangeTestFiveWhile() {
        Iterable<Integer> i = new Range(5);
        Iterator<Integer> rangeIterator = i.iterator();

        List<Integer> expected = new ArrayList<>(Arrays.asList(
                0, 1, 2, 3, 4
        ));
        List<Integer> output = new ArrayList<>();
        while (rangeIterator.hasNext()) {
            Integer value = rangeIterator.next();
            output.add(value);
        }

        assertEquals(expected, output);
    }

    // The For-Each loop is a shortcut for using the iterator()
    // without having to save it in a variable, or man
    /*
        for (Integer value : i) { ... }
    */

    // is equivalent to
    /*
        Iterator<Integer> temp = i.iterator();
        while (temp.hasNext()) {
          Integer value = temp.next();
          ...
        }
     */
    @Test
    public void RangeTestZeroForEach() {
        Iterable<Integer> i = new Range(0);
        int count = 0;
        for (Integer value : i) {
            count++;
        }

        assertEquals(0, count);

        for (Integer value : i) {
            count++;
        }

        assertEquals(0, count);
    }

    // Range(1) should contain exactly one value, [0]
    @Test
    public void RangeTestOne() {
        Iterable<Integer> i = new Range(1);
        int count = 0;
        int current = 0;
        for (Integer value : i) {
            count++;
            assertEquals(Integer.valueOf(current++), value);
        }

        assertEquals(1, count);
        current = 0;
        for (Integer value : i) {
            count++;
            assertEquals(Integer.valueOf(current++), value);
        }

        assertEquals(2, count);
    }

    // Range(5) should contain five values, [0, 1, 2, 3, 4]
    @Test
    public void RangeTestFive() {
        Iterable<Integer> i = new Range(5);
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> output = new ArrayList<>();
        for (Integer value : i) {
            output.add(value);
        }

        assertEquals(expected, output);

        // Range should be re-usable
        output = new ArrayList<>();
        for (Integer value : i) {
            output.add(value);
        }
        assertEquals(expected, output);
    }

    // RangeIterator should throw an exception when empty
    // and .next() is called again
    @Test
    public void RangeIteratorThrowsExceptionEmpty() {
        Iterable<Integer> i = new Range(0);
        Iterator<Integer> rangeIterator = i.iterator();

        assertFalse(rangeIterator.hasNext());
        try {
            rangeIterator.next();
            fail(); // line should not be reached
        } catch (NoSuchElementException e) { };

        i = new Range(5);
        rangeIterator = i.iterator();

        assertTrue(rangeIterator.hasNext());
        while (rangeIterator.hasNext()) {
            rangeIterator.next();
        }

        assertFalse(rangeIterator.hasNext());
        try {
            rangeIterator.next();
            fail(); // line should not be reached
        } catch (NoSuchElementException e) { };
    }

    // RangeIterator "implements" remove()
    // by throwing an UnsupportedOperationException
    // to indicate at *runtime* that it should not be used
    @Test
    public void RangeIteratorDoesNotImplementRemove() {
        Iterable<Integer> i = new Range(0);
        Iterator<Integer> rangeIterator = i.iterator();

        try {
            rangeIterator.remove();
            fail(); // line should not be reached
        } catch (UnsupportedOperationException e) { };
    }

    // NOT A TEST: helper function validates a range and the output
    public void validateRange(Range range, List<Integer> expected) {
        Iterable<Integer> i = range;
        Iterator<Integer> rangeIterator = i.iterator();

        List<Integer> output = new ArrayList<>();
        while (rangeIterator.hasNext()) {
            Integer next = rangeIterator.next();
            output.add(next);
        }

        try {
            rangeIterator.next();
            fail(); // this line should not be reached
        } catch (NoSuchElementException e) { };

        assertFalse(rangeIterator.hasNext());
        assertEquals(expected, output);

        // Range should be reusable,
        // iterators should be independent
        output = new ArrayList<>();
        rangeIterator = i.iterator();
        Iterator<Integer> secondIterator = i.iterator();

        while (rangeIterator.hasNext()) {
            assertTrue(secondIterator.hasNext());

            Integer first = rangeIterator.next();
            Integer second = secondIterator.next();
            assertEquals(first, second);

            output.add(second);
        }

        // Both iterators should now be exhausted
        assertFalse(secondIterator.hasNext());
        try {
            rangeIterator.next();
            fail();
        } catch (NoSuchElementException e) { };
        try {
            secondIterator.next();
            fail();
        } catch (NoSuchElementException e) { };

        assertEquals(expected, output);

        // Range should behave the same with a for-each loop
        output = new ArrayList<>();
        for (Integer value : range) {
            output.add(value);
        }

        assertEquals(expected, output);

        // ...and the Range should also be re-usable
        output = new ArrayList<>();
        for (Integer value : range) {
            output.add(value);
        }

        assertEquals(expected, output);
    }

    @Test
    public void RangeTestSix() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5);
        validateRange(new Range(6), expected);
    }

    @Test
    public void RangeTestInterval() {
        List<Integer> expected = Arrays.asList(5, 6, 7, 8, 9);
        validateRange(new Range(5, 10), expected);

        expected = Arrays.asList(-5, -4, -3, -2, -1);
        validateRange(new Range(-5, 0), expected);

        expected = Arrays.asList(-10, -9, -8, -7, -6);
        validateRange(new Range(-10, -5), expected);

        expected = Collections.emptyList();
        validateRange(new Range(0, 0), expected);
        validateRange(new Range(5, 5), expected);
        validateRange(new Range(-20, -20), expected);

        // Default step is always 1
        // Range will not go backwards in this case
        // as the step is unspecified
        validateRange(new Range(5, 0), expected);
    }

    @Test
    public void RangeTestStep() {
        List<Integer> expected = Arrays.asList(0, 5, 10, 15, 20);
        validateRange(new Range(0, 25, 5), expected);
        validateRange(new Range(0, 24, 5), expected);
        validateRange(new Range(0, 23, 5), expected);
        validateRange(new Range(0, 22, 5), expected);
        validateRange(new Range(0, 21, 5), expected);

        expected = Arrays.asList(-100, -99, -98, -97, -96);
        validateRange(new Range(-100, -95, 1), expected);

        expected = new ArrayList<>();
        for (int i = -100; i < 100; i += 10) {
            expected.add(i);
        }
        validateRange(new Range(-100, 100, 10), expected);
    }

    @Test
    public void RangeTestStepBackwards() {
        List<Integer> expected = Arrays.asList(50, 49, 48, 47, 46);
        validateRange(new Range(50, 45, -1), expected);

        expected = Arrays.asList(0, -5, -10, -15, -20);
        validateRange(new Range(0, -25, -5), expected);
        validateRange(new Range(0, -24, -5), expected);
        validateRange(new Range(0, -23, -5), expected);
        validateRange(new Range(0, -22, -5), expected);
        validateRange(new Range(0, -21, -5), expected);

        expected = new ArrayList<>();
        for (int i = 100; i > -100; i -= 25) {
            expected.add(i);
        }
        validateRange(new Range(100, -100, -25), expected);
    }
}
