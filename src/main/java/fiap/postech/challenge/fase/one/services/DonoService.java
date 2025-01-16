package fiap.postech.challenge.fase.one.services;

import fiap.postech.challenge.fase.one.adapters.ClienteAdapter;
import fiap.postech.challenge.fase.one.adapters.DonoAdapter;
import fiap.postech.challenge.fase.one.dtos.request.*;
import fiap.postech.challenge.fase.one.dtos.response.DonoResponseDTO;
import fiap.postech.challenge.fase.one.entities.Cliente;
import fiap.postech.challenge.fase.one.entities.Dono;
import fiap.postech.challenge.fase.one.repositories.DonoRepositoryImp;
import fiap.postech.challenge.fase.one.services.exceptions.InternalServerErrorException;
import fiap.postech.challenge.fase.one.services.exceptions.ResourceNotFoundException;
import fiap.postech.challenge.fase.one.services.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class DonoService {

    private final DonoRepositoryImp donoRepositoryImp;

    public DonoService(DonoRepositoryImp donoRepositoryImp) {this.donoRepositoryImp = donoRepositoryImp;}

    public DonoResponseDTO findById(Long id) {
        var dono = donoRepositoryImp.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        return new DonoResponseDTO(dono.getId(), dono.getNome(), dono.getEmail(), dono.getUltimaAlteracao());
    }

    public void createDono(DonoCreateRequestDTO dono) {
        Dono donoFormatado = new DonoAdapter().createDono(dono, new Date());
        var save = donoRepositoryImp.create(donoFormatado);

        if (save != 1) {
            throw new InternalServerErrorException("Erro ao realizar o cadastro. Por favor tente novamente");
        }
    }

    public void updateDono(DonoUpdateRequestDTO donoRequest, Long id) {
        var dono = donoRepositoryImp.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        var donoUpdated = new DonoAdapter().updateDono(
                dono.getId(),
                donoRequest.nome(),
                donoRequest.email(),
                donoRequest.login(),
                dono.getSenha(),
                new Date()
        );

        var update = donoRepositoryImp.update(donoUpdated, id);

        if (update != 1) {
            throw new InternalServerErrorException(
                    "Erro ao realizar atualização do cadastro. Por favor tente novamente"
            );

        }
    }

    public void updateSenha(UpdateSenha senhas, Long id) {
        var dono = donoRepositoryImp.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        if (!Objects.equals(dono.getSenha(), senhas.senhaAtual())) {
            throw new UnprocessableEntityException("Senha atual incorreta, por favor tente novamente");
        }

        Dono donoFormatado = new DonoAdapter()
                .updateDono(
                        dono.getId(),
                        dono.getNome(),
                        dono.getEmail(),
                        dono.getLogin(),
                        senhas.novaSenha(),
                        new Date()
                );

        var update = donoRepositoryImp.update(donoFormatado, dono.getId());

        if (update != 1) {
            throw new InternalServerErrorException("Erro ao atualizar a senha");
        }
    }

    public void deleteDono(DonoDeleteRequestDTO donoDTO) {
        var dono = donoRepositoryImp.findByEmail(donoDTO.email())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (!Objects.equals(dono.getSenha(), donoDTO.senha())) {
            throw new UnprocessableEntityException("Senha incorreta, por favor tente novamente");
        }

        donoRepositoryImp.delete(dono.getId());

    }

}
