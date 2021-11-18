package com.makespace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.makespace.Commands.CommandInvoker;
import com.makespace.Configs.ApplicationConfig;
import com.makespace.Exceptions.NoInputException;
import com.makespace.Repositories.Data.DataLoader;

public class App {
    public static void run(String inputFile){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        DataLoader dataLoader = applicationConfig.getDataLoader();
        dataLoader.executeData("ROOM-DATA");
        try{
        File file=new File(inputFile);    //creates a new file instance  
        FileReader fr=new FileReader(file);   //reads the file  
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
        String line;  
        while((line=br.readLine())!=null)  
        {   
            List<String>tokens = Arrays.asList(line.split(" "));
            commandInvoker.executeCommand(tokens.get(0), tokens);
        }  
        fr.close();    //closes the stream and release the resources   
      }  
      catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
    }

    public static void main(String[] args) {
        if(args.length < 1) 
        {
            System.out.println("No input");
            throw new NoInputException();
         }
        run(args[0]);
    }
}
