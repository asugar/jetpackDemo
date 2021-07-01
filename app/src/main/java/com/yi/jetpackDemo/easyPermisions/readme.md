

# RxPermissions:
https://github.com/tbruyelle/RxPermissions


// 存储说明
private fun testStorage() {
        // /data/user/0/com.baidu.searchbox
        log(reclaimTag, "${LiveBaseRuntime.getApplication().dataDir}")
        // /data/user/0/com.baidu.searchbox
        log(reclaimTag, "fileDir= ${LiveBaseRuntime.getApplication().filesDir}")
        // /data/user/0/com.baidu.searchbox/cache
        log(reclaimTag, "cacheDir= ${LiveBaseRuntime.getApplication().cacheDir}")
        // /storage/emulated/0/Android/data/com.baidu.searchbox/cache
        log(reclaimTag, "externalCacheDirs= ${LiveBaseRuntime.getApplication().externalCacheDir}")
        // /storage/emulated/0/Android/data/com.baidu.searchbox/files/mounted
        LiveBaseRuntime.getApplication().getExternalFilesDir(null)
        log(reclaimTag, "externalFilesDirs= ${LiveBaseRuntime.getApplication().getExternalFilesDir(Environment.MEDIA_MOUNTED)}")
        // /storage/emulated/0
        log(reclaimTag, "storageDirectory= ${Environment.getExternalStorageDirectory()}")
        val storagePublicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()
        // /storage/emulated/0/DCIM
        log(reclaimTag, "storagePublicDir= $storagePublicDir")
        // /system
        log(reclaimTag, "rootDir= ${Environment.getRootDirectory()}")
        // /data
        log(reclaimTag, "dataDir= ${Environment.getDataDirectory()}")

        if (mRootForder?.isDirectory == true) {
            mRootForder?.listFiles()?.forEach {
                log(reclaimTag, "test storage ${it.name}")
            }
        } else {
            log(reclaimTag, "test storage ${mRootForder?.name}")
        }
    }
