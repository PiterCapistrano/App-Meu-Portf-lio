package com.pitercapistrano.meuportiflio

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pitercapistrano.meuportiflio.databinding.ActivityContatoBinding
import android.Manifest
import android.widget.Toast

class Contato : AppCompatActivity() {

    private lateinit var binding: ActivityContatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbarContato = binding.toolbarContato
        toolbarContato.setNavigationOnClickListener {
            finish()
        }
        
        binding.btWhatsapp.setOnClickListener {
            abrirWhatsApp()
        }

        binding.telefone.setOnClickListener {
            ligar()
        }

        binding.email.setOnClickListener {
            irParaEmail()
        }

        binding.linkedin.setOnClickListener {
            irParaLinkedIn()
        }

        binding.github.setOnClickListener {
            irParaGithub()
        }
    }

    private fun abrirWhatsApp(){
        val numeroTelefone = "5511982731114"
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numeroTelefone")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

        try {

            if (numeroTelefone.equals(numeroTelefone)) {
                startActivity(intent)
            } else {
                val playStoreIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                )
                startActivity(playStoreIntent)
            }

        }catch (e: Exception){
            Toast.makeText(this, "Não foi possível abrir o aplicativo WhatsApp!", Toast.LENGTH_LONG).show()
        }
    }

    private fun ligar(){
        val numeroTelefone = "tel:5511982731114"
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse(numeroTelefone)

        try {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }

        }catch (e: Exception){
                Toast.makeText(this, "Não foi possível efetuar a ligação!", Toast.LENGTH_LONG).show()
        }
    }

    private fun irParaEmail() {
        val email = "pitercapistrano@gmail.com"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email") // Somente apps de email devem responder a essa intent
            putExtra(Intent.EXTRA_SUBJECT, "Assunto do Email")
            putExtra(Intent.EXTRA_TEXT, "Mensagem do corpo do email")
        }

        try {
            // Verifica se existe algum app que pode tratar essa intent antes de iniciar
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Caso não haja app de e-mail, redireciona para a Play Store do Gmail
                val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm"))
                startActivity(playStoreIntent)
            }
        } catch (e: Exception) {
            // Caso ocorra um erro, exibe uma mensagem ao usuário
            Toast.makeText(this, "Não foi possível abrir o aplicativo de e-mail!", Toast.LENGTH_LONG).show()
        }
    }

    private fun irParaLinkedIn(){
        val linkedInProfile = "https://www.linkedin.com/in/piter-capistrano-on"
        val packageManager = packageManager
        val linkedInAppUri = Uri.parse("linkedin://piter-capistrano-on") // URI para o app do LinkedIn
        val linkedInWebUri = Uri.parse(linkedInProfile) // URL para abrir no navegador
        val linkedInAppIntent = Intent(Intent.ACTION_VIEW, linkedInAppUri)

        // Verifica se o aplicativo do LinkedIn está instalado
        val appExists = linkedInAppIntent.resolveActivity(packageManager) != null

        try {

            if (appExists) {
                // Abre o perfil no aplicativo do LinkedIn
                startActivity(linkedInAppIntent)
            } else {
                // Tenta abrir no navegador
                val webIntent = Intent(Intent.ACTION_VIEW, linkedInWebUri)

                // Verifica se há navegador disponível
                if (webIntent.resolveActivity(packageManager) != null) {
                    startActivity(webIntent)
                } else {
                    // Se não houver navegador, abre a Play Store para baixar o app do LinkedIn
                    val playStoreIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.linkedin.android")
                    )
                    startActivity(playStoreIntent)
                }
            }
        }catch (e: Exception){
            Toast.makeText(this, "Não foi possível abrir o aplicativo LinkedIn!", Toast.LENGTH_LONG).show()
        }
    }

    private fun irParaGithub(){
        val perfilGithub = "https://github.com/PiterCapistrano"
        val packageManager = packageManager
        val githubAppUri = Uri.parse("github://user?username=PiterCapistrano") // URI para o app do GitHub
        val githubWebUri = Uri.parse(perfilGithub) // URL para abrir no navegador
        val githubAppIntent = Intent(Intent.ACTION_VIEW, githubAppUri)

        // Verifica se o aplicativo do GitHub está instalado
        val appExists = githubAppIntent.resolveActivity(packageManager) != null

        try {

            if (appExists) {
                // Abre o perfil no aplicativo do GitHub
                startActivity(githubAppIntent)
            } else {
                // Tenta abrir no navegador
                val webIntent = Intent(Intent.ACTION_VIEW, githubWebUri)

                // Verifica se há navegador disponível
                if (webIntent.resolveActivity(packageManager) != null) {
                    startActivity(webIntent)
                } else {
                    // Se não houver navegador, abre a Play Store para baixar o app do GitHub
                    val playStoreIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.github.android")
                    )
                    startActivity(playStoreIntent)
                }
            }
        }catch (e: Exception){
            Toast.makeText(this, "Não foi possível abrir o aplicativo Github!", Toast.LENGTH_LONG).show()
        }
    }
}