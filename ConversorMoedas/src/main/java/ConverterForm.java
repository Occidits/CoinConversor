import api.ExchangeRateAPIClient;
import api.ExchangeRateResponse;
import db.Moeda;
import db.MoedasDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ConverterForm {
    private JTextField valueText;
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> converterComboBox;
    private JButton converterButton;
    private JTextField finalValueText;
    private JLabel valueTextLabel;
    private JLabel converterTextLabel;
    private JLabel finalValueTextLabel;
    private JPanel mainPanel;
    private JButton saveButton;
    private ExchangeRateResponse lastKnownRates = null;
    private ExchangeRateAPIClient apiClient = new ExchangeRateAPIClient();
    private MoedasDAO moedaDAO = new MoedasDAO();


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ConverterForm() {
        converterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSalario();
            }
        });
        populateCurrenciesSync();

    }

    private void populateCurrenciesSync() {
        try{
            String jsonResponse = apiClient.fetchExchangeRates();
            ExchangeRateResponse response = apiClient.parseRates(jsonResponse);

            if(response != null && response.getConversionRates() != null){
                this.lastKnownRates = response;

                Map<String, Double> rates = response.getConversionRates();
                List<String> currencyCodes = new ArrayList<>(rates.keySet());
                Collections.sort(currencyCodes);

                sourceComboBox.removeAllItems();
                converterComboBox.removeAllItems();
                for(String code : currencyCodes){
                    sourceComboBox.addItem(code);
                    converterComboBox.addItem(code);
                }
                sourceComboBox.setSelectedItem("USD");
                converterComboBox.setSelectedItem("BRL");
            }else{
                JOptionPane.showMessageDialog(mainPanel, "API Error: Could not load rates",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                converterButton.setEnabled(false);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(mainPanel, "Network Error: Could not connect to API",
                                        "Error", JOptionPane.ERROR_MESSAGE);
            converterButton.setEnabled(false);
        }
    }

    private void performConversion() {
        if(lastKnownRates == null){
            JOptionPane.showMessageDialog(mainPanel, "Rates not loaded yet. Please wait",
                                        "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String sourceCode = (String) sourceComboBox.getSelectedItem();
            String converterCode = (String) converterComboBox.getSelectedItem();
            Double amountToConvert = Double.parseDouble(valueText.getText());

            Map<String, Double> rates = lastKnownRates.getConversionRates();
            Double sourceRate = rates.get(sourceCode);
            Double converterRate = rates.get(converterCode);

            if (sourceRate == null || converterRate == null || sourceRate == 0) {
                finalValueText.setText("Error: Rate for Selected Country not available");
                return;
            }

            Double result = amountToConvert * (converterRate / sourceRate);

            finalValueText.setText(String.format("%.2f %s", result, converterCode));
        }catch(NumberFormatException ex){
            finalValueText.setText("Error: Invalid Number Format");
        }catch(Exception ex){
            finalValueText.setText("An Unexpected Error Occurred");
            ex.printStackTrace();
        }
    }

    private void saveSalario(){
        try{
            String name = sourceComboBox.getSelectedItem().toString();
            double salario = Double.parseDouble(valueText.getText().trim());

            Moeda newMoeda = new Moeda(name, salario);

            boolean sucess = moedaDAO.insert(newMoeda);

            if(sucess){
                JOptionPane.showMessageDialog(mainPanel, "Salario Inserted",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);

                valueText.setText("");
            }else{
                JOptionPane.showMessageDialog(mainPanel, "Erro ao cadastrar a moeda",
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(mainPanel, "Invalid Number Format",
                                        "NumberFormat", JOptionPane.ERROR_MESSAGE);
        }
    }
}
