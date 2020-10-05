package hello;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator implements Iterator<Integer> {
    //Fields declaration
    private int begin, end, step;

    public RangeIterator(int begin, int end, int step) {
        this.begin = begin;
        this.end = end;
        this.step = step;
    }

    public boolean hasNext() {
        if(step > 0) return begin < end;
        else if(step < 0) return begin > end;
        else throw new IllegalArgumentException();
    }

    public Integer next() {
        if((step > 0 && begin < end) || (step < 0 && begin > end)) {
            int res = begin;
            begin += step;
            return res;
        }
        else throw new NoSuchElementException();
    }

    public void remove() {
        // Not implemented
        throw new UnsupportedOperationException();
    }
}
