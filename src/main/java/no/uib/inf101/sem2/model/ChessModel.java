package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControlableChessModel;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.pieces.*;
import no.uib.inf101.sem2.view.ViewableChessModel;

public class ChessModel implements ViewableChessModel, ControlableChessModel {
    ChessBoard board;
    ChessAlliance currentPlayersTurn;

    public ChessModel(ChessBoard board){
        this.board = board;
        this.currentPlayersTurn = ChessAlliance.WHITE;
        initiateBoard();
    }

    public void initiateBoard(){
        setupBlackPieces();
        setupWhitePieces();
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
        Knight bKnight1 = new Knight(this,new CellPosition(0, 1), ChessAlliance.BLACK);
        Knight bKnight2 = new Knight(this,new CellPosition(0, 6), ChessAlliance.BLACK);
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
        King bKing = new King(this,new CellPosition(0, 3), ChessAlliance.BLACK);
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
        Knight wKnight1 = new Knight(this,new CellPosition(7,1),ChessAlliance.WHITE);
        Knight wKnight2 = new Knight(this,new CellPosition(7,6),ChessAlliance.WHITE);
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
        King wKing = new King(this,new CellPosition(7,3),ChessAlliance.WHITE);
        board.get(wKing.getPos()).setPiece(wKing);
    }

    @Override
    public boolean isLegalMove(Move move){
        CellPosition tempPos = new CellPosition(move.getDestination().row(),move.getDestination().col());
        return (board.positionIsOnGrid(tempPos)) && (move.pieceToBeMoved().getCandidateMoves().contains(move));
    }
    @Override
    public void newTurn(){
        if(currentPlayersTurn == ChessAlliance.WHITE){
            currentPlayersTurn = ChessAlliance.BLACK;
        } else currentPlayersTurn = ChessAlliance.WHITE;
    }

    public ChessAlliance getCurrentPlayersTurn(){
        return currentPlayersTurn;
    }

    public CellPosition getKingPosition(ChessAlliance alliance){
        for(GridCell<Tile> cell : getTilesOnBoard()){
            Tile tile = cell.value();
            if(tile.getPiece() instanceof King && tile.getPiece().getAlliance() == alliance){
                return cell.pos();
            }
        }
        //Should never be the case as there must always be a king alive for the game to be played
        System.out.println("This shouldnt happen");
        return null;
    }

    public boolean isCheck(){
        ChessAlliance alliance = this.currentPlayersTurn;
        CellPosition kingPos = getKingPosition(alliance);
        ChessAlliance oppAlliance;


        if (alliance == ChessAlliance.WHITE) {
             oppAlliance = ChessAlliance.BLACK;
        } else {
             oppAlliance = ChessAlliance.WHITE;
        }


        for(GridCell<Tile> cell : getTilesOnBoard()){
            Tile tile = cell.value();
            if (tile.getPiece() != null) {
                if (tile.getPiece().getAlliance() == oppAlliance) {
                    if (canAttack(tile.getPiece().getPos(), kingPos)) {
                        return true;
                    }
                }
            }
        } return false;
    }


    /**
     *
     * @param attacker the attacking piece
     * @param target the piece "attacker" wants to attack
     * @return if the target position is the destination of the one of the candidateMoves of the attacker, return true, else return false
     */
    public boolean canAttack(CellPosition attacker, CellPosition target){
        Move tryMove = new Move(getBoard().get(attacker).getPiece(),new CellPosition(target.row()-attacker.row(),target.col()-attacker.col()));
        for(Move move : board.get(attacker).getPiece().getCandidateMoves()){
            if (move.getDestination().equals(tryMove.getDestination())){
                return true;
            }
        } return false;
    }

    public boolean isCheckMate(){
        for(GridCell<Tile> tile : getTilesOnBoard()){
            if(tile.value().getPiece() != null && tile.value().getPiece().getAlliance() == currentPlayersTurn){
                tile.value().getPiece().updateCandidateMoves();
                if(tile.value().getPiece().getCandidateMoves().size() != 0){
                    return false;
                }
            }
        } return true;
    }

    /**
     *
     * @return the board object of the model
     */
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
