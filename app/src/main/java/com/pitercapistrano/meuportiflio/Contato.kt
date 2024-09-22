package com.pitercapistrano.meuportiflio

// Importações necessárias para funcionalidades de Intents, permissões e interfaces
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge // Habilita suporte a telas de borda a borda
import androidx.appcompat.app.AppCompatActivity // Base para atividades que utilizam a action bar
import androidx.core.app.ActivityCompat // Permite solicitar permissões em tempo de execução
import androidx.core.content.ContextCompat // Facilita o acesso a recursos como permissões e temas
import androidx.core.view.ViewCompat // Facilita o trabalho com visões (Views)
import androidx.core.view.WindowInsetsCompat // Para manipular margens e preenchimentos de janelas
import com.pitercapistrano.meuportiflio.databinding.ActivityContatoBinding // Vincula o layout XML com a Activity
import android.Manifest // Usado para acessar as permissões de telefone
import android.widget.Toast // Exibe mensagens temporárias ao usuário

// Classe principal da Activity de Contato
class Contato : AppCompatActivity() {

    // Variável para vincular o layout XML à Activity
    private lateinit var binding: ActivityContatoBinding

    // Função chamada ao criar a Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita a interface para telas de borda a borda
        enableEdgeToEdge()

        // Infla (carrega) o layout e associa a variável 'binding' ao layout XML
        binding = ActivityContatoBinding.inflate(layoutInflater)

        // Define o layout associado a esta Activity
        setContentView(binding.root)

        // Ajusta os preenchimentos (padding) da visualização principal para se adaptar às barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura a ação de clique no ícone de navegação da toolbar, que encerra a Activity
        val toolbarContato = binding.toolbarContato
        toolbarContato.setNavigationOnClickListener {
            finish()
        }

        // Define ações para os botões de contato
        binding.btWhatsapp.setOnClickListener {
            abrirWhatsApp() // Chama a função para abrir o WhatsApp
        }

        binding.telefone.setOnClickListener {
            ligar() // Chama a função para realizar uma ligação telefônica
        }

        binding.email.setOnClickListener {
            irParaEmail() // Chama a função para enviar um e-mail
        }

        binding.linkedin.setOnClickListener {
            irParaLinkedIn() // Chama a função para abrir o perfil no LinkedIn
        }

        binding.github.setOnClickListener {
            irParaGithub() // Chama a função para abrir o perfil no GitHub
        }
    }

    // Função para abrir o WhatsApp com o número de telefone fornecido
    private fun abrirWhatsApp(){
        val numeroTelefone = "5511982731114"
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numeroTelefone")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

        try {
            // Verifica se o número de telefone foi passado corretamente
            if (numeroTelefone.equals(numeroTelefone)) {
                startActivity(intent)
            } else {
                // Se o WhatsApp não estiver instalado, abre a Play Store para instalação
                val playStoreIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                )
                startActivity(playStoreIntent)
            }
        } catch (e: Exception) {
            // Exibe uma mensagem de erro caso não consiga abrir o WhatsApp
            Toast.makeText(this, "Não foi possível abrir o aplicativo WhatsApp!", Toast.LENGTH_LONG).show()
        }
    }

    // Função para realizar uma ligação telefônica
    private fun ligar(){
        val numeroTelefone = "tel:5511982731114"
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse(numeroTelefone)

        try {
            // Verifica se a permissão de chamada foi concedida
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startActivity(intent) // Se a permissão foi concedida, realiza a chamada
            } else {
                // Solicita a permissão de chamada caso não tenha sido concedida
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
        } catch (e: Exception) {
            // Exibe uma mensagem de erro caso não consiga realizar a ligação
            Toast.makeText(this, "Não foi possível efetuar a ligação!", Toast.LENGTH_LONG).show()
        }
    }

    // Função para enviar um e-mail
    private fun irParaEmail() {
        val email = "pitercapistrano@gmail.com"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email") // Define o formato de e-mail
            putExtra(Intent.EXTRA_SUBJECT, "Assunto do Email") // Define o assunto do e-mail
            putExtra(Intent.EXTRA_TEXT, "Mensagem do corpo do email") // Define o corpo da mensagem
        }

        try {
            // Verifica se há algum app capaz de lidar com o e-mail
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Se não houver app de e-mail, abre a Play Store para baixar o Gmail
                val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm"))
                startActivity(playStoreIntent)
            }
        } catch (e: Exception) {
            // Exibe uma mensagem de erro caso não consiga abrir o app de e-mail
            Toast.makeText(this, "Não foi possível abrir o aplicativo de e-mail!", Toast.LENGTH_LONG).show()
        }
    }

    // Função para abrir o perfil no LinkedIn
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
                // Se o app estiver instalado, abre o perfil no LinkedIn
                startActivity(linkedInAppIntent)
            } else {
                // Caso contrário, abre o perfil no navegador
                val webIntent = Intent(Intent.ACTION_VIEW, linkedInWebUri)
                if (webIntent.resolveActivity(packageManager) != null) {
                    startActivity(webIntent)
                } else {
                    // Se não houver navegador, abre a Play Store para baixar o LinkedIn
                    val playStoreIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.linkedin.android")
                    )
                    startActivity(playStoreIntent)
                }
            }
        } catch (e: Exception) {
            // Exibe uma mensagem de erro caso não consiga abrir o LinkedIn
            Toast.makeText(this, "Não foi possível abrir o aplicativo LinkedIn!", Toast.LENGTH_LONG).show()
        }
    }

    // Função para abrir o perfil no GitHub
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
                // Se o app estiver instalado, abre o perfil no GitHub
                startActivity(githubAppIntent)
            } else {
                // Caso contrário, abre o perfil no navegador
                val webIntent = Intent(Intent.ACTION_VIEW, githubWebUri)
                if (webIntent.resolveActivity(packageManager) != null) {
                    startActivity(webIntent)
                } else {
                    // Se não houver navegador, abre a Play Store para baixar o GitHub
                    val playStoreIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.github.android")
                    )
                    startActivity(playStoreIntent)
                }
            }
        } catch (e: Exception) {
            // Exibe uma mensagem de erro caso não consiga abrir o GitHub
            Toast.makeText(this, "Não foi possível abrir o aplicativo Github!", Toast.LENGTH_LONG).show()
        }
    }
}
