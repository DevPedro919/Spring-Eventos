package com.suaentrada.br.suaentrada.service;

import com.suaentrada.br.suaentrada.dto.InscricaoDto;
import com.suaentrada.br.suaentrada.dto.mapper.InscricaoMapper;
import com.suaentrada.br.suaentrada.model.EventoModel;
import com.suaentrada.br.suaentrada.model.InscricaoModel;
import com.suaentrada.br.suaentrada.model.UsuarioModel;
import com.suaentrada.br.suaentrada.repository.EventoRepository;
import com.suaentrada.br.suaentrada.repository.InscricaoRepository;
import com.suaentrada.br.suaentrada.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public InscricaoDto salvar(InscricaoDto dto) {
        UsuarioModel usuario = usuarioRepository.findById(dto.codigoUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        EventoModel evento = eventoRepository.findById(dto.codigoEvento())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (evento.getVagasDisponiveisEvento() <= 0) {
            throw new RuntimeException("Não há vagas disponíveis para este evento");
        }

        InscricaoModel inscricao = new InscricaoModel();
        inscricao.setDataInscricao(LocalDate.now());
        inscricao.setHoraInscricao(LocalTime.now());
        inscricao.setUsuarioModel(usuario);
        inscricao.setEventoModel(evento);

        evento.setVagasDisponiveisEvento(evento.getVagasDisponiveisEvento() - 1);
        eventoRepository.save(evento);

        InscricaoModel salva = inscricaoRepository.save(inscricao);
        return InscricaoMapper.toDto(salva);
    }

    public List<InscricaoDto> buscarTodas() {
        return inscricaoRepository.findAll().stream()
                .map(InscricaoMapper::toDto)
                .toList();
    }

    public List<InscricaoDto> buscarPorUsuario(String emailUsuario) {
        UsuarioModel usuario = usuarioRepository.findByEmailUsuario(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<InscricaoModel> inscricoes = inscricaoRepository.findByUsuarioModelCodigoUsuario(usuario.getCodigoUsuario());

        return inscricoes.stream()
                .map(InscricaoMapper::toDto)
                .toList();
    }

    public InscricaoDto buscarPorId(Long id) {
        return inscricaoRepository.findById(id)
                .map(InscricaoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
    }

    public void excluir(Long id) {
        InscricaoModel inscricao = inscricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));

        EventoModel evento = inscricao.getEventoModel();
        evento.setVagasDisponiveisEvento(evento.getVagasDisponiveisEvento() + 1);
        eventoRepository.save(evento);

        inscricaoRepository.deleteById(id);
    }
}
