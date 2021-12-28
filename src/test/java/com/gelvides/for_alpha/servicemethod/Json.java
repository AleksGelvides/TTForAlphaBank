package com.gelvides.for_alpha.servicemethod;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public interface Json {
    static String getJSONString(String path){
        StringBuilder jsonToString = new StringBuilder();
        try {
            FileReader fr = new FileReader(path);
            Scanner scanner = new Scanner(fr);
            while(scanner.hasNext()){
                jsonToString.append(scanner.nextLine());
            }
        }catch (IOException e){
            System.out.println(e + "File not found");
        }
        return jsonToString.toString();
    }
}
