package com.kurkkumopo.bfmc_android_rt

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kurkkumopo.bfmc_android_rt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var selectedModel: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupDropDown()
        setupCameraButton()
    }

    private fun setupDropDown() {
        val mockModels = resources.getStringArray(R.array.mock_models) //TODO: get models straight from assets
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, mockModels)
        val dropdownTextView = findViewById<AutoCompleteTextView>(R.id.dropdown_text_view)
        dropdownTextView.setAdapter(adapter)
        dropdownTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedModel = parent.getItemAtPosition(position).toString()
            setModel(selectedModel)
        }
    }

    private fun setupCameraButton() {
        viewBinding.cardButton.setOnClickListener {
            if (hasPermissions(baseContext))
                startCamera()
            else
                permissionLauncher.launch(REQUIRED_PERMISSIONS)
        }
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        permissions ->
            val permissionsGranted = REQUIRED_PERMISSIONS.all { permissions[it] == true }
            if (permissionsGranted)
                startCamera()
            else
                Toast.makeText(this, "Required permission denied.", Toast.LENGTH_LONG).show()
    }

    private fun startCamera() {
        if (selectedModel=="") {
            Toast.makeText(this, "Please select model", Toast.LENGTH_LONG).show()
            return
        }
        // TODO: actually start camera
    }

    private fun setModel(newModel: String) {
        selectedModel = newModel
    }

    companion object {
        private val REQUIRED_PERMISSIONS = mutableListOf (android.Manifest.permission.CAMERA).toTypedArray()
        fun hasPermissions(context: Context) = REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    }

}