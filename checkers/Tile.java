// The tile class represents a tile on the board. It holds a row and column
// and sometimes has a piece associated with it. This can be changed as
// pieces move around the board during the game.
public class Tile {
    private int row; // row of tile
    private int col; // column of tile
    private Piece piece; // piece associated with tile (not always the case)

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    // return associated piece (null if no piece)
    public Piece getCurrentPiece() {
        return this.piece;
    }

    // return row
    public int getRow() {
        return this.row;
    }

    // return col
    public int getCol() {
        return this.col;
    }

    // change the piece associated with Tile
    public void changePiece(Piece newPiece) {
        this.piece = newPiece;
    }

    // returns true if a piece is associated with Tile
    public boolean isAPiecePresent() {
        if (this.piece == null)
            return false;
        return true;
    }
}
