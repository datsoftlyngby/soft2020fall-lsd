package dk.cphbusiness.logging;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import co.elastic.apm.attach.ElasticApmAttacher;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.CountingMode;
import io.micrometer.core.instrument.simple.SimpleConfig;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.IllegalFormatException;

public class Program {
    private static final Logger logger = LogManager.getLogger("Program");
    private static MeterRegistry registry = new SimpleMeterRegistry(
            new SimpleConfig() {
                @Override
                public CountingMode mode() {
                    return CountingMode.STEP;
                    }
                @Override
                public Duration step() {
                    return Duration.ofSeconds(30);
                    }
                @Override
                public String get(String s) {
                    return null;
                    }
                },
            Clock.SYSTEM
            );
    public static void main(String[] args) throws IOException {
        logger.info("main started");
        ElasticApmAttacher.attach();
        var input = new BufferedReader(new InputStreamReader(System.in));
        String line = input.readLine();
        while (line != null && !"X".equalsIgnoreCase(line)) {
            var transaction = ElasticApm.startTransaction();
            try {
                transaction.setName("Program#main");
                transaction.setType("main");
                if (line.startsWith("!")) throw new RuntimeException("! not allowed in word: "+line);
                System.out.println("You wrote: "+line);
                registry.counter("program.print");
                }
            catch (Exception e) {
                registry.counter("program.error");
                transaction.captureException(e);
                logger.error("main failed", e);
                // throw e;
                }
            finally {
                transaction.end();
                }
            doSomething();
            line = input.readLine();
            }
        logger.info("main exited");
        }

    @CaptureSpan
    public static void doSomething() {
        System.out.println(" ... done!");
        registry.counter("program.done");
        }
    }
