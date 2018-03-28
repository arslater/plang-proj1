import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Queue<Node> sn1 = new LinkedList<Node>();
        sn1.add(new TwoOperandNode('+',new RegisterOperand(0),new RegisterOperand(1)));
        sn1.add(new TwoOperandNode('+',new RegisterOperand(4),new RegisterOperand(5)));
        sn1.add(new TwoOperandNode('+',new RegisterOperand(5),new RegisterOperand(6)));

        Queue<Node> sn2 = new LinkedList<Node>();
        sn2.add(new TwoOperandNode('*',new RegisterOperand(1),new RegisterOperand(3)));
        sn2.add(new TwoOperandNode('*',new RegisterOperand(1),new RegisterOperand(4)));
        sn2.add(new TwoOperandNode('-',new RegisterOperand(2),new RegisterOperand(5)));
        sn2.add(new TwoOperandNode('-',new AddressOperand(1000),new AddressOperand(2000)));

        Queue<Node> sn3 = new LinkedList<Node>();
        sn3.add(new TwoOperandNode('/',new RegisterOperand(1),new RegisterOperand(3)));
        sn3.add(new TwoOperandNode('*',new RegisterOperand(1),new RegisterOperand(7)));
        sn3.add(new TwoOperandNode('-',new RegisterOperand(7),new RegisterOperand(94)));

        Queue<Node> wyle = new LinkedList<Node>();
        wyle.add(new TwoOperandNode('+',new RegisterOperand(1),new RegisterOperand(2)));
        wyle.add(new TwoOperandNode('*',new RegisterOperand(3),new RegisterOperand(47)));
        wyle.add(new WhileNode(new StatementsNode(sn2),new StatementsNode(sn3)));


        Queue<Node> adds = new LinkedList<Node>();
        adds.add(new TwoOperandNode('+',new RegisterOperand(0),new RegisterOperand(1)));
        adds.add(new TwoOperandNode('+',new RegisterOperand(1),new RegisterOperand(2)));
        adds.add(new TwoOperandNode('+',new RegisterOperand(2),new RegisterOperand(3)));
        adds.add(new TwoOperandNode('+',new RegisterOperand(3),new RegisterOperand(4)));
        adds.add(new TwoOperandNode('+',new RegisterOperand(4),new RegisterOperand(5)));

        Queue<Node> subs = new LinkedList<Node>();
        subs.add(new TwoOperandNode('-',new RegisterOperand(0),new RegisterOperand(1)));
        subs.add(new TwoOperandNode('-',new RegisterOperand(1),new RegisterOperand(2)));
        subs.add(new TwoOperandNode('-',new RegisterOperand(2),new RegisterOperand(3)));
        subs.add(new TwoOperandNode('-',new RegisterOperand(3),new RegisterOperand(4)));
        subs.add(new TwoOperandNode('-',new RegisterOperand(4),new RegisterOperand(5)));
        subs.add(new TwoOperandNode('-',new AddressOperand(1000),new AddressOperand(2000)));

        System.out.println("------------------Test case 1---------");
        Queue<Node> baseNodes = new LinkedList<Node>();
        baseNodes.add(new TwoOperandNode('*',new RegisterOperand(1),new RegisterOperand(2)));
        Node base = new StatementsNode(baseNodes);
        Visitor.Visit(base);
        baseNodes.clear();
        System.out.println("------------------Test case 2---------");
        baseNodes = new LinkedList<Node>();
        baseNodes.add(new WhileNode(new StatementsNode(sn1),new StatementsNode(sn2)));
        base = new StatementsNode(baseNodes);
        Visitor.Visit(base);
        baseNodes.clear();
        System.out.println("------------------Test case 3---------");
        baseNodes.add(new StatementsNode(sn3));
        base = new StatementsNode(baseNodes);
        Visitor.Visit(base);
        baseNodes.clear();
        System.out.println("------------------Test case 4---------");
        baseNodes.add(new WhileNode(new StatementsNode(wyle),new StatementsNode(sn1)));
        base = new StatementsNode(baseNodes);
        Visitor.Visit(base);
        baseNodes.clear();
        System.out.println("------------------Test case 5---------");
        baseNodes.add(new TwoOperandNode('-',new AddressOperand(3333),new AddressOperand(4444)));
        baseNodes.add(new WhileNode(new StatementsNode(adds),new StatementsNode(subs)));
        base = new StatementsNode(baseNodes);
        Visitor.Visit(base);
    }
}
