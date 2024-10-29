package com.example.bai2_2910_android

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonShow.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == -1){
                textViewResult.text = "Vui lòng chọn một lựa chọn !"
                return@setOnClickListener
            }
            val number = editTextNumber.text.toString().toIntOrNull()
            if (number == null) {
                textViewResult.text = "Vui lòng nhập một số hợp lệ !"
                return@setOnClickListener
            }
            if (number < 1) {
                textViewResult.text = "Vui lòng nhập một số lớn hơn 0 !"
                return@setOnClickListener
            }
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val results = when (selectedRadioButtonId) {
                R.id.radioEven -> (1..number).filter { it % 2 == 0 }
                R.id.radioOdd -> (1..number).filter { it % 2 != 0 }
                R.id.radioSquare -> (1..number).filter { Math.sqrt(it.toDouble()).toInt() * Math.sqrt(it.toDouble()).toInt() == it }
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
            textViewResult.text = "Results: ${results.size} items"
        }
    }
}