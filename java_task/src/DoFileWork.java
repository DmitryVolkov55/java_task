import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoFileWork extends  Thread {
    private String filename;
    private String path = "data/";

    public DoFileWork(String filename){
        this.filename = filename;
    }
    public void run(){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] ln = new String[lines.size()];

        for(int i = 0; i < lines.size();i++){
            ln[i] = lines.get(i);
        } // в массиве ln находятся считаные строки файла

        String [] titles = ln[0].split("\\;"); // заголовки файлов
        String [] components = new String[titles.length];

        for(int i = 0;i < components.length; i++){
            components[i]="";
        }

        for(int i = 1; i < ln.length; i++){
            String[] temp = ln[i].split("\\;");
            for( int j = 0; j < temp.length; j++) {
                if(!components[j].contains(temp[j])) {
                    components[j] = components[j] + temp[j] + ";"; //в каждой ячейке массива строка с отсортированными компонентами
                }
            }
        }
        for(int i = 0; i < titles.length; i++){
            try {
                File file = new File(path + titles[i] + ".txt"); // проверка есть ли такой файл уже
                if(file.exists()){
                    lines = Files.readAllLines(Paths.get("data/"+ titles[i] +".txt"));
                    String alreadyHave = lines.get(0);
                    String[] needToWrite = components[i].split("\\;");
                    for(int j = 0; j < needToWrite.length; j++){
                        if(!alreadyHave.contains(needToWrite[j])){
                            alreadyHave = alreadyHave + needToWrite[j] + ";";
                        }
                    }
                    Files.write(Paths.get("data/" + titles[i] + ".txt"), Collections.singleton(alreadyHave));
                }else {
                    Files.write(Paths.get("data/" + titles[i] + ".txt"), Collections.singleton(components[i]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
