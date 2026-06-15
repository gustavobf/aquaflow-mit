package com.infnet.aquaflow.adaptador.tratamento;

import com.infnet.aquaflow.aplicacao.dto.requisicao.CriarAlunoRequisicao;
import com.infnet.aquaflow.dominio.excecao.AlunoNaoEncontradoException;
import com.infnet.aquaflow.dominio.excecao.RegraDeNegocioException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TratadorGlobalExcecoesTest {

    private final TratadorGlobalExcecoes tratador = new TratadorGlobalExcecoes();

    @Test
    void deveRetornar404ParaNaoEncontrado () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/alunos/1");

        ResponseEntity<ErroPadraoResposta> response = tratador.tratarNaoEncontrado(
                new AlunoNaoEncontradoException("Aluno nao encontrado."), request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().status());
        assertEquals("/api/alunos/1", response.getBody().caminho());
    }

    @Test
    void deveRetornar400ParaRegraDeNegocio () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/matriculas");

        ResponseEntity<ErroPadraoResposta> response = tratador.tratarRegraNegocio(
                new RegraDeNegocioException("Regra violada"), request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Regra violada", response.getBody().mensagem());
    }

    @Test
    void deveRetornar400ComDetalhesParaErroDeValidacao () throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/alunos");

        Method method = DummyEntrada.class.getDeclaredMethod("metodo", CriarAlunoRequisicao.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "requisicao");
        bindingResult.addError(new FieldError("requisicao", "email", "email invalido"));

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<ErroPadraoResposta> response = tratador.tratarValidacao(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(1, response.getBody().detalhes().size());
        assertEquals("email", response.getBody().detalhes().get(0).campo());
    }

    @Test
    void deveRetornar400ComDetalhesParaConstraintViolation () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/frequencias");

        @SuppressWarnings("unchecked") ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("tipoFrequencia");
        when(violation.getPropertyPath()).thenReturn(path);
        when(violation.getMessage()).thenReturn("deve ser PRESENTE ou AUSENTE");

        ConstraintViolationException exception = new ConstraintViolationException(Set.of(violation));

        ResponseEntity<ErroPadraoResposta> response = tratador.tratarConstraintViolation(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.getBody().detalhes().isEmpty());
        assertEquals("tipoFrequencia", response.getBody().detalhes().get(0).campo());
    }

    @Test
    void deveRetornar500ParaErroGenerico () {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/erro");

        ResponseEntity<ErroPadraoResposta> response = tratador.tratarGenerica(new RuntimeException("falha"), request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno inesperado.", response.getBody().mensagem());
    }

    private static class DummyEntrada {
        @SuppressWarnings("unused")
        void metodo (CriarAlunoRequisicao requisicao) {
        }
    }
}

