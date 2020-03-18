package com.zk.test;

import com.zk.utils.PropertiesUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;


/**
 * 设置testng用例失败重试次数
 */
public  class MyRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 1;
    private final int MAX_RETRY_COUNT = PropertiesUtils.getInteger("reErrorCount");
    private final boolean reErrorRun=PropertiesUtils.getBoolean("reErrorRun");

    @Override
    public  synchronized boolean retry(ITestResult iTestResult) {
            if(reErrorRun){

                if (retryCount <= MAX_RETRY_COUNT) {
                    String msg = "执行用例：" + iTestResult.getName() + "第" + retryCount + "次运行失败";
                    Reporter.log(msg);


                    retryCount++;

                    return true;
                }
            }
            return false;

    }

    public synchronized void reset(){

        retryCount=1;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getMAX_RETRY_COUNT() {
        return MAX_RETRY_COUNT;
    }
}
