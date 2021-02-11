import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String[] files = scanner.nextLine().split(";");
        for(int j = 0; j < files.length;j++){
            if(!files[j].contains(".csv")){
                files[j] = null;
            }
        }
        Thread[] threads = new Thread[files.length];
         for(int i = 0; i < files.length; i++){
             if(files[i] != null) {
                 threads[i] = new DoFileWork(files[i]);
                 threads[i].start();
             }
        }





    }
}
