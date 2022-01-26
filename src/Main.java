
import View.View;
import Module.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "C:/Users/Lou Minxi/Desktop/Universidad/Adaptacion del plan nuevo/Tercer Año/IS2/Proyecto/input.txt";
        CurrencyLoader lista = new CurrencyLoader(url);
        View view = new View(url);
    }
}
