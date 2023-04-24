package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.Tile;

public interface ViewableChessModel {

    GridDimension getDimensions();
    Iterable<GridCell<Tile>> getTilesOnBoard();

}
