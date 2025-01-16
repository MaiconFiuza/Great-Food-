package fiap.postech.challenge.fase.one.controllers;

import fiap.postech.challenge.fase.one.dtos.request.ClienteDeleteRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.ClienteCreateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.ClienteUpdateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.UpdateSenha;
import fiap.postech.challenge.fase.one.dtos.response.ClienteResponseDTO;
import fiap.postech.challenge.fase.one.entities.Cliente;
import fiap.postech.challenge.fase.one.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Crud para gerenciamento da conta de cliente")
public class ClienteController {

    private static  final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            description = "Busca o cadastro do cliente",
            summary = "Endpoint responsável por retornar o cadastro do usuário",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getCliente(@PathVariable Long id) {
        logger.info("GET /clientes/"+id);
        var response =  clienteService.getClienteById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Criar Cadastro",
            summary = "Endpoint responsável pela criação da conta do cliente",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> createCliente(@Valid @RequestBody ClienteCreateRequestDTO cliente) {
        logger.info("POST /clientes");
        clienteService.createCliente(cliente);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Troca a senha do usuário",
            summary = "Endpoint responsável por trocar a senha do usuário",
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204")
            }
    )
    @PutMapping("/{id}/senhas")
    public ResponseEntity<Void> updateSenha(@PathVariable Long id, @Valid @RequestBody UpdateSenha senhas) {
        logger.info("PUT (senha) /clientes/"+id);
        clienteService.updateSenha(senhas, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Atualiza o cadastro do cliente",
            summary = "Endpoint responsável por atualizar o cadastro do usuário",
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteUpdateRequestDTO cliente) {
        logger.info("PUT /clientes/"+id);
        clienteService.updateCliente(cliente, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Exclui o cadastro do cliente",
            summary = "Endpoint responsável pela exclusão do cadastro do usuário",
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204")
            }
    )
    @DeleteMapping()
    public ResponseEntity<Void> deleteCliente(@Valid @RequestBody ClienteDeleteRequestDTO clientePayload) {
        logger.info("DELETE /clientes");
        clienteService.deleteCliente(clientePayload);
        return ResponseEntity.noContent().build();
    }
}
