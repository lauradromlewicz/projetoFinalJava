package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Logger {
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static List<String> textos = new ArrayList<>();

    public static void salvar(String mensagem, String nomeArquivo) throws Exception{
    
        File pasta = new File("log");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    
        pasta = new File("log/"+ nomeArquivo + ".txt");
        
        if (pasta.exists()) textos.addAll(ler(nomeArquivo));
    
        try {
            PrintWriter gravar = new PrintWriter(new FileWriter(pasta));
            String dataHora = formato.format(LocalDateTime.now());
            textos.add("Data e hora: " + dataHora + " -- " + mensagem);

            for (String string : textos) {
                gravar.println(string);
            }
            textos.clear();
            gravar.close();
            
        }catch(IOException e) {
            throw new Exception("O arquivo não pode ser gravado " + e.getMessage());
        }catch(Exception e) {
            throw new Exception("Ocorreu um erro inesperado " + e.getMessage());
        }
    }
public static void salvarErros(String mensagem, String nome) throws Exception{
    
        File pasta = new File("log");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    
        pasta = new File("log/"+ nome + ".txt");
        
        if (pasta.exists()) textos.addAll(ler(nome));
    
        try {
            PrintWriter gravar = new PrintWriter(new FileWriter(pasta));
            String dataHora = formato.format(LocalDateTime.now());
            textos.add("Data e hora: " + dataHora + " -- " + mensagem);
            
            for (String string : textos) {
                gravar.println(string);
            }
            textos.clear();
            gravar.close();
            
        }catch(IOException e) {
            throw new Exception("O arquivo não pode ser gravado " + e.getMessage());
        }catch(Exception e) {
            throw new Exception("Ocorreu um erro inesperado " + e.getMessage());
        }
    }

    public static List<String> ler(String nome) throws Exception{
        List<String> textos = new ArrayList<>();
    
        String nomeArquivo = "log/"+ nome + ".txt";
        try{
            File arquivo = new File(nomeArquivo);
            Scanner scanner = new Scanner(arquivo);
    
            while (scanner.hasNextLine()) {
                textos.add(scanner.nextLine());
            }
            scanner.close();
            return textos;
        }catch(FileNotFoundException e) {
            throw new Exception("Arquivo não encontrado " + e.getMessage());
        }catch(Exception e){
            throw new Exception("Ocorreu um erro inesperado " + e.getMessage());
        }
    }
         
    
    public static List<String> getTextos() {
        return textos;
    }

    public void setTextos(List<String> textos) {
        Logger.textos = textos;
    }
}
