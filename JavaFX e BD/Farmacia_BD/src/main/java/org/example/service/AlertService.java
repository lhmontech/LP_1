package org.example.service;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AlertService {

    public enum Tipo { ERRO, AVISO, SUCESSO }

    public static void mostrar(Pane container, String mensagem, Tipo tipo) {
        HBox alerta = new HBox();
        alerta.setAlignment(Pos.CENTER_LEFT);
        alerta.setSpacing(8);
        alerta.setPrefHeight(40);
        alerta.setPrefWidth(320);
        alerta.setLayoutX(container.getWidth() / 2 - 160);
        alerta.setLayoutY(16);
        alerta.setOpacity(0);

        String icone;
        String cor;
        switch (tipo) {
            case ERRO:
                icone = "✖";
                cor = "#c0392b";
                break;
            case AVISO:
                icone = "⚠";
                cor = "#e67e22";
                break;
            default:
                icone = "✔";
                cor = "#27ae60";
                break;
        }

        alerta.setStyle(
                "-fx-background-color: " + cor + ";" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 8 16 8 16;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.35), 8, 0, 0, 2);"
        );

        Label lblIcone = new Label(icone);
        lblIcone.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        Label lblTexto = new Label(mensagem);
        lblTexto.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");
        lblTexto.setWrapText(true);
        lblTexto.setMaxWidth(260);

        alerta.getChildren().addAll(lblIcone, lblTexto);
        container.getChildren().add(alerta);

        // Animação: fade in → pausa → fade out → remove
        FadeTransition fadeIn = new FadeTransition(Duration.millis(250), alerta);
        fadeIn.setFromValue(0); fadeIn.setToValue(1);

        PauseTransition pausa = new PauseTransition(Duration.seconds(3));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), alerta);
        fadeOut.setFromValue(1); fadeOut.setToValue(0);

        SequentialTransition seq = new SequentialTransition(fadeIn, pausa, fadeOut);
        seq.setOnFinished(e -> container.getChildren().remove(alerta));
        seq.play();
    }
}