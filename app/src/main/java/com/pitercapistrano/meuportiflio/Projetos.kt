package com.pitercapistrano.meuportiflio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pitercapistrano.meuportiflio.databinding.ActivityContatoBinding
import com.pitercapistrano.meuportiflio.databinding.ActivityProjetosBinding

class Projetos : AppCompatActivity() {

    private lateinit var binding: ActivityProjetosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProjetosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbarProjetos.setNavigationOnClickListener {
            finish()
        }

        binding.btDtFpcalculator.setOnClickListener {
            val intent = Intent(this, DetalhesFPCalculator::class.java)
            startActivity(intent)
        }

        binding.btDtLojaVirtual.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/Loja-Virtual-Client"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtBarberShop.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/BarberShopApp"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtDelivery.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/AppDelivery"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtFilmes.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/AppFilmes"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtBanco.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/Banco-PC"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtMusica.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/PlayMusic"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtWhatsapp.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/WhatsApp-List"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtCredit.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/credit-application-system"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

        binding.btDtImc.setOnClickListener {
            val diretorio = "https://github.com/PiterCapistrano/Calculadora-de-IMC"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(diretorio))
            startActivity(intent)
        }

    }
}