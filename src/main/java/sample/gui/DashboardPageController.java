package sample.gui;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Main;
import sample.core.objects.Card;
import sample.core.objects.Transaction;
import sample.util.operations.StageOperations;

public class DashboardPageController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Text bankAccountBalance;

    @FXML
    private ComboBox<?> bankAccountScollable;

    @FXML
    private Text creditCardBalance;

    @FXML
    private ComboBox<?> creditCardScrollable;

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
    private Text nameText;

    @FXML
    void initialize()
    {
        String replaced = nameText.getText().replace("%{name}", Main.userLoggedIn.getFirstName());

        nameText.setText(replaced);
        nameText.setTextAlignment(TextAlignment.JUSTIFY);


        ObservableList<Transaction> s = FXCollections.observableArrayList();

        Main.userLoggedIn.getBankAccounts().get(0).getTransactions().forEach(transaction -> s.add(transaction));


        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
        ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        wOrDColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("depositOrWithdraw"));
        transactionTable.setItems(s);




        bankAccountBalance.setText(Main.userLoggedIn.getBankAccounts().get(0).getBalance()+"");
    }


    public void onTransferIconClicked(MouseEvent mouseEvent)
    {
        StageOperations.switchToTransfersScene();
    }

    public void onCardIconClicked(MouseEvent mouseEvent)
    {

    }

    public void onProfileClicked(MouseEvent mouseEvent)
    {
        StageOperations.switchToProfileScene();
    }

    public void onLogoutClicked(MouseEvent mouseEvent)
    {

        Alert alert = new Alert(Alert.AlertType.WARNING,
                "You have 5 seconds! To respond or you will be logged out!",
            new ButtonType("Abort!", ButtonBar.ButtonData.CANCEL_CLOSE),
            new ButtonType("Okay!", ButtonBar.ButtonData.OK_DONE));

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().getText().equals("Abort!"))
        {
            return;
        }


        PauseTransition delay = new PauseTransition(Duration.seconds(5.0));
        delay.setOnFinished(event ->
        {
            try
            {
                Main.userLoggedIn = null;
                Main.open("/loginpage");
            }
            catch (Exception e)
            {
                System.out.printf("Error occured: %s", e.getMessage());
            }
        });
        delay.play();
    }
}



