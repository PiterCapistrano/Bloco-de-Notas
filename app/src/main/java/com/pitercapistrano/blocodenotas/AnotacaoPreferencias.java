package com.pitercapistrano.blocodenotas;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {
    // Context é responsável por salvar nossas anotações
    private Context context;
    // SharedPreferences é responsável  por recuperar nossas anotações
    private SharedPreferences preferences;
    // SharedPreferences.Editor é responsável por salvar nossas anotações
    private SharedPreferences.Editor editor;

    // Constante que cria o nome do arquivo que irá armazenar nossas anotações
    private final  String NOME_ARQUIVO = "anotacao.preferencias";

    //Chave para salvar nossas anotações
    private final String CHAVE_NOME = "nome";


    public AnotacaoPreferencias(Context c) {
        this.context = c;
        // Cria um arquivo para armazenar nossas anotações
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
        // O editor vai receber nossas preferências
        editor = preferences.edit();
    }
    public void salvarAnotacao (String anotacao) {
        editor.putString(CHAVE_NOME, anotacao);
        editor.commit();
    }

    public String recuperarAnotacao () {
        return preferences.getString(CHAVE_NOME,"");
    }

}
