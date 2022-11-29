package command;


import cashMachine.ConsoleHelper;
import cashMachine.CurrencyManipulator;
import cashMachine.CurrencyManipulatorFactory;
import exception.InterruptOperationException;


import java.util.ResourceBundle;

public class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(DepositCommand.class.getPackage().getName() + ".resources.deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        //String[] matrix = ConsoleHelper.getValidTwoDigits(currencyCode);
        //currencyManipulator.addAmount(Integer.parseInt(matrix[0]), Integer.parseInt(matrix[1]));

        while (true) {
            String[] split = ConsoleHelper.getValidTwoDigits(currencyCode);
            try {
                int denomination = Integer.parseInt(split[0]);
                int count = Integer.parseInt(split[1]);
                currencyManipulator.addAmount(denomination, count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), (denomination * count), currencyCode));
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }

    }
}
