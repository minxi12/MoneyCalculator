package Module;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyLoader {

    public static Map<String, Currency> lista;

    public CurrencyLoader(String url) throws IOException {
        lista = new HashMap<String, Currency>();
        add(url);
    }

    private void add(String urlPass) throws IOException {
        String line = "";
        FileReader reader = new FileReader(urlPass);
        BufferedReader buffReader = new BufferedReader(reader);
        while ((line = buffReader.readLine()) != null) {
            String[] res = line.split(", ");
            String code = res[0];
            String name = res[1];
            String symbol = res[2];
            lista.put(code, new Currency(name, symbol, code));
        }
        buffReader.close();

    }

}
