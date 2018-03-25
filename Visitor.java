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
    static void Visit(Queue<Node> listOfNodes)
    {
      System.out.println("--------------");
      Iterator <Node> iter = listOfNodes.iterator();
      Visitor.parseNodes(listOfNodes, iter);
      System.out.println("--------------");
      Visitor.uglyCode(listOfNodes);

    }
    static void parseNodes(Queue<Node> listOfNodes, Iterator <Node> iter)
    {
        if( iter.hasNext())
        {
            Queue<Node> nodeChildren = null;
            Iterator <Node> childIter = null;

            Node curNode = iter.next();

            // int hasChildren = 0;
            if( curNode.GetType() == 'w')
            {
                nodeChildren = ((WhileNode)curNode).conditions.children;
                childIter    = nodeChildren.iterator();
                 System.out.println("THIS IS A WHILE NODE AND THE FOLLOWING " +
                        "ARE ITS CHILDREN");

                parseOperands(nodeChildren, childIter);

            }
            System.out.println( "## "+curNode);
            parseNodes(listOfNodes, iter);
        }
    }
    public static void parseOperands(Queue <Node> nodeChildren,
                                     Iterator<Node> childIter)
    {
        if(childIter != null && childIter.hasNext())
        {
            Node curNode = childIter.next();
            //Node condition = nodeChildren.element();

            Operand reg1 = ((TwoOperandNode)curNode).operand1;
            Operand reg2 = ((TwoOperandNode)curNode).operand2;

            printRegs(reg1,reg2,curNode);
            parseOperands(nodeChildren,childIter);
        }
    }
    public static void printRegs(Operand reg1, Operand reg2,
                                Node oper)
    {
        System.out.print("V_pR: R"+((RegisterOperand)reg1).GetRegister());
        System.out.print(" "+oper.GetType());
        System.out.println(" R"+((RegisterOperand)reg2).GetRegister());
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

                    printRegs(reg1,reg2,condition);
                }
                for ( Node condition: ((WhileNode)item).statements
                    .children )
                {
                     Operand reg1 = ((TwoOperandNode)condition).operand1;
                     Operand reg2 = ((TwoOperandNode)condition).operand2;

                    if( reg1.GetType() != 'A' && reg2.GetType() != 'A')
                    {
                        printRegs(reg1,reg2,condition);
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