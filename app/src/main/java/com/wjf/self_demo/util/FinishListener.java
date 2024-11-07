package com.wjf.self_demo.util;

public interface FinishListener {
    void onActivityFinished();

    void dumpIntermediateCoverage(String filePath);
}