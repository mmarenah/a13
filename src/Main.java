import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField inputField;
    private Label outputLabel;

    private static final double MILES_TO_KM = 1.60934;
    private static final double KILOGRAMS_TO_POUNDS = 2.20462;
    private static final double LITERS_TO_GALLONS = 0.264172;

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Unit Converter");
        titleLabel.setStyle("-fx-font-size: 24px;");

        Label inputLabel = new Label("Enter a value:");
        inputField = new TextField();

        Label inputUnitLabel = new Label("Unit:");
        ComboBox<String> inputUnitComboBox = new ComboBox<>();
        inputUnitComboBox.getItems().addAll("Kilometers", "Miles", "Kilograms", "Pounds", "Liters", "Gallons");

        Label outputTitleLabel = new Label("Converted value:");
        outputTitleLabel.setStyle("-fx-font-weight: bold;");

        outputLabel = new Label();

        Label outputUnitLabel = new Label("Unit:");
        ComboBox<String> outputUnitComboBox = new ComboBox<>();
        outputUnitComboBox.getItems().addAll("Kilometers", "Miles", "Kilograms", "Pounds", "Liters", "Gallons");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert(inputUnitComboBox.getValue(), outputUnitComboBox.getValue()));

        HBox inputBox = new HBox(inputLabel, inputField, inputUnitLabel, inputUnitComboBox);
        inputBox.setSpacing(10);
        inputBox.setAlignment(Pos.CENTER);

        HBox outputBox = new HBox(outputTitleLabel, outputLabel, outputUnitLabel, outputUnitComboBox);
        outputBox.setSpacing(10);
        outputBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(titleLabel, inputBox, outputBox, convertButton);
        mainBox.setSpacing(20);
        mainBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainBox, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Unit Converter");
        stage.show();
    }

    private void convert(String inputUnit, String outputUnit) {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            double convertedValue = 0.0;
            switch (inputUnit) {
                case "Kilometers":
                    switch (outputUnit) {
                        case "Miles":
                            convertedValue = inputValue / MILES_TO_KM;
                            break;
                        default:
                            convertedValue = inputValue;
                            break;
                    }
                    break;
                case "Miles":
                    switch (outputUnit) {
                        case "Kilometers":
                            convertedValue = inputValue * MILES_TO_KM;
                            break;
                        default:
                            convertedValue = inputValue;
                            break;
                    }
                    break;
                case "Kilograms":
                    switch (outputUnit) {
                        case "Pounds":
                            convertedValue = inputValue * KILOGRAMS_TO_POUNDS;
                            break;
                        default:
                            convertedValue = inputValue;
                            break;
                    }
                    break;
                case "Pounds":
                    switch (outputUnit) {
                        case "Kilograms":
                            convertedValue = inputValue / KILOGRAMS_TO_POUNDS;
                            break;
                        default:
                        convertedValue = inputValue;
                        break;
                }
                break;
                case "Liters":
                    switch (outputUnit) {
                        case "Gallons":
                            convertedValue = inputValue * LITERS_TO_GALLONS;
                            break;
                        default:
                            convertedValue = inputValue;
                            break;
                    }
                    break;
                case "Gallons":
                    switch (outputUnit) {
                        case "Liters":
                            convertedValue = inputValue / LITERS_TO_GALLONS;
                            break;
                        default:
                            convertedValue = inputValue;
                            break;
                    }
                    break;
            }
            outputLabel.setText(String.format("%.2f %s", convertedValue, outputUnit));
        } catch (NumberFormatException e) {
            outputLabel.setText("Invalid input");
        }
    }
}