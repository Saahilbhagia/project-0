package hello;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
    //Fields declaration for Range class
    private int begin, end, step;

    /**
     * Default constructor
     * that initialize the end of the range object
     *  and default begin and step
     * @param end the end of the range
     */
    public Range(int end) {
        this.begin = 0; // Default
        this.step = 1; // Default
        this.end = end;
    }

    /**
     * Overloaded constructor
     * that initialize begin and end of range object
     * with default step
     * @param begin the begin point of the range
     * @param end the last point iterating
     */
    public Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
        this.step = 1; // default
    }

    /**
     * Another overloaded construcotr
     * that initialize begin, end and step of range object
     * @param begin the begin point of the range
     * @param end the last point iterating
     * @param step the step between two adjacent point
     */
    public Range(int begin, int end, int step) {
        this.begin = begin;
        this.end = end;
        this.step = step;
    }

    @Override
    /**
     * Override interface
     * that return the RangeIterator object
     * @return range iterator object
     */
    public Iterator<Integer> iterator() {
        // return RangeIterator object
        return new RangeIterator(this.begin,this.end,this.step);
    }
}
