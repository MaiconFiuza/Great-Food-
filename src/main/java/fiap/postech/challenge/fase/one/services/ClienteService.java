package fiap.postech.challenge.fase.one.services;

import fiap.postech.challenge.fase.one.adapters.ClienteAdapter;
import fiap.postech.challenge.fase.one.dtos.request.ClienteDeleteRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.ClienteCreateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.ClienteUpdateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.UpdateSenha;
import fiap.postech.challenge.fase.one.dtos.response.ClienteResponseDTO;
import fiap.postech.challenge.fase.one.entities.Cliente;
import fiap.postech.challenge.fase.one.repositories.ClienteRepository;
import fiap.postech.challenge.fase.one.services.exceptions.InternalServerErrorException;
import fiap.postech.challenge.fase.one.services.exceptions.ResourceNotFoundException;
import fiap.postech.challenge.fase.one.services.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO getClienteById(Long id) {
        var cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        return new ClienteResponseDTO(
                cliente.getNome(), cliente.getEmail(), cliente.getEndereco(), cliente.getUltimaAlteracao()
        );
    }

    public void createCliente(ClienteCreateRequestDTO cliente) {
         var clienteFormatado = new ClienteAdapter().transform(cliente, new Date());
         var save = clienteRepository.save(clienteFormatado);

         if (save !=1 ) {
             throw new InternalServerErrorException("Erro ao Realizar o cadastro. Por favor tente novamente");

         }
    }

    public void updateCliente(ClienteUpdateRequestDTO updatedCliente, Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Cliente clienteFormatado = new ClienteAdapter()
                .updateCliente(
                    cliente.getId(),
                    updatedCliente.nome(),
                    updatedCliente.email(),
                    updatedCliente.login(),
                    cliente.getSenha(),
                    updatedCliente.endereco(),
                    new Date()
                );

        var update = clienteRepository.update(clienteFormatado, cliente.getId());

        if (update != 1) {
            throw new InternalServerErrorException("Erro ao atualizar o perfil");
        }
    }

    public void updateSenha(UpdateSenha senhas, Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (!Objects.equals(cliente.getSenha(), senhas.senhaAtual())) {
            throw new UnprocessableEntityException("Senha atual incorreta, por favor tente novamente");
        }

        Cliente clienteFormatado = new ClienteAdapter()
                .updateCliente(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getLogin(),
                        senhas.novaSenha(),
                        cliente.getEndereco(),
                        new Date()
                );

        var update = clienteRepository.update(clienteFormatado, cliente.getId());

        if (update != 1) {
            throw new InternalServerErrorException("Erro ao atualizar a senha");
        }
    }


    public void deleteCliente(ClienteDeleteRequestDTO clienteDTO) {
        var cliente = clienteRepository.findByEmail(clienteDTO.email())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

       if (!Objects.equals(cliente.getSenha(), clienteDTO.senha())) {
           throw new UnprocessableEntityException("Senha incorreta, por favor tente novamente");
       }

       clienteRepository.delete(cliente.getId());

    }

}
