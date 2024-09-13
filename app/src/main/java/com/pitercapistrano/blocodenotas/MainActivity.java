package com.pitercapistrano.blocodenotas;

import android.content.Context;  // Importa a classe Context para acessar recursos do aplicativo
import android.os.Bundle;  // Importa a classe Bundle para salvar e restaurar o estado da atividade
import android.view.View;  // Importa a classe View para manipular interações com a interface do usuário
import android.widget.EditText;  // Importa a classe EditText para entrada de texto
import android.widget.Toast;  // Importa a classe Toast para exibir mensagens breves ao usuário

import androidx.activity.EdgeToEdge;  // Importa a classe EdgeToEdge para otimizar o uso da tela completa
import androidx.appcompat.app.AppCompatActivity;  // Importa a classe AppCompatActivity, base para atividades
import androidx.core.graphics.Insets;  // Importa a classe Insets para manipular margens de barras de status e navegação
import androidx.core.view.ViewCompat;  // Proporciona compatibilidade entre versões antigas e atuais do Android
import androidx.core.view.WindowInsetsCompat;  // Manipula as margens das barras do sistema (status e navegação)

import com.google.android.material.floatingactionbutton.FloatingActionButton;  // Importa a classe FloatingActionButton para um botão flutuante

public class MainActivity extends AppCompatActivity {

    // Declaração de variáveis: `preferencias` para gerenciar as preferências do usuário e `editAnotacao` para o campo de anotação
    private AnotacaoPreferencias preferencias;
    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita a utilização total da tela até as bordas, incluindo áreas de status e navegação
        EdgeToEdge.enable(this);

        // Define o layout da atividade com o arquivo XML `activity_main`
        setContentView(R.layout.activity_main);

        // Configura as margens da interface para considerar as barras de status e navegação
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa o botão flutuante para salvar a anotação
        FloatingActionButton fbSalvar = findViewById(R.id.fb_salvar);

        // Inicializa o campo de entrada de texto para as anotações
        editAnotacao = findViewById(R.id.editAnotação);

        // Inicializa a classe `AnotacaoPreferencias` que gerencia as preferências de anotação do usuário
        preferencias = new AnotacaoPreferencias(getApplicationContext());

        // Define a ação do botão "Salvar" quando for clicado
        fbSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Recupera o texto inserido no campo de anotação
                String textoRecuperado = editAnotacao.getText().toString();

                // Verifica se o campo está vazio
                if (textoRecuperado.equals("")) {
                    // Exibe uma mensagem de erro se o campo estiver vazio
                    Toast.makeText(getApplicationContext(), "Preencha a anotação!", Toast.LENGTH_SHORT).show();
                } else {
                    // Salva o texto nas preferências do usuário
                    preferencias.salvarAnotacao(textoRecuperado);

                    // Exibe uma mensagem de sucesso ao salvar a anotação
                    Toast.makeText(getApplicationContext(), "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Recupera a última anotação salva, se houver
        String anotacao = preferencias.recuperarAnotacao();

        // Se houver uma anotação salva, define o texto no campo de anotação
        if (!anotacao.equals("")) {
            editAnotacao.setText(anotacao);
        }
    }
}
