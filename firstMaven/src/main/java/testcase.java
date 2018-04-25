import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class testcase {
    private static Logger logger = LogManager.getLogger(testcase.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("hello log4j");
        logger.error("hello log4j");
    }
}
