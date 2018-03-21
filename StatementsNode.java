//@formatter:off
import java.util.*;


public class StatementsNode extends Node
{
  public Queue<Node> children;
  public StatementsNode ( Queue<Node> statementNodes)
  {
     children = statementNodes;
     System.out.println("SN:Statements Node:"+children.element());
  }
  public char GetType()
  {
      return 's';
  }
}