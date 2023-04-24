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

public class Pawn implements IChessPiece{
    private ChessModel model;
    private final ChessBoard board;
    private CellPosition pos;
    private final ChessAlliance pieceColor;
    private ImageIcon imageIcon;
    private List<Move> candidateMoves = new ArrayList<>();
    private boolean isAttacking;

    public Pawn(ChessModel model, CellPosition position,ChessAlliance color){
        this.pos = position;
        this.pieceColor = color;
        this.model = model;
        this.board = model.getBoard();
        this.isAttacking = false;

        if(this.pieceColor == ChessAlliance.WHITE){
            this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_White-Pawn.png");
        } else this.imageIcon = new ImageIcon("src/main/java/no/uib/inf101/sem2/images/Chess_Black-Pawn.png");
    }

    public void addCandidateMove(Move move){
        if(!resultsInCheck(move)){
            candidateMoves.add(move);
        } else if(board.get(move.getDestination()).getPiece() != null && board.get(move.getDestination()).getPiece().isAttacking()){
            candidateMoves.add(move);
        }
    }

    public boolean resultsInCheck(Move move){
        ChessAlliance alliance = model.getCurrentPlayersTurn();
        CellPosition kingPos = model.getKingPosition(alliance);
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
                        if(candMove.getDestination().equals(kingPos)) {
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

    @Override
    public void updateCandidateMoves(){
       this.candidateMoves.clear();

       if(pieceColor == ChessAlliance.WHITE){
           updateWhiteMoves();
       } else {
           updateBlackMoves();
       }
    }

    @Override
    public void redoMove(Move move) {

    }

    @Override
    public boolean isAttacking() {
        ChessAlliance oppAlliance;

        if (pieceColor == ChessAlliance.WHITE) {
            oppAlliance = ChessAlliance.BLACK;
        } else {
            oppAlliance = ChessAlliance.WHITE;
        }

        for(Move move : getCandidateMoves()){
            if(move.getDestination().equals(model.getKingPosition(oppAlliance))){
                this.isAttacking = true;
            } else this.isAttacking = false;
        }
        return this.isAttacking;
    }

    private void updateBlackMoves() {

        //Checks if pawn has moved, and can do pawn jump (2 tiles)
        if(pos.row() == 1 && !board.isOccupied(new CellPosition(pos.row()+2, pos.col())) && !board.isOccupied(new CellPosition(pos.row()+1, pos.col()))){
            addCandidateMove(new Move(this,new CellPosition(2,0)));
        }

        //Checks if pawn can do a regular move (1 tile)
        if(!board.isOccupied(new CellPosition(pos.row()+1, pos.col()))){
            addCandidateMove(new Move(this,new CellPosition(1,0)));
        }

        //Checks if diagonal move to the right is legal
        if(pos.col() != 7){
            if(board.isOccupied(new CellPosition(pos.row()+1,pos.col()+1))) {
                if (board.get(new CellPosition(pos.row()+1, pos.col()+1)).getPiece().getAlliance() == ChessAlliance.WHITE) {
                    addCandidateMove(new Move(this,new CellPosition(1, 1)));
                }
            }
        }

        //Checks if diagonal move to the left is legal
        if(pos.col() != 0){
            if(board.isOccupied(new CellPosition(pos.row()+1,pos.col()-1))) {
                if (board.get(new CellPosition(pos.row()+1, pos.col()-1)).getPiece().getAlliance() == ChessAlliance.WHITE) {
                    addCandidateMove(new Move(this,new CellPosition(1, -1)));
                }
            }
        }
    }


    private void updateWhiteMoves() {
        //Checks if pawn has moved, and can do pawn jump (2 tiles)
        if(pos.row() == 6 && !board.isOccupied(new CellPosition(pos.row()-2, pos.col())) && !board.isOccupied(new CellPosition(pos.row()-1, pos.col()))){
            addCandidateMove(new Move(this,new CellPosition(-2,0)));
        }

        //Checks if pawn can do a regular move (1 tile)
        if(!board.isOccupied(new CellPosition(pos.row()-1, pos.col()))){
            addCandidateMove(new Move(this,new CellPosition(-1,0)));
        }

        //Checks if diagonal move to the right is legal
        if(pos.col() != 7){
            if(board.isOccupied(new CellPosition(pos.row()-1,pos.col()+1))) {
                if (board.get(new CellPosition(pos.row() - 1, pos.col() + 1)).getPiece().getAlliance() == ChessAlliance.BLACK) {
                    addCandidateMove(new Move(this,new CellPosition(-1, 1)));
                }
            }
        }

        //Checks if diagonal move to the left is legal
        if(pos.col() != 0){
            if(board.isOccupied(new CellPosition(pos.row()-1,pos.col()-1))) {
                if (board.get(new CellPosition(pos.row() - 1, pos.col() - 1)).getPiece().getAlliance() == ChessAlliance.BLACK) {
                    addCandidateMove(new Move(this,new CellPosition(-1, -1)));
                }
            }
        }
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
}
