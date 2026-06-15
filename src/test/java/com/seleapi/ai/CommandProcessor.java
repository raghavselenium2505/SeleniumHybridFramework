package com.seleapi.ai;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class CommandProcessor {

    private static final Logger logger =
            LoggerFactory.getLogger(CommandProcessor.class);

    Properties prop = new Properties();

    // =========================================
    // MAIN COMMAND PROCESSOR
    // =========================================

    public String processCommand(String input) {

        try {

            prop.load(
                    new FileInputStream(
                            "src/test/resources/properties/config.properties"));

            String command = input.trim();

            logger.info(
                    "Command Received : {}",
                    command);

            if(command.startsWith("run ")) {

                String value =
                        command.replace("run ", "")
                               .trim();

                // =========================================
                // RUN FULL SUITE
                // =========================================

                if(value.equalsIgnoreCase("suite")) {

                    return runSuite();
                }

                // =========================================
                // MULTIPLE EXECUTION
                // =========================================

                else if(value.contains(",")) {

                    String[] values =
                            value.split(",");

                    boolean allClasses = true;

                    for(String item : values) {

                        Class<?> clazz =
                                findClassByName(
                                        item.trim());

                        if(clazz == null) {

                            allClasses = false;
                            break;
                        }
                    }

                    // =========================================
                    // MULTIPLE CLASS EXECUTION
                    // =========================================

                    if(allClasses) {

                        runMultipleClasses(value);

                        return "Multiple Class Execution Completed";
                    }

                    // =========================================
                    // MULTIPLE METHOD EXECUTION
                    // =========================================

                    else {

                        runMultipleMethods(value);

                        return "Multiple Method Execution Completed";
                    }
                }

                // =========================================
                // SINGLE CLASS EXECUTION
                // =========================================

                else {

                    Class<?> clazz =
                            findClassByName(value);

                    if(clazz != null) {

                        logger.info(
                                "Class Found : {}",
                                clazz.getName());

                        runSingleClass(value);

                        return "Single Class Execution Completed";
                    }

                    // =========================================
                    // SINGLE METHOD EXECUTION
                    // =========================================

                    else {

                        logger.info(
                                "Trying Method Execution : {}",
                                value);

                        runSingleMethodDynamic(value);

                        return "Single Method Execution Completed";
                    }
                }
            }

            return "Invalid Command";

        }

        catch(Exception e) {

            logger.error(
                    "Execution Failed",
                    e);

            return "Execution Failed";
        }
    }

    // =========================================
    // RUN SUITE
    // =========================================

    private String runSuite() {

        try {

            String suitePath =
                    prop.getProperty(
                            "testngSuitePath");

            logger.info(
                    "Running Full Suite");

            TestNG testng = new TestNG();

            testng.setTestSuites(
                    List.of(suitePath));

            testng.run();

            return "Suite Execution Completed";
        }

        catch(Exception e) {

            logger.error(
                    "Suite Execution Failed",
                    e);

            return "Suite Execution Failed";
        }
    }

    // =========================================
    // SINGLE CLASS EXECUTION
    // =========================================

    private void runSingleClass(
            String className) {

        try {

            Class<?> clazz =
                    findClassByName(className);

            if(clazz == null) {

                logger.error(
                        "Class Not Found : {}",
                        className);

                return;
            }

            logger.info(
                    "Executing Class : {}",
                    clazz.getName());

            TestNG testng = new TestNG();

            testng.setTestClasses(
                    new Class[] { clazz });

            testng.run();

            logger.info(
                    "Class Execution Completed");
        }

        catch(Exception e) {

            logger.error(
                    "Single Class Execution Failed",
                    e);
        }
    }

    // =========================================
    // MULTIPLE CLASS EXECUTION
    // =========================================

    private void runMultipleClasses(
            String input) {

        try {

            String[] classNames =
                    input.split(",");

            List<Class<?>> classList =
                    new ArrayList<>();

            for(String className : classNames) {

                Class<?> clazz =
                        findClassByName(
                                className.trim());

                if(clazz != null) {

                    classList.add(clazz);

                    logger.info(
                            "Added Class : {}",
                            clazz.getName());
                }
            }

            if(classList.isEmpty()) {

                logger.error(
                        "No Valid Classes Found");

                return;
            }

            TestNG testng = new TestNG();

            testng.setTestClasses(
                    classList.toArray(
                            new Class[0]));

            testng.run();

            logger.info(
                    "Multiple Class Execution Completed");
        }

        catch(Exception e) {

            logger.error(
                    "Multiple Class Execution Failed",
                    e);
        }
    }

    // =========================================
    // SINGLE METHOD EXECUTION
    // =========================================

    private void runSingleMethodDynamic(
            String methodName) {

        try {

            Class<?> clazz =
                    findClassByMethod(methodName);

            if(clazz == null) {

                logger.error(
                        "Method Not Found : {}",
                        methodName);

                return;
            }

            logger.info(
                    "Executing Method : {} from {}",
                    methodName,
                    clazz.getName());

            TestNG testng = new TestNG();

            XmlSuite suite =
                    new XmlSuite();

            suite.setName(
                    "SingleMethodSuite");

            XmlTest test =
                    new XmlTest(suite);

            test.setName(
                    "SingleMethodTest");

            XmlClass xmlClass =
                    new XmlClass(
                            clazz.getName());

            List<XmlInclude> methods =
                    new ArrayList<>();

            methods.add(
                    new XmlInclude(methodName));

            xmlClass.setIncludedMethods(
                    methods);

            test.setXmlClasses(
                    List.of(xmlClass));

            testng.setXmlSuites(
                    List.of(suite));

            testng.run();

            logger.info(
                    "Single Method Execution Completed");
        }

        catch(Exception e) {

            logger.error(
                    "Single Method Execution Failed",
                    e);
        }
    }

    // =========================================
    // MULTIPLE METHOD EXECUTION
    // =========================================

    private void runMultipleMethods(
            String input) {

        try {

            String[] methods =
                    input.split(",");

            Class<?> clazz =
                    findClassByMethod(
                            methods[0].trim());

            if(clazz == null) {

                logger.error(
                        "Method Class Not Found");

                return;
            }

            XmlSuite suite =
                    new XmlSuite();

            suite.setName(
                    "MultipleMethodSuite");

            suite.setParallel(
                    XmlSuite.ParallelMode.METHODS);

            suite.setThreadCount(3);

            XmlTest test =
                    new XmlTest(suite);

            test.setName(
                    "MultipleMethodTest");

            XmlClass xmlClass =
                    new XmlClass(
                            clazz.getName());

            List<XmlInclude> includeMethods =
                    new ArrayList<>();

            for(String method : methods) {

                includeMethods.add(
                        new XmlInclude(
                                method.trim()));
            }

            xmlClass.setIncludedMethods(
                    includeMethods);

            test.setXmlClasses(
                    List.of(xmlClass));

            TestNG testng = new TestNG();

            testng.setXmlSuites(
                    List.of(suite));

            testng.run();

            logger.info(
                    "Multiple Method Execution Completed");
        }

        catch(Exception e) {

            logger.error(
                    "Multiple Method Execution Failed",
                    e);
        }
    }

    // =========================================
    // DYNAMIC CLASS FINDER
    // =========================================

    private Class<?> findClassByName(
            String simpleClassName) {

        String[] packages = {

                "com.seleapi.tests",
                "com.seleapi.tests.api",
                "com.seleapi.tests.playwright"
        };

        for(String pkg : packages) {

            try {

                String fullClassName =
                        pkg + "." + simpleClassName;

                logger.info(
                        "Trying Class : {}",
                        fullClassName);

                return Class.forName(fullClassName);

            }

            catch(ClassNotFoundException e) {

                // Continue searching
            }
        }

        return null;
    }

    // =========================================
    // DYNAMIC METHOD FINDER
    // =========================================

    private Class<?> findClassByMethod(
            String methodName) {

        String[] packages = {

                "com.seleapi.tests",
                "com.seleapi.tests.api",
                "com.seleapi.tests.playwright"
        };

        for(String pkg : packages) {

            try {

                List<Class<?>> classes =
                        getClassesFromPackage(pkg);

                for(Class<?> clazz : classes) {

                    Method[] methods =
                            clazz.getDeclaredMethods();

                    for(Method method : methods) {

                        if(method.getName()
                                .equalsIgnoreCase(methodName)) {

                            logger.info(
                                    "Matched Method : {} in {}",
                                    methodName,
                                    clazz.getName());

                            return clazz;
                        }
                    }
                }
            }

            catch(Exception e) {

                logger.error(
                        "Method Search Failed",
                        e);
            }
        }

        return null;
    }

    // =========================================
    // PACKAGE CLASS SCANNER
    // =========================================

    private List<Class<?>> getClassesFromPackage(
            String packageName) {

        List<Class<?>> classes =
                new ArrayList<>();

        try {

            String path =
                    packageName.replace('.', '/');

            ClassLoader classLoader =
                    Thread.currentThread()
                          .getContextClassLoader();

            URL resource =
                    classLoader.getResource(path);

            if(resource == null) {

                return classes;
            }

            File directory =
                    new File(resource.getFile());

            if(directory.exists()) {

                String[] files =
                        directory.list();

                if(files != null) {

                    for(String file : files) {

                        if(file.endsWith(".class")) {

                            String className =
                                    packageName + "."
                                    + file.replace(".class", "");

                            try {

                                Class<?> clazz =
                                        Class.forName(className);

                                classes.add(clazz);

                                logger.info(
                                        "Loaded Class : {}",
                                        clazz.getName());

                            }

                            catch(Exception e) {

                                logger.error(
                                        "Unable To Load Class : {}",
                                        className);
                            }
                        }
                    }
                }
            }
        }

        catch(Exception e) {

            logger.error(
                    "Package Scan Failed",
                    e);
        }

        return classes;
    }
}