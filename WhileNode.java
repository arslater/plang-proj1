//@formatter:off
public class WhileNode extends Node
{
  public StatementsNode statements;
  public StatementsNode conditions;
  public WhileNode (StatementsNode childNodes, StatementsNode  statementNodes)
  {
    conditions = childNodes;
    statements = statementNodes;
    System.out.println("W:Conditions: "+conditions.children.element());
    System.out.println("W:Statements: "+statements.children.element());
  }
  public char GetType() { return 'w';}
}