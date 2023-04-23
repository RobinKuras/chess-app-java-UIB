package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessModel;
import no.uib.inf101.sem2.model.Move;
import no.uib.inf101.sem2.model.pieces.IChessPiece;
import no.uib.inf101.sem2.view.CellPositionToPixelConverter;
import no.uib.inf101.sem2.view.ChessView;

import javax.swing.text.View;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.sql.SQLOutput;

public class ChessController extends MouseAdapter {

        private ChessModel model;
        private ChessView view;
        private IChessPiece selectedPiece;

        /**
         * Construct a controller recating to key presses.
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
        public void mousePressed(MouseEvent event) {
            System.out.println(model.getCurrentPlayersTurn());
            if(model.isCheck()){
                System.out.println("SJAKK");
            }

            //If no piece is selected, get possible moves from the piece at clicked tile, prepare for it to be moved
            if(selectedPiece == null){
                Point2D mouseCoordinate = event.getPoint();
                CellPositionToPixelConverter converter = this.view.getCellPositionToPixelConverter();
                CellPosition pos = converter.getCellPositionOfPoint(mouseCoordinate);
                IChessPiece piece = model.getBoard().getPieceAt(pos);
                if(piece != null && piece.getAlliance() == model.getCurrentPlayersTurn()){
                    selectedPiece = piece;
                    piece.updateCandidateMoves();
                    System.out.println(piece.getCandidateMoves());
                } else System.out.println("No Piece here!");

                //If you have a selected piece, try to move it to the tile you clicked
            } else {
                Point2D mouseCoordinate = event.getPoint();
                CellPositionToPixelConverter converter = this.view.getCellPositionToPixelConverter();
                CellPosition newPos = converter.getCellPositionOfPoint(mouseCoordinate);
                CellPosition oldPos = selectedPiece.getPos();

                int deltaRow = newPos.row() - oldPos.row();
                int deltaCol = newPos.col() - oldPos.col();
                Move move = new Move(selectedPiece,new CellPosition(deltaRow, deltaCol));

                if(model.isLegalMove(selectedPiece,move)){
                    IChessPiece tempPiece = model.getBoard().getPieceAt(newPos);

                    selectedPiece.movePiece(move);
                    model.getBoard().get(newPos).setPiece(selectedPiece);
                    model.getBoard().get(oldPos).setPiece(null);
/*
                    if(model.isCheck()){
                        selectedPiece.redoMove(move);
                        model.getBoard().get(newPos).setPiece(tempPiece);
                        model.getBoard().get(oldPos).setPiece(selectedPiece);
                        System.out.println("You cant move yourself into check");
                        selectedPiece = null;
                        return;
                    }
*/                  selectedPiece.updateCandidateMoves();
                    selectedPiece = null;
                    model.newTurn();


                } else{
                    System.out.println("Illegal move");
                    selectedPiece = null;
                }
            }
        }
}
