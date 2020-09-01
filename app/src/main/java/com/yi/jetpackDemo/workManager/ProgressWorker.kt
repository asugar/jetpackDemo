package com.yi.jetpackDemo.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class ProgressWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 2000L
    }

    override suspend fun doWork(): Result {
        val firstUpdate = workDataOf(Progress to 0)
        val lastUpdate = workDataOf(Progress to 100)
        setProgress(firstUpdate)
        delay(delayDuration)
        setProgress(workDataOf(Progress to 20))
        delay(delayDuration)
        setProgress(workDataOf(Progress to 50))
        delay(delayDuration)
        setProgress(lastUpdate)
        delay(delayDuration)
        return Result.success()
    }
}