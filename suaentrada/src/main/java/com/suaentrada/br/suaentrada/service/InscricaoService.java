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

    public InscricaoDto salvar(Long codigoEvento, String emailUsuario) {
        UsuarioModel usuario = usuarioRepository.findByEmailUsuario(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        EventoModel evento = eventoRepository.findById(codigoEvento)
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
        System.out.println("=== DEBUG SERVICE ===");
        System.out.println("Email recebido: '" + emailUsuario + "'");

        UsuarioModel usuario = usuarioRepository.findByEmailUsuario(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        System.out.println("Usuário encontrado - ID: " + usuario.getCodigoUsuario() + ", Email: '" + usuario.getEmailUsuario() + "'");

        List<InscricaoModel> inscricoes = inscricaoRepository.findByUsuarioModelCodigoUsuario(usuario.getCodigoUsuario());

        System.out.println("Query retornou " + inscricoes.size() + " inscrições");
        if (!inscricoes.isEmpty()) {
            inscricoes.forEach(i -> System.out.println("  Inscrição ID: " + i.getCodigoInscricao() + ", Usuario ID: " + i.getUsuarioModel().getCodigoUsuario()));
        }
        System.out.println("==================");

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
