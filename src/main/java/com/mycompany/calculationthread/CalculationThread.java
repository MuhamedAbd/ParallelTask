class CalculationThread extends Thread {
    private String operation;
    private double num1, num2;

    public CalculationThread(String operation, double num1, double num2) {
        this.operation = operation;
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                System.out.println("Addition: " + num1 + " + " + num2 + " = " + result);
                break;
            case "subtract":
                result = num1 - num2;
                System.out.println("Subtraction: " + num1 + " - " + num2 + " = " + result);
                break;
            case "multiply":
                result = num1 * num2;
                System.out.println("Multiplication: " + num1 + " * " + num2 + " = " + result);
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Division: " + num1 + " / " + num2 + " = " + result);
                } else {
                    System.out.println("Division by zero is not allowed.");
                }
                break;
            default:
                System.out.println("Invalid operation!");
        }
    }
}

 class MathOperations {
    public static void main(String[] args) {
        double num1 = 50, num2 = 10;

        CalculationThread addThread = new CalculationThread("add", num1, num2);
        CalculationThread subtractThread = new CalculationThread("subtract", num1, num2);
        CalculationThread multiplyThread = new CalculationThread("multiply", num1, num2);
        CalculationThread divideThread = new CalculationThread("divide", num1, num2);

        addThread.start();
        subtractThread.start();
        multiplyThread.start();
        divideThread.start();
    }
}
