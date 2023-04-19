package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMove {
    @Test
    public void compareToTest(){
        // create two CellPosition objects to represent the delta position of the moves
        CellPosition deltaPos1 = new CellPosition(1, 0);
        CellPosition deltaPos2 = new CellPosition(0, 1);

        // create two Move objects with different delta positions
        Move move1 = new Move(deltaPos1);
        Move move2 = new Move(deltaPos2);

        // test that move1 compares less than move2
        assertTrue(move1.compareTo(move2) > 0);

        // test that move2 compares greater than move1
        assertTrue(move2.compareTo(move1) < 0);

        // create a third Move object with the same delta position as move1
        Move move3 = new Move(deltaPos1);

        // test that move1 and move3 compare as equal
        assertTrue(move1.compareTo(move3) == 0);
    }
}
