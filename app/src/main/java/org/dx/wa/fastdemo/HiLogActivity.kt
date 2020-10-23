package org.dx.wa.fastdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.devio.hi.library.executor.HiExecutor
import org.devio.hi.library.log.*
import kotlinx.android.synthetic.main.activity_main.*

class HiLogActivity : AppCompatActivity() {
    private lateinit var viewPrinter:HiViewPrinter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPrinter = HiViewPrinter(this)
        HiLogManager.getInstance().addPrinter(viewPrinter)

        exeTask()
    }
    class MyCallable : HiExecutor.Callable<String>(){
        override fun onPrepare() {
            HiLog.d("onPrepare")
        }
        override fun onBackground(): String? {
            HiLog.d("onBackground")
            return "exe over"
        }

        override fun onCompleted(t: String?) {
            HiLog.d("onCompleted:$t")
        }

    }
    private fun exeTask() {
        val myCallable = MyCallable()
        HiExecutor.execute(runnable = myCallable)
    }

    fun resume(view: View){
        HiExecutor.resume()
    }
    fun pause(view: View){
        HiExecutor.pause()
    }

    fun printLog(view: View){
        HiLog.d("基础打印,使用全局tag")
        HiLog.dt("duanjl","指定tag打印")
        myPrinter("自定义打印器,可指定tag,线程打印层级,json转换器,打印执行者")
    }
    fun myPrinter(contents:String){
        HiLog.log(object : HiLogConfig() {
            override fun getGlobalTag(): String {
                return "tag2"
            }

            override fun printers(): Array<HiLogPrinter> {
                return arrayOf(HiConsolePrinter())
            }
        }, HiLogType.D,"tag3",contents)
    }
    fun showLogWindow(view: View){
        viewPrinter.viewProvider.showFloatingView()
    }
    fun closeLogWindow(view: View){
        viewPrinter.viewProvider.closeFloatingView()
    }

}