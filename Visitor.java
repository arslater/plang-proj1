//@formatter:off
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Visitor
{
    public static void Visit(Node base)
    {
        int lineNum = 0;

        switch (base.GetType())
        {
            case 'w':
                parseWhile(base,lineNum);
                break;
            case 's':
                parseStatements(base,lineNum);
                break;
            case 'R':
                parseArgs(base,lineNum);
                break;
            case 'A':
                parseAddress(base,lineNum);
                break;
        }
    }
    private static int parseStatements(Node thisNode,int lineNum)
    {
        Queue<Node> nodeQueue = ((StatementsNode)thisNode).children;
        Iterator<Node> iter   = nodeQueue.iterator();
        lineNum = parseQueue(iter, lineNum);
        return lineNum;
    }
    private static int parseWhile(Node thisNode,int lineNum)
    {
        Node statements = ((WhileNode)thisNode).statements;
        Node conditions = ((WhileNode)thisNode).conditions;

        lineNum = parseStatements(statements,lineNum);
        parseStatements(conditions, lineNum);
        return lineNum;
    }
    private static int parseQueue(Iterator<Node>iter, int lineNum)
    {
        Node thisNode        = iter.next();

        if(thisNode.GetType() == 'w')
            lineNum = parseWhile(thisNode,lineNum);
        else if(thisNode.GetType() == 's')
            lineNum = parseStatements(thisNode,lineNum);
        else
        {
            ///////////////////////////////
            // If it gets here, it must be either a 2 operand Node or an Address Node
            //System.out.println("You have reached the end of your journey");
            lineNum = parseArgs(thisNode,lineNum);
        }
        if(iter.hasNext())
            lineNum = (parseQueue(iter,(lineNum+1)));
        return lineNum;
    }
    private static int parseArgs(Node thisNode, int lineNum)
    {
        int oper = thisNode.GetType();

        Operand reg1 = ((TwoOperandNode)thisNode).operand1;
        Operand reg2 = ((TwoOperandNode)thisNode).operand2;

        lineNum = printOut(reg1,reg2,oper, lineNum);
        return lineNum;
    }
    private static int printOut(Operand reg1, Operand reg2, int oper,int lineNum)
    {
        System.out.print(lineNum+": ");

        switch (oper)
        {
            case '-':
                System.out.print("sub ");
                break;
            case '+':
                System.out.print("add ");
                break;
        }
        if(reg1.GetType() != 'A' && reg2.GetType() != 'A')
        {
            System.out.print("R"+((RegisterOperand)reg1).GetRegister());
            System.out.println(",R"+((RegisterOperand)reg2).GetRegister());
        }
        else
        {
            long addr1 = ((AddressOperand)reg1).GetAddress();
            long addr2 = ((AddressOperand)reg2).GetAddress();

            System.out.println("$"+addr1+",$"+addr2);
        }
        return lineNum;
    }
    private static void parseAddress(Node thisNode,int lineNum){}
    //private int getLineNum()
}
/*
    //private Queue<Node> children;
    public static void Visit(Node base)
    {
        if( ((StatementsNode)base).children.peek() != null)
        {
         int lineNum=0;
            Queue<Node>childNodes=((StatementsNode)base).children;
            Iterator <Node> iter = childNodes.iterator();
            int i = Visitor.parseNodes(lineNum, iter,0);
            System.out.println("-------$$$-------"+i);
        }
    }
    static int parseNodes(int lineNum, Iterator <Node> iter, int jNum)
    {
        if( iter.hasNext())
        {
            Queue<Node> nodeChildren = null;
            Iterator <Node> childIter = null;

            Node curNode = iter.next();

            // int hasChildren = 0;
            if( curNode.GetType() == 'w')
            {
                /////////////////////////////////
                // Conditions
                nodeChildren = ((WhileNode)curNode).conditions.children;
                childIter    = nodeChildren.iterator();

                lineNum = parseOperands(lineNum, childIter);

                System.out.println(lineNum+": bne $00000000");
                lineNum++;

                /////////////////////////////////
                // Statements
                nodeChildren = ((WhileNode)curNode).statements.children;
                childIter    = nodeChildren.iterator();
                //parseOperands(parseOperands(lineNum, childIter), childIter);
                lineNum = parseOperands(lineNum, childIter);

                System.out.println(lineNum+": jmp $"+jNum);
                jNum = 0;
                lineNum++;
            }
            else
            {
                Operand reg1 = ((TwoOperandNode)curNode).operand1;
                Operand reg2 = ((TwoOperandNode)curNode).operand2;

                jNum = lineNum;
                lineNum = printRegs(reg1,reg2,curNode,lineNum)+1;

            }
            return (parseNodes(lineNum, iter,jNum));
        }
        return lineNum;
    }
    public static int parseOperands(int lineNum,
                                     Iterator<Node> childIter)
    {
        if(childIter != null && childIter.hasNext())
        {
            Node curNode = childIter.next();
            if (curNode.GetType() != 'w')
            {
                //Node condition = nodeChildren.element();

                Operand reg1 = ((TwoOperandNode)curNode).operand1;
                Operand reg2 = ((TwoOperandNode)curNode).operand2;

                lineNum = printRegs(reg1,reg2,curNode,lineNum);

                return (parseOperands(lineNum+1,childIter));
            }
        }
        return lineNum;
    }
    public static int printRegs(Operand reg1, Operand reg2,
                                Node oper, int lineNum)
    {
        System.out.print(lineNum+": ");

        switch (oper.GetType())
        {
            case '-':
                System.out.print("sub ");
                break;
            case '+':
                System.out.print("add ");
                break;
        }
        if(reg1.GetType() != 'A' && reg2.GetType() != 'A')
        {
            System.out.print("R"+((RegisterOperand)reg1).GetRegister());
            System.out.println(",R"+((RegisterOperand)reg2).GetRegister());
        }
        else
        {
            long addr1 = ((AddressOperand)reg1).GetAddress();
            long addr2 = ((AddressOperand)reg2).GetAddress();

            System.out.println("$"+addr1+",$"+addr2);
        }
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

*/