package br.com.fiap.vota_e.exception;

public class SugestaoNaoEncontradaException extends RuntimeException {
    public SugestaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
