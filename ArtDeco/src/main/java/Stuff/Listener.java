package Stuff;

import org.testng.*;

/**
 * Created by savchenkoaleksandr on 5/15/17.
 */
public class Listener implements ISuiteListener, ITestListener , IInvokedMethodListener {

    private String returnMethodName(ITestNGMethod method){
        return method.getRealClass().getSimpleName()+"." + method.getMethodName();
    }

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        String textMsg = "About to begin following method "+ returnMethodName(iInvokedMethod.getTestMethod());
        Reporter.log(textMsg , true);
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        String textMsg = "Completed executing following method : "+ returnMethodName(iInvokedMethod.getTestMethod());
        Reporter.log(textMsg, true);
    }
@Override
    public void onStart(ISuite iSuite) {
        Reporter.log("About to begin SUITE "+ iSuite.getName(), true);
    }
@Override
    public void onFinish(ISuite iSuite) {
        Reporter.log("About to end SUITE "+ iSuite.getName(), true);
    }

    public void onTestStart(ITestResult iTestResult) {
        Reporter.log("About to start TEST "+iTestResult.getName(), true);
    }

    public void onTestSuccess(ITestResult iTestResult) {
        printTestResults(iTestResult);
    }

    public void onTestFailure(ITestResult iTestResult) {
        printTestResults(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        printTestResults(iTestResult);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        Reporter.log("About to to start executing test "+ iTestContext.getName(), true);
    }

    public void onFinish(ITestContext iTestContext) {
        Reporter.log("Completed executing test "+ iTestContext.getName(), true);
    }

    private void printTestResults(ITestResult result){
        Reporter.log("Test Method resides in "+result.getTestClass().getName(), true);
        if (result.getParameters().length !=0){
            String params = null;
            for (Object parameter : result.getParameters()){
                params += parameter.toString()+",";
            }
            Reporter.log("Test Method had the fallowing parameters : "+params , true);
        }
        String status = null;
        switch (result.getStatus()){
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
                break;
        }
        Reporter.log("Test status :" +status , true);
    }
}
