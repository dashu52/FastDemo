package org.dx.wa.fastdemo;

import android.app.Application;

import com.google.gson.Gson;

import org.devio.hi.library.log.HiConsolePrinter;
import org.devio.hi.library.log.HiFilePrinter;
import org.devio.hi.library.log.HiLogConfig;
import org.devio.hi.library.log.HiLogManager;

/**
 * Description
 *
 * @author jianlinduan
 * 2020/10/20, Create file
 */
class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private void initLog() {
        HiLogManager.init(new HiLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return new JsonParser() {
                    @Override
                    public String toJson(Object src) {
                        return new Gson().toJson(src);
                    }
                };
            }

            @Override
            public String getGlobalTag() {
                return "my_application_tag";
            }

            @Override
            public boolean enable() {
                return true;
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 3;
            }
        },
                new HiConsolePrinter()
        ,HiFilePrinter.getInstance(getApplicationContext().getCacheDir().getAbsolutePath(),0));
    }
}
