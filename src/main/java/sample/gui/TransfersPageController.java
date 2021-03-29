package sample.gui;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.core.objects.Transaction;
import sample.util.Regex;
import sample.util.operations.AlertOperations;
import sample.util.operations.StageOperations;
import sample.util.operations.StringOperations;

import javax.imageio.stream.ImageInputStream;

public class TransfersPageController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ObservableList<Transaction> s = FXCollections.observableArrayList();

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView transferIcon;

    @FXML
    private ImageView creditCardIcon;

    @FXML
    private ImageView profileIcon;

    @FXML
    private ImageView logoutIcon;

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> accountColumn;

    @FXML
    private TableColumn<Transaction, String> ammountColumn;

    @FXML
    private TableColumn<Transaction, String> wOrDColumn;

    @FXML
    private TextField amountToSend;

    @FXML
    private TextField accountNumberTF;

    @FXML
    void onCardIconClicked(MouseEvent event)
    {

    }

    @FXML
    void onLogoutClicked(MouseEvent event)
    {

    }

    @FXML
    void onProfileClicked(MouseEvent event)
    {
        StageOperations.switchToProfileScene();
    }

    @FXML
    void onTransferIconClicked(MouseEvent event)
    {
        StageOperations.switchToTransfersScene();
    }

    @FXML
    void onHomeButtonClicked(MouseEvent event){ StageOperations.switchToDashboardScene(); };



    @FXML
    void initialize()
    {

    }

    public void transferMoneyButtonClicked(ActionEvent actionEvent)
    {
        String amountTosend = amountToSend.getText();
        String accountNumber = accountNumberTF.getText();

        if (!Regex.number(amountTosend) && !Regex.number(accountNumber))
        {
            AlertOperations.AlertShortner("bad", "Invalid Number!", "Please only insert numbers in the sending field!");
            return;
        }

        if (!accountNumber.startsWith("814")) {
            AlertOperations.AlertShortner("bad", "Invalid Account Number!", "Please type in a valid account number!");
            return;
        }

        Stage primaryStage = new Stage();
        Button btn = new Button();
        btn.setStyle("-fx-background-color: #313131");
        btn.setText("Confirm");
        btn.setTextFill(Color.WHITE);

        Text text = new Text("Enter your password");

        text.setFont(Font.font("Berlin Sans FB Demi Bold"));
        text.setStyle("-fx-text-inner-color: #FFFF");

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FFFF; -fx-text-fill: #FFFF");
        passwordField.setPromptText("Password");

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);


        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-color: #212121");
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setFillWidth(false);
        dialogVbox.getChildren().addAll(text, passwordField, btn);


        double amt = Double.parseDouble(amountTosend);

        if (Main.userLoggedIn.getBankAccounts().get(0).getBalance() < amt) {
            AlertOperations.AlertShortner("bad", "Not enough funds", "You do not have enough funds in your account to complete this transaction");
            return;
        }

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        if (!dialog.isShowing())
        {
            dialog.setScene(dialogScene);
            dialog.show();
            btn.setOnAction(
                    new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent event)
                        {
                            if (Objects.equals(Main.userLoggedIn.gethashedPass(), StringOperations.hashPassword(passwordField.getText())))
                            {
                                Transaction transaction = new Transaction(new BigDecimal(amt+""), 9999, new Date(), "-");
                                Main.userLoggedIn.getBankAccounts().get(0).getTransactions().add(transaction);

                                dialog.close();

                                s.add(transaction);
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
                                accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
                                ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
                                wOrDColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("depositOrWithdraw"));
                                transactionTable.setItems(s);


                            }
                            else {
                                passwordField.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-fill: #FFFF");
                            }
                        }
                    }
            );
        }
    }
}
