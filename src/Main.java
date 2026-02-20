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
    public static double calculateRPN(String ar){
        double y=0;
        double x=0;
        Stack<Double> stack = new LinkedStack<>(); // a linkedStack called stack to store the values
        String[] inpSplit = ar.split(" "); // splitting the input string by a space
        for (String str : inpSplit){ // this iterates through every element in the array and checks
            // if the element being popped is a number or an operator
            if(isNum(str)){
                stack.push(Double.parseDouble(str)); // interprets the double value from the string and pushes it
            } else if (isOperator(str)) { // if the element is an operator, it does the appropriate calculation
                x = stack.pop();
                y = stack.pop();
                switch (str) {
                    case "+":
                        stack.push(y + x);
                        break;
                    case "-":
                        stack.push(y - x);
                        break;
                    case "*":
                        stack.push(y * x);
                        break;
                    case "/":
                        stack.push(y / x);
                        break;
                    default:
                        return 0;
                }
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
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }
}
