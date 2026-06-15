package com.seleapi.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {

    public static void writeJavaFile(String filePath,
                                     String content) {

        try {

            File file = new File(filePath);

            File parentFolder = file.getParentFile();

            if (!parentFolder.exists()) {

                parentFolder.mkdirs();
            }

            FileWriter writer = new FileWriter(file);

            writer.write(content);

            writer.flush();

            writer.close();

            System.out.println("File Generated Successfully");

            System.out.println(filePath);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}