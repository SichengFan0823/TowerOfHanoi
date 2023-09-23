package towerofhanoi;

import java.util.EmptyStackException;

import student.TestCase;

public class LinkedStackTest extends TestCase {

	private LinkedStack<String> stack;

    public void setUp() {
        stack = new LinkedStack<>();
    }

   public void testSize() {
        assertEquals(0, stack.size());

        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertEquals(3, stack.size());

        stack.pop();
        assertEquals(2, stack.size());

        stack.clear();
        assertEquals(0, stack.size());
    }

    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push("a");
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());

        stack.clear();
        assertTrue(stack.isEmpty());
    }

    public void testClear() {
        stack.push("a");
        stack.push("b");
        stack.push("c");

        stack.clear();
        assertTrue(stack.isEmpty());
    }

    public void testToString() {
        stack.push("a");
        stack.push("b");
        stack.push("c");

        assertEquals("[c, b, a]", stack.toString());

        stack.pop();
        assertEquals("[b, a]", stack.toString());

        stack.clear();
        assertEquals("[]", stack.toString());
    }

    public void testPush() {
        stack.push("a");
        stack.push("b");
        stack.push("c");

        assertEquals("c", stack.peek());
        assertEquals(3, stack.size());
    }

    public void testPopEmpty() {
        
        Exception thrown = null;
        try
        {
        	stack.pop();
        }
        catch (Exception exception)
        {
        	thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
    }

    public void testPop() {
        stack.push("a");
        stack.push("b");
        stack.push("c");

        String result = stack.pop();
        assertEquals("c", result);
        assertEquals(2, stack.size());
        assertEquals("b", stack.peek());
    }

   public void testPeekEmpty() {
        Exception thrown = null;
        try
        {
        	stack.peek();
        }
        catch (Exception exception)
        {
        	thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
    }

    
    public void testPeek() {
        stack.push("a");
        stack.push("b");
        stack.push("c");

        String result = stack.peek();
        assertEquals("c", result);
        assertEquals(3, stack.size());
    }

}
