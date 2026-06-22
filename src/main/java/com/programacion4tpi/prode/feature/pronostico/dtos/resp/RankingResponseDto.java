package com.programacion4tpi.prode.feature.pronostico.dtos.resp;

public class RankingResponseDto {

    private Long usuarioId;
    private String username;
    private Integer totalPuntos;
    private Long totalResultadosExactos;

    public RankingResponseDto() {}

    public RankingResponseDto(Long usuarioId, String username,
                              Integer totalPuntos, Long totalResultadosExactos) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.totalPuntos = totalPuntos;
        this.totalResultadosExactos = totalResultadosExactos;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Integer getTotalPuntos() { return totalPuntos; }
    public void setTotalPuntos(Integer totalPuntos) { this.totalPuntos = totalPuntos; }

    public Long getTotalResultadosExactos() { return totalResultadosExactos; }
    public void setTotalResultadosExactos(Long totalResultadosExactos) {
        this.totalResultadosExactos = totalResultadosExactos;
    }
}