package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControlableChessModel;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.pieces.*;
import no.uib.inf101.sem2.view.ViewableChessModel;

public class ChessModel implements ViewableChessModel, ControlableChessModel {
    ChessBoard board;

    public ChessModel(ChessBoard board){
        this.board = board;
        initiateBoard();
    }

    public void initiateBoard(){
        setupBlackPieces();
        setupWhitePieces();
    }
    public void setupBlackPieces(){
        //Sets up all black pawns
        for (int i = 0; i < board.getCols(); i++) {
            Pawn pawn = new Pawn(this.board,new CellPosition(1,i), ChessAlliance.BLACK);
            board.get(new CellPosition(1,i)).setPiece(pawn);
        }

        //sets up black rooks
        Rook BRook1 = new Rook(new CellPosition(0,0),ChessAlliance.BLACK);
        Rook BRook2 = new Rook(new CellPosition(0,7),ChessAlliance.BLACK);
        board.get(new CellPosition(0,0)).setPiece(BRook1);
        board.get(new CellPosition(0,7)).setPiece(BRook2);

        //sets up black knights
        Knight BKnight1 = new Knight(new CellPosition(0,1),ChessAlliance.BLACK);
        Knight BKnight2 = new Knight(new CellPosition(0,6),ChessAlliance.BLACK);
        board.get(new CellPosition(0,1)).setPiece(BKnight1);
        board.get(new CellPosition(0,6)).setPiece(BKnight2);

        //Sets up black bishops
        Bishop BBishop1 = new Bishop(new CellPosition(0,2),ChessAlliance.BLACK);
        Bishop BBishop2 = new Bishop(new CellPosition(0,5),ChessAlliance.BLACK);
        board.get(new CellPosition(0,2)).setPiece(BBishop1);
        board.get(new CellPosition(0,5)).setPiece(BBishop2);

        //Sets up black queen
        Queen BQueen = new Queen(new CellPosition(0,3),ChessAlliance.BLACK);
        board.get(new CellPosition(0,3)).setPiece(BQueen);

        //Sets up black king
        King BKing = new King(new CellPosition(0,4),ChessAlliance.BLACK);
        board.get(new CellPosition(0,4)).setPiece(BKing);

    }
    public void setupWhitePieces(){
        //Sets up all white pawns
        for (int i = 0; i < board.getCols(); i++) {
            Pawn pawn = new Pawn(this.board,new CellPosition(6,i), ChessAlliance.WHITE);
            board.get(new CellPosition(6,i)).setPiece(pawn);
        }

        //sets up white rooks
        Rook WRook1 = new Rook(new CellPosition(7,0),ChessAlliance.WHITE);
        Rook WRook2 = new Rook(new CellPosition(7,7),ChessAlliance.WHITE);
        board.get(new CellPosition(7,0)).setPiece(WRook1);
        board.get(new CellPosition(7,7)).setPiece(WRook2);

        //sets up white knights
        Knight WKnight1 = new Knight(new CellPosition(7,1),ChessAlliance.WHITE);
        Knight WKnight2 = new Knight(new CellPosition(7,6),ChessAlliance.WHITE);
        board.get(new CellPosition(7,1)).setPiece(WKnight1);
        board.get(new CellPosition(7,6)).setPiece(WKnight2);

        //Sets up white bishops
        Bishop WBishop1 = new Bishop(new CellPosition(7,2),ChessAlliance.WHITE);
        Bishop WBishop2 = new Bishop(new CellPosition(7,5),ChessAlliance.WHITE);
        board.get(new CellPosition(7,2)).setPiece(WBishop1);
        board.get(new CellPosition(7,5)).setPiece(WBishop2);

        //Sets up white queen
        Queen WQueen = new Queen(new CellPosition(7,3),ChessAlliance.WHITE);
        board.get(new CellPosition(7,3)).setPiece(WQueen);

        //Sets up black king
        King WKing = new King(new CellPosition(7,4),ChessAlliance.WHITE);
        board.get(new CellPosition(7,4)).setPiece(WKing);
    }

    public void movePiece(IChessPiece piece, Move move){
        piece.movePiece(move);
    }

    public boolean isLegalMove(IChessPiece piece,Move move){
        CellPosition tempPos = new CellPosition(piece.getPos().row()+move.deltaPos().row(),piece.getPos().col()+ move.deltaPos().col());
        return board.positionIsOnGrid(tempPos) && board.getPieceAt(tempPos).getAlliance() != piece.getAlliance();
    }

    public ChessBoard getBoard(){
        return this.board;
    }
    @Override
    public GridDimension getDimensions() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Tile>> getTilesOnBoard() {
        return this.board;
    }
}
