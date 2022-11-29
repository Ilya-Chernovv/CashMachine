package command;

import exception.InterruptOperationException;
import exception.NotEnoughMoneyException;

interface Command {
    void execute() throws InterruptOperationException, NotEnoughMoneyException;
}
