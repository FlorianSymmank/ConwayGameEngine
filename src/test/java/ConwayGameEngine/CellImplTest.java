package ConwayGameEngine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellImplTest {

    Cell cell;

    @BeforeEach
    void setUp() {
        cell = new CellImpl(1, 1);
    }

    @Test
    void die() {
        cell.die();
        assertFalse(cell.isAlive());
    }

    @Test
    void resurrect() {
        cell.resurrect();
        assertTrue(cell.isAlive());
    }

    @Test
    void invert() {
        cell.die();
        cell.invert();
        assertTrue(cell.isAlive());
        cell.invert();
        assertFalse(cell.isAlive());
    }

    @Test
    void isAlive() {
        assertFalse(cell.isAlive());
        cell.resurrect();
        assertTrue(cell.isAlive());
    }

    @Test
    void getX() {
        assertEquals(1, cell.getX());
    }

    @Test
    void getY() {
        assertEquals(1, cell.getY());
    }
}