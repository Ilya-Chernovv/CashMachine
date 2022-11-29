package cashMachine;

import exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    //хранит всю информацию про выбранную валюту
    private String currencyCode;
    private static Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    private CurrencyManipulator() {

    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        int getCount = 0;
        if (denominations.containsKey(denomination)) {
            getCount = denominations.get(denomination);
            denominations.put(denomination, count + getCount);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int cash = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            cash += key * value;
        }
        return cash;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        int cash = 0;
        if (denominations.size() == 0) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            cash += key * value;
        }
        return cash >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> mapResult = new HashMap<>();
        Map<Integer, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(denominations);
        boolean flag = true;
        int cash = 0;

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int key = entry.getKey();
            flag = true;
            while (flag) {
                if (expectedAmount >= (cash + key)) {
                    cash += key;
                    if (mapResult.get(key) == null) {
                        mapResult.put(key, 1);
                    } else {
                        int value = mapResult.get(key);
                        value += 1;
                        mapResult.put(key, value);
                    }
                    if (denominations.get(key) > 1) {
                        int valueSorted = denominations.get(key);
                        valueSorted -= 1;
                        denominations.put(key, valueSorted);
                    } else {
                        denominations.remove(key);
                    }

                    if (denominations.get(key) == null) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
        }

        if (cash != expectedAmount) {
            throw new NotEnoughMoneyException();
        }
        return mapResult;
    }
}
