package com.isa.analysis.service.runtime.impl;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author zhaobing
 */
@Component
public class UtilRead {
    //读文件，变成流文件
    public BufferedReader getBufferedReaderForJson(String file) throws FileNotFoundException {
        File inFilePath = new File(file);
        FileInputStream fileInputStream = new FileInputStream(inFilePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader read = new BufferedReader(inputStreamReader);
        return read;
    }
}
