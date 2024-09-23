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
}
