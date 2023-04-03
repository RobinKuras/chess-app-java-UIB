package no.uib.inf101.sem2.model;


public class ChessBoard {
    private static final int rows = 8;
    private static final int cols = 8;
    private final Tile[][] tiles = new Tile[rows][cols];

    public ChessBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new EmptyTile(new TilePosition(i,j));
            }
        }
    }

    public void getTiles() {
        for (Tile[] tileList : tiles) {
            for(Tile tile : tileList){
                System.out.println(tile);
            }

        }
    }
}
