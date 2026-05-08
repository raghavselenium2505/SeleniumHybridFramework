package com.seleapi.ai;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotEngine {

    private static final Logger logger =
            LoggerFactory.getLogger(BotEngine.class);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // =========================================
            // LOAD CONFIG
            // =========================================

            Properties prop = new Properties();

            prop.load(
                    new FileInputStream(
                            "src/test/resources/properties/config.properties"));

            String continueExecution =
                    prop.getProperty(
                            "continueExecution",
                            "N");

            logger.info(
                    "===== AI TEST BOT STARTED =====");

            while (true) {

                logger.info("Enter Command:");

                String input =
                        sc.nextLine();

                // =========================================
                // EXIT COMMAND
                // =========================================

                if (input.equalsIgnoreCase("exit")) {

                    logger.info("Bot Closed");

                    break;
                }

                try {

                    CommandProcessor processor =
                            new CommandProcessor();

                    String response =
                            processor.processCommand(input);

                    logger.info(response);

                }

                catch (Exception e) {

                    logger.error(
                            "Error processing command: {}",
                            input,
                            e);
                }

                // =========================================
                // AUTO CLOSE LOGIC
                // =========================================

                if (continueExecution
                        .equalsIgnoreCase("N")) {

                    logger.info(
                            "Execution completed. Auto closing Bot Engine");

                    break;
                }
            }

        }

        catch (Exception e) {

            logger.error(
                    "Error in Bot Engine",
                    e);
        }

        finally {

            sc.close();
        }
    }
}