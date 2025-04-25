package Utilities;

// Importing necessary classes
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    // Declaring variables for retry count and maximum retry count
    private int retryCount = 0;
    private static final int maxRetryCount = 3;

    // Overriding the retry method to implement retry logic
    @Override
    public boolean retry(ITestResult result) {
        // Checking if the current retry count is less than the maximum retry count
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true; // Retry the test
        }
        return false; // Do not retry the test
    }
}
