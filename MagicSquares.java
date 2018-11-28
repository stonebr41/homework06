import java.util.*;

public class MagicSquares
{
  
  public static void main(String [] args)
  {
    Scanner s = new Scanner(System.in);
    MagicSquares m = new MagicSquares();
    //System.out.println(m.testArrayListMagicSquare(s));
    /*int [] [] ms = m.createArrayMagicSquare(7);
    for(int i = 0; i < ms.length; i++)
    {
      for(int j = 0; j < ms.length; j++)
      {
        System.out.print(ms[i][j] + " " );
      }
      System.out.println();
    }
    */
    
    ArrayList<ArrayList<Integer>> ms = m.createArrayListMagicSquare(7);
    for(int i = 0; i < ms.size(); i++)
    {
      for(int j = 0; j < ms.size(); j++)
      {
        System.out.print(ms.get(i).get(j) + " ");
      }
      System.out.println();
    }
    
    
  }
  
  
  
  
  public boolean testArrayMagicSquare(Scanner s)
  {
    //populate msay
    System.out.println("Enter numbers. Enter 'q' to stop.");
    int [] sqr = new int[1];
    int num = -1;
    String input = s.nextLine();
    try
    {
      num = Integer.parseInt(input);
    }
    catch(Exception e)
    {
      System.out.println("INVALID INPUT.");
    }
    sqr[0] = num;
    int values = 1;
    while(true)
    {
      if(values == 16)
      {
        System.out.println("16 Values Reached. Let's see if they're proper.");
        boolean broken = false;
        for(int i = 1; i <= 16; i++)
        {
          if(!broken)
          {
            boolean containsThis = false; //guilty until proven innocent
            for(int j = 0; j < sqr.length; j++)
            {
              if(sqr[j] == i) containsThis = true;
            }
            if(!containsThis)
            {
              System.out.println("That's no good. You need every integer from 1 to 16 and you're missing " + i + ".");
              System.out.println("Starting over...");
              
              sqr = new int [0];
              values = 0;
              broken = true; //because break will kill both loops.
            }
          }
          
        }
        
        
        
        if(!broken)
        {
          System.out.println("Everything checks out! Now we see if it's a magic square.");
          System.out.println(Arrays.toString(sqr));
          break;
        }
      }
      System.out.println(Arrays.toString(sqr));
      input = s.nextLine();
      if(input.equals("q")) break;
      try
      {
        num = Integer.parseInt(input);
        int [] temp = sqr;
        
        
        boolean contains = false;
        for(int i : temp)
        {
          if(i == num) contains = true;
        }
        if(contains == false)
        {
          sqr = new int[sqr.length + 1];
          for(int i = 0; i < temp.length; i++) //iterate through clone
          {
            sqr[i] = temp[i];
          }
          sqr[sqr.length-1] = num;
          ++values;
        }
        
        
      }
      catch(Exception e)
      {
        System.out.println("INVALID INPUT.");
      }
      
    } //INPUT/QUALITY CONTROL LOOP ENDS
    
    int [][] ms = new int [4][4];
    //keeping track of where we are in sqr
    int index = 0;
    for(int i = 0; i < ms.length; i++)
    {
      for(int j = 0; j < ms[i].length; j++)
      {
        
        ms[i][j] = sqr[index];
        ++index;
      }
      
    }
    
    for(int i = 0; i < ms.length; i++)
    {
      System.out.println(Arrays.toString(ms[i]));
    }
    
    
    
    //CHECK HORIZONTALS
    int prevsum = 0;
    for(int i = 0; i < 4; i++)
    {
      int sum = 0;
      for(int j : ms[i])
      {
        sum += j;
      }
      System.out.println(prevsum + " --> " + sum);
      if(prevsum != sum && prevsum != 0)
      {
        return false;
      }
      prevsum = sum; //if it doesn't have the same sum as the previous one then not all sums are the same
      
      
    }
    
    
    //CHECK VERTICAL
    for(int i = 0; i < 4; i++)
    {
      int sum = 0;
      for(int j = 0; j < 4; j++)
      {
        sum += ms[j][i];
      }
      System.out.println(prevsum + " --> " + sum);
      if(prevsum != sum && prevsum != 0)
      {
        
        return false;
      }
      prevsum = sum;
    }
    
    //CHECK DIAGONALS
    int sum = 0;
    for(int i = 0; i < 4; i++)
    {
      sum += ms[i][i];
    }
    if(prevsum != sum) return false;
    
    sum = 0;
    for(int i = 3; i >= 0; i--)
    {
      sum += ms[i][i];
    }
    if(prevsum != sum) return false;
    return true;
    
  }
  
