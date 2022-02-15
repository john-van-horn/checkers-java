import java.awt.Color;

// This class handles the GUI of the game, including user input and output that
// updates after a move in the game. It importantly grabs a tile from a given
// mouse click and repaints the board after a move is executed to reflect
// changes on the state of the board.
public class CheckerGUI {
    private Game game;
    private final int SIZE = 8;
    private final int SQUARE_SIZE = 100;
    private final int canvasHeight = SQUARE_SIZE * SIZE;
    private final int canvasWidth = SQUARE_SIZE * SIZE;

    public CheckerGUI(Game game) {
        this.game = game;

        // Set size of GUI and enable double buffering
        StdDraw.setXscale(0, SIZE * SQUARE_SIZE);
        StdDraw.setYscale(0, SIZE * SQUARE_SIZE);
        StdDraw.enableDoubleBuffering();
    }

    // Get a tile from a mouseclick on the checkerboard.
    // @citation Adapted from Alfredo Velasco's "XOXOStdDraw" getMouseInput
    // method from video. Specifically,the formula to get board coordinates
    // from a mouse click, labelled as "squareCol" and "squareRow" below.
    // https://www.loom.com/share/9fc900e953b64543a595ff3045abf930
    public Tile mouseToTile() {
        // Pause to prevent NullExceptions
        StdDraw.pause(120);

        if (StdDraw.isMousePressed()) {
            // Get x-coordinate from mouse click
            int squareCol = Math.min(SIZE - 1, (int) StdDraw.mouseX()
                    / (canvasWidth / SIZE));
            // Get y-coordinate from mouse click
            int squareRow = Math.min(SIZE - 1, (int) StdDraw.mouseY()
                    / (canvasHeight / SIZE));

            // return tile at mouse click
            return game.getBoard()[squareRow][squareCol];
        }
        return null;
    }

    // Repaint the board after a move is executed
    // @citation Adapted from Alfredo Velasco's "XOXOStdDraw" paint() method. In this
    // case, the creation of circles and a cross-shape to define a kinged
    // piece is very similar to the "+" and "o" he used in the video.
    // https://www.loom.com/share/9fc900e953b64543a595ff3045abf930
    public void draw() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);

        // Draw checkerboard pattern
        for (int i = 0; i < 800; i += 100) {
            for (int j = 0; j < 800; j += 100) {
                StdDraw.line(j, 0, j, 800);
                StdDraw.line(0, j, 800, j);
                if (i % 200 == 100) {
                    if (j % 200 == 100)
                        StdDraw.filledSquare(i + 50, j + 50, 50);
                }
                if (i % 200 == 0) {
                    if (j % 200 == 0)
                        StdDraw.filledSquare(i + 50, j + 50, 50);
                }
            }
        }

        // Determine piece sizes (circles)
        int shapeSize = (int) (0.45 * SQUARE_SIZE);
        Tile[][] board1 = game.getBoard();

        // Initialize board with pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board1[i][j].isAPiecePresent()) {
                    int size2 = SQUARE_SIZE / 2;
                    boolean isPlayer1 = board1[i][j].getCurrentPiece()
                                                    .getPlayer().toString().equals("player1");
                    // Color piece as color associated with piece's player
                    if (isPlayer1)
                        StdDraw.setPenColor(game.getPlayerColor(1));
                    else
                        StdDraw.setPenColor(game.getPlayerColor(2));

                    // @citation Draw pieces with respective player's colors, adapted from
                    // Alfredo Velasco https://www.loom.com/share/9fc900e953b64543a595ff3045abf930
                    StdDraw.filledCircle(j * SQUARE_SIZE + size2,
                                         i * SQUARE_SIZE + size2, shapeSize);

                    if (board1[i][j].getCurrentPiece().isKinged()) {
                        StdDraw.setPenColor(Color.WHITE);

                        // Cross pattern added to a "kinged" piece, adapted
                        // from Alfredo Velasco
                        StdDraw.filledRectangle(j * SQUARE_SIZE + size2,
                                                i * SQUARE_SIZE + size2,
                                                SQUARE_SIZE / 3, SQUARE_SIZE / 10);
                        StdDraw.filledRectangle(j * SQUARE_SIZE + size2,
                                                i * SQUARE_SIZE + size2,
                                                SQUARE_SIZE / 10, SQUARE_SIZE / 3);
                    }
                }

            }
        }
        StdDraw.show();
    }
}
