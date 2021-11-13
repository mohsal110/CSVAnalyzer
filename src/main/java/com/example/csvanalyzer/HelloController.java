package com.example.csvanalyzer;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;


public class HelloController  {

    @FXML
    private VBox mainWindow;
    @FXML
    private Stage mainStage;


    @FXML
    private Label inputDir;

    @FXML
    private Label outputDir;


    @FXML
    private TextArea main_titles;
    @FXML
    private TextArea transfer_titles;

    String[] titles;
    String[] transfer_title_list;

    URI inputPath , outputPath;
    CSVAnalysis csvAnalysis;



    @FXML
    protected void findInputFile(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv*"));

        mainStage=(Stage) mainWindow.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(mainStage);

        if (selectedFile != null) {
            inputDir.setText("Selected file path:"+selectedFile.getPath());
            inputPath=selectedFile.toURI();
            mainStage.show();
        }
    }

    @FXML
    protected void findOutputDirectory(){

        DirectoryChooser directoryChooser=new DirectoryChooser();
        directoryChooser.setTitle("Output location");
        URI directoryPath=directoryChooser.showDialog(mainStage).toURI();
        outputDir.setText(directoryPath.getPath());
        outputPath=directoryPath;

    }

    @FXML
    protected void startAnalyzing(){

        titles=main_titles.getText().split("\\r\\n|\\r|\\n");
        transfer_title_list=transfer_titles.getText().split("\\r\\n|\\r|\\n");

        csvAnalysis=new CSVAnalysis(inputPath,titles,outputPath,transfer_title_list);
        csvAnalysis.demo();

    }

    @FXML
    protected void stopProgram(){
        inputDir.setText("Input Directory");
        outputDir.setText("Output Directory");

        try {
            csvAnalysis.stopProcess();
        }catch (NullPointerException exception){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The process have not been started and result file not generated yet.");
            alert.show();
        }

    }



}