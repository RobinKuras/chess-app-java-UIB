package no.uib.inf101.sem2.model.pieces;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.ChessBoard;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.Tile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class King implements IChessPiece{
    private ChessModel model;
    private ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private List<Move> candidateMoves = new ArrayList<>();
    public King(ChessModel model, CellPosition position, ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.model = model;
        this.board = model.getBoard();

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-King.png");
        } else this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-King.png");
    }

    public void addCandidateMove(Move move){
        if(!resultsInCheck(move)){
            candidateMoves.add(move);
        }
    }

    public boolean resultsInCheck(Move move){
        ChessAlliance alliance = model.getCurrentPlayersTurn();
        ChessAlliance oppAlliance;

        if (alliance == ChessAlliance.WHITE) {
            oppAlliance = ChessAlliance.BLACK;
        } else {
            oppAlliance = ChessAlliance.WHITE;
        }

        for(GridCell<Tile> cell : model.getTilesOnBoard()){
            Tile tile = cell.value();
            if (tile.getPiece() != null) {
                if (tile.getPiece().getAlliance() == oppAlliance) {
                    for(Move candMove : tile.getPiece().getCandidateMoves()){
                        if(candMove.getDestination().equals(move.getDestination())) {
                            return true;
                        }
                    }
                }
            }
        } return false;
    }


    @Override
    public void movePiece(Move move) {
        if(candidateMoves.contains(move)){
            this.pos = new CellPosition(this.pos.row()+move.deltaPos().row(),this.pos.col()+move.deltaPos().col());
        }
    }

    public void redoMove(Move move){
        Move redo = new Move(this,new CellPosition(move.deltaPos().row()*-1,move.deltaPos().col()*-1));
        movePiece(redo);
    }

    @Override
    public String getImageFilePath() {
        return imageIcon.toString();
    }

    @Override
    public ChessAlliance getAlliance() {
        return pieceColor;
    }

    @Override
    public CellPosition getPos() {
        return pos;
    }

    @Override
    public List<Move> getCandidateMoves() {
        return candidateMoves;
    }

    @Override
    public void updateCandidateMoves() {
        this.candidateMoves.clear();

        int row = pos.row();
        int col = pos.col();

        // loop over all adjacent squares to the king
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // check if the square is inside the board boundaries
                if (board.positionIsOnGrid(new CellPosition(i,j))) {
                    CellPosition nextPos = new CellPosition(i, j);

                        // check if the square is not occupied or is occupied by an enemy piece
                        if (!board.isOccupied(nextPos) || board.get(nextPos).getPiece().getAlliance() != pieceColor) {

                            CellPosition deltaPos = new CellPosition(i - row, j - col);
                            addCandidateMove(new Move(this, deltaPos));

                    }
                }
            }
        }
    }

    @Override
    public boolean isAttacking() {
        return false;
    }
}
