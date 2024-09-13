package com.pitercapistrano.blocodenotas;

import android.content.Context;  // Importa a classe Context para acessar recursos e preferências de aplicação
import android.content.SharedPreferences;  // Importa a classe SharedPreferences para salvar e recuperar dados persistentes

public class AnotacaoPreferencias {

    // Declara a variável `context` que representa o ambiente atual da aplicação para salvar e recuperar dados
    private Context context;

    // Declara o objeto `preferences`, responsável por acessar e recuperar os dados salvos no arquivo de preferências
    private SharedPreferences preferences;

    // Declara o objeto `editor`, que será usado para editar (inserir, atualizar ou remover) os dados salvos nas preferências
    private SharedPreferences.Editor editor;

    // Constante que define o nome do arquivo onde as anotações serão armazenadas
    private final String NOME_ARQUIVO = "anotacao.preferencias";

    // Chave usada para identificar o valor da anotação nas preferências
    private final String CHAVE_NOME = "nome";

    // Construtor da classe, recebe um `Context` como parâmetro para associar o armazenamento de preferências ao contexto da aplicação
    public AnotacaoPreferencias(Context c) {
        this.context = c;

        // Inicializa o objeto `preferences`, criando ou acessando o arquivo de preferências definido por `NOME_ARQUIVO`
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);

        // Inicializa o editor das preferências para permitir salvamento ou edição de dados
        editor = preferences.edit();
    }

    // Método para salvar uma anotação no arquivo de preferências
    public void salvarAnotacao(String anotacao) {
        // Salva a string `anotacao` nas preferências com a chave `CHAVE_NOME`
        editor.putString(CHAVE_NOME, anotacao);

        // Confirma a gravação dos dados chamando o método `commit()`
        editor.commit();
    }

    // Método para recuperar a anotação salva, retornando o valor associado à chave `CHAVE_NOME`
    public String recuperarAnotacao() {
        // Retorna o valor armazenado ou uma string vazia se não houver nenhuma anotação salva
        return preferences.getString(CHAVE_NOME, "");
    }
}
