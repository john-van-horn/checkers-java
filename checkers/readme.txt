COS126 Final Project: Implementation

Please complete the following questions and upload this readme.txt to the
TigerFile assignment for the "Final Project Implementation".


/**********************************************************************
 * Basic Information                                                  *
 **********************************************************************/

Name 1: Grace Yoo

NetID 1: gy2895

Name 2: John Van Horn

NetID 2: jv8294

Project preceptor name: John Yang

Project title: Checkers

CodePost link for proposal feedback: https://codepost.io/code/488163

Link to project video: https://www.loom.com/share/folder/49cdc2129fdb44a1a98601053f9df144

Approximate number of hours to complete the final project
Number of hours: 20

/**********************************************************************
 * Required Questions                                                 *
 **********************************************************************/

Describe your project in a few sentences.

Our project is a human vs. human Checkers client. It accepts mouse click input from StdDraw
on an 8 x 8 checkerboard of tile objects to determine a piece and then a tile for that piece
to move. The game continues until one of the players runs out of checkers, following
Conventional checkers rules.


Describe in detail your three features and where in your program
they are implemented (e.g. starting and ending line numbers,
function names, etc.).

1. Our first feature is drawing the 2D board[][] of tile objects to StdDraw. This feature
is implemented in our CheckerGUI class, specifically in the constructer CheckerGUI(Game newGame)
And the draw() method, which is called to redraw the board after a legal move is executed. 
In the checkerGUI class, this code is from lines 7-21 and 51-110. This draws the checkerboard
Pattern of the board as well as the pieces (kinged or not) in the color of their player(s).

2. Our second feature is creating a move from a mouse input on StdDraw. This feature takes place
In the CheckerGUI class and the Player class. In the CheckerGUI class, it includes the 
mouseToTile() method, (lines 28-44) which takes a mouse input and returns the tile associated with
That input. From here, this tile is taken in the chooseMove(CheckerGUI gui) and
choosePiece(CheckerGUI guy) (lines 46-76) in the Player class. choosePiece continually calls 
mouseToTile() until a tile with a piece of that player object is selected, and then chooseMove
Will select a tile for that piece to move to, constituting a possible move. 


3. Our third feature is the game logic for checkers, specifically implemented in the Game class.
The game constructer (lines 15-38) creates a board[][] of tiles and initializes these tiles
Where checker pieces for each player should be. It also creates two players, and sets player1
To begin first. Then, the main method calls play(), (lines 215-226) which continually runs 
the game until a Player has won. play() calls the helper method move(Player p) which takes
A player and executes a move from the player that is legal. It does this by calling two helper
Methods, isLegalMove(Piece p, Tile moveTo) and isLegalJump(Piece p, Tile moveTo) (lines 42-106) 
to determine Whether a move or jump is legal according to the rules of checkers. Then the move
Method calls executeMove(Piece p, Tile moveTo) (lines 161-211) which will update the state of 
the players and the board to reflect the chosen move. Finally move will call the CheckerGUI 
instance to repaint the board.



Describe in detail how to compile and run your program. Include a few example
run commands and the expected results of running your program. For non-textual
outputs (e.g. graphical or auditory), feel free to describe in words what the
output should be or reference output files (e.g. images, audio files) of the
expected output.

Our output is primarily graphical, in that the game of checkers will take place
On a StdDraw window with mouse clicks on it. But, we have some command line outputs
To help the player understand what is going on in the game. 

In order to compile Checkers: "javac-introcs Game.java"
In order to run Checkers: "java-intros Game"

This will start the game and prompt the current player to pick a piece on the StdDraw
Board.



Describe how your program accepts user input and mention the line number(s) at
which your program accepts user input.

Our program accepts user input from mouse clicks on a StdDraw window, specifically in 
the mouseToTile() method in the CheckerGUI class, lines 28-44. From here, various 
methods manipulate the returned tile until a valid move is created.



Describe how your program produces output based on user input (mention line
numbers).

Our program produces output via StdDraw by redrawing the board after a move 
Is executed, as well as command line statements that help the player know if they 
Have successfully completed a legal move, whose turn it is, and whether or not
They have captured a piece.



Describe the data structure your program uses and how it supports your program's
functionality (include the variable name and the line number(s) at which it is
declared and initialized).
Our program uses the very important "Tile[][] board" instance variable. This 
Is used by the Game.java class and the CheckerGUI.java class to compute game logic
And to relate our StdDraw GUI to the board's logic. "Board" is declared in 
Game.java at line 8 and initialized on lines 16-19 and lines 29-34.




List the two custom functions written by your project group, including function
signatures and line numbers; if your project group wrote more than two custom
functions, choose the two functions that were most extensively tested.
1. public boolean isLegalMove(Piece p, Tile moveTo)
	Lines 42-72, in Game.java class

2. public Tile chooseMove(CheckerGUI gui)
	Lines 65-76, in Player.java class

List the line numbers where you test each of your two custom functions twice.
For each of the four tests (two for each function), explain what was being
tested and the expected result. For non-textual results (e.g. graphical or
auditory), you may describe in your own words what the expected result
should be or reference output files (e.g. images, audio files).
1. Test 1 for Function 1 calls testLegality(Piece p, Tile moveTo) (lines 270-280)
	and makes this function call on line 294. It makes a legal move. This method 
