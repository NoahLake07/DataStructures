import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import com.prog2.stack.Stack;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class Calculator extends GraphicsProgram {

    private GLabel display = new GLabel("");
    // number stack: the operands of your math
    Stack<Integer> ns = new Stack<>();

    // operator stack: the operators of your math
    Stack<Character> op = new Stack<>();

    @Override
    public void init(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new acm.gui.TableLayout(5, 4));

        String[] labels = { "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "(", "0", ")", "/",
                "C", "<", "=", "." };

        for (int i = 0; i < 20; i++) {
            JButton button = new JButton(labels[i]);
            buttonPanel.add(button);
        }

        add(buttonPanel, CENTER);

        addActionListeners();

        display.setFont("Times-bold-24");
        add(display, 0, 18);
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        switch (ae.getActionCommand()){

            case "=": // cause an evaluation

                System.out.flush(); // clear the console

                int response = evaluate(display.getLabel());

                StringBuilder ops = new StringBuilder();
                ops.append(response);

                String output = ops.toString();
                display.setLabel(output);
                System.out.println(">>>>OUTPUT:: " + output);

                break;

            case "C": // clear the display
                display.setLabel("");

                break;

            case "<": // delete a single character from the display IF a character exists to be deleted

                if(display.getLabel().length()>0){
                    StringBuilder sb = new StringBuilder(display.getLabel());
                    sb = sb.deleteCharAt(display.getLabel().length()-1);
                    display.setLabel(sb.toString());
                }

                break;

            default:
                display.setLabel(display.getLabel() + ae.getActionCommand());
        }
    }

    private boolean precedence(char peek, char ch){
        if((peek == '*' || peek == '/') && (ch == '+' || ch == '-')){
            return true;
        }

        // forces left to right operation
        if(peek != ch && peek != '('){
            return true;
        }

        return false;
    }

    private int calculate(char op, int a, int b){
        if(op == '*'){
            return a*b;
        }

        if(op == '/'){
            return a/b;
        }

        if(op == '+'){
            return a+b;
        }

        if(op == '-'){
            return a-b;
        }

        return 0;
    }

    private int evaluate(String input){

        // I don't want to say input.length() all the time
        int strlen = input.length();

        int i = 0; // an iteration marker for the first while loop
        char ch;

        StringBuffer s;

        if(!input.isEmpty()){ // ensure there is actually some input to process

            postMessage("Beginning evaluation...", 100);

            // loop across the input String
            while(i < strlen) {
                // store the character found at a given point (i)
                ch = input.charAt(i);

                // skip over blank spaces (this shouldn't happen)
                if(ch == ' '){
                    i++;
                    continue;
                }

                postMessage("Searching for symbols...");

                if(ch <= '9' && ch >= '0'){
                    // number digit found
                    postMessage("Found Number: " + ch);
                    s = new StringBuffer();
                    s.append(ch);
                    i++;

                    // loop through the rest of the number (if it is multi-digit)
                    while(i < strlen && input.charAt(i) <= '9' && input.charAt(i) >= '0'){
                        s.append(input.charAt(i));
                        i++;
                    }

                    int foundInt = Integer.parseInt(String.valueOf(s));
                    ns.push(foundInt);
                    continue;

                } else if (ch == '('){
                    // open parenthesis
                    postMessage("Found open parenthesis");
                    op.push(ch);
                    i++;

                } else if (ch == ')'){
                    // close parenthesis
                    postMessage("Found close parenthesis");

                    // calculate everything inside the parenthesis (PEMDAS)
                    while(!(op.peek() == '(')){
                        ns.push(calculate(op.peek(), ns.pop(), ns.pop()));
                    }
                    op.pop();
                    i++;

                } else {
                    // found an operator
                    postMessage("Symbol found.");
                    if(op.isEmpty()){
                        op.push(ch);
                    } else if (precedence(op.peek(), ch)){
                        ns.push(calculate(op.pop(), ns.pop(), ns.pop()));
                        op.push(ch);
                    } else {
                        op.push(ch);
                        i++;
                    }
                }

            } // end of outer loop and primary evaluation

            while(!op.isEmpty()){
                ns.push(calculate(op.pop(), ns.pop(), ns.pop()));
                ns.printStack();
                op.printStack();
            }
            postMessage("CALCULATED!");
            ns.printStack();
            op.printStack();

            // return whatever is left on the number stack.
            return ns.pop();

        } else {
            return 0;
        }

    }

    // for debugging use
    private void postMessage(String s, int time){
        System.out.println(s);
        pause(time);
    }

    private void postMessage(String s){
        System.out.println(s);
        pause(250);
    }

}