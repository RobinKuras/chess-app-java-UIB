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
        setupTestPieces();
    }

    private void setupTestPieces() {
        // TEST REMOVE AFTER USE ****************************************************
        King testKing = new King(new CellPosition(3, 0), ChessAlliance.BLACK);
        board.get(testKing.getPos()).setPiece(testKing);

        //TEST REMOVE AFTER USE ****************************************************
        King testKing2 = new King(new CellPosition(4,0),ChessAlliance.WHITE);
        board.get(testKing2.getPos()).setPiece(testKing2);

        Pawn testWhitePawn = new Pawn(this,new CellPosition(2,1),ChessAlliance.WHITE);
        board.get(testWhitePawn.getPos()).setPiece(testWhitePawn);

        Pawn testBlackPawn = new Pawn(this,new CellPosition(5,6),ChessAlliance.BLACK);
        board.get(testBlackPawn.getPos()).setPiece(testBlackPawn);

        Rook testRook = new Rook(this,new CellPosition(4,4),ChessAlliance.WHITE);
        board.get(testRook.getPos()).setPiece(testRook);
    }

    public void setupBlackPieces(){
        //Sets up all black pawns
        for (int i = 0; i < board.getCols(); i++) {
            Pawn pawn = new Pawn(this,new CellPosition(1,i), ChessAlliance.BLACK);
            board.get(pawn.getPos()).setPiece(pawn);
        }

        //sets up black rooks
        Rook bRook1 = new Rook(this,new CellPosition(0, 0), ChessAlliance.BLACK);
        Rook bRook2 = new Rook(this,new CellPosition(0, 7), ChessAlliance.BLACK);
        board.get(bRook1.getPos()).setPiece(bRook1);
        board.get(bRook2.getPos()).setPiece(bRook2);


        //sets up black knights
        Knight bKnight1 = new Knight(new CellPosition(0, 1), ChessAlliance.BLACK);
        Knight bKnight2 = new Knight(new CellPosition(0, 6), ChessAlliance.BLACK);
        board.get(bKnight1.getPos()).setPiece(bKnight1);
        board.get(bKnight2.getPos()).setPiece(bKnight2);

        //Sets up black bishops
        Bishop bBishop1 = new Bishop(this,new CellPosition(0, 2), ChessAlliance.BLACK);
        Bishop bBishop2 = new Bishop(this,new CellPosition(0, 5), ChessAlliance.BLACK);
        board.get(bBishop1.getPos()).setPiece(bBishop1);
        board.get(bBishop2.getPos()).setPiece(bBishop2);

        // Sets up black queen
        Queen bQueen = new Queen(this,new CellPosition(0, 4), ChessAlliance.BLACK);
        board.get(bQueen.getPos()).setPiece(bQueen);

        // Sets up black king
        King bKing = new King(new CellPosition(0, 3), ChessAlliance.BLACK);
        board.get(bKing.getPos()).setPiece(bKing);
    }
    public void setupWhitePieces(){
        //Sets up all white pawns
        for (int i = 0; i < board.getCols(); i++) {
            Pawn pawn = new Pawn(this,new CellPosition(6,i), ChessAlliance.WHITE);
            board.get(pawn.getPos()).setPiece(pawn);
        }

        //sets up white rooks
        Rook wRook1 = new Rook(this,new CellPosition(7,0),ChessAlliance.WHITE);
        Rook wRook2 = new Rook(this,new CellPosition(7,7),ChessAlliance.WHITE);
        board.get(wRook1.getPos()).setPiece(wRook1);
        board.get(wRook2.getPos()).setPiece(wRook2);

        //sets up white knights
        Knight wKnight1 = new Knight(new CellPosition(7,1),ChessAlliance.WHITE);
        Knight wKnight2 = new Knight(new CellPosition(7,6),ChessAlliance.WHITE);
        board.get(wKnight1.getPos()).setPiece(wKnight1);
        board.get(wKnight2.getPos()).setPiece(wKnight2);

        //Sets up white bishops
        Bishop wBishop1 = new Bishop(this,new CellPosition(7,2),ChessAlliance.WHITE);
        Bishop wBishop2 = new Bishop(this,new CellPosition(7,5),ChessAlliance.WHITE);
        board.get(wBishop1.getPos()).setPiece(wBishop1);
        board.get(wBishop2.getPos()).setPiece(wBishop2);

        //Sets up white queen
        Queen wQueen = new Queen(this,new CellPosition(7,4),ChessAlliance.WHITE);
        board.get(wQueen.getPos()).setPiece(wQueen);

        //Sets up white king
        King wKing = new King(new CellPosition(7,3),ChessAlliance.WHITE);
        board.get(wKing.getPos()).setPiece(wKing);
    }

    public boolean isLegalMove(IChessPiece piece,Move move){
        CellPosition tempPos = new CellPosition(piece.getPos().row()+move.deltaPos().row(),piece.getPos().col()+ move.deltaPos().col());
        IChessPiece tempPiece = board.getPieceAt(tempPos);
        return (board.positionIsOnGrid(tempPos)) && (piece.getCandidateMoves().contains(move));
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
