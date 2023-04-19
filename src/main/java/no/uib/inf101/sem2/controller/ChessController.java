package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.ChessModel;
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
        private IChessPiece draggedPiece;

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
        }

        @Override
        public void mousePressed(MouseEvent event) {
            Point2D mouseCoordinate = event.getPoint();
            CellPositionToPixelConverter converter = this.view.getCellPositionToPixelConverter();
            CellPosition pos = converter.getCellPositionOfPoint(mouseCoordinate);
            IChessPiece piece = model.getBoard().getPieceAt(pos);
            /*if(piece != null){
                this.draggedPiece = piece;
            }
            this.view.repaint();
        }*/

        }

}
