package com.example.fragmentexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentexample1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Controla si el FragmentOne esta cargado o no
    private var isFragmentOneLoaded = true
    private var isFragmentTwoLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Se muestra en pantalla el primer fragment
        showFragmentOne()

        // Listener del boton
        binding.btnChange.setOnClickListener {
            if (isFragmentOneLoaded) {
                showFragmentTwo()
            } else if (isFragmentTwoLoaded) {
                showFragmentThree()
            } else {
                showFragmentOne()
            }
        }
    }

    private fun showFragmentOne() {
        // Se establece la transaccion de fragments, necesarios para añadir, quitar o reemplazar fragments
        val transaction = supportFragmentManager.beginTransaction()
        // Se instancia el fragment a mostrar
        val fragment = FragmentOne()

        // Indicamos el elemento del layout donde haremos el cambio
        transaction.replace(R.id.fragment_holder, fragment)

        // Se establece valor null para indicar que no se esta interesado en volver a ese fragment mas tarde,
        // en caso contrario, se inidicara el nombre del fragment, por ejemplo fragment.TAG, aprovechando la
        // variable creada en la clase
        transaction.addToBackStack(null)

        // se muestra el fragment
        transaction.commit()
        isFragmentOneLoaded = true
    }

    // Igual que showFragmentOne() pero para el segundo
    private fun showFragmentTwo() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentTwo()

        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded = false
        isFragmentTwoLoaded = true
    }

    private fun showFragmentThree() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentThree()

        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentTwoLoaded = false
    }
}