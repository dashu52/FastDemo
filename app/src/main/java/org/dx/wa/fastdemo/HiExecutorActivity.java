package org.dx.wa.fastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import org.devio.hi.library.executor.HiExecutor;
import org.devio.hi.library.log.HiLog;
import org.jetbrains.annotations.Nullable;

public class HiExecutorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        exe();
    }

    private void exe() {
        for (int i = 0; i < 100; i++) {
            MyTask task = new MyTask(i);
            HiExecutor.INSTANCE.execute(task);
        }
    }

    public void resume(View view){
        HiLog.d("resume");
        HiExecutor.INSTANCE.resume();
    }
    public void pause(View view){
        HiLog.d("pause");
        HiExecutor.INSTANCE.pause();
    }

    class MyTask extends HiExecutor.Callable<String>{

        private final int mId;

        public MyTask(int id){
            mId = id;
        }
        @Override
        public void onPrepare() {
            HiLog.d("onPrepare");
        }

        @Nullable
        @Override
        public String onBackground() {
            HiLog.d(mId+"onBackground start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HiLog.d(mId+"onBackground end");
            return mId+"exe over";
        }

        @Override
        public void onCompleted(@Nullable String s) {
            HiLog.d("onCompleted:"+s);
        }
    }
}