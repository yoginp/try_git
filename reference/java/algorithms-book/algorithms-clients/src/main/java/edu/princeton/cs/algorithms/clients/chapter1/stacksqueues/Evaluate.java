/************************************************************************* *  Compilation:  javac Evaluate.java *  Execution:    java Evaluate *  Dependencies: Stack.java * *  Evaluates (fully parenthesized) arithmetic expressions using *  Dijkstra's two-stack algorithm. * *  % java Evaluate  *  ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )  *  101.0  * *  % java Evaulate *  ( ( 1 + sqrt ( 5 ) ) / 2.0 )  *  1.618033988749895 * * * *  Remarkably, Dijkstra's algorithm computes the same *  answer if we put each operator *after* its two operands *  instead of *between* them. * *  % java Evaluate *  ( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )  *  101.0 * *  Moreover, in such expressions, all parentheses are redundant! *  Removing them yields an expression known as a postfix expression. *  1 2 3 + 4 5 * * +  *  * *************************************************************************/package edu.princeton.cs.algorithms.clients.chapter1.stacksqueues;import edu.princeton.cs.algorithms.Stack;import edu.princeton.cs.algorithms.stdlib.StdIn;/** * <b>NOTE:</b> This is Dijkstra's Two-Stack Algorithm for Expression * Evaluation. For simplicity, this code assumes that the expression is fully * parenthesized, with numbers and characters separated by whitespace.<br/> * <br/> * <b>To execute:</b><br/> * java Evaluate<br/> * ( ( 1 + sqrt ( 5.0 ) ) / 2.0 )<br/> * (1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )<br/> *  */public class Evaluate {    public static void main(String[] args) {        Stack<String> ops = new Stack<String>();        Stack<Double> vals = new Stack<Double>();        while (!StdIn.isEmpty()) { // Read token, push if operator.            String s = StdIn.readString();            if (s.equals("("))                ;            else if (s.equals("+"))                ops.push(s);            else if (s.equals("-"))                ops.push(s);            else if (s.equals("*"))                ops.push(s);            else if (s.equals("/"))                ops.push(s);            else if (s.equals("sqrt"))                ops.push(s);            else if (s.equals(")")) {                // Pop, evaluate, and push result if token is ")"                String op = ops.pop();                double v = vals.pop();                if (op.equals("+"))                    v = vals.pop() + v;                else if (op.equals("-"))                    v = vals.pop() - v;                else if (op.equals("*"))                    v = vals.pop() * v;                else if (op.equals("/"))                    v = vals.pop() / v;                else if (op.equals("sqrt"))                    v = Math.sqrt(v);                vals.push(v);            } // Token not operator or paren: push double value.            else                vals.push(Double.parseDouble(s));        }    }}