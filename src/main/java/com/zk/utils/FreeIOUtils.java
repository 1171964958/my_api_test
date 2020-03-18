package com.zk.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

public class FreeIOUtils {

    public static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {

            }
        }
    }

    public static void close(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {

            }
        }
    }

    public static void close(Reader r) {
        if (r != null) {
            try {
                r.close();
            } catch (IOException e) {

            }
        }
    }

    public static void close(Writer w) {
        if (w != null) {
            try {
                w.close();
            } catch (IOException e) {

            }
        }
    }
    public static void close(Workbook w) {
        if (w != null) {
            try {
                w.close();
            } catch (IOException e) {

            }
        }
    }
}