//@formatter:off
import java.util.Iterator;import java.util.List;import java.util.Queue;

public class Visitor
{

    //private Queue<Node> children;
    public static void Visit(Node base)
    {
        //(StatementsNode)base;
        //if( base.GetType() == 's')
        //{
          //  System.out.println("V:The first child is: "+((StatementsNode)
           //         base).children.remove());
        //}
        //if( ((StatementsNode)base).children.peek() != null)
        //{
         //   base=((StatementsNode)base).children.remove();
          //  Visit(base);
        //}
    }
    private int lineNum;

    static void Visit(Queue<Node> listOfNodes)
    {
      int lineNum = 0;
      System.out.println("-------^^^-------");
      Iterator <Node> iter = listOfNodes.iterator();
      int i = Visitor.parseNodes(lineNum, iter);
      System.out.println("-------$$$-------"+i);
      Visitor.uglyCode(listOfNodes);

    }
    static int parseNodes(int lineNum, Iterator <Node> iter)
    {
        if( iter.hasNext())
        {
            Queue<Node> nodeChildren = null;
            Iterator <Node> childIter = null;
            int i = 8;

            Node curNode = iter.next();

            // int hasChildren = 0;
            if( curNode.GetType() == 'w')
            {
                /////////////////////////////////
                // Conditions
                nodeChildren = ((WhileNode)curNode).conditions.children;
                childIter    = nodeChildren.iterator();
                lineNum = parseOperands(lineNum, childIter);
                i = lineNum;

                System.out.println( "## "+lineNum);

                /////////////////////////////////
                // Statements
                nodeChildren = ((WhileNode)curNode).statements.children;
                childIter    = nodeChildren.iterator();
                //parseOperands(parseOperands(lineNum, childIter), childIter);
                lineNum = parseOperands(lineNum, childIter);
            }
            //System.out.println( "## "+lineNum);
            //lineNum++;
            return (parseNodes(lineNum+1, iter));
        }
        return lineNum;
    }
    public static int parseOperands(int lineNum,
                                     Iterator<Node> childIter)
    {
        if(childIter != null && childIter.hasNext())
        {
            Node curNode = childIter.next();
            //Node condition = nodeChildren.element();

            Operand reg1 = ((TwoOperandNode)curNode).operand1;
            Operand reg2 = ((TwoOperandNode)curNode).operand2;

            if( reg1.GetType() != 'A' && reg2.GetType() != 'A')
            {
                lineNum = printRegs(reg1,reg2,curNode,lineNum);
            }
            else
            {
                long addr1 = ((AddressOperand)reg1).GetAddress();
                long addr2 = ((AddressOperand)reg1).GetAddress();
                //System.out.print("What's this? "+reg1.GetType());
                System.out.println("V_pR: R"+addr1+" "+curNode.GetType()+" " +
                        "R"+addr2);
            }
            return (parseOperands(lineNum+1,childIter));
        }
        return lineNum;
    }
    public static int printRegs(Operand reg1, Operand reg2,
                                Node oper, int lineNum)
    {
        System.out.print(lineNum+":V_pR: R"+((RegisterOperand)reg1).GetRegister
                ());
        System.out.print(" "+oper.GetType());
        System.out.println(" R"+((RegisterOperand)reg2).GetRegister());

        return lineNum;
    }

    public static void uglyCode(Queue<Node> listOfNodes)
    {
        for ( Node item : listOfNodes )
         {
             System.out.println("V_pL: "+item+"----"+item.GetType());
             if(item.GetType() == 'w')
            {
                //////////////////////
                // whilenode
                for ( Node condition: ((WhileNode)item).conditions
                    .children )
                {
                    Operand reg1 = ((TwoOperandNode)condition).operand1;
                    Operand reg2 = ((TwoOperandNode)condition).operand2;

                    printRegs(reg1,reg2,condition,0);
                }
                for ( Node condition: ((WhileNode)item).statements
                    .children )
                {
                     Operand reg1 = ((TwoOperandNode)condition).operand1;
                     Operand reg2 = ((TwoOperandNode)condition).operand2;

                    if( reg1.GetType() != 'A' && reg2.GetType() != 'A')
                    {
                        printRegs(reg1,reg2,condition,0);
                    }

                }

                //System.out.println("V_pl_if1: "+((WhileNode)item).statements
                //    .children);
            }
         }

    }
}

      /*  WhileNode myWhile;
      for (Node item : givenNodes)
      {
          if(item.GetType() instanceof )
          {
              System.out.println("V_dW_if: by golly, there's a while here");
              System.out.println("V_dW: "+i);
          }
          System.out.println("V_dW: "+item.toString());
      }
      return "works";
    }
}
*//*
if (someOperand.GetType() == 'R’)
    int x = ((RegisterOperand)someOperand).GetRegister()
 */