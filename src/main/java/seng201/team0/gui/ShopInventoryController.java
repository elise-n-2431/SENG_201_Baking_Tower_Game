package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import seng201.team0.models.Tower;

public class ShopInventoryController {
    public ShopInventoryController(Class Tower1){
        Tower = Tower1;
    }
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];

    @FXML
    private TextArea inputTextArea;
    @FXML
    private Label speakingLabel;

    @FXML
    private void onSpeakClicked() {
        /**speakingLabel.setText(inputTextArea.getText());
        inputTextArea.setText("");**/
    }
}
