// feature/pronostico/service/impl/PuntuacionServiceImpl.java
package com.programacion4tpi.prode.feature.pronostico.service.impl;

import com.programacion4tpi.prode.feature.partido.models.Partido;
import com.programacion4tpi.prode.feature.partido.models.Partido.ResultadoPartido;
import com.programacion4tpi.prode.feature.pronostico.models.Pronostico;
import com.programacion4tpi.prode.feature.pronostico.repository.PronosticoRepository;
import com.programacion4tpi.prode.feature.pronostico.service.domain.PuntuacionService;
import com.programacion4tpi.prode.feature.usuario.models.Usuario;
import com.programacion4tpi.prode.feature.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuntuacionServiceImpl implements PuntuacionService {

    private final PronosticoRepository pronosticoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void calcularYAsignarPuntos(Partido partido) {
        ResultadoPartido resultadoReal = partido.getResultado();
        List<Pronostico> pronosticos = pronosticoRepository.findByPartidoId(partido.getId());

        for (Pronostico pronostico : pronosticos) {
            int puntos = calcularPuntos(
                    partido.getGolesLocal(),
                    partido.getGolesVisitante(),
                    pronostico.getGolesLocalPronosticado(),
                    pronostico.getGolesVisitantePronosticado(),
                    resultadoReal
            );
            pronostico.setPuntosObtenidos(puntos);
            pronosticoRepository.save(pronostico);

            // Acumular en el usuario
            Usuario usuario = pronostico.getUsuario();
            usuario.setPuntosTotales(usuario.getPuntosTotales() + puntos);
            if (puntos == 3) {
                usuario.setResultadosExactos(usuario.getResultadosExactos() + 1);
            }
            usuarioRepository.save(usuario);
        }
    }


    int calcularPuntos(int golesLocalReal, int golesVisitanteReal,
                       int golesLocalProno, int golesVisitanteProno,
                       ResultadoPartido resultadoReal) {

        // Pleno: marcador exacto
        if (golesLocalProno == golesLocalReal && golesVisitanteProno == golesVisitanteReal) {
            return 3;
        }

        // Tendencia: el pronostico acertó el ganador/empate
        ResultadoPartido tendenciaProno = derivarResultado(golesLocalProno, golesVisitanteProno);
        if (tendenciaProno == resultadoReal) {
            return 1;
        }

        return 0;
    }

    private ResultadoPartido derivarResultado(int golesLocal, int golesVisitante) {
        if (golesLocal > golesVisitante) return ResultadoPartido.LOCAL;
        if (golesLocal < golesVisitante) return ResultadoPartido.VISITANTE;
        return ResultadoPartido.EMPATE;
    }
}