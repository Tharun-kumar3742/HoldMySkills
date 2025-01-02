import java.util.*;
public class SnakeIterative {
    private char[][]snakeboard = null;
    private Queue<Node>queue = new LinkedList<Node>();
    private Queue<Node>food = new LinkedList<Node>();
    SnakeIterative(int row,int col)
    {
        snakeboard = new char[row][col];
        queue.add(new Node(0,0));

        food.add(new Node(1,0));
        food.add(new Node(2,2));
        food.add(new Node(3,4));
        food.add(new Node(5,2));
        food.add(new Node(4,5));
        displayfood(food.poll());
    }
    public void initiateSnake()
    {
        int row = 0,col = 0;
        snakeboard[row][col]='.';

        while (true) {
            printSnake(); 
            System.out.print("Enter the direction: ");
            Scanner sc = new Scanner(System.in);
            char direction = sc.next().charAt(0);
             if(direction =='u')
             {
                snakemove(--row,col);
             }
             if(direction =='d')
             {
                snakemove(++row,col);
             }
             if(direction =='l')
             {
                snakemove(row,--col);
             }
             if(direction =='r')
             {
                snakemove(row,++col);
             }
            
        }
    }
    public void snakemove(int row,int col)
    {
        if(row>=0 && row<snakeboard.length && col>=0 && col<snakeboard[0].length)
        {
            queue.add(new Node(row, col));
            if(snakeboard[row][col]!='O')
            {
                Node node = queue.poll();
                int r = node.getRow();
                int c = node.getColumn();
                snakeboard[r][c]='\0';
            }
            if(snakeboard[row][col]=='O')
            {
                if(food.isEmpty())
                {
                    moveforandprint(row,col);
                    System.out.println("Game Over");
                    System.exit(0);
                }
                displayfood(food.poll());
            }
            if(snakeboard[row][col]=='.')
            {
                System.out.println("Game Over!!");
                System.exit(0);
            }
            moveforandprint(row,col);
        }
        else{
            System.out.println("ivalid move!!");
            System.exit(0);
        }
    }
    public void displayfood(Node node)
    {
        int r = node.getRow();
        int c = node.getColumn();
        snakeboard[r][c] = 'O';
    }

    public void moveforandprint(int row,int col)
    {
        snakeboard[row][col]='.';
        printSnake();
    }


    public void printSnake()
    {
        for(char[] chars:snakeboard)
        {
            for(int j=0;j<snakeboard[0].length;j++)
            {
                System.out.print(chars[j]+" ");
            }
            System.out.println();
        }
    }

}
