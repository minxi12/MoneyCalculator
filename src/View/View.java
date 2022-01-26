package View;

import Controller.Control;
import Module.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

public class View extends JFrame {

    JPanel mainPanel, panelDivisaBase, panelDivisaDest, panelTitulo, panelButton;
    JLabel jblTitulo, jblDivisaBase, jblDivisaDest, jblImporte, jblImporte2;
    JTextField txtDivisaBase, txtDivisaDest;
    JButton btnCambiar;
    JComboBox comboDivisaBase, comboDivisaDest;

    public View(String urlPass) throws IOException {
        init();

        btnCambiar.addActionListener((ActionEvent e) -> {
            String currencySelecBase = (String) comboDivisaBase.getSelectedItem();
            String currencySelecDest = (String) comboDivisaDest.getSelectedItem();

            if (currencySelecBase.equals(currencySelecDest)) {
                JOptionPane.showMessageDialog(null, "Mismo tipo de Moneda", "Error", JOptionPane.WARNING_MESSAGE);
            }
            if (txtDivisaBase.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Introduzca una cantidad", "Error", JOptionPane.WARNING_MESSAGE);
            }
;
            String base = currencySelecBase.substring(0, currencySelecBase.indexOf(" "));
            String dest = currencySelecDest.substring(0, currencySelecDest.indexOf(" "));

            double cantidad = Double.parseDouble(txtDivisaBase.getText());

            System.out.println(base);
            System.out.println(dest);

            System.out.println(CurrencyLoader.lista.get(base).toString());
            System.out.println(CurrencyLoader.lista.get(dest).toString());
            try {
                ExchangeRate a = new ExchangeRate(CurrencyLoader.lista.get(base), CurrencyLoader.lista.get(dest));
                Control control = new Control(CurrencyLoader.lista.get(base), CurrencyLoader.lista.get(dest), cantidad);
                txtDivisaDest.setText(String.valueOf((int) control.getResRate()));
                System.out.println("Resultado final: " + control.getResRate());
                System.out.println(a.getRate());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        setContentPane(mainPanel);
        this.setTitle("Money Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void init() {
        initPanel();
        initJLabel();
        initJText();
        initJButton();
        initJComboBox();
        addComponent();

    }

    private void addComponent() {
        Component vertical = Box.createVerticalStrut(10);
        Component vertical2 = Box.createVerticalStrut(10);

        panelButton.add(btnCambiar);

        panelTitulo.add(jblTitulo);

        panelDivisaBase.setLayout(new BoxLayout(panelDivisaBase, BoxLayout.PAGE_AXIS));
        panelDivisaBase.add(jblDivisaBase);
        panelDivisaBase.add(comboDivisaBase);
        panelDivisaBase.add(vertical);
        panelDivisaBase.add(jblImporte);
        panelDivisaBase.add(txtDivisaBase);

        panelDivisaDest.setLayout(new BoxLayout(panelDivisaDest, BoxLayout.PAGE_AXIS));
        panelDivisaDest.add(jblDivisaDest);
        panelDivisaDest.add(comboDivisaDest);
        panelDivisaDest.add(vertical2);
        panelDivisaDest.add(jblImporte2);
        panelDivisaDest.add(txtDivisaDest);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        mainPanel.add(panelTitulo, BorderLayout.NORTH);
        mainPanel.add(panelDivisaBase, BorderLayout.WEST);
        mainPanel.add(panelDivisaDest, BorderLayout.EAST);
        mainPanel.add(panelButton, BorderLayout.CENTER);
    }

    private void initJComboBox() {
        comboDivisaBase = new JComboBox();
        for (Map.Entry<String, Currency> i : CurrencyLoader.lista.entrySet()) {
            String line = i.getKey() + " " + i.getValue().getName();
            comboDivisaBase.addItem(line);
        }
        comboDivisaDest = new JComboBox();
        for (Map.Entry<String, Currency> i : CurrencyLoader.lista.entrySet()) {
            String line = i.getKey() + " " + i.getValue().getName();
            comboDivisaDest.addItem(line);
        }
    }

    private void initJButton() {
        btnCambiar = new JButton("<>");
    }

    private void initJText() {
        txtDivisaBase = new JTextField(15);
        txtDivisaDest = new JTextField(15);
    }

    private void initJLabel() {
        jblTitulo = new JLabel("Money Calculator");
        jblDivisaBase = new JLabel("Tengo esta divisa:");
        jblDivisaDest = new JLabel("Quiero esta divisa");
        jblImporte = new JLabel("IMPORTE");
        jblImporte2 = new JLabel("IMPORTE");

    }

    private void initPanel() {
        mainPanel = new JPanel();
        panelDivisaBase = new JPanel();
        panelDivisaDest = new JPanel();
        panelTitulo = new JPanel();
        panelButton = new JPanel();
    }
}
