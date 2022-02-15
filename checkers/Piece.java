// A piece has a player, tile, and direction associated with it. It also
// knows whether or not it is a kinged piece. A piece will always have a
// tile object associated with it, but not every tile will have a piece.
public class Piece {
    private Player player;
    private Tile tile;
    private int direction;
    private boolean isKing = false;


    public Piece(Player player, Tile tile, int direction) {
        this.player = player;
        this.tile = tile;
        this.direction = direction;
    }

    public boolean canCapture(Piece other) {
        if (other.player != this.player)
            return true;
        return false;
    }

    public Tile getTile() {
        return this.tile;
    }

    public void setTile(Tile newTile) {
        this.tile = newTile;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isKinged() {
        return isKing;
    }

    public void king() {
        isKing = true;
    }

    public int getDirection() {
        return this.direction;
    }

    // String representation of Piece object
    public String toString() {
        return "Piece of " + this.getPlayer() + " located at coordinates (" +
                this.getTile().getCol() + ", " + this.getTile().getRow() + ").";
    }
    
}
