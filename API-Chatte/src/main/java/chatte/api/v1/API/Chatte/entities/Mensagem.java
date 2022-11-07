package chatte.api.v1.API.Chatte.entities;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class Mensagem {

    @Schema(
            description = "Corpo da mensagem",
            example = "Bom dia, tudo bem ?",
            type = "String"
    )
    private String corpo;

    @Schema(
            description = "Horário no qual a mensagem foi enviada",
            example = "2022/04/04 15:30",
            type = "LocalDate"
    )
    private LocalDate horario;

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public LocalDate getHorario() {
        return horario;
    }

    public void setHorario() {
        this.horario = LocalDate.now();
    }
}
