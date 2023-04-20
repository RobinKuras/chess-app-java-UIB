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
            if(selectedPiece == null){
                Point2D mouseCoordinate = event.getPoint();
                CellPositionToPixelConverter converter = this.view.getCellPositionToPixelConverter();
                CellPosition pos = converter.getCellPositionOfPoint(mouseCoordinate);
                IChessPiece piece = model.getBoard().getPieceAt(pos);
                if(piece != null){
                    piece.updateCandidateMoves();
                    selectedPiece = piece;
                    System.out.println(piece.getCandidateMoves());
                } else System.out.println("No Piece here!");

            } else {
                Point2D mouseCoordinate = event.getPoint();
                CellPositionToPixelConverter converter = this.view.getCellPositionToPixelConverter();
                CellPosition newPos = converter.getCellPositionOfPoint(mouseCoordinate);
                CellPosition oldPos = selectedPiece.getPos();

                int deltaRow = newPos.row() - oldPos.row();
                int deltaCol = newPos.col() - oldPos.col();
                Move move = new Move(new CellPosition(deltaRow, deltaCol));

                if(model.isLegalMove(selectedPiece,move)){
                    selectedPiece.movePiece(move);
                    model.getBoard().get(newPos).setPiece(selectedPiece);
                    model.getBoard().get(oldPos).setPiece(null);
                    selectedPiece = null;
                    System.out.println("Piece at new pos: "+model.getBoard().get(newPos).getPiece());
                    System.out.println("Piece at old pos: "+model.getBoard().get(oldPos).getPiece());

                } else{
                    System.out.println("Illegal move");
                    selectedPiece = null;
                }
            }
        }
}
