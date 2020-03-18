package com.zk.mit;

import java.text.DecimalFormat;

public class Test_forOcr {
    public static void main(String[] args) {
        double a=39.51;  //上次版本
        double b=34.13 ;    //安全版本

        System.out.println(calculate(a,b));


    }


    /**
     *
     * @param a  提升前
     * @param b  提升后(安全版本)
     * @return
     */
    public static String calculate(double a,double b){
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format((b-a)/a *100) +"%";

    }
}
