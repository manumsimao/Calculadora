package com.ifsp.pdm.emanoela.calculadora

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifsp.pdm.emanoela.calculadora.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private var number1String = ""
    private var number2String = ""
    private var operation = ""
    private var resultado = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == "Modo básico") {
            activityMainBinding.advancedLL.visibility = View.GONE
        } else if (item.title == "Modo Avançado") {
            activityMainBinding.advancedLL.visibility = View.VISIBLE
        }
        return true
    }

    fun numberClick(view: View) {
        var b: Button = view as Button
        if (operation == "") {
            number1String += b.text
            activityMainBinding.numberTV.text = number1String
        } else {
            number2String += b.text
            activityMainBinding.numberTV.text = number2String
        }
    }

    fun symbolClick(view: View) {
        var op: String = (view as Button).text as String
        if (operation == "") {
            operation = op
            if(operation == "√") {
                calculate(view)
            }
        } else {
            calculate(view)
        }
    }

    fun calculate(view: View) {
        if (number1String != "" && operation == "√") {
            var number1 = number1String.toFloat()
            resultado = sqrt(number1)
            activityMainBinding.numberTV.text = resultado.toString()
            reset(view)
        }
        if (number1String != "" && number2String != "") {
            var number1 = number1String.toFloat()
            var number2 = number2String.toFloat()
            if (operation == "+") {
                resultado = number1 + number2
            } else if (operation == "-") {
                resultado = number1 - number2
            } else if (operation == "*") {
                resultado = number1 * number2
            } else if (operation == "/") {
                resultado = number1 / number2
            } else if (operation == "√") {
                resultado = sqrt(number1)
            } else if (operation == "xⁿ") {
                resultado = number1.pow(number2)
            } else if (operation == "%") {
                resultado = (number1 / 100) * number2
            }
            activityMainBinding.numberTV.text = resultado.toString()
            reset(view)
        }
    }

    fun reset(view: View) {
        number1String = ""
        number2String = ""
        operation = ""
        if (view.id == R.id.clearB)
            activityMainBinding.numberTV.text = "0"
    }

}