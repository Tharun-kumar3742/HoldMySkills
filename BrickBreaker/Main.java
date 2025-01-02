import java.util.*;
public class Main {
    
    public static void main(String[] args)
    {
        BrickBreaker brickbreaker = new BrickBreaker(7,7);
        brickbreaker.placeBricks(2,2,2);
        brickbreaker.placeBricks(2,3,2);
        brickbreaker.placeBricks(2,4,2);
        brickbreaker.placeBricks(3,2,2);
        brickbreaker.placeBricks(3,3,2);
        brickbreaker.placeBricks(3,4,2);

        while(true)
        {
            brickbreaker.printGameBoard();

            if(brickbreaker.getBallLife()<=0)
            {
                System.out.println("Ball Life Over");
                System.exit(0);
            }
            System.out.println("Enter the Ball Direction: ");
            String direction = new Scanner(System.in).next();

            switch(direction)
            {
                case "lt" ->
                {
                    int[] ballPos = brickbreaker.getBallPosition();
                    brickbreaker.initiateBall(ballPos[0],ballPos[1],-1,-1);
                    
                }
                case "rt" ->
                {
                    int[] ballPos = brickbreaker.getBallPosition();
                    brickbreaker.initiateBall(ballPos[0],ballPos[1],-1,1);
                }
                case "st" ->
                {
                    int[] ballPos = brickbreaker.getBallPosition();
                    brickbreaker.initiateBall(ballPos[0],ballPos[1],-1,0);
                }
                default ->
                {
                    System.out.println("choose vaild direction");
                }
            }
        }
    }
}
