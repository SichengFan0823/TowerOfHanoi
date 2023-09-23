package towerofhanoi;

import student.TestCase;
import static towerofhanoi.PuzzleWindow.DISK_HEIGHT;
import static towerofhanoi.PuzzleWindow.WIDTH_FACTOR;

public class DiskTest extends TestCase {

    public void testCompareTo() {
        Disk disk1 = new Disk(1);
        Disk disk2 = new Disk(2);
        Disk disk3 = new Disk(3);

        assertTrue(disk1.compareTo(disk2) < 0);
        assertTrue(disk2.compareTo(disk1) > 0);
        assertTrue(disk1.compareTo(disk1) == 0);
        assertTrue(disk3.compareTo(disk2) > 0);
    }

   public void testCompareToNull() {
    	Exception thrown = null;
    	try
    	{
    		Disk disk = new Disk(1);
            disk.compareTo(null);
    	}
    	catch (Exception exception)
    	{
    	 //”Catch” and store the exception
    	 thrown = exception;
    	}
    	//assert that an exception was thrown
    	assertNotNull(thrown);
    	//assert that the correct exception was thrown
    	assertTrue(thrown instanceof IllegalArgumentException);
        
    }

    public void testToString() {
        Disk disk = new Disk(4);
        assertEquals(String.valueOf(WIDTH_FACTOR + 4 * WIDTH_FACTOR), disk.toString());
    }

    public void testEquals() {
        Disk disk1 = new Disk(1);
        Disk disk2 = new Disk(1);
        Disk disk3 = new Disk(2);

        assertTrue(disk1.equals(disk2));
        assertFalse(disk1.equals(disk3));
        assertFalse(disk1.equals(new Object()));
    }
}
