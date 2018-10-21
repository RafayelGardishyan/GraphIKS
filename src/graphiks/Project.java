package graphiks;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Project {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label project_title;

    @FXML
    private Button project_open_button;

    @FXML
    private Label project_lastopened;

    @FXML
    void initialize() {
    }
}
