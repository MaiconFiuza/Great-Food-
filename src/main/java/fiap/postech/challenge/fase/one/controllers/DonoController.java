package fiap.postech.challenge.fase.one.controllers;

import fiap.postech.challenge.fase.one.dtos.request.DonoCreateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.DonoDeleteRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.DonoUpdateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.UpdateSenha;
import fiap.postech.challenge.fase.one.dtos.response.DonoResponseDTO;
import fiap.postech.challenge.fase.one.services.DonoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/donos")
@RestController
@Tag(name = "Donos", description = "Crud para gerenciamento das contas de Donos dos restaurantes")
public class DonoController {
    private static  final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final DonoService donoService;

    public DonoController(DonoService donoService) {this.donoService = donoService;}

    @Operation(
            description = "Busca o cadastro do dono do restaurante",
            summary = "Endpoint responsável por retornar o cadastro do dono de restaurante",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DonoResponseDTO> getDonoById(@PathVariable Long id) {
        var response = donoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Criar Cadastro",
            summary = "Endpoint responsável pela criação da conta do dono de restaurante",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> createDono(@Valid @RequestBody DonoCreateRequestDTO donoRequest) {
        donoService.createDono(donoRequest);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Atualiza o cadastro",
            summary = "Endpoint responsável por atualizar o cadastro das contas de donos de restaurante",
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDono(
            @PathVariable Long id,
            @Valid @RequestBody DonoUpdateRequestDTO donoRequest
    ) {
        donoService.updateDono(donoRequest, id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
        description = "Troca a senha do usuário",
        summary = "Endpoint responsável por trocar a senha do cadastro de dono de restaurante",
        responses = {
                @ApiResponse(description = "NO CONTENT", responseCode = "204")
        }
    )
    @PutMapping("/{id}/senhas")
    public ResponseEntity<Void> updateSenha(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSenha senhas
    ) {
        donoService.updateSenha(senhas, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Exclui o cadastro do cliente",
            summary = "Endpoint responsável pela exclusão do cadastro do usuário",
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204")
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteDono(
            @Valid @RequestBody DonoDeleteRequestDTO donoPayload
    ) {
        donoService.deleteDono(donoPayload);
        return ResponseEntity.noContent().build();
    }
}
