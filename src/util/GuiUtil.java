package util;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.LoginView;

public class GuiUtil {

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                message);
        alert.showAndWait();
    }

    public static void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                message);
        alert.showAndWait();
    }

    public static ButtonType showConfirm(String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return ButtonType.OK;
        }
        return null;

    }

    public static void logout() {
        LoginView loginView = new LoginView();

        Scene loginScene = new Scene(loginView.getView(), 800, 600);
        Stage loginStage = new Stage();
        loginStage.setScene(loginScene);
        loginStage.show();
    }
}
