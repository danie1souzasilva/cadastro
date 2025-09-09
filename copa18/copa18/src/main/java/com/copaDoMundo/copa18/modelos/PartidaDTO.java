package com.copaDoMundo.copa18.modelos;


public record PartidaDTO(
        Long id,
        String data,
        String selecao1,
        String selecao2,
        Integer golsSelecao1,
        Integer golsSelecao2
) {

    @Override
    public String toString() {
        return String.format(
                "Partida: %s vs %s | Placar: %d x %d \n",
                selecao1, selecao2, golsSelecao1, golsSelecao2
        );
    }

}

