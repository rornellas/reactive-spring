package br.com.fiap.reactivespring.controller

import br.com.fiap.reactivespring.dto.PessoaDTO
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class PessoaController {

    @GetMapping
    fun getPessoas(): Flux<PessoaDTO> = Flux.create{
        it.next(PessoaDTO(1, "Teste", "123"))
        it.next(PessoaDTO(2, "Teste 1", "1235"))
        it.next(PessoaDTO(3, "Teste 2", "1243"))
        it.complete()
    }

    @GetMapping(value= ["produces"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getPessoasAosPoucos(): Flux<PessoaDTO> = Flux.just(
        arrayListOf(
                PessoaDTO(1, "Teste", "1233"),
                PessoaDTO(1, "Teste1", "1512"),
                PessoaDTO(1, "Teste2", "6142")
        )
    )
            .flatMapIterable { it }
            .delayElements(Duration.ofSeconds(2))
}