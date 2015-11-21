package br.com.senai.postocorreio.dao;

public class LoginInvalidoException extends Exception {

    public LoginInvalidoException() {
        super("Usuário/senha inválido");
    }

}
