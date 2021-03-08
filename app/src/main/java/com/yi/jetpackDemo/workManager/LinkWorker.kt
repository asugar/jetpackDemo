package com.yi.jetpackDemo.workManager

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Logger
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.StringBuilder

class LinkWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    companion object {
        const val link = "link"
    }

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        val sb = StringBuilder()
        this.tags.forEach {
            sb.append(it)
            sb.append("-")
        }
        Logger.LogcatLogger.get().info("xiaoyi", "LinkWorker dowork $sb")
        return Result.success(workDataOf(link to 1))
    }
}