package graphiks;

import graphiks.database.db;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller {

    private final String project_filename = "project.fxml";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button new_button;

    @FXML
    private VBox projects_list;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void initialize() throws IOException, SQLException, ClassNotFoundException {
        //Set new_button action to create a new project
        new_button.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Create a new project");
            dialog.setHeaderText("New project creation");
            dialog.setContentText("Please enter new project name:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(s -> {
                try {
                    db.Conn();
                    db.WriteDB(s, Date.now(), "[]");
                    db.CloseDB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        });

        //Getting projects from database
        graphiks.database.db.Conn();
        ArrayList<ProjectData> projects = db.ReadDB();
        graphiks.database.db.CloseDB();

        for (ProjectData project : projects) {
            open_project_fxml(project_filename, project.getM_name(), project.getM_lastopened());
        }
    }

    private void open_project_fxml(String filename, String projectname, Date lastopened) throws IOException {
        // Load project panel fxml to a pane
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(filename));

        // Set project name
        int maxTitleLenght = 17;
        int maxLength = (projectname.length() < maxTitleLenght)?projectname.length(): maxTitleLenght; //Getting project name's maximal length
        String points = (projectname.length() < maxTitleLenght) ?"":"...";
        Label name = (Label) newLoadedPane.getChildren().get(0); // Get the project name label
        name.setText(projectname.substring(0, maxLength) + points); // Change the text of project name label

        Label date = (Label) newLoadedPane.getChildren().get(2); // Get the last opened date label
        date.setText("Last opened: " + lastopened.getDateString()); // Change the text of last opened date label

        // Add the panel to the list of projects
        projects_list.getChildren().add(newLoadedPane);
    }



}
