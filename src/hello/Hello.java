package hello;

public class Hello implements Comparable<Hello> {
    private String name;
    /* YOUR CODE HERE */

    public Hello() {
        /* YOUR CODE HERE */
    }

    public Hello(String name) {
        this.name = name;
        /* YOUR CODE HERE */
    }

    public String greeting() {
        /* YOUR CODE HERE */
        if(name == null) return "Hello, World!";
        else return "Hello, " + name + "!";
    }

    public String toString() {
        if(name == null) return "Hello, World!";
        return "Hello, " + name + "!";
    }

    // Hello should be compared by the length of the greeting in ascending order
    // i.e. if this > o in length, return a positive number
    //         this = o in length, return 0
    //         this < o in length, return a negative number
    // Bonus: can you write this implementation in only one line?
    @Override
    public int compareTo(Hello o) {
        /* YOUR CODE HERE */
        return (this.greeting().length() > o.greeting().length()) ? 1 : (this.greeting().length() == o.greeting().length()) ? 0 : -1;
    }
    // sandbox main method, feel free to edit

    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello.greeting());

        Hello named = new Hello("There");
        System.out.println(named);
        System.out.println("General Kenobi!");
    }

}
