package se.liu.tddd78.examples;

import se.liu.jonkv82.annotations.BorrowedCode;

/*
    Detta kan vara användbart i projektet, där man behöver markera eventuell
    kod som har lånats från andra källor.  Klasser, fält, metoder och
    konstruktorer som har lånats ska då annoteras med @BorrowedCode och
    källan ska anges.

    Markerar man en hel klass behöver man så klart inte markera dess fält
    och metoder separat, men vi vill ändå visa var markeringen ska placeras
    i alla dessa fall.

    Mer information finns på
    https://www.ida.liu.se/~TDDD78/labs/2024/project/borrowing.
 */

@BorrowedCode(source = "Somewhere")
public class HowToBorrow
{
    @BorrowedCode(source = "Somewhere") private int x = 10;

    @BorrowedCode(source = "Somewhere") public HowToBorrow() {
    }

    /**
     * Javadoc for this method
     *
     * @param args
     */
    @BorrowedCode(source = "Somewhere") public static void main(String[] args) {
	System.out.println("Hello World!");
    }

    @BorrowedCode(source = "Somewhere")
    private class Foo
    {
	int y;
    }
}
