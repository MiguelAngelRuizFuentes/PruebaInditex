import com.intuit.karate.Results;
import com.intuit.karate.Runner;

public class PetStoreAutomation {

    public static void main(String[] args) {
        Results results = Runner.path("classpath:petstore.feature").tags("~@ignore").parallel(1);
        results.reportDir("target/surefire-reports");
    }
}
