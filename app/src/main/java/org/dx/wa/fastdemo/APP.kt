package org.dx.wa.fastdemo

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import org.devio.hi.library.log.HiConsolePrinter
import org.devio.hi.library.log.HiFilePrinter
import org.devio.hi.library.log.HiLogConfig
import org.devio.hi.library.log.HiLogConfig.JsonParser
import org.devio.hi.library.log.HiLogManager

/**
 * Description
 * @author  monkey.liu
 * 2020/10/19, Create file
 */
class APP : Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initLogPrinter()
    }

    private fun initLogPrinter() {

        HiLogManager.init(
            object : HiLogConfig() {
                override fun injectJsonParser(): JsonParser {
                    return JsonParser { src -> Gson().toJson(src) }
                }

                override fun getGlobalTag(): String {
                    return "smartRobot_tag"
                }

                override fun enable(): Boolean {
                    return true
                }

                override fun includeThread(): Boolean {
                    return true
                }

                override fun stackTraceDepth(): Int {
                    return 2
                }

            }
        ,HiConsolePrinter(),
            HiFilePrinter.getInstance(applicationContext.cacheDir.absolutePath,0)
        )
    }

}