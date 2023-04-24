package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.pieces.IChessPiece;
import no.uib.inf101.sem2.view.CellPositionToPixelConverter;
import no.uib.inf101.sem2.view.ChessView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class ChessController extends MouseAdapter {
        private final ControllableChessModel model;
        private final ChessView view;
        private IChessPiece selectedPiece;

        /**
         * Construct a controller reacting to key presses.
         *
         * @param model model to update on key press
         * @param view view to listen to key presses in, and to be repainted when model changes
         */
        public ChessController(ChessModel model, ChessView view) {
            this.model = model;
            this.view = view;
            this.view.addMouseListener(this);
            this.selectedPiece = null;
        }

        @Override
        public void mousePressed(MouseEvent event){
            if(!model.isCheckMate()){
                //If no piece is selected, get possible moves from the piece at clicked tile, prepare for it to be moved
                if(selectedPiece == null){
                    Point2D mouseCoordinate = event.getPoint();
                    CellPositionToPixelConverter converter = view.getCellPositionToPixelConverter();
                    CellPosition pos = converter.getCellPositionOfPoint(mouseCoordinate);

                    try {
                        IChessPiece piece = model.getBoard().getPieceAt(pos);
                        if(piece != null && piece.getAlliance() == model.getCurrentPlayersTurn()){
                            selectedPiece = piece;
                            piece.updateCandidateMoves();
                            System.out.println(piece.getCandidateMoves());
                        } else System.out.println("No Piece here!");
                    } catch (NullPointerException e) {
                        System.out.println("You clicked out of bounds");
                    }

                    //If you have a selected piece, try to move it to the tile you clicked
                } else {
                    try{
                        Point2D mouseCoordinate = event.getPoint();
                        CellPositionToPixelConverter converter = view.getCellPositionToPixelConverter();
                        CellPosition newPos = converter.getCellPositionOfPoint(mouseCoordinate);
                        CellPosition oldPos = selectedPiece.getPos();

                        int deltaRow = newPos.row() - oldPos.row();
                        int deltaCol = newPos.col() - oldPos.col();
                        Move move = new Move(selectedPiece,new CellPosition(deltaRow, deltaCol));

                        if(model.isLegalMove(move)){
                            selectedPiece.movePiece(move);
                            model.getBoard().get(newPos).setPiece(selectedPiece);
                            model.getBoard().get(oldPos).setPiece(null);
                            selectedPiece.updateCandidateMoves();
                            selectedPiece = null;
                            model.newTurn();


                        } else{
                            System.out.println("Illegal move ... Resetting selected piece");
                            selectedPiece = null;
                        }
                    } catch (NullPointerException e){
                        System.out.println("You clicked out of bounds ... Resetting selected piece");
                        selectedPiece = null;
                    }
                }
            }
            }

}
