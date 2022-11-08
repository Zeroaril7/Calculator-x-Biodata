package com.fakhril.praktikumactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            var inputLength = edtLength.text.toString().trim()
            var inputWidth = edtWidth.text.toString().trim()
            var inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true
                edtLength.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true
                edtWidth.setError("Field tak boleh kosong")
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true
                edtHeight.setError("Field tak boleh kosong")
            }

            var length = inputLength
            var width = inputWidth
            var height = inputHeight

            if (length == null) {
                isInvalidDouble = true
                edtLength.setError("Field harus berupa nomer yang valid")
            }

            if (width == null) {
                isInvalidDouble = true
                edtLength.setError("Field harus berupa nomer yang valid")
            }

            if (height == null) {
                isInvalidDouble = true
                edtHeight.setError("field harus berupa nomer yang valid")
            }

            if (!isEmptyFields && !isInvalidDouble) {

                var volume = length.toInt() * width.toInt() * height.toInt();
                tvResult.setText(volume.toString())
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException){
            null
        }
    }
}

