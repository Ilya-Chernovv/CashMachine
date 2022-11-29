package command;


import cashMachine.ConsoleHelper;
import cashMachine.CurrencyManipulator;
import cashMachine.CurrencyManipulatorFactory;
import exception.InterruptOperationException;

import java.util.List;
import java.util.ResourceBundle;


class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(InfoCommand.class.getPackage().getName() + ".resources.info_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        List<CurrencyManipulator> list = (List<CurrencyManipulator>) CurrencyManipulatorFactory.getAllCurrencyManipulators();
        int count = 0;
        for (CurrencyManipulator manipulator : list) {
            if (manipulator.hasMoney()) {
                System.out.println(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                count++;
            }

        }
        if (count == 0) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
