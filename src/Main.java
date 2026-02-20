import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // scanner to get the input and calling the calculateRPN method in this block
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter RPN expression:");
        String input = scanner.nextLine().trim();
        scanner.close();
        double result = calculateRPN(input);
        System.out.println(result);
    }
    // method to calculate the RPN expression
    public static double calculateRPN(String ar) {
        Stack<Double> stack = new LinkedStack<>();  // a linkedStack called stack to store the values
        String[] inpSplit = ar.trim().split(" ");   // splitting the input string by a space

        for (String str : inpSplit) {   // this iterates through every element in the array and checks
                                        // if the element being popped is a number or an operator
            if (isNum(str)) {
                stack.push(Double.parseDouble(str));
            } else if (isOperator(str)) {

                if (stack.size() < 2) {     //validating if there are enough operands in the stack
                    throw new IllegalArgumentException(
                            "Not enough operands"
                    );
                }

                double x = stack.pop();
                double y = stack.pop();
                switch (str) {
                    case "+": stack.push(y + x); break;
                    case "-": stack.push(y - x); break;
                    case "*": stack.push(y * x); break;
                    case "/": if(x == 0){
                        throw new IllegalArgumentException("Division by zero is not possible"); // a special case for division by zero is taken care of here
                    } else stack.push(y / x); break;
                    case "^": stack.push(Math.pow(y, x)); break;
                    default: return 0;
                }
            } else {
                // in a case where it's not a number or an operator
                throw new IllegalArgumentException("Invalid expression"); // throws an exception if the input is invalid
            }
        }
        return stack.pop();
    }

    static boolean isNum(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
    static boolean isOperator(String str){
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("^");
    }
}
