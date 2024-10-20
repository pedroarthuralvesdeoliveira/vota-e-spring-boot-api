package br.com.fiap.vota_e.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
