import java.util.Scanner;
public class TicTacToe
{   
    static char ai='O';
    static char human='X';
    public static char[][] getBoard()
    {
        char[][] board=new char[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=' ';
            }
        }
        return board;
    }
    public static void printboard(char[][] board)
    {
        System.out.println();
        for(int i=0;i<3;i++)
        {
            System.out.println(" ");
            for(int j=0;j<3;j++)
            {
                System.out.print(board[i][j]);
                if(j<2) System.out.print(" | ");
            }
            System.out.println();
            if(i<2) System.out.print("---------");
        }
        System.out.println();
    }
    public static boolean Winner(char[][] board, char currentPlayer)
    {
     for(int i=0;i<3;i++)
     {
         if((board[i][0]==currentPlayer  && board[i][1]==currentPlayer && board[i][2]==currentPlayer)||
            (board[0][i]==currentPlayer && board[1][i]==currentPlayer && board[2][i]==currentPlayer))
             return true;
     }
         if((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)||
             (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer))
             return true;

             return false;
    }   
    public static boolean isDraw(char[][] board)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]==' ')
                    return false;
            }
        }
        return true;
    }
   public static int evaluate(char[][] board)
   {
    if(Winner(board,human)) return -1;
    if(Winner(board, ai)) return 1;
    return 0;
   }
   public static int minimax(char[][] board,boolean isMax)
   {
    int score=evaluate(board);
    if(score !=0)
        return score;
    if(isDraw(board))
        return 0;
    if(isMax)
    {
      int best=-1000;
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
            if(board[i][j]==' ')
            {
                board[i][j]=ai;
                best=Math.max(best,minimax(board, false));
                board[i][j]=' ';
            }
        }
    }
     return best;
    }
    else 
    {
         int best=1000;
        for(int i=0;i<3;i++)
        {
          for(int j=0;j<3;j++)
          {
            if(board[i][j]==' ')
            {
                board[i][j]=human;
                best=Math.min(best,minimax(board, true));
                board[i][j]=' ';
            }
          }
        }
     return best;
    }
   }  
   public static int[] bestmovefind(char[][]board)
   {
    int bestval=-1000;
    int[] move=new int[2];
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
        {
           if(board[i][j]==' ')
           {
             board[i][j]=ai;
             int bestmove=minimax(board, false);
             board[i][j]=' ';
             if(bestmove>bestval)
             {
                move[0]=i;
                move[1]=j;
                bestval=bestmove;
             }
           }
        }
    }
    return move;
   }
public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        char board[][]=getBoard();
        //System.out.println();
        System.out.println("This is a terminal Based Tic Tac Toe Game");
        System.out.println("       Lets Start");
        System.out.print("Enter Your Name: ");
        String name=sc.nextLine();
        System.out.println();
        System.out.println("Your move will be X");
        char currentPlayer=human;
        while(true)
        {
          printboard(board);
          if(currentPlayer==human)
          { 
            System.out.print("Enter your move row n colm: ");
            int r=sc.nextInt();
            int c=sc.nextInt();
            if(!(r<3 && r>=0 && c>=0 && c<3 && board[r][c]==' '))
            {
                System.out.println("Its a Invalid Move. Try again");
                continue;
            }
            board[r][c]=human;
          }
          else 
          {
            System.out.println("  Computer Move");
            System.out.println("Computer move complete. Now your turn");
            int[]move=bestmovefind(board);
            board[move[0]][move[1]]=ai;
          }

          if(Winner(board, currentPlayer))
          {
            printboard(board);
            System.out.println();
            System.out.println("   Game Over   ");
            System.out.println("The Winner is: "+ ((currentPlayer==human)?name:"Computer"));
            break;
          }
          if(isDraw(board))
          {
            printboard(board);
            System.out.println("Its a draw!!!!");
            break;
          }
          currentPlayer=(currentPlayer==human)?ai:human; 
        }     
        }      
}