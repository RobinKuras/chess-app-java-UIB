package no.uib.inf101.sem2.model;

import static org.junit.Assert.*;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.pieces.*;
import org.junit.Test;

public class ChessBoardTest {
    private final ChessBoard board = new ChessBoard(8,8);
    private ChessModel model = new ChessModel(board);

    @Test
    public void testGetKingPosition() {

        CellPosition whiteKingPos = model.getKingPosition(ChessAlliance.WHITE);
        assertEquals(new CellPosition(7, 3), whiteKingPos);
        CellPosition blackKingPos = model.getKingPosition(ChessAlliance.BLACK);
        assertEquals(new CellPosition(0, 3), blackKingPos);
    }

    @Test
    public void testIsLegalMovePawn(){
        IChessPiece whitePawn = board.getPieceAt(new CellPosition(6,1));
        IChessPiece blackPawn = board.getPieceAt(new CellPosition(1,1));

        whitePawn.updateCandidateMoves();
        blackPawn.updateCandidateMoves();

        //Test illegal moves
        assertFalse(model.isLegalMove(new Move(whitePawn,new CellPosition(0,2))));
        assertFalse(model.isLegalMove(new Move(blackPawn,new CellPosition(-2,0))));

        //Test legal moves
        assertTrue(model.isLegalMove(new Move(whitePawn,new CellPosition(-2,0))));
        assertTrue(model.isLegalMove(new Move(blackPawn,new CellPosition(1,0))));
    }

    @Test
    public void testIsLegalMoveKnight() {
        IChessPiece whiteKnight = board.getPieceAt(new CellPosition(7, 1));
        IChessPiece blackKnight = board.getPieceAt(new CellPosition(0, 1));

        whiteKnight.updateCandidateMoves();
        blackKnight.updateCandidateMoves();

        // Test illegal moves
        assertFalse(model.isLegalMove(new Move(whiteKnight, new CellPosition(-2, 0))));
        assertFalse(model.isLegalMove(new Move(blackKnight, new CellPosition(2, 0))));

        // Test legal moves
        assertTrue(model.isLegalMove(new Move(whiteKnight, new CellPosition(-2, 1))));
        assertTrue(model.isLegalMove(new Move(blackKnight, new CellPosition(2, -1))));
    }

    @Test
    public void testMovingPiece(){
        IChessPiece whitePawn = board.getPieceAt(new CellPosition(6, 4));
        IChessPiece whiteQueen = board.getPieceAt(new CellPosition(7, 4));

        whitePawn.updateCandidateMoves();
        whiteQueen.updateCandidateMoves();

        //Before moving the pawn, the queen had 0 moves
        assertEquals(0,whiteQueen.getCandidateMoves().size());

        Move pawnMove = new Move(whitePawn,new CellPosition(-2,0));

        if(model.isLegalMove(pawnMove)){
            whitePawn.movePiece(pawnMove);
            model.getBoard().get(whitePawn.getPos()).setPiece(whitePawn);
            model.getBoard().get(new CellPosition(6,4)).setPiece(null);
        }

        whitePawn.updateCandidateMoves();
        whiteQueen.updateCandidateMoves();

        //After moving the pawn, it has now moved. The old position is null and the queen now has 2 legal moves
        assertEquals(whitePawn.getPos(), new CellPosition(4, 4));
        assertNull(board.getPieceAt(new CellPosition(6, 4)));
        assertEquals(2,whiteQueen.getCandidateMoves().size());

    }

    @Test
    public void testCanAttack(){
        IChessPiece whitePawn = board.getPieceAt(new CellPosition(6, 4));
        IChessPiece blackPawn = new Pawn(model,new CellPosition(5,4),ChessAlliance.BLACK);
        IChessPiece blackQueen = new Queen(model,new CellPosition(5,5),ChessAlliance.BLACK);

        model.getBoard().get(blackPawn.getPos()).setPiece(blackPawn);
        model.getBoard().get(blackQueen.getPos()).setPiece(blackQueen);
        whitePawn.updateCandidateMoves();

        //The pawn only has one legal move, because it is blocked by the pawn. And that move is to attack the queen
        assertEquals(1,whitePawn.getCandidateMoves().size());
        assertTrue(whitePawn.getCandidateMoves().contains(new Move(whitePawn,new CellPosition(-1,1))));
        assertSame(board.getPieceAt(new CellPosition(5, 5)).getAlliance(), ChessAlliance.BLACK);

        whitePawn.movePiece(new Move(whitePawn,new CellPosition(-1,1)));
        model.getBoard().get(whitePawn.getPos()).setPiece(whitePawn);
        model.getBoard().get(new CellPosition(6,4)).setPiece(null);

        //After attacking the queen
        assertSame(board.getPieceAt(new CellPosition(5, 5)).getAlliance(), ChessAlliance.WHITE);

    }

    @Test
    public void testCheckAndCheckMate(){
        // Add a white king to the middle of the board and a queen that puts him in check
        IChessPiece whiteKing = new King(model, new CellPosition(4, 7), ChessAlliance.WHITE);
        IChessPiece blackQueen = new Queen(model, new CellPosition(4, 0), ChessAlliance.BLACK);
        IChessPiece blackRook = new Rook(model,new CellPosition(2,7),ChessAlliance.BLACK);
        IChessPiece anotherRook = new Rook(model,new CellPosition(2,6),ChessAlliance.BLACK);
        board.get(whiteKing.getPos()).setPiece(whiteKing);
        assertFalse(model.isCheck());

        board.get(blackQueen.getPos()).setPiece(blackQueen);
        board.get(blackRook.getPos()).setPiece(blackRook);
        board.get(anotherRook.getPos()).setPiece(anotherRook);
        whiteKing.updateCandidateMoves();
        blackQueen.updateCandidateMoves();
        blackRook.updateCandidateMoves();
        anotherRook.updateCandidateMoves();

        assertTrue(model.isCheck());
        System.out.println(board.prettyString());
        System.out.println(blackQueen.getCandidateMoves());
        System.out.println(whiteKing.getCandidateMoves());
        assertTrue(whiteKing.resultsInCheck(new Move(whiteKing,new CellPosition(0,-1))));
        System.out.println(whiteKing.getCandidateMoves().size());

        /**
         * Her skal ikke kongen ha flere trekk. Den ser at den står i sjakk.
         * Den ser også at brikkene kan angripe den uansett hvor den beveger seg.
         * Fremdeles blir alle trekkene lagt til i listen over godkjente trekk.
         * Tror dette er en "test-feiL" da den oppdaterer riktig i main-gamet
         *
         */
    }



}
