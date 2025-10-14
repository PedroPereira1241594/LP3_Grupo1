package com.example.Utils;

public class Tools {
    public enum tipoUtilizador {
        CLIENTE(1), GESTOR(2);

        private final int codigo;

        tipoUtilizador(int codigo) {
            this.codigo = codigo;
        }

        public int getCodigo() {
            return codigo;
        }

        public static tipoUtilizador fromCodigo(int codigo) {
            for (tipoUtilizador tipo : tipoUtilizador.values()) {
                if (tipo.getCodigo() == codigo) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }
    }

    public enum estadoUtilizador {
        DEFAULT(0), PENDENTE(1), ATIVO(2), INATIVO(3);

        private final int codigo;

        estadoUtilizador(int codigo) {
            this.codigo = codigo;
        }

        public int getCodigo() {
            return codigo;
        }

        public static estadoUtilizador fromCodigo(int codigo) {
            for (estadoUtilizador estado : estadoUtilizador.values()) {
                if (estado.getCodigo() == codigo) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }

        public static estadoUtilizador getDefault() {
            return DEFAULT; // Valor Default do enum estadoUtilizador
        }
    }
}
