//@formatter:off
import java.util.*;
import java.lang.*;

public class Main {

  public static void main(String[] args) {
    Queue<Node> adds = new LinkedList<Node>();
    adds.add(new TwoOperandNode('+',new RegisterOperand(0),new RegisterOperand(1)));


    //Visitor.Visit

    Queue<Node> subs = new LinkedList<Node>();
    subs.add(new TwoOperandNode('-',new RegisterOperand(0),new RegisterOperand(1)));
    subs.add(new TwoOperandNode('-',new AddressOperand(1000),new AddressOperand(2000)));

    Queue<Node> wyle = new LinkedList<Node>();
    wyle.add(new TwoOperandNode('-',new RegisterOperand(12),new RegisterOperand(13)));
    wyle.add(new TwoOperandNode('-',new RegisterOperand(13),new RegisterOperand(14)));
    wyle.add(new TwoOperandNode('-',new RegisterOperand(14),new RegisterOperand(15)));
    wyle.add(new WhileNode(new StatementsNode(adds),new StatementsNode(subs)));

    Queue<Node> baseNodes = new LinkedList<Node>();
    baseNodes.add(new TwoOperandNode('-',new RegisterOperand(420),new RegisterOperand(69)));
    baseNodes.add(new TwoOperandNode('-',new AddressOperand(1000),new AddressOperand(2000)));
    baseNodes.add(new WhileNode(new StatementsNode(wyle),new StatementsNode(adds)));


    //Queue<Node> baseNodes = new LinkedList<Node>();
    //baseNodes.add(new WhileNode(new StatementsNode(wyle),new StatementsNode(subs)));
    Node base = new StatementsNode(baseNodes);

    Visitor.Visit(base);
    System.out.println("Second visitor call---------------------");
  }
}
