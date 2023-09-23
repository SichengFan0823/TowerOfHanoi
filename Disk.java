// Virginia Tech Honor Code Pledge:
//f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// Sicheng Fan
package towerofhanoi;

import cs2.Shape;
import student.TestableRandom;
import java.awt.Color;
import java.util.Random;
import static towerofhanoi.PuzzleWindow.DISK_HEIGHT;
import static towerofhanoi.PuzzleWindow.WIDTH_FACTOR;

/**
 *
 * @author
 */
public class Disk extends Shape implements Comparable<Disk> {

	static Random random = new TestableRandom(1235l);

	/**
	 * Constructs a disk object with the specified width factor and width, and a
	 * random background color.
	 * 
	 * @param a the disk size
	 * 
	 */
	public Disk(int a) {
		super(0, 0, WIDTH_FACTOR + a * WIDTH_FACTOR, DISK_HEIGHT);
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		Color color = new Color(r, g, b);
		System.out.println(r + " " + g + " " + b);
		setBackgroundColor(color);
	}

	/**
	 * Compares this disk object with the specified disk object for order.
	 * 
	 * @param other the disk object to be compared.
	 * @return a negative integer, zero, or a positive integer as this disk is less
	 *         than, equal to, or greater than the other Disk
	 * @throws IllegalArgumentException if the specified disk is null.
	 */
	public int compareTo(Disk other) {
		if (other == null) {
			throw new IllegalArgumentException();
		}
		return getWidth() - other.getWidth();
	}

	/**
	 * Returns a string representation of this disk object.
	 * 
	 * @return a string representation of this disk object, which is the width of
	 *         the disk as a string.
	 */
	public String toString() {
		return getWidth() + "";
	}

	/**
	 * Determines whether the specified object is equal to this disk.
	 * 
	 * @param other the object to compare with this disk.
	 * @return true if the given object other is equal to this disk, false
	 *         otherwise.
	 * @throws NullPointerException if the given object is null.
	 */
	public boolean equals(Object other) {
		if (!other.getClass().equals(Disk.class)) {
			return false;
		}
		Disk disk = (Disk) other;
		return getWidth() == disk.getWidth();
	}
}
