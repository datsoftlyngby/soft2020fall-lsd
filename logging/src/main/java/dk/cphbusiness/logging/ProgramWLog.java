package dk.cphbusiness.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramWLog {
    private final static Logger logger = LogManager.getLogger("ProgramWLog");

    public static void main(String[] args) throws IOException {
        logger.info("main started");
        var input = new BufferedReader(new InputStreamReader(System.in));

        String line = input.readLine();
        while (line != null && !"x".equalsIgnoreCase(line)) {
            try {
                if (line.startsWith("!")) {
                    throw new RuntimeException("line cannot start with ! as in " + line);
                    }
                System.out.println("Line was: " + line);
                }
            catch (Exception e) {
                System.err.println("Ups "+e.getMessage());
                logger.error("Ups der", e);
                }
            line = input.readLine();
            }
        logger.info("main exited");

        }

    }
