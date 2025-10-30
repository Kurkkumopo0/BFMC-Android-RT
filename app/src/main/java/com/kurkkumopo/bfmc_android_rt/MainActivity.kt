package com.kurkkumopo.bfmc_android_rt

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var selectedModel: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDropDown()
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

    private fun setModel(newModel: String) {
        selectedModel = newModel
    }

}