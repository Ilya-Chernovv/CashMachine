package cashMachine;

import command.CommandExecutor;
import exception.InterruptOperationException;
import exception.NotEnoughMoneyException;

import java.util.Locale;

public class CashMachine {
    public static boolean flag = true;
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        //System.out.println(CashMachine.class.getRealPath(""));
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation = Operation.LOGIN;
            CommandExecutor.execute(operation);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            ConsoleHelper.writeMessage("Спасибо за посещение банкомата. Удачи.");
            ConsoleHelper.printExitMessage();
        } catch (NotEnoughMoneyException e) {

        }
    }
}
