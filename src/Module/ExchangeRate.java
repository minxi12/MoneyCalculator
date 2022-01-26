package Module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ExchangeRate {

    private Currency from;
    private Currency to;
    private double rate;

    public ExchangeRate(Currency from, Currency to) throws IOException {
        this.from = from;
        this.to = to;
        rate = webServiceRate();
    }

    private double webServiceRate() throws IOException {
        String enlace = "https://free.currconv.com/api/v7/convert?q=" + from.getCode() + "_" + to.getCode()
                + "&compact=ultra&apiKey=abb4c0cef83ed2c21197";
        URL url = new URL(enlace);
        InputStream input = url.openStream();
        //Buscar en flujo de datos el resultado con input introducido
        byte[] reader = new byte[2048];
        int length = input.read(reader);
        String res = new String(reader, 0, length);
        return Double.parseDouble(res.substring(res.indexOf(to.getCode()) + 5, res.length() - 1));
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public double getRate() {
        return rate;
    }

}
