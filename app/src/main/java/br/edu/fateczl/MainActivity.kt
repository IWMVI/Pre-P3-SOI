package br.edu.fateczl

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


// Pre P3 - Fazer uma aplicacao Android que Receba um numero n em um EditText, Calcule e mostre o resultado da serie 1 + 1/2 + 1/3 + ... + 1/N em um TextView.
// Validar para n > 0 e n < 20

class MainActivity : AppCompatActivity() {
    private lateinit var etNumero: EditText
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumero = findViewById(R.id.etNumero)
        tvResultado = findViewById(R.id.tvResultado)

        val btnCalc: Button = findViewById(R.id.btnCalc)
        btnCalc.setOnClickListener {
            calc()
            etNumero.setText("")
        }

        etNumero.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN &&
                        event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                etNumero.clearFocus()
                calc()
                etNumero.setText("")
                true
            } else {
                false
            }
        }
    }

    private fun calc() {
        val input = etNumero.text.toString().trim()

        if (input.isEmpty()) {
            etNumero.error = "Informe um número"
            return
        }

        val n = input.toInt()

        if (n <= 0 || n >= 20) {
            Toast.makeText(this@MainActivity, "O número deve estar entre 1 e 19", Toast.LENGTH_LONG)
                .show()
            return
        }

        val sum = calcular(n)

        tvResultado.text = String.format("Resultado: %.4f", sum)
    }

    private fun calcular(n: Int): Float {
        return if (n == 1) {
            1.0f
        } else {
            calcular(n - 1) + 1.0f / n
        }
    }
}
