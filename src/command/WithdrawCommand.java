package command;


import cashMachine.ConsoleHelper;
import cashMachine.CurrencyManipulator;
import cashMachine.CurrencyManipulatorFactory;
import exception.InterruptOperationException;


import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(WithdrawCommand.class.getPackage().getName() + ".resources.withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper consoleHelper = new ConsoleHelper();
        consoleHelper.writeMessage(res.getString("before"));
        String currencyCode = consoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        boolean flag = true;
        String nominal = null;
        while (flag) {
            int sum = 0;
            consoleHelper.writeMessage("Введите сумму:");
            consoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                nominal = consoleHelper.readString();
                sum = Integer.parseInt(nominal);
            } catch (Exception e) {
                consoleHelper.writeMessage("Вы ввели некорректные данные. \n" +
                        "Повторите попытку. \n");
                consoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!currencyManipulator.isAmountAvailable(sum)) {
                consoleHelper.writeMessage("На счету недостаточно средств. \n" +
                        "Повторите попытку. \n");
                consoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try {
                Map<Integer, Integer> map = currencyManipulator.withdrawAmount(sum);
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    System.out.println(key + " - " + value);
                }
            } catch (Exception e) {
                System.out.println("Банкомат не можем выдать запрашиваемую сумму. \n");
                consoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }

            flag = false;
        }
    }
}
