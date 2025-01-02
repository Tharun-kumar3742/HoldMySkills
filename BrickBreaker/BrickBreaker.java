import java.util.*;
public class BrickBreaker
{
    private static String wall = "w";
    private static String brick = "1";
    private static String ground = "g";
    private static String ball = "o";

    private static Map<Integer,Integer>brickswithlife=new HashMap<>();
    private static int[] ballPos = null;
    private static int balllife = 5;

    private String[][] gameBoard = null;

    BrickBreaker(int row,int col)
    {
        gameBoard = new String[row][col];
        prepareBoard();
        gameBoard[row-1][(col/2)]=ball;
        ballPos = new int[]{row-1,col/2};
    }

    public void placeBricks(int row,int col,int life)
    {
        gameBoard[row][col]=brick;
        int exactpos = getExactBallPosition(row,col);
        brickswithlife.put(exactpos,life);
    }

    public void initiateBall(int ballrow, int ballcol, int rowdiretion,int coldirection)
    {
        moveDirection(ballrow,ballcol,rowdiretion,coldirection);
        if(!gameBoard[ballrow][ballcol].equals(ball)) gameBoard[ballrow][ballcol]=ground;
        
    }
    private void moveDirection(int ballrow,int ballcol,int rowdiretion,int coldirection)
    {
        while(!gameBoard[ballrow][ballcol].equals(wall))
        {
            if(gameBoard[ballrow][ballcol].equals(brick))
            {
                ballGoesDown(ballrow, ballcol);
                return;
            }
            movingIllusion(ballrow,ballcol);
            ballrow += rowdiretion;
            ballcol += coldirection;
        }
        wallHit(ballrow,ballcol);

        rowdiretion=0;
        coldirection = coldirection*-1;
        if(coldirection==0)
        {
            ballGoesDown(ballrow+1,ballcol);
        }
        else{
            moveDirection(ballrow, ballcol+coldirection, rowdiretion, coldirection);
        }
    }

    private int getExactBallPosition(int row,int col)
    {
        return (row*gameBoard[0].length)+col +1;
    }
    private void wallHit(int ballRow, int ballCol) {
        gameBoard[ballRow][ballCol] = ball;
        printGameBoard();
        sleepForOneSec();
        gameBoard[ballRow][ballCol] = wall;
    }

    private void ballGoesDown(int ballRow, int ballCol) {
        while(ballRow != gameBoard.length) {
            movingIllusion(ballRow, ballCol); // move the ball
            ballRow++;
        }
        ballPos = new int[]{ballRow-1, ballCol};
        gameBoard[ballPos[0]][ballPos[1]] = ball; // new ball position
    }

    private void movingIllusion(int ballRow, int ballCol) {
        if(gameBoard[ballRow][ballCol].equals(brick)) {
            reduceBrickAndBallLife(ballRow, ballCol);
            if(brickswithlife.get(getExactBallPosition(ballRow, ballCol)) == 0) {
                gameBoard[ballRow][ballCol] = " ";
            }
        }
        else {
            gameBoard[ballRow][ballCol] = ball;
            printGameBoard();
            gameBoard[ballRow][ballCol] = " ";
            sleepForOneSec();
        }
    }

    private void reduceBrickAndBallLife(int ballRow, int ballCol) {
        int exactPosition = getExactBallPosition(ballRow, ballCol);
        balllife--;  // if ball hits brick, ball's life reduces

        if(balllife >= 0) // if ball life is not negative
            brickswithlife.put(exactPosition, brickswithlife.get(exactPosition) - 1);
    }



    private void prepareBoard()
    {
        for(int i=0;i<gameBoard.length;i++)
        {
            for(int j=0;j<gameBoard[0].length;j++)
            {
                if(i==0||j==0||j==gameBoard[0].length-1)
                {
                    gameBoard[i][j] = wall;
                }
                else if(i==gameBoard.length-1)
                {
                    gameBoard[i][j]=ground;
                }
                else{
                    gameBoard[i][j]=" ";
                }
            }
        }
    }
    public void printGameBoard()
    {
        for(String[] str : gameBoard)
        {
            for(String s: str)
            {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
    private void sleepForOneSec()
    {
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.getCause();
        }
    }
    public int[] getBallPosition() {
        return ballPos;
    }
    public int getBallLife()
    {
        return balllife;
    }
}
 