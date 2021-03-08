package com.yi.jetpackDemo.easyPermisions

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.FragmentEasyPermisionsBinding
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


const val EASY_PERMISIONS_TAG = "EasyPermisionsFragment"

class EasyPermisionsFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private var binding: FragmentEasyPermisionsBinding? = null
    private val REQUEST_CODE_P = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEasyPermisionsBinding.inflate(layoutInflater)
        binding!!.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private val RC_CAMERA_AND_LOCATION: Array<String> =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private fun initView() {
        val hasPermision = EasyPermissions.hasPermissions(
            requireContext(),
            RC_CAMERA_AND_LOCATION[0],
            RC_CAMERA_AND_LOCATION[1]
        )
        Logger.t(EASY_PERMISIONS_TAG).d("initView hasPermision=$hasPermision")
        if (!hasPermision) {
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(
                    this,
                    REQUEST_CODE_P,
                    RC_CAMERA_AND_LOCATION[0],
                    RC_CAMERA_AND_LOCATION[1]
                ).setRationale("app需要获取相机权限")
//                    .setPositiveButtonText("确定")
//                    .setNegativeButtonText(R.string.rationale_ask_cancel)
//                    .setTheme(R.style.my_fancy_style)
                    .build()
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Logger.t(EASY_PERMISIONS_TAG).d("onRequestPermissionsResult $permissions $grantResults")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        if (EasyPermissions.somePermissionPermanentlyDenied(this, permissions.asList())) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Logger.t(EASY_PERMISIONS_TAG).d("onPermissionsDenied $requestCode $perms")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Logger.t(EASY_PERMISIONS_TAG).d("onPermissionsGranted $requestCode $perms")

    }
}