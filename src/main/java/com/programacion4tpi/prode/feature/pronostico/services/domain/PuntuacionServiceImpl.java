package com.programacion4tpi.prode.feature.pronostico.services.domain;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.models.enums.ResultadoPartido;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import com.programacion4tpi.prode.feature.pronostico.repository.IPronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.services.domain.interfaces.PuntuacionService;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import com.programacion4tpi.prode.feature.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  DEBUGGEAR la garcha esta
 */

@Service
@RequiredArgsConstructor
public class PuntuacionServiceImpl implements PuntuacionService {

    private final IPronosticoRepository pronosticoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void calcularYAsignarPuntos(Partido partido) {

        List<Pronostico> pronosticos = pronosticoRepository.findByPartidoId(partido.getId());

        for (Pronostico pronostico : pronosticos) {

            if (pronostico.getPuntosOtorgados() != null && pronostico.getPuntosOtorgados() > 0) {
                continue;
            }

            int puntos = calcularPuntos(
                    partido.getGolesLocal(),
                    partido.getGolesVisitante(),
                    pronostico.getGolesLocalPredicho(),
                    pronostico.getGolesVisitantePredicho()
            );

            pronostico.setPuntosOtorgados(puntos);

            Usuario usuario = pronostico.getUsuario();
            usuario.setPuntosTotales(usuario.getPuntosTotales() + puntos);

            if (puntos == 3) {
                usuario.setResultadosExactos(usuario.getResultadosExactos() + 1);
            }
            usuarioRepository.save(usuario);
        }
    }

    // --- Helpers ---

    private int calcularPuntos(int golesLocalReal, int golesVisitanteReal,
                               int golesLocalProno, int golesVisitanteProno) {

        if (golesLocalProno == golesLocalReal &&
                golesVisitanteProno == golesVisitanteReal) {
            return 3;
        }

        ResultadoPartido resultadoReal = derivarResultado(golesLocalReal, golesVisitanteReal);

        ResultadoPartido resultadoPronosticado = derivarResultado(golesLocalProno, golesVisitanteProno);

        if (resultadoPronosticado == resultadoReal) {
            return 1;
        }

        return 0;
    }

    private ResultadoPartido derivarResultado(int golesLocal, int golesVisitante) {

        if (golesLocal > golesVisitante) {
            return ResultadoPartido.LOCAL;
        }

        if (golesLocal < golesVisitante) {
            return ResultadoPartido.VISITANTE;
        }

        return ResultadoPartido.EMPATE;
    }
}