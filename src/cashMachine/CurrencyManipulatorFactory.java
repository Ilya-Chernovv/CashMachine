package cashMachine;


import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new CaseInsensitiveMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {

        CurrencyManipulator manipulator = map.get(currencyCode);
        if (manipulator == null) {
            manipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, manipulator);
        }
        return manipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        List<CurrencyManipulator> list = new ArrayList<>();

        for (Map.Entry<String, CurrencyManipulator> entry : map.entrySet()) {
            CurrencyManipulator value = entry.getValue();
            list.add(value);
        }
        return list;
    }
}
