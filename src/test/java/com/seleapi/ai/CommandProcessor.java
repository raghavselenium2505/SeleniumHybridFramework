package com.seleapi.ai;

import java.io.FileInputStream;
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

    public String processCommand(String input) {

        try {

            String command = input.trim();

            logger.info("Command Received: {}", command);

            // =========================================
            // LOAD CONFIG
            // =========================================

            Properties prop = new Properties();

            prop.load(
                    new FileInputStream(
                            "src/test/resources/properties/config.properties"));

            String suitePath =
                    prop.getProperty("testngSuitePath");

            // =========================================
            // PARSE COMMAND
            // =========================================

            if (command.startsWith("run ")) {

                String value =
                        command.replace("run ", "").trim();

                // =========================================
                // 🔥 CASE 1 — RUN SUITE
                // =========================================

                if (value.equalsIgnoreCase("suite")) {

                    logger.info("Running Full Suite");

                    TestNG testng = new TestNG();

                    testng.setTestSuites(
                            List.of(suitePath));

                    testng.run();

                    return "Suite Execution Completed";
                }

                // =========================================
                // 🔥 CASE 2 — MULTIPLE EXECUTION
                // =========================================

                else if (value.contains(",")) {

                    // 🔥 CHECK WHETHER CLASSES OR METHODS
                    if (value.contains("Test")) {

                        runMultipleClasses(value);

                        return "Multiple Class Execution Completed";
                    }

                    else {

                        runMultipleMethods(value);

                        return "Multiple Method Execution Completed";
                    }
                }

                // =========================================
                // 🔥 CASE 3 — SINGLE CLASS
                // =========================================

                else if (value.endsWith("Test")) {

                    String className =
                            "com.seleapi.tests." + value;

                    logger.info(
                            "Running Single Class: {}",
                            className);

                    TestNG testng = new TestNG();

                    testng.setTestClasses(
                            new Class[]{
                                    Class.forName(className)
                            });

                    testng.run();

                    return "Single Class Execution Completed";
                }

                // =========================================
                // 🔥 CASE 4 — SINGLE METHOD
                // =========================================

                else {

                    runSingleMethodDynamic(value);

                    return "Single Method Execution Completed";
                }
            }

            else {

                return "Invalid Command Format";
            }

        }

        catch (Exception e) {

            logger.error(
                    "Bot Execution Failed",
                    e);

            return "Execution Failed";
        }
    }

    // =========================================
    // 🔥 SINGLE METHOD EXECUTION
    // =========================================

    private void runSingleMethodDynamic(
            String methodName) {

        try {

            Class<?> clazz =
                    findClassByMethod(methodName);

            if (clazz == null) {

                logger.error(
                        "Method not found: {}",
                        methodName);

                return;
            }

            logger.info(
                    "Running Method '{}' from class '{}'",
                    methodName,
                    clazz.getName());

            TestNG testng = new TestNG();

            XmlSuite suite = new XmlSuite();

            suite.setName("SingleMethodSuite");

            XmlTest test =
                    new XmlTest(suite);

            test.setName("SingleMethodTest");

            XmlClass xmlClass =
                    new XmlClass(clazz.getName());

            xmlClass.setIncludedMethods(
                    List.of(
                            new XmlInclude(methodName)));

            test.setXmlClasses(
                    List.of(xmlClass));

            testng.setXmlSuites(
                    List.of(suite));

            testng.run();

        }

        catch (Exception e) {

            logger.error(
                    "Error running single method",
                    e);
        }
    }

    // =========================================
    // 🔥 MULTIPLE METHODS EXECUTION
    // =========================================

    private void runMultipleMethods(
            String input) {

        try {

            String[] methods =
                    input.split(",");

            // 🔥 CHANGE THIS CLASS BASED ON YOUR NEED
            Class<?> clazz =
                    com.seleapi.tests
                    .MilkManLoginWithMontlyTest.class;

            TestNG testng = new TestNG();

            XmlSuite suite =
                    new XmlSuite();

            suite.setName("MultipleMethodSuite");

            suite.setParallel(
                    XmlSuite.ParallelMode.METHODS);

            suite.setThreadCount(3);

            XmlTest test =
                    new XmlTest(suite);

            test.setName("MultipleMethodTest");

            XmlClass xmlClass =
                    new XmlClass(clazz.getName());

            List<XmlInclude> includeMethods =
                    new ArrayList<>();

            for (String method : methods) {

                includeMethods.add(
                        new XmlInclude(
                                method.trim()));
            }

            xmlClass.setIncludedMethods(
                    includeMethods);

            test.setXmlClasses(
                    List.of(xmlClass));

            testng.setXmlSuites(
                    List.of(suite));

            logger.info(
                    "Running Multiple Methods: {}",
                    input);

            testng.run();

        }

        catch (Exception e) {

            logger.error(
                    "Error running multiple methods",
                    e);
        }
    }

    // =========================================
    // 🔥 MULTIPLE CLASS EXECUTION
    // =========================================

    private void runMultipleClasses(
            String input) {

        try {

            Properties prop = new Properties();

            prop.load(
                    new FileInputStream(
                            "src/test/resources/properties/config.properties"));

            String executionMode =
                    prop.getProperty(
                            "executionMode",
                            "SEQUENTIAL");

            int threadCount =
                    Integer.parseInt(
                            prop.getProperty(
                                    "threadCount",
                                    "2"));

            String[] classes =
                    input.split(",");

            List<Class<?>> classList =
                    new ArrayList<>();

            for (String className : classes) {

                String fullClassName =
                        "com.seleapi.tests."
                        + className.trim();

                classList.add(
                        Class.forName(fullClassName));
            }

            TestNG testng = new TestNG();

            // =========================================
            // 🔥 PARALLEL EXECUTION
            // =========================================

            if (executionMode
                    .equalsIgnoreCase("PARALLEL")) {

                XmlSuite suite =
                        new XmlSuite();

                suite.setName("ParallelSuite");

                suite.setParallel(
                        XmlSuite.ParallelMode.CLASSES);

                suite.setThreadCount(threadCount);

                XmlTest test =
                        new XmlTest(suite);

                test.setName("ParallelTest");

                List<XmlClass> xmlClasses =
                        new ArrayList<>();

                for (Class<?> clazz : classList) {

                    xmlClasses.add(
                            new XmlClass(
                                    clazz.getName()));
                }

                test.setXmlClasses(xmlClasses);

                testng.setXmlSuites(
                        List.of(suite));

                logger.info(
                        "Running classes in PARALLEL mode");

            }

            // =========================================
            // 🔥 SEQUENTIAL EXECUTION
            // =========================================

            else {

                testng.setTestClasses(
                        classList.toArray(new Class[0]));

                logger.info(
                        "Running classes in SEQUENTIAL mode");
            }

            testng.run();

        }

        catch (Exception e) {

            logger.error(
                    "Error running multiple classes",
                    e);
        }
    }

    // =========================================
    // 🔥 CLASS FINDER
    // =========================================

    private Class<?> findClassByMethod(
            String methodName) {

        try {

            Class<?>[] classes =
                    new Class[]{

                            com.seleapi.tests
                            .MilkManSignupAnnuallyTest.class,

                            com.seleapi.tests
                            .MilkManLoginWithMontlyTest.class,

                            com.seleapi.tests
                            .MilkManLoginWithWeeklyTest.class
                    };

            for (Class<?> clazz : classes) {

                for (java.lang.reflect.Method method :
                        clazz.getDeclaredMethods()) {

                    if (method.getName()
                            .equalsIgnoreCase(methodName)) {

                        return clazz;
                    }
                }
            }

        }

        catch (Exception e) {

            logger.error(
                    "Error finding class",
                    e);
        }

        return null;
    }
}