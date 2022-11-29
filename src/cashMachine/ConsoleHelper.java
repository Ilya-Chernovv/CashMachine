package cashMachine;

import command.DepositCommand;
import exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;


public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(DepositCommand.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String message = null;
        try {
            message = bis.readLine();
            if (message.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {

        }
        return message;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        System.out.println("Введите трехзначный код валюты");
        String currency = null;
        boolean flag = true;
        while (flag) {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            currency = readString();
            if (currency.equalsIgnoreCase("EXIT")) {
                throw new InterruptOperationException();
            }
            if (currency.length() == 3) {
                flag = false;
            } else {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                System.out.println("Вы ввели неправильный код валюты. \n" +
                        "Повторите попытку.");
            }
        }
        return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        System.out.println("Ведите 2 целых положительных числа. \n" +
                "Первое число - номинал, второе - количество банкнот.");

        boolean flag = true;
        String[] matrix = null;
        String nominal = null;
        while (flag) {
            ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            try {
                nominal = readString();
                if (nominal.equalsIgnoreCase("EXIT")) {
                    throw new InterruptOperationException();
                }
                matrix = nominal.split(" ");
                if (matrix.length > 2) {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                    System.out.println("Вы ввели некорректные данные. \n" +
                            "Повторите попытку.");
                    continue;
                } else if (Integer.parseInt(matrix[0]) > 0 && Integer.parseInt(matrix[1]) > 0) {
                    flag = false;
                } else {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                    System.out.println("Вы ввели некорректные данные. \n" +
                            "Повторите попытку.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException i) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                System.out.println("Вы ввели некорректные данные. \n" +
                        "Повторите попытку.");
                continue;
            }
        }
        return matrix;
    }

    public static Operation askOperation() throws InterruptOperationException {
        boolean flag = true;
        Operation operations = null;
        System.out.println("Введите 1 для выполнения операции ИНФОРМАЦИЯ. \n" +
                "Введите 2 для пополнения Вашего счета. \n" +
                "Введите 3 для вывода Ваших средств. \n" +
                "Введите 4 для выхода.\n");

        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        ConsoleHelper.writeMessage("\t 1 - " + res.getString("operation.INFO"));
        ConsoleHelper.writeMessage("\t 2 - " + res.getString("operation.DEPOSIT"));
        ConsoleHelper.writeMessage("\t 3 - " + res.getString("operation.WITHDRAW"));
        ConsoleHelper.writeMessage("\t 4 - " + res.getString("operation.EXIT"));

        while (flag) {
            try {
                String message = readString();
                if (message.equalsIgnoreCase("EXIT")) {
                    throw new InterruptOperationException();
                }
                int operation = Integer.parseInt(message);
                operations = Operation.getAllowableOperationByOrdinal(operation);
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели некорректные данные. \n " +
                        "Попробуйте еще раз");
            }
        }
        return operations;
    }

    public static void printExitMessage(){
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

}
