package com.example.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class Kostki extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        GridPane dataGrid = new GridPane();

        dataGrid.setPadding(new Insets(20, 20, 20, 20));
        dataGrid.setVgap(10);
        dataGrid.setHgap(10);

        TextField numDiceField = new TextField();
        numDiceField.setPromptText("Liczba kostek");

        Button rollButton = new Button("Losuj");
        Button closeButton = new Button("Zamknij");

        Label resultLabel = new Label();

        rollButton.setOnAction(e -> {
            try {
                int numDice = Integer.parseInt(numDiceField.getText());
                if (numDice < 3 || numDice > 10) {
                    resultLabel.setText("Liczba kostek od 3 do 10");
                    return;
                }

                StringBuilder result = new StringBuilder();
                Random random = new Random();
                int allRolls = 0;
                for (int i = 0; i < numDice; i++) {
                    int roll = random.nextInt(6) + 1;
                    allRolls += roll;
                    result.append("Kostka ").append(i + 1).append(": ").append(roll).append("\n");
                }
                result.append("\nŁączna liczba wyrzuconych: ").append(allRolls);
                resultLabel.setText(result.toString());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Proszę wpisać poprawną liczbę kostek");
            }
        });

        closeButton.setOnAction(e -> mainStage.close());

        TitledPane dataPane = new TitledPane();
        dataPane.setText("Dane");
        dataPane.setCollapsible(false);

        dataGrid.add(new Label("Liczba kostek:"), 0, 1);
        dataGrid.add(numDiceField, 1, 1);
        dataGrid.add(rollButton, 1, 2);
        dataGrid.add(closeButton, 2, 2);

        dataPane.setContent(dataGrid);

        TitledPane resultPane = new TitledPane();
        resultPane.setText("Wynik");
        resultPane.setCollapsible(false);

        VBox resultBox = new VBox(10);
        resultBox.setPadding(new Insets(10));
        resultBox.getChildren().add(resultLabel);

        resultPane.setContent(resultBox);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(dataPane, resultPane);

        Scene scene = new Scene(layout, 400, 500);

        mainStage.setTitle("Kostki");
        mainStage.setScene(scene);
        mainStage.show();
    }
}
