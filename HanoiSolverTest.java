package towerofhanoi;

import student.TestCase;

public class HanoiSolverTest extends TestCase {
	HanoiSolver hanoiSolver;

	public void setUp() {
		hanoiSolver = new HanoiSolver(4);
	}

	public void testDisks() {
		hanoiSolver = new HanoiSolver(4);
		assertEquals(4, hanoiSolver.disks());
	}

	public void testGetTower() {

	}

	public void testSolve() {
		HanoiSolver solver = new HanoiSolver(3);
		solver.getTower(Position.LEFT).push(new Disk(3));
		solver.getTower(Position.LEFT).push(new Disk(2));
		solver.getTower(Position.LEFT).push(new Disk(1));
		solver.solve();
		// assert that all disks are on the right tower
		assertEquals("[]", solver.getTower(Position.LEFT).toString());
		assertEquals("[]", solver.getTower(Position.MIDDLE).toString());
		assertEquals("[40, 60, 80]", solver.getTower(Position.RIGHT).toString());

	}

	public void testToString() {
		HanoiSolver solver = new HanoiSolver(3);
		solver.getTower(Position.LEFT).push(new Disk(3));
		solver.getTower(Position.LEFT).push(new Disk(2));
		solver.getTower(Position.LEFT).push(new Disk(1));
		
		String expected = "[40, 60, 80][][]";
		assertEquals(expected, solver.toString());
		solver.getTower(Position.LEFT).pop();
		expected = "[60, 80][][]";
		assertEquals(expected, solver.toString());
	}
}
