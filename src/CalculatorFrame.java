import javax.swing.*;
import java.awt.event.*;

public class CalculatorFrame extends JFrame implements ActionListener {

    private JTextField textField;
    private double num1, num2, result;
    private char operator;

    public CalculatorFrame() {
        setTitle("Simple Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 280, 30);
        add(textField);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int x = 30, y = 100;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 60, 40);
            button.addActionListener(this);
            add(button);

            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 50;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if ((input.charAt(0) >= '0' && input.charAt(0) <= '9') || input.equals(".")) {
            textField.setText(textField.getText() + input);
        } else if (input.charAt(0) == '+' || input.charAt(0) == '-' ||
                input.charAt(0) == '*' || input.charAt(0) == '/') {
            num1 = Double.parseDouble(textField.getText());
            operator = input.charAt(0);
            textField.setText("");
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        textField.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
        }
    }
}
