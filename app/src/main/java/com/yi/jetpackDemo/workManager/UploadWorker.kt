package com.yi.jetpackDemo.workManager

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Logger
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private var count: Int = 0

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {

        val imageUrlInput = inputData.getString("imageUrl")
        count++
        Logger.LogcatLogger.get().info("xiaoyi", "UploadWorker dowork $imageUrlInput")
        // Indicate whether the task finished successfully with the Result
        if (count == 400) {
            val outputData = workDataOf("imageUrl" to "http://outputurl")
            return Result.success(outputData)
        } else {
            setProgressAsync(workDataOf("Progress" to count))
            return Result.retry()
        }
    }
}
