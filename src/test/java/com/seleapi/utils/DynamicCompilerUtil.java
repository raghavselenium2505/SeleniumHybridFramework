package com.seleapi.utils;

import java.io.File;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamicCompilerUtil {

    private static final Logger logger =
            LogManager.getLogger(DynamicCompilerUtil.class);

    public static boolean compileJavaFile(String javaFilePath) {

        logger.info("Compiling Java File : {}",
                javaFilePath);

        File file = new File(javaFilePath);

        if (!file.exists()) {

            logger.error("Java File Not Found");

            return false;
        }

        JavaCompiler compiler =
                ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {

            logger.error("Compiler Not Found. Use JDK.");

            return false;
        }

        int result =
                compiler.run(null,
                        null,
                        null,
                        javaFilePath);

        if (result == 0) {

            logger.info("Compilation Successful");

            return true;
        }

        logger.error("Compilation Failed");

        return false;
    }
}