is called from Game.java's main method with The command-line argument "--test"

2. Test 2 for Function 1 calls testLegality(Piece p, Tile moveTo) (lines 270-280)
	and makes this function call on line 296. It makes an illegal move. This 
method is called from Game.java's main method with The command-line argument "--test"

3. Our test method for chooseMove(CheckerGUI gui) is called testTile(CheckerGUI gui)
And is on lines 108-118. This method is called from Player.java's main method with 
The command-line argument "--test". After calling this test method, the GUI opens
And the tests are run every time a user clicks, producing a total of 64 possible tests.

4. Look at test #3 for chooseMove ^^


/**********************************************************************
 * Citing Resources                                                   *
 **********************************************************************/

List below *EVERY* resource your project group looked at (bullet lists and
links suffice).

https://www.ultraboardgames.com/checkers/game-rules.php

https://www.loom.com/share/9fc900e953b64543a595ff3045abf930

https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html


Remember that you should *ALSO* be citing every resource that informed your
code at/near the line(s) of code that it informed.

Did you receive help from classmates, past COS 126 students, or anyone else?
If so, please list their names.  ("A Sunday lab TA" or "Office hours on
Thursday" is ok if you don't know their name.)
Yes or no?

No


Did you encounter any serious problems? If so, please describe.
Yes or no?

No



List any other comments here.

N/a


/**********************************************************************
 * Submission Checklist                                               *
 **********************************************************************/

Please mark that you’ve done all of the following steps:
[X] Created a project.zip file, unzipped its contents, and checked that our
    compile and run commands work on the unzipped contents. Ensure that the .zip
    file is under 50MB in size.
[X] Created and uploaded a Loom or YouTube video, set its thumbnail/starting
    frame to be an image of your program or a title slide, and checked that
    the video is viewable in an incognito browser.
[X] Uploaded all .java files to TigerFile.
[X] Uploaded project.zip file to TigerFile.

After you’ve submitted the above on TigerFile, remember to do the following:
[X] Complete and upload readme.txt to TigerFile.
[X] Complete and submit Google Form
    (https://forms.cs50.io/de2ccd26-d643-4b8a-8eaa-417487ba29ab).


/**********************************************************************
 * Partial Credit: Bug Report(s)                                      *
 **********************************************************************/

For partial credit for buggy features, you may include a bug report for at
most 4 bugs that your project group was not able to fix before the submission
deadline. For each bug report, copy and paste the following questions and
answer them in full. Your bug report should be detailed enough for the grader
to reproduce the bug. Note: if your code appears bug-free, you should not
submit any bug reports.

BUG REPORT #1:
1. Describe in a sentence or two the bug.
If a user holds down the mouse on a tile with a piece, a nullpointerexception is created.
This was described as an issue when adapting the code from Alfredo Velasco's video.



2. Describe in detail how to reproduce the bug (e.g. run commands, user input,
   etc.).
On a players turn, hold down the mouse on a player's piece.



3. Describe the resulting effect of bug and provide evidence (e.g.
   copy-and-paste the buggy output, reference screenshot files and/or buggy
   output files, include a Loom video of reproducing and showing the effects of
   the bug, etc.).

If a user does not know how to click the checker and believes they should drag it, etc.
A user will not be able to play the game and they will believe it does not work.



4. Describe where in your program code you believe the bug occurs (e.g. line
   numbers).

In the mouseToTile() method, in CheckerGUI.java lines 28-43.




5. Please describe what steps you tried to fix the bug.

Increased the StdDraw.pause() command to account for longer mouse clicks.



/**********************************************************************
 * Extra Credit: Runtime Analysis                                     *
 **********************************************************************/

You may earn a small amount of extra credit by analyzing the efficiency of one
substantial component of your project. Please answer the following questions if
you would like to be considered for the extra credit for program analysis
(remember to copy and paste your answers to the following questions into the
Google form as well for credit).

Specify the scope of the component you are analyzing (e.g. function name,
starting and ending lines of specific .java file).




What is the estimated runtime (e.g. big-O complexity) of this component?
Provide justification for this runtime (i.e. explain in your own words why
you expect this component to have this runtime performance).




Provide experimental evidence in the form of timed analysis supporting this
runtime estimate. (Hint: you may find it helpful to use command-line
arguments/flags to run just the specified component being analyzed).





/**********************************************************************
 * Extra Credit: Packaging project as an executable .jar file         *
 **********************************************************************/

You may earn a small amount of extra credit by packaging your project as an
executable .jar file. Please answer the following question if you would like to
be considered for this extra credit opportunity (remember to copy and paste your
answers to the following questions into the Google form as well for credit).

Describe in detail how to execute your .jar application (e.g. what execution
command to use on the terminal). Include a few example execution commands and
the expected results of running your program. For non-textual outputs
(e.g. graphical or auditory), feel free to describe in words what the output
should be or reference output files (e.g. images, audio files) of the expected
output.



