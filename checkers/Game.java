import java.awt.Color;

// This class defines the game logic for the Checkers game, handles and
// updates the GUI as the game progresses, determines a winner, and
// updates the board, player, tile and piece states
public class Game {

    private Tile[][] board; // board of tiles associated with game
    private Player player1; // player1, player who moves first
    private Player player2; // player2, player who moves second
    private CheckerGUI gui; // GUI associated with game
    private final int BOARD_SIZE = 8; // Size of board[][] width and height

    // Construct new game, initializes board of tiles and players
    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Tile(i, j);
            }
        }
        // initialize players with a color and direction, start with player 1
        player1 = new Player(Color.RED, 1);
        player2 = new Player(Color.BLUE, -1);
        player1.setTurn(true);

        // add pieces to player's lists and board
        for (int i = 0; i < 4; i++) {
            board[0][2 * i].changePiece(player1.addPiece(board[0][2 * i]));
            board[1][2 * i + 1].changePiece(player1.addPiece(board[1][2 * i + 1]));
            board[2][2 * i].changePiece(player1.addPiece(board[2][2 * i]));
            board[5][2 * i + 1].changePiece(player2.addPiece(board[5][2 * i + 1]));
            board[6][2 * i].changePiece(player2.addPiece(board[6][2 * i]));
            board[7][2 * i + 1].changePiece(player2.addPiece(board[7][2 * i + 1]));
        }
        // initialize GUI with this game
        gui = new CheckerGUI(this);
    }

    // Check if the given move from a player is a legal movement, return
    // true if it is legal
    public boolean isLegalMove(Piece piece, Tile moveTo) {
        // cannot move to occupied space
        if (moveTo.isAPiecePresent()) {
            return false;
        }
        // cannot move a player's piece when it is not their turn
        if (!piece.getPlayer().isTurn())
            return false;

        // check if this movement is a legal jump, if so it is a legal move
        if (isLegalJump(piece, moveTo)) {
            return true;
        }

        // find how selected tile relates to piece's current tile
        int fromCol = piece.getTile().getCol();
        int fromRow = piece.getTile().getRow();
        int colDiff = moveTo.getCol() - fromCol;
        int rowDiff = moveTo.getRow() - fromRow;

        // if the piece is kinged, let it move in any direction
        if (piece.isKinged() && (Math.abs(colDiff) == 1 && Math.abs(rowDiff) == 1)) {
            return true;
        }
        // if not and move is diagonal in piece's direction, let it move
        else if (Math.abs(colDiff) == 1 && rowDiff == piece.getDirection()) {
            return true;
        }
        // move therefore is not legal
        return false;
    }

    // check if move is a legal jump
    public boolean isLegalJump(Piece p, Tile moveTo) {
        // not legal if a piece is present
        if (moveTo.isAPiecePresent())
            return false;

        // find how selected tile relates to piece's current tile
        int fromCol = p.getTile().getCol();
        int fromRow = p.getTile().getRow();
        int colDiff = moveTo.getCol() - fromCol;
        int rowDiff = moveTo.getRow() - fromRow;

        // if piece is kinged, let it jump in any direction as long as there
        // is an opponent's piece for it to jump over in said direction
        if (p.isKinged() && Math.abs(colDiff) == 2 && Math.abs(rowDiff) == 2) {
            // define the tile that a captured piece would be
            Tile possibleCapture = board[fromRow + rowDiff / 2][fromCol + colDiff / 2];
            // if we can capture this piece, we have a legal jump
            if (possibleCapture.isAPiecePresent() &&
                    p.canCapture(possibleCapture.getCurrentPiece())) {
                return true;
            }
        }
        // only let jump if it is over an opponent and in the piece's player's direction
        else if (Math.abs(colDiff) == 2 && rowDiff == 2 * p.getDirection()) {
            Tile possibleCapture = board[fromRow + rowDiff / 2][fromCol + colDiff / 2];
            if (possibleCapture.isAPiecePresent() &&
                    p.canCapture(possibleCapture.getCurrentPiece())) {
                return true;
            }
        }
        // this is not a legal jump
        return false;
    }

    // determine opponent's captured piece from a given move from active player
    private Piece captured(Piece p, Tile moveTo) {
        // relate jump to piece that will be capturing another
        int fromCol = p.getTile().getCol();
        int fromRow = p.getTile().getRow();
        int colDiff = moveTo.getCol() - p.getTile().getCol();
        int rowDiff = moveTo.getRow() - p.getTile().getRow();
        // return the piece that would be captured
        return board[fromRow + rowDiff / 2][fromCol + colDiff / 2].getCurrentPiece();
    }

    // keep ask given player for a piece and then tile until there is a legal
    // move executed. Then execute this move
    private void move(Player player) {
        // check which player this is
        int num;
        if (player.getDirection() == 1)
            num = 1;
        else
            num = 2;

        // Give player directions while playing
        StdOut.println("Player " + num + ", select your piece.");

        // Get a piece and it's x & y coordinates
        Piece p = player.choosePiece(getGUI());
        int y = p.getTile().getRow();
        int x = p.getTile().getCol();
        // Print where player has selected a piece
        String prompt1 = "Piece selected at (";
        String prompt2 = "), please select a move.";
        StdOut.println(prompt1 + x + ", " + y + prompt2);
        // Ask player for a tile to move piece to
        Tile t = player.chooseMove(getGUI());

        // Keep searching for move until it is legal
        while (!isLegalMove(p, t)) {
            StdOut.println("Illegal move, please select a piece.");
            p = player.choosePiece(getGUI());
            y = p.getTile().getRow();
            x = p.getTile().getCol();
            StdOut.println(prompt1 + x + ", " + y + prompt2);
            t = player.chooseMove(getGUI());
        }
        // Execute the move now that a legal move has been selected
        StdOut.println("Move executed!");
        executeMove(p, t);
        // update GUI to reflect move on board
        getGUI().draw();
    }

    // execute move after determing if it is legal
    private void executeMove(Piece p, Tile moveTo) {
        // if move is a jump, take away captured piece and move piece
        if (isLegalJump(p, moveTo)) {
            // take away player2's piece if player1 has jumped
            if (player1.isTurn()) {
                // capture and remove piece
                Piece capture = captured(p, moveTo);
                capture.getTile().changePiece(null);
                player2.removePiece(capture);

                // move piece to new tile
                p.getTile().changePiece(null);
                p.setTile(moveTo);
                p.getTile().changePiece(p);
            }
            else {
                // take away player1's piece if player2 has jumped
                Piece capture = captured(p, moveTo);
                capture.getTile().changePiece(null);
                player1.removePiece(capture);

                p.getTile().changePiece(null);
                p.setTile(moveTo);
                p.getTile().changePiece(p);
            }
            // check if jump moved piece to top/bottom row, if so king
            int newRow = p.getTile().getRow();
            Player player = p.getPlayer();

            if ((newRow == 0 && player.equals(player2)) ||
                    (newRow == 7 && player.equals(player1))) {
                p.king();
            }
            StdOut.println("Piece captured!");
        }
        // if not a jump, don't capture a piece and move it
        else {
            p.getTile().changePiece(null);
            p.setTile(moveTo);

            int newRow = p.getTile().getRow();
            p.getTile().changePiece(p);

            // also check if move will king a piece
            Player player = p.getPlayer();
            if ((newRow == 0 && player.equals(player2)) ||
                    (newRow == 7 && player.equals(player1))) {
                p.king();
            }
        }
    }

    // Method that controls gameplay, ask for moves from players, switch
    // their turn after every move until one player has no pieces left.
    public void play() {
        getGUI().draw();
        while (!this.endGame()) {
            if (player1.isTurn()) {
                move(player1);
            }
            else {
                move(player2);
            }
            switchTurns();
        }
    }

    // switch player's turns
    private void switchTurns() {
        if (player1.isTurn()) {
            player1.setTurn(false);
            player2.setTurn(true);
        }
        else {
            player2.setTurn(false);
            player1.setTurn(true);
        }
    }

    // End game if either player has no pieces left
    private boolean endGame() {
        if (player1.getNumPieces() == 0) {
            StdOut.println("Player 2 Wins!");
            return true;
        }
        if (player2.getNumPieces() == 0) {
            StdOut.println("Player 1 Wins!");
            return true;
        }
        return false;
    }

    // return GUI from game
    public CheckerGUI getGUI() {
        return this.gui;
    }

    // get color from either player 1 or 2 in this game
    public Color getPlayerColor(int n) {
        if (n == 1)
            return player1.getColor();
        return player2.getColor();
    }

    // Get the board associated with this game
    public Tile[][] getBoard() {
        return this.board;
    }

    public void testLegality(Piece p, Tile moveTo) {
        if (isLegalMove(p, moveTo)) {
            StdOut.println("Legal move from (" + p.getTile().getCol() + "," + p.getTile().getRow()
                                   + ") to (" + moveTo.getCol() + "," + moveTo.getRow() + ")");

        }
        else
            StdOut.println(
                    "Illegal move from (" + p.getTile().getCol() + "," + p.getTile().getRow()
                            + ") to (" + moveTo.getCol() + "," + moveTo.getRow() + ")");
    }

    // Main method to instantiate game object, test game, and start game
    public static void main(String[] args) {
        Game newGame = new Game();
        // Call "testLegality" if given test parameter
        if (args.length > 0 && (args[0].equals("--test") || args[0].equals("-t"))) {
            Tile t = new Tile(0, 0);
            Tile moveTo1 = new Tile(1, 1);
            Tile moveTo2 = new Tile(1, 3);
            Player player1 = newGame.player1;

            Piece p1 = new Piece(player1, t, player1.getDirection());
            // Legal move, should print "Legal move from (0,0) to (1,1)"
            newGame.testLegality(p1, moveTo1);
            // Illegal move, should print "Illegal move from (0,0) to (3,1)"
            newGame.testLegality(p1, moveTo2);
        }
        else {
            // start game
            StdOut.println("Welcome to Java Checkers! Player 1, "
                                   + "you are red. Player 2, you are blue.");
            newGame.play();
        }
    }
}
