package edu.washington.bycao96.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import java.text.NumberFormat
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define the UI component based on the ID
        val cur: Regex = Regex("[$,.]")
        val percentage: Double = 0.15
        val button: Button = findViewById(R.id.tipBtn)
        val editText: EditText = findViewById(R.id.txtInput)

        // Disable the button on Create
        button.isClickable = false
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                button.isClickable = !editText.text.isNullOrBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!editText.text.isNullOrBlank())
                    editText.removeTextChangedListener(this)
                var amount = s.toString().replace(cur, "").toDouble()
                var formatAmount = NumberFormat.getCurrencyInstance().format((amount / 100))
                editText.setText(formatAmount)
                editText.setSelection(formatAmount.length)
                editText.addTextChangedListener(this)

            }
        })

        button.setOnClickListener() {
            val calAmount = (editText.text.toString().substring(1).toDouble() * 100).toInt() * percentage / 100
            val tip = "%.2f".format(calAmount).toDouble()
            Toast.makeText(this, "Tip: $$tip", Toast.LENGTH_LONG).show()
        }


    }

}
