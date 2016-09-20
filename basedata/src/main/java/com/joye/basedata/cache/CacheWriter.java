package com.joye.basedata.cache;

import java.io.File;

/**
 * Created by W,x (JoyeWang)
 * on 2016/9/20.
 * 304622254@qq.com
 * Remeark:
 */

public class CacheWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    public CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
        this.fileManager = fileManager;
        this.fileToWrite = fileToWrite;
        this.fileContent = fileContent;
    }

    @Override public void run() {
        this.fileManager.writeToFile(fileToWrite, fileContent);
    }
}
