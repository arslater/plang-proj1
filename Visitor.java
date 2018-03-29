//@formatter:off
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
        }
    }
    private static int parseStatements(Node thisNode,int lineNum)
    {
        Queue<Node> nodeQueue = ((StatementsNode)thisNode).children;
        //System.out.print("s:"+thisNode.toString());
        lineNum = parseQueue(nodeQueue, lineNum);
        return lineNum;
    }
    private static int parseWhile(Node thisNode,int lineNum)
    {
        int  i          = 0;
        Node statements = ((WhileNode)thisNode).statements;
        Node conditions = ((WhileNode)thisNode).conditions;

        //System.out.print("w::"+statements.toString());
        //System.out.print("Wc:"+conditions.toString());

        i = lineNum;
        lineNum = parseStatements(conditions,lineNum);
        System.out.println((lineNum)+": bne $00000000");

        lineNum = parseStatements(statements, lineNum+1);

        System.out.println((lineNum)+": jmp $"+(i));
        lineNum++;
        return lineNum;
    }
    private static int parseQueue(Queue<Node> nodeQueue, int lineNum)
    {
        if(nodeQueue.peek() != null)
        {
            Node thisNode        = nodeQueue.remove();
            //System.out.print("**"+thisNode.toString()+"**");
            if(thisNode.GetType() == 'w')
                lineNum = parseWhile(thisNode,lineNum);
            else if(thisNode.GetType() == 's')
                lineNum = parseStatements(thisNode,lineNum);
            else
            {
                lineNum = parseArgs(thisNode,lineNum);
            }
            lineNum = (parseQueue(nodeQueue,(lineNum+1)));

        }
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
            case '*':
                System.out.print("mul ");
                break;
            case '/':
                System.out.print("div ");
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
}