package com.yi.jetpackDemo.workManager

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.*
import com.yi.jetpackDemo.workManager.LinkWorker.Companion.link
import java.lang.ref.SoftReference
import java.util.concurrent.TimeUnit

/**
 * 处理WorkManagerFragment的Click事件
 */
class ClickHandler(val fragment: SoftReference<WorkManagerFragment>) {

    fun onOnceClick(view: View) {
        view.context.let {
            // 添加工作约束
            val constraints = Constraints.Builder()
//                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build()

            val imageData = workDataOf("imageUrl" to "http://www.baidu.com")
            val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(constraints)
                .setInitialDelay(5, TimeUnit.SECONDS)//设置初始延时
                .setBackoffCriteria(// 设置重试和退避政策
                    BackoffPolicy.LINEAR,
                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .setInputData(imageData)// 设置输入参数
                .addTag("xiaoyi")// 添加tag
                .build()
            WorkManager.getInstance(it).enqueue(uploadWorkRequest)
        }
    }

    private fun cancleWork(context: Context, tag: String) {
        WorkManager.getInstance(context).getWorkInfosByTag(tag).cancel(true)

//        WorkManager.getInstance(context).getWorkInfoByIdLiveData(uploadWorkRequest.id)
//            .observe(viewLifecycleOwner, Observer { workInfo ->
//                workInfo?.let {
//                    val pr = it.progress.getInt("Progress", -1)
//                    Log.d("xiaoyi", "observe dowork $pr")
//                }
//            })
//            val pr = WorkManager.getInstance(it)
//                .getWorkInfosByTag("xiaoyi")
//                .get()
//                .first()
//                .progress
//                .getInt("Progress", -1)
    }

    fun onProgressWorkerClick(view: View) {
        view.context.let {
            val work = OneTimeWorkRequestBuilder<ProgressWorker>()
                .addTag("progressWorker")
                .build()

            WorkManager.getInstance(it).enqueue(work)

            fragment.get()?.let { f ->
                WorkManager.getInstance(it).getWorkInfoByIdLiveData(work.id)
                    .observe(f.viewLifecycleOwner, Observer { workInfo: WorkInfo? ->
                        if (workInfo != null) {
                            val progress = workInfo.progress
                            val value = progress.getInt(ProgressWorker.Progress, -1)
                            Log.d("xiaoyi", "onMoreClick $value")
                        }
                    })
            }
        }
    }

    fun onLinkWorkerClick(view: View) {
        view.context.let {
            val first = OneTimeWorkRequestBuilder<LinkWorker>().addTag("link-1").build()
            val second = OneTimeWorkRequestBuilder<LinkWorker>().addTag("link-2").build()
            val third = OneTimeWorkRequestBuilder<LinkWorker>().addTag("link-3").build()
            val compress = OneTimeWorkRequestBuilder<CompressWorker>().addTag("link-3")
                .setInputMerger(ArrayCreatingInputMerger::class)
                .build()
            WorkManager.getInstance(it)
                // Candidates to run in parallel
                .beginWith(listOf(first, second, third))
                // Dependent work (only runs after all previous work in chain)
                .then(compress)
//                .then(upload)
                // Don't forget to enqueue()
                .enqueue()

            WorkManager.getInstance(it).getWorkInfoByIdLiveData(compress.id)
                .observe(fragment.get()!!.viewLifecycleOwner, Observer { workInfo: WorkInfo? ->
                    workInfo?.let {
                        if (it.state == WorkInfo.State.SUCCEEDED) {
                            val value = it.outputData.getInt(link, -1)
                            Log.d("xiaoyi", "onLinkWorkerClick $value")
                        }
                    }
                })

        }
    }
}