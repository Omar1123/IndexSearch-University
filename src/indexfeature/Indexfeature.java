/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexfeature;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jake
 */
public class Indexfeature {


    private File INDEX = new File("/Users/jake/NetBeansProjects/indexfeature/src/indexfeature/index.txt");
    private File REAL = new File("/Users/jake/NetBeansProjects/indexfeature/src/indexfeature/real.txt");
    private Scanner scanner = new Scanner(System.in);    
    private String numberOfPlaca;
    private boolean exist;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Indexfeature index = new Indexfeature();
            index.run();
        } catch(Exception ex) {            
        }        
    }
    
    private void run() {
        System.out.println("Ejercicio con indices");
        System.out.println("Ingrese la placa");
        
        numberOfPlaca = scanner.next();  
        this.existInIndex(numberOfPlaca);
    }        
    
    private void existInIndex(String number) {                
        try{
            
            int index = 0;       
            
            BufferedReader file = new BufferedReader(new FileReader(INDEX));;
            String line = "";
            
            boolean search = false;
            boolean getNext= false;
            
            while((line = file.readLine())!= null && !getNext){
                index++;
                if(search){                    
                    getNext = true;
                }else if(line.indexOf(number)!= -1){
                    search = true;
                    exist = true;
                }else{
                    exist = false;
                } 
            }   
            
            if(exist != true) {
                System.out.println("Esta placa no existe: " + number);
            } else {
                System.out.println("Esta placa si existe: " + number);
                this.readFromRealFile(number);
            }
            
        }catch(IOException exception){
            System.out.println(exception);
        }
    }       
    
    private void readFromRealFile(String number) {
        try {
    
            BufferedReader buffer = new BufferedReader(new FileReader(REAL));                
            String lines;

            while((lines = buffer.readLine())!= null) {
                if(lines.indexOf(",")!= -1){
                    if (lines.split(",")[0].equalsIgnoreCase(number)) {                        
                        System.out.println("Marca: " + lines.split(",")[1]);
                        System.out.println("Modelo: " + lines.split(",")[2]);
                        System.out.println("Color: " + lines.split(",")[3]);
                        System.out.println("Año: " + lines.split(",")[4]);
                    } 
                }
            }

            buffer.close();
        } catch(Exception ex) {
            System.out.println("Error in the lecture");
        }        
    }
}
