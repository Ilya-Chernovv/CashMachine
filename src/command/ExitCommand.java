package command;


import cashMachine.CashMachine;
import cashMachine.ConsoleHelper;
import exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(ExitCommand.class.getPackage().getName() + ".resources.exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper consoleHelper = new ConsoleHelper();

        consoleHelper.writeMessage("Вы действительно хотите выйти? \n" +
                "y - Да, n - Нет");
        consoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String userResponse = ConsoleHelper.readString();

        if(userResponse.equals("y")){
            consoleHelper.writeMessage("Досвидания");
            consoleHelper.writeMessage(res.getString("thank.message"));
            CashMachine.flag = false;
        }else if(userResponse.equals("n")){
            CashMachine.flag = false;
        }
    }
}
