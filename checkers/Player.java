import java.awt.Color;
import java.util.ArrayList;

// The player keeps track of their own pieces, their color, direction, and
// whether or not it is their turn. They define the state of pieces when
// interacting with one another and the color of pieces on the GUI.
// Players also importantly receive a tile/piece from mouse input on their turn.
public class Player {

    private ArrayList<Piece> pieceList; // List of pieces player still has on board
    private Color color; // Color associated with player
    private int direction; // Direction on board player is going
    private boolean myTurn = false; // Whether or not it is player's turn

    // Constructs player with a color, direction, and empty list
    public Player(Color color, int direction) {
        pieceList = new ArrayList<Piece>();
        this.color = color;
        this.direction = direction;
    }

    // Adds a piece at given tile to list and returns the piece created
    public Piece addPiece(Tile initialPosition) {
        Piece p = new Piece(this, initialPosition, this.direction);
        pieceList.add(p);
        return p;
    }

    // return the list of pieces assoc. w/ player
    public ArrayList<Piece> getPieces() {
        return pieceList;
    }

    // returns number of pieces player has
    public int getNumPieces() {
        return pieceList.size();
    }

    // removes a piece from player's list
    public void removePiece(Piece remove) {
        pieceList.remove(remove);
    }

    // Player chooses a piece from mouse input on the GUI, assuming the tile
    // clicked has a piece associated with the player
    public Piece choosePiece(CheckerGUI gui) {
        // get tile from mouse input
        Tile selected = gui.mouseToTile();
        // keep accepting tile input until it has a piece associated w/ player
        while (selected == null || !selected.isAPiecePresent()
                || !selected.getCurrentPiece().getPlayer().equals(this)) {
            // check for another tile
            selected = gui.mouseToTile();
            if (selected != null && selected.isAPiecePresent()
                    && selected.getCurrentPiece().getPlayer().equals(this)) {
                // return piece that player has selected
                return selected.getCurrentPiece();
            }
        }
        return null;
    }

    // Player chooses a tile from mouse after selecting a piece, determining
    // attempted move for that piece
    public Tile chooseMove(CheckerGUI gui) {
        // get tile from mouse input on GUI
        Tile moveTo = gui.mouseToTile();
        // wait until a tile is actually clicked
        while (moveTo == null) {
            moveTo = gui.mouseToTile();
            if (moveTo != null) {
                return moveTo;
            }
        }
        return moveTo;
    }

    // return color of player
    public Color getColor() {
        return this.color;
    }

    // return direction of player
    public int getDirection() {
        return this.direction;
    }

    // true if player's turn is right now
    public boolean isTurn() {
        return this.myTurn;
    }

    // sets player's turn to true or false
    public void setTurn(boolean turn) {
        myTurn = turn;
    }

    // player object in their string format - state name to differentiate
    public String toString() {
        if (this.direction == 1)
            return "player1";
        return "player2";
    }

    // Tests whether tile is located on board and whether or not tile has a
    // piece with it via CheckerGUI. Prints coordinates and piece found to
    // StdOut as user clicks on GUI.
    public void testTile(CheckerGUI gui) {
        Tile t = chooseMove(gui);
        // continue looking for tile
        while (t != null) {
            StdOut.println("Tile selected at (" + t.getCol() + "," + t.getRow() + ").");
            if (t.getCurrentPiece() != null) {
                StdOut.println("Piece found!");
            }
            t = chooseMove(gui);
        }
    }

    // Calls test method "testTile" which gets input via CheckerGUI
    // and prints to StdOut the coordinates of clicked tile and whether a piece
    // was found or not. Every click is essentially another test.
    public static void main(String[] args) {
        if (args.length > 0 && (args[0].equals("--test") || args[0].equals("-t"))) {
            Game newGame = new Game();
            Player player1 = new Player(Color.RED, 1);
            CheckerGUI gui = newGame.getGUI();
            gui.draw();
            player1.testTile(gui);
        }
    }
}
