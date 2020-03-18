package com.zk.test.listener;


import com.zk.test.MyRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public synchronized void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        // 对于dataProvider的用例，每次成功后，重置Retry次数
        MyRetryAnalyzer retry = (MyRetryAnalyzer) tr.getMethod().getRetryAnalyzer();


        retry.reset();

    }

    @Override
    public synchronized void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        // 对于dataProvider的用例，每次失败后，重置Retry次数
        MyRetryAnalyzer retry = (MyRetryAnalyzer) tr.getMethod().getRetryAnalyzer();


        retry.reset();

    }

}
