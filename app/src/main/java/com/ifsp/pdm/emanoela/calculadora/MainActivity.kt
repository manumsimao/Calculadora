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
    private var fullExpression = ""
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
        var buttonText: String = (view as Button).text.toString()
        if (operation == "") {
            if (buttonText == ".") {
                if (!number1String.contains(".")) {
                    if (number1String == "") {
                        number1String += "0"
                    }
                    number1String += buttonText
                }
            } else {
                number1String += buttonText
            }
            fullExpression = number1String
            activityMainBinding.numberTV.text = number1String
            activityMainBinding.operationTV.text = fullExpression
        } else {
            if (buttonText == ".") {
                if (!number2String.contains(".")) {
                    if (number2String == "") {
                        number2String += "0"
                    }
                    number2String += buttonText
                    fullExpression += buttonText
                }
            } else {
                number2String += buttonText
                fullExpression += buttonText
            }
            activityMainBinding.numberTV.text = number2String
            activityMainBinding.operationTV.text = fullExpression
        }
    }

    fun symbolClick(view: View) {
        var symbol: String = (view as Button).text.toString()
        if (operation == "") {
            fullExpression += " $symbol "
            activityMainBinding.operationTV.text = fullExpression
            operation = symbol
            if (operation == "√") {
                calculate(view)
            }
        } else {
            if (!fullExpression.contains("=")) {
                fullExpression += "="
                activityMainBinding.operationTV.text = fullExpression
            }
            calculate(view)
        }

        if (resultado != 0f && symbol != "√") {
            nextOperation(symbol)
        }
    }

    fun calculate(view: View) {
        if (number1String != "" && operation == "√") {
            var number1 = number1String.toFloat()
            resultado = sqrt(number1)
            activityMainBinding.numberTV.text = resultado.toString()
            number2String = ""
        }
        if (number1String != "" && number2String != "") {

            var number1 = number1String.toFloat()
            var number2 = number2String.toFloat()
            when (operation) {
                "+" -> {
                    resultado = number1 + number2
                }
                "-" -> {
                    resultado = number1 - number2
                }
                "*" -> {
                    resultado = number1 * number2
                }
                "/" -> {
                    resultado = number1 / number2
                }
                "√" -> {
                    resultado = sqrt(number1)
                }
                "xⁿ" -> {
                    resultado = number1.pow(number2)
                }
                "%" -> {
                    resultado = (number1 / 100) * number2
                }
            }
        }
        activityMainBinding.numberTV.text = resultado.toString()

        nextOperation(null)
    }

    fun reset(view: View) {
        if (view.id == R.id.clearB) {
            number1String = ""
            number2String = ""
            fullExpression = ""
            operation = ""
            activityMainBinding.numberTV.text = "0"
            activityMainBinding.operationTV.text = "0"
            resultado = 0f
        }
    }

    private fun nextOperation(op: String?) {
        number1String = resultado.toString()
        operation = ""
        fullExpression = number1String
        number2String = ""
        if (op != null) {
            operation = op
            fullExpression += " $op "
        }
        activityMainBinding.operationTV.text = fullExpression
    }
}