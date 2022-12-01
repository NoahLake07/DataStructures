import acm.program.GraphicsProgram;
import acm.graphics.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import com.prog2.stack.Stack;

public class Calculator extends GraphicsProgram {

    private GLabel display = new GLabel("");

    @Override
    public void init() {
        this.getMenuBar().setVisible(false);
        this.setBackground(new Color(225, 225, 225));
        this.setSize(300,300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new acm.gui.TableLayout(5, 4));
        GRect calculateBox = new GRect(getWidth(), 50);
        calculateBox.setFilled(true);
        calculateBox.setFillColor(new Color(225, 148, 69));
        calculateBox.setColor(calculateBox.getFillColor());

        String[] labels = { "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "(", "0", ")", "/",
                "C", "<", "=", "."};

        for (int i = 0; i < 20; i++) {
            JButton button = new JButton(labels[i]);
            buttonPanel.add(button);
        }

        add(buttonPanel, 60,100);
        add(calculateBox,0,0);

        addActionListeners();

        display.setFont("Times-bold-24");
        add(display, 18, 36);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){

            case "=": //evaluates
                System.out.flush();  //clears console

                int response = evaluate(display.getLabel());

                StringBuilder ops = new StringBuilder();
                ops.append(response);

                String output = ops.toString();
                display.setLabel(output);
                break;

            case "C":
                display.setLabel("");
                break;

            case "<":
                if (display.getLabel().length() >0) {
                    StringBuilder sb = new StringBuilder(display.getLabel());
                    sb = sb.deleteCharAt(display.getLabel().length()-1);
                    display.setLabel(sb.toString());
                }

            default:
                display.setLabel(display.getLabel() + ae.getActionCommand());
        }
    }


    private boolean precedence(char peek, char ch){
        if ((peek == '*' || peek == '/') && (ch == '+' || ch == '-')){
            return true;
        }

        if (peek != ch && peek != '('){
            return true;
        }

        return false;
    }

    private int calculate(char op, int a, int b){
        switch (op) {
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                return a / b;
            }
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            default -> {
                postMessage("Operator Error");
                return 0;
            }
        }
    }

    private int evaluate(String input){
        int strlen = input.length();

        // make the stacks
        Stack<Integer> ns = new Stack<>();
        Stack<Character> op = new Stack<>();

        int i = 0;
        char ch;

        // populating the stacks
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < input.length(); j++) {
            sb.append(input.charAt(j));
        }

        // evaluation of stacks
        if (!input.isEmpty()){
            // loop across the given string
            while (i < strlen){
                ch = input.charAt(i++);

                if (ch == ' '){
                    i++;
                    continue;
                }

                if (ch <= '9' && ch >= '0'){
                    // found a valid number
                    StringBuffer s = new StringBuffer();
                    s.append(ch);

                    // multi-digit functionality
                    while (i < strlen && input.charAt(i) <= '9' && input.charAt(i) >= '0'){
                        s.append(input.charAt(i++));
                    }
                    ns.push(Integer.parseInt(new String(s)));

                } else if (ch == '('){
                    op.push(ch);

                } else if (ch == ')'){

                    // calculate everything inside the parenthesis (PEMDAS)
                    while(op.peek() != '('){
                        ns.push(calculate(op.pop(), ns.pop(), ns.pop()));
                    }
                    op.pop();

                } else {
                    if (op.isEmpty()){
                        op.push(ch);
                    } else if (precedence(op.peek(), ch)){
                        // checking for precedence
                        ns.push(calculate(op.pop(), ns.pop(), ns.pop()));
                        op.push(ch);
                    } else {
                        // we have found a basic operator
                        op.push(ch);
                    }
                }
            }

            // calculate what's left
            while(!op.isEmpty()){
                ns.push(calculate(op.pop(), ns.pop(), ns.pop()));
            }

            return ns.pop();
        } else {
            postMessage("uh-oh.. Something isn't right.");
            return 0;
        }
    }

    private void postMessage(String s){
        System.out.println(s);
        pause(250);
    }
}