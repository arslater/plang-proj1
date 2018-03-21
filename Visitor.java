//@formatter:off
import java.util.Queue;

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
    static void printList(Queue<Node> listOfNodes)
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

                    System.out.print("V_pL_if_f: "+((RegisterOperand)reg1)
                            .GetRegister());
                    System.out.print("  "+condition.GetType());
                    System.out.println(" "+((RegisterOperand)reg2)
                            .GetRegister());
                }
                for ( Node condition: ((WhileNode)item).statements
                    .children )
                {
                    System.out.print("V_pL_if_f: "+((TwoOperandNode)
                            condition).operand1);
                    System.out.print("  "+condition.GetType());
                    System.out.println(" "+((TwoOperandNode)
                            condition).operand2);
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