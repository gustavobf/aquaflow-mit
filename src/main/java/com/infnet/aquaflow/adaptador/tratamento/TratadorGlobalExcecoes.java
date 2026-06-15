package com.infnet.aquaflow.adaptador.tratamento;

import com.infnet.aquaflow.dominio.excecao.AlunoNaoEncontradoException;
import com.infnet.aquaflow.dominio.excecao.ProfessorNaoEncontradoException;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import com.infnet.aquaflow.dominio.excecao.TurmaNaoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class TratadorGlobalExcecoes {

    @ExceptionHandler({AlunoNaoEncontradoException.class, ProfessorNaoEncontradoException.class,
            TurmaNaoEncontradaException.class})
    public ResponseEntity<ErroPadraoResposta> tratarNaoEncontrado (RuntimeException excecao,
                                                                   HttpServletRequest request) {
        return respostaSemDetalhes(HttpStatus.NOT_FOUND, excecao.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroPadraoResposta> tratarRegraNegocio (RegraDeNegocioException excecao,
                                                                  HttpServletRequest request) {
        return respostaSemDetalhes(HttpStatus.BAD_REQUEST, excecao.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoResposta> tratarValidacao (MethodArgumentNotValidException excecao,
                                                               HttpServletRequest request) {
        List<ErroPadraoResposta.DetalheErro> detalhes = excecao.getBindingResult().getFieldErrors().stream()
                .map(this::mapearErroCampo).toList();

        return respostaComDetalhes(HttpStatus.BAD_REQUEST, "Dados invalidos na requisicao", request.getRequestURI(),
                detalhes);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroPadraoResposta> tratarConstraintViolation (ConstraintViolationException excecao,
                                                                         HttpServletRequest request) {
        List<ErroPadraoResposta.DetalheErro> detalhes = excecao.getConstraintViolations().stream()
                .map(violation -> new ErroPadraoResposta.DetalheErro(violation.getPropertyPath().toString(),
                        violation.getMessage())).toList();

        return respostaComDetalhes(HttpStatus.BAD_REQUEST, "Violacao de restricoes da requisicao",
                request.getRequestURI(), detalhes);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoResposta> tratarGenerica (Exception excecao, HttpServletRequest request) {
        return respostaSemDetalhes(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado.",
                request.getRequestURI());
    }

    private ErroPadraoResposta.DetalheErro mapearErroCampo (FieldError erroCampo) {
        return new ErroPadraoResposta.DetalheErro(erroCampo.getField(), erroCampo.getDefaultMessage());
    }

    private ResponseEntity<ErroPadraoResposta> respostaSemDetalhes (HttpStatus status, String mensagem,
                                                                    String caminho) {
        ErroPadraoResposta erro = new ErroPadraoResposta(Instant.now(), status.value(), status.getReasonPhrase(),
                mensagem, caminho, List.of());

        return ResponseEntity.status(status).body(erro);
    }

    private ResponseEntity<ErroPadraoResposta> respostaComDetalhes (HttpStatus status, String mensagem, String caminho,
                                                                    List<ErroPadraoResposta.DetalheErro> detalhes) {
        ErroPadraoResposta erro = new ErroPadraoResposta(Instant.now(), status.value(), status.getReasonPhrase(),
                mensagem, caminho, detalhes);

        return ResponseEntity.status(status).body(erro);
    }
}
