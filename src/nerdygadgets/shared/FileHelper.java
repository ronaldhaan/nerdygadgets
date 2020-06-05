package nerdygadgets.shared;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

public final class FileHelper {

    private FileHelper() { }

    public static void writeToLogFile(String data) {
        try {
            data = "\r\n\r\n" + data;
            String path = getLogFileAbsoluthPath();
            System.out.println(path);
            Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.APPEND);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLogFileAbsoluthPath() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 

        final String logFileName = "log-" + dtf.format(now) + ".txt";

        String logFilePath = "";
        try {

            Path root = getRootFolder();

            File file = new File(root.toString() + "\\src\\log\\");
            if(!file.exists()) {
                System.out.println(file.mkdirs());               
            }

            logFilePath = Paths.get(root.toString() + "\\src\\log\\" + logFileName).normalize().toAbsolutePath().toString();
                
            file = new File(logFilePath);
            System.out.println("Writable: " + file.setWritable(true));
            System.out.println("Existing log file: " + file.exists());

            if(!file.exists()) {
                System.out.println(file.createNewFile());
            }
        } catch(IOException e) {
            e.printStackTrace();
        } 
        
        return logFilePath;
    }

    public static Path getRootFolder() {
        return Paths.get(".").normalize().toAbsolutePath();
    }
}
