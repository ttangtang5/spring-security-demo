package com.tang.security.demo;

import java.io.File;
import java.util.*;

/**
 * @Description
 * @Author tang
 * @Date 2019-08-22 21:26
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        //File file = new File("/Users/tang/Desktop/software/ideaWorkspace/security-oauth/security-demo/src");
        //file(file);
        //file2(file);
    }

    public static void file(File file) {
        if (file.exists()) {
            if(file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length != 0) {
                    for (int i = 0; i < files.length; i++) {
                        file(files[i]);
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile());
            }
        } else {
            System.out.println("输入错误");
        }
    }

    public static void file2(File file) {
        if (file.exists()) {
            if(file.isDirectory()) {
            LinkedList<File> list = new LinkedList<File>();
            list.add(file);
            while (!list.isEmpty()) {
                File temp = list.removeFirst();
                File[] files = temp.listFiles();
                if (files != null && files.length != 0) {
                    for (File file1 : files) {
                        if (file1.isDirectory()) {
                            list.add(file1);
                        } else {
                            System.out.println(file1.getAbsoluteFile());
                        }
                    }
                }
            }
            } else {
                System.out.println(file.getAbsoluteFile());
            }
        } else {
            System.out.println("输入错误");
        }
    }
}
