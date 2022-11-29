package command;


import cashMachine.Operation;
import exception.InterruptOperationException;
import exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;


public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {

        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
    }

    private CommandExecutor() {
    }

    public static final void execute(Operation operation) throws InterruptOperationException, NotEnoughMoneyException {
        Command command = allKnownCommandsMap.get(operation);
        command.execute();
    }
}
