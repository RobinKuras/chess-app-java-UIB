package no.uib.inf101.sem2.pieces;

import no.uib.inf101.sem2.ChessAlliance;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.TilePosition;

import java.util.List;

public class ChessPiece implements IChessPiece{

    private final TilePosition pieceCoordinate;
    private final ChessAlliance color;

    protected ChessPiece(TilePosition pieceCoordinate, ChessAlliance color){
        this.pieceCoordinate = pieceCoordinate;
        this.color = color;
    }

    @Override
    public boolean movePiece(TilePosition currPos, TilePosition deltaPos) {
        return false;
    }

    @Override
    public List<Move> legalMoves(ChessBoard board) {
        return null;
    }
}
