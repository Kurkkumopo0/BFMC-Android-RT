package com.kurkkumopo.bfmc_android_rt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import com.kurkkumopo.bfmc_android_rt.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCameraBinding
    private lateinit var controller: LifecycleCameraController
    private lateinit var modelPath: String
    private var flashOn: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupModelTitle()
        setupBackButton()
        setupFlashlightButton()
        setupCamera()
    }

    private fun setupModelTitle() {
        modelPath = intent.getStringExtra(MainActivity.MODEL) ?: ""
        viewBinding.modelTitle.text = modelPath.substringBeforeLast(".")
    }

    private fun setupBackButton() {
        viewBinding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun setupFlashlightButton() {
        viewBinding.flashButton.setOnClickListener {
            flashOn = !flashOn
            controller.enableTorch(flashOn)
        }

    }

    private fun setupCamera() {
        val preview: PreviewView = viewBinding.cameraPreview
        controller = LifecycleCameraController(baseContext)
        controller.bindToLifecycle(this)
        controller.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        preview.controller = controller
    }

    companion object {
        private const val TAG = "CameraActivity"
    }

}