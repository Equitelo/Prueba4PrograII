package mypart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class MiArchivo {
    private File myFile = null;
    
    void setFile(String direccion){
        myFile = new File(direccion);
    }
    
    void info(){
        if (myFile.exists()) {
            System.out.println("\nNombre: "+myFile.getName());
            System.out.println("Path: "+myFile.getPath());
            System.out.println("Absoluta: "+myFile.getAbsolutePath());
            System.out.println("Bytes: "+myFile.length());
            System.out.println("Modificado en: "+new Date(myFile.lastModified()));
            System.out.println("Padre: "+myFile.getAbsoluteFile().getName());
            if (myFile.isFile()) {
                System.out.println("ES UN ARCHIVO");
            } else if(myFile.isDirectory()){
                System.out.println("ES FOLDER");
            }
            System.out.println("-+-+-+-+-+-+-+");
        }else {
            System.out.println("No existe!");
        } 
    }
    
    boolean crearFile() throws IOException{
        return myFile.createNewFile();
    }
    
    boolean crearFolder(){
        return myFile.mkdirs();
    }
    
    void borrar(){
        if (antidoto(myFile)) {
            System.out.println("Borrado!");
        } else {
            System.out.println("No se pudo borrar!");
        }
    }
    
    private boolean antidoto(File mf){
        if (mf.isDirectory()) {
            for (File child: mf.listFiles()) {
                antidoto(child);
            }
        }
        return mf.delete();
    }
    
    void dir(){
        if (myFile.isDirectory()) {
            System.out.println("Directorio de: "+myFile.getAbsolutePath());
            System.out.println("");
            //contadores
            int cfiles = 0, cdirs = 0, totalBytes=0;
            //recorrido
            for(File child: myFile.listFiles()){
                if (!child.isHidden()) {
                    //ultima modificacion
                    Date ultimo = new Date(child.lastModified());
                    System.out.print(ultimo+"\t"); //tabulacion
                    
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.println("<DIR>\t\t");
                    }else{
                        cfiles++;
                        //tbytes = totalizado
                        totalBytes += child.length();
                        System.out.println("    \t"+child.length()+"\t");
                    }
                    System.out.println(child.getName());   
                }
            }
            System.out.println(cfiles+" archivos\t"+totalBytes+" bytes");
            System.out.println(cdirs+" dirs\t");
        }
    }
    
    private void tree(File dir, String tab){
        if (dir.isDirectory()) {
            System.out.println(tab+dir.getName());
            for (File child: dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab+"--");
                }
            }
        }
    }
    
    void tree(){
        tree(myFile, "-");
    }
    
    public void rewriteInfo(String contenido) throws IOException {
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(myFile))) {
            buffWriter.write(contenido);
        }
    }
    
    public void addInfo(String contenido) throws IOException {
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(myFile, true))) {
            buffWriter.write(contenido);
            buffWriter.newLine();
        }
    }
    
    public String readFile() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(myFile))){
            String line;
            while ((line = buffRead.readLine()) != null){
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }
    
}