  public boolean testArrayListMagicSquare(Scanner s)
  {
    ArrayList<Integer> sqr = new ArrayList<Integer>();
    int values = 0;
    while(true)
    {
      if(values == 16)
      {
        System.out.println("16 Values Reached. Let's see if they're proper.");
        boolean broken = false;
        for(int i = 1; i <= 16; i++)
        {
          if(!broken)
          {
            boolean containsThis = false; //guilty until proven innocent
            for(int j = 0; j < sqr.size(); j++)
            {
              if(sqr.get(j) == i) containsThis = true;
            }
            if(!containsThis)
            {
              System.out.println("That's no good. You need every integer from 1 to 16 and you're missing " + i + ".");
              System.out.println("Starting over...");
              
              sqr = new ArrayList<Integer>();
              values = 0;
              broken = true; //because break will kill both loops.
            }
          }
          
        }
        
        
        
        if(!broken)
        {
          System.out.println("Everything checks out! Now we see if it's a magic square.");
          System.out.println(sqr);
          break;
        }
      }
      int num = 0;
      System.out.println(sqr);
      String input = s.nextLine();
      if(input.equals("q")) break;
      try
      {
        num = Integer.parseInt(input);
        
        boolean contains = false;
        for(int i : sqr)
        {
          if(i == num) contains = true;
        }
        if(contains == false)
        {
          sqr.add(num);
          ++values;
        }
        
        
      }
      catch(Exception e)
      {
        System.out.println("INVALID INPUT.");
      }
      
    }
    
    //WRAP TO SQUARE
    System.out.println("SQR: " + sqr);
    ArrayList<ArrayList<Integer>> ms = new ArrayList<ArrayList<Integer>>();
    for(int i = 0; i < 4; i++) //fill up with empty msaylists
    {
      ms.add(new ArrayList<Integer>());
      System.out.println(ms.get(i));
    }
    
    int index = 0;
    for(int i = 0; i < 4; i++)
    {
      ArrayList<Integer> temp = ms.get(i);
      for(int j = 0; j < 4; j++)
      {
        temp.add(sqr.get(index));
        ++index;
      }
      ms.set(i, temp);
      System.out.println(ms.get(i).get(0));
    }
    

    //CHECK HORIZONTALS
    int prevsum = 0;
    for(int i = 0; i < 4; i++)
    {
      int sum = 0;
      for(int j : ms.get(i))
      {
        sum += j;
      }
      System.out.println(prevsum + " --> " + sum);
      if(prevsum != sum && prevsum != 0)
      {
        return false;
      }
      prevsum = sum; //if it doesn't have the same sum as the previous one then not all sums are the same
      
      
    }
    
    
    //CHECK VERTICAL
    for(int i = 0; i < 4; i++)
    {
      int sum = 0;
      for(int j = 0; j < 4; j++)
      {
        sum += ms.get(j).get(i);
      }
      System.out.println(prevsum + " --> " + sum);
      if(prevsum != sum && prevsum != 0)
      {
        
        return false;
      }
      prevsum = sum;
    }
    
    //CHECK DIAGONALS
    int sum = 0;
    for(int i = 0; i < 4; i++)
    {
      sum += ms.get(i).get(i);
    }
    if(prevsum != sum) return false;
    
    sum = 0;
    for(int i = 3; i >= 0; i--)
    {
      sum += ms.get(i).get(i);
    }
    if(prevsum != sum) return false;

    return true;
  
  }
  
  public int [] [] createArrayMagicSquare(int n)
  {
    int [] [] ms = new int [n][n];
    int row = n/2;
    int column = n-1;
    for(int k = 1; k <= (n*n);)
    {
      if(row == -1 && column == n)
      {
        column = n-2;
        row = 0;
      }
      else
      {
        if(column == n)
        {
          column = 0;
        }
        if(row < 0)
        {
          row = n-1;
          
        }
        
      }
      if(ms[row][column] != 0)
      {
        column -= 2;
        row++;
        continue;
      }
      else
      {
        ms[row][column] = k++;
      }
      column++;
      row--;
    }
    return ms;
  }
  
  public ArrayList<ArrayList<Integer>> createArrayListMagicSquare(int n)
  {
    //create the arraylist
    ArrayList<ArrayList<Integer>> ms = new ArrayList<ArrayList<Integer>>();
    for(int i = 0; i < n; i++)
    {
      ArrayList<Integer> temp = new ArrayList<Integer>();
      for(int j = 0; j < n; j++)
      {
        temp.add(0);
      }
      ms.add(temp);
    }
    
    
    
    
    
    
    int row = n/2;
    int column = n-1;
    for(int k = 1; k <= (n*n);)
    {
      if(row == -1 && column == n)
      {
        column = n-2;
        row = 0;
      }
      else
      {
        if(column == n)
        {
          column = 0;
        }
        if(row < 0)
        {
          row = n-1;
          
        }
        
      }
      if(ms.get(row).get(column) != 0)
      {
        column -= 2;
        row++;
        continue;
      }
      else
      {
        ms.get(row).set(column, k++);
      }
      column++;
      row--;
    }
    return ms;
  }
  
  
  
}