package com.example.csvanalyzer;

import javafx.scene.control.*;
import org.apache.commons.csv.CSVFormat;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;



public class CSVAnalysis {

    URI inputPatStr;
    URI outputPatStr;

    String[]main_headers;
    String[]transfer_headers;

    String[]headers={"id","name1","name4"};

    File file;
    public CSVAnalysis(){

    }
public CSVAnalysis(URI inputPatStr,String[]main_headers,URI outputPatStr,String[]transfer_headers){
    this.inputPatStr=inputPatStr;
    this.outputPatStr=outputPatStr;

    this.main_headers=main_headers;
    this.transfer_headers=transfer_headers;



}

public void createCSVFile(){

    try {
        file = File.createTempFile("result",".csv",new File(outputPatStr));
    } catch (IOException e) {
        e.printStackTrace();
    }catch (NullPointerException nullPointerException){
        if(inputPatStr==null){
            showErrorMessageDialog("You have not selected any files yet, Please select a CSV file to start analyzing.");
        }else if(outputPatStr==null){
            showErrorMessageDialog("You have not selected the directory that you want the result file been saved there. Please select it first! ");
        }

    }
}
    public
    void demo ( )
    {
        createCSVFile();
        try
        {


            // Read CSV file.
            Path pathInput = Paths.get(inputPatStr);

            Path pathOutput = Paths.get ( outputPatStr);

            try (
                    final BufferedReader reader = Files.newBufferedReader ( pathInput , StandardCharsets.UTF_8 );

                    final CSVPrinter printer = CSVFormat.RFC4180.withHeader ( transfer_headers[0],transfer_headers[1],transfer_headers[2]).print ( file , StandardCharsets.UTF_8 );

            )
            {
                Iterable < CSVRecord > records = CSVFormat.RFC4180.withFirstRecordAsHeader ().parse ( reader );
                // We expect these headers: name1,name2,name3
                System.out.println("name1 ,name2 ,name3");
                for ( CSVRecord record : records )
                {
                    // Read.
                    //Integer id = Integer.valueOf ( record.get ( "ID" ) );
                    String name1 = record.get ( transfer_headers[0] );
                    String name2 = record.get ( transfer_headers[1] );
                    String name3 = record.get ( transfer_headers[2] );


                    // Write.
                    printer.printRecord ( name1 , name2 , name3 );
                }
            }
            showResultMessageDialog("The process was run successfully. Now, you can see the result file with .csv extension" +
                    " in the selected output directory.");

        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            showErrorMessageDialog("Please add at least 3 title in text area of new titles!");
            file.delete();
        }
        catch ( InvalidPathException e )
        {
            e.printStackTrace ();
        } catch (FileSystemException fileSystemException)
        {

            showErrorMessageDialog("The CSV file that selected as result file,\n" +
                    "is in use. Please close the file and try again.");

        }catch (NullPointerException nullPointerException){

        }catch (IllegalArgumentException illegalArgumentException){
            showErrorMessageDialog("New headers are not exist in main headers , please enter right headers.");
            file.delete();
        }
        catch ( Exception e )
        {
            e.printStackTrace ();
        }
    }

    public void showErrorMessageDialog(String message){

        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();

    }
    public void showResultMessageDialog(String message){

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();

    }
    public void stopProcess(){
        if(file!=null){
            file.delete();
        }
    }

    

}
