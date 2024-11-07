package com.wjf.self_demo.util;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.wjf.self_demo.IndexActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@RequiresApi(api = Build.VERSION_CODES.N)
public class JacocoInstrumentation  implements FinishListener {
    public static String TAG = "JacocoInstrumentation:";
    private static String DEFAULT_COVERAGE_FILE_PATH = "/mnt/sdcard/coverage.ec";
    private final Bundle mResults = new Bundle();
    private Intent mIntent;
    private static final boolean LOGD = true;
    private boolean mCoverage = true;
    private String mCoverageFilePath;

    public JacocoInstrumentation() {

    }

    public void onCreate(Context context) {
        DEFAULT_COVERAGE_FILE_PATH = context.getFilesDir().getPath() + "/coverage.ec";

        File file = new File(DEFAULT_COVERAGE_FILE_PATH);
        if (file.isFile() && file.exists()) {
            if (file.delete()) {
                Log.e(TAG, "file del successs");
            } else {
                Log.e(TAG, "file del fail !");
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "异常 : " + e);
                e.printStackTrace();
            }
        }

//
//        mIntent = new Intent(getTargetContext(), IndexActivity.class);
//        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        start();
    }


    public void onStart() {
        Log.e(TAG, "onStart def");
        if (LOGD) {
            Log.e(TAG, "onStart()");
        }

//        Looper.prepare();
//        IndexActivity activity = (IndexActivity) startActivitySync(mIntent);
//        activity.setFinishListener(this);
    }

    private boolean getBooleanArgument(Bundle arguments, String tag) {
        String tagString = arguments.getString(tag);
        return tagString != null && Boolean.parseBoolean(tagString);
    }

    private void generateCoverageReport() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(getCoverageFilePath(), false);
            Object agent = Class.forName("org.jacoco.agent.rt.RT")
                    .getMethod("getAgent")
                    .invoke(null);
            out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class)
                    .invoke(agent, false));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getCoverageFilePath() {
        if (mCoverageFilePath == null) {
            return DEFAULT_COVERAGE_FILE_PATH;
        } else {
            return mCoverageFilePath;
        }
    }

    private boolean setCoverageFilePath(String filePath) {
        if (filePath != null && filePath.length() > 0) {
            mCoverageFilePath = filePath;
            return true;
        }
        return false;
    }

    private void reportEmmaError(Exception e) {
        reportEmmaError("", e);
    }

    private void reportEmmaError(String hint, Exception e) {
        String msg = "Failed to generate emma coverage. " + hint;
        Log.e(TAG, msg);
        mResults.putString(Instrumentation.REPORT_KEY_STREAMRESULT, "\nError: "
                + msg);
    }

    @Override
    public void onActivityFinished() {
        if (LOGD) {
            Log.e(TAG, "onActivityFinished()");
        }
        if (mCoverage) {
            Log.e(TAG, "onActivityFinished mCoverage true");
            generateCoverageReport();
        }
    }

    @Override
    public void dumpIntermediateCoverage(String filePath) {
        // TODO Auto-generated method stub
        if (LOGD) {
            Log.e(TAG, "Intermidate Dump Called with file name :" + filePath);
        }
        if (mCoverage) {
            if (!setCoverageFilePath(filePath)) {
                if (LOGD) {
                    Log.e(TAG, "Unable to set the given file path:" + filePath + " as dump target.");
                }
            }
            generateCoverageReport();
            setCoverageFilePath(DEFAULT_COVERAGE_FILE_PATH);
        }
    }


}
