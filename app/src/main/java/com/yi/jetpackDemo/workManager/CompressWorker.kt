package com.yi.jetpackDemo.workManager

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Logger
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class CompressWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {

        val links = inputData.getIntArray("link")

        var re = 0
        links?.forEach {
            re += it
        }

        Logger.LogcatLogger.get().info("xiaoyi", "CompressWorker dowork ${links?.size}")
        return Result.success(workDataOf(LinkWorker.link to re))
    }
}