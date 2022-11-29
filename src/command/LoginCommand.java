package command;


import cashMachine.ConsoleHelper;
import exception.InterruptOperationException;


import java.util.ResourceBundle;

public class LoginCommand implements Command {
    //private String card = "123456789012";
    //private int password = 1234;
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(LoginCommand.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(LoginCommand.class.getPackage().getName() + ".resources.login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean flag = true;
        ConsoleHelper consoleHelper = new ConsoleHelper();

        while (flag) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            consoleHelper.writeMessage("Введите 2 числа.\n" +
                    "Номер кредитной карты, состоящий из 12 цифр, и пин - состоящий из 4 цифр.\n");

            String lineCard = consoleHelper.readString();
            String linePass = consoleHelper.readString();

            long numberCard = 0;
            int pass = 0;
            if (lineCard.length() != 12) {
                consoleHelper.writeMessage("Вы ввели некорректные данные карты. \n" +
                        "Повторите попытку.\n");
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            } else if (linePass.length() != 4) {
                consoleHelper.writeMessage("Вы ввели некорректный пароль от карты. \n" +
                        "Повторите попытку.\n");
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            try {
                numberCard = Long.parseLong(lineCard);
                pass = Integer.parseInt(linePass);
            }catch (NumberFormatException e){
                System.out.println("Вы ввели некорректные данные. \n" +
                        "Повторите попытку.\n");
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), lineCard));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
            if(validCreditCards.containsKey(lineCard)){
                if(validCreditCards.getString(lineCard).equals(linePass)){
                    consoleHelper.writeMessage("Верификация прошла успешно.\n");
                    consoleHelper.writeMessage(String.format(res.getString("success.format"), lineCard));
                }
                break;
            }
        }

    }
}
