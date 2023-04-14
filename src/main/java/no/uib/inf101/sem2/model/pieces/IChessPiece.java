package no.uib.inf101.sem2.model.pieces;

public interface IChessPiece {

    void movePiece();

    String getImageFilePath();
    ChessAlliance getAlliance();
}
