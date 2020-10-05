package test;

import hello.Hello;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HelloTest {
    @Test
    public void greetingTestWorld() {
        Hello hello = new Hello();
        assertEquals("Hello, World!", hello.greeting());
    }

    @Test
    public void greetingTestName() {
        Hello name = new Hello("there");
        assertEquals("Hello, there!", name.greeting());
    }

    @Test
    public void greetingTestOther() {
        Hello other = new Hello("Earth");
        assertEquals("Hello, Earth!", other.greeting());
    }

    @Test
    public void helloToString() {
        Hello hello = new Hello();
        assertEquals("Hello, World!", hello.toString());

        Hello adele = new Hello("its me");
        assertEquals("Hello, its me!", adele.toString());

        Hello name = new Hello("General Kenobi");
        assertEquals("Hello, General Kenobi!", name.toString());
    }

    // Hello fulfills the Comparable<Hello> interface
    // and therefore must implement .compareTo(Hello o)
    @Test
    public void helloComparable() {
        Comparable<Hello> helloExample = new Hello();
        assertTrue(new Hello() instanceof Comparable);

        Hello helloA = new Hello("Alice");
        Hello helloB = new Hello("Bob");
        Hello helloC = new Hello("Chris");

        assertTrue(helloA.compareTo(helloB) > 0);
        assertTrue(helloB.compareTo(helloA) < 0);
        assertEquals(0, helloA.compareTo(helloC));
        assertEquals(0, helloC.compareTo(helloA));
    }

    // compareTo should enable Hello to be sorted in ascending order
    // Collections.sort can sort any Comparable<T>
    @Test
    public void helloSort() {
        Hello a = new Hello("Alice");
        Hello b = new Hello("Bob");
        Hello c = new Hello("Charlie");
        Hello d = new Hello("Dominick");
        Hello e = new Hello("Ed");

        List<Hello> list = Arrays.asList(
            a, b, c, d, e
        );

        Collections.sort(list);
        List<Hello> expected = Arrays.asList(
            e, b, a, c, d
        );

        assertEquals(expected, list);
    }
}
