
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MyStackTest {
    MyStack myStack;
    @Before
    public void setup() {
        myStack = new MyStack();
    }

    @Test
    public void pushOneInteger() {
        myStack.push(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void popWithoutPush() {
        myStack.pop();
    }

    @Test
    public void pushOneInteger_popOneInteger() {
        myStack.push(1);
        Assert.assertTrue("Push 1 but popped sth else.", myStack.pop() == 1);
    }

    @Test
    public void pushTwoIntegers_popTwoIntegers() {
        myStack.push(1);
        myStack.push(2);
        int poppedValue = myStack.pop();
        Assert.assertEquals(String.format("Expected: %d. Popped %d.", 2, poppedValue), 2, poppedValue);

        poppedValue = myStack.pop();
        Assert.assertEquals(String.format("Expected: %d. Popped %d.", 1, poppedValue), 1, poppedValue);
    }

    @Test(expected = NullPointerException.class)
    public void getMinWithoutPushing() {
        myStack.min();
    }

    @Test
    public void pushOne_getMin() {
        myStack.push(1);
        Assert.assertEquals(1, myStack.pop());
    }

    @Test
    public void pushTwoValues_getMin() {
        myStack.push(1);
        myStack.push(2);
        Assert.assertEquals(1, myStack.min());
    }

    @Test
    public void pushTwoValues_popMin_getMin() {
        myStack.push(2);
        myStack.push(1);

        myStack.pop();  // pop 1

        Assert.assertEquals(2, myStack.min());
    }

    @Test
    public void pushRepeatedValues_popRepeatedValues() {
        myStack.push(2);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);

        Assert.assertEquals(1, myStack.min());

        myStack.pop();
        Assert.assertEquals(1, myStack.min());

        myStack.pop();
        Assert.assertEquals(1, myStack.min());

        myStack.pop();
        Assert.assertEquals(2, myStack.min());
    }

    @Test
    public void pushInterleavedValues_pop_checkMin() {
        myStack.push(1); // stack: 1
        Assert.assertEquals(1, myStack.min());

        myStack.push(2); // stack: 1, 2
        myStack.push(2); // stack: 1, 2, 2
        Assert.assertEquals(1, myStack.min());

        myStack.pop(); // removes 2 , stack: 1, 2
        Assert.assertEquals(1, myStack.min());

        myStack.pop(); // removes 2 , stack: 1
        Assert.assertEquals(1, myStack.min());

        myStack.pop(); // removes 1 , stack:

        myStack.push(2); // stack: 2
        Assert.assertEquals(2, myStack.min());
    }
}