package JAVA.JogoDaVelha;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Botoes {

    private JButton[] bt;
    private JButton ativarIA;
    private JLabel px;
    private JLabel po;
    private boolean IAon = false;

    private int pontosX = 0;
    private int pontosO = 0;
    private boolean xo = false;
    private boolean[] click = new boolean[9];
    private boolean iaAtiva = false;

    private static final int[][] WINS_POSSIBLE = {
            { 0, 1, 2 },
            { 3, 4, 5 },
            { 6, 7, 8 },
            { 0, 4, 8 },
            { 2, 4, 6 },
            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 }
    };

    public Botoes(JButton[] bt, JButton novo, JButton zerar, JButton ativarIA, JLabel px, JLabel po) {
        this.bt = bt;
        this.ativarIA = ativarIA;
        this.px = px;
        this.po = po;

        // Inicializar estado dos botões
        for (int i = 0; i < 9; i++) {
            click[i] = false;
            bt[i].setFont(new Font("Arial", Font.BOLD, 40));
        }

        novo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpar();
            }
        });

        zerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pontosO = 0;
                pontosX = 0;
                atualizarPlacar();
            }
        });

        ativarIA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iaAtiva = !iaAtiva;
                ativarIA.setText(iaAtiva ? "DESATIVAR IA" : "ATIVAR IA");
                IAon = !IAon;
                xo=false;
                limpar();
            }
        });

        for (int i = 0; i < 9; i++) {
            final int index = i;
            bt[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!click[index]) {
                        click[index] = true;
                        mudar(bt[index]);
                        if (iaAtiva && !xo) {
                            jogarIA();
                        }
                    }
                }
            });
        }
    }

    private void mudar(JButton btn) {
        if (xo) {
            btn.setText("O");
            xo = false;
        } else {
            btn.setText("X");
            if (IAon) {
                xo = false;
            } else {
                xo = true;
            }
        }
        verificarVitoria();
    }

    private void atualizarPlacar() {
        px.setText("X: " + pontosX);
        po.setText("O: " + pontosO);
    }

    private void verificarVitoria() {
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            if (click[i]) {
                cont++;
            }
        }

        if (verificarLinhas("X") || verificarColunas("X") || verificarDiagonais("X")) {
            JOptionPane.showMessageDialog(null, "'X' GANHOU");
            pontosX++;
            atualizarPlacar();
            limpar();
        } else if (verificarLinhas("O") || verificarColunas("O") || verificarDiagonais("O")) {
            JOptionPane.showMessageDialog(null, "'O' GANHOU");
            pontosO++;
            atualizarPlacar();
            limpar();
        } else if (cont == 9) {
            JOptionPane.showMessageDialog(null, "EMPATE");
            limpar();
        }
    }

    private boolean verificarLinhas(String jogador) {
        return (bt[0].getText().equals(jogador) && bt[1].getText().equals(jogador) && bt[2].getText().equals(jogador))
                || (bt[3].getText().equals(jogador) && bt[4].getText().equals(jogador)
                        && bt[5].getText().equals(jogador))
                || (bt[6].getText().equals(jogador) && bt[7].getText().equals(jogador)
                        && bt[8].getText().equals(jogador));
    }

    private boolean verificarColunas(String jogador) {
        return (bt[0].getText().equals(jogador) && bt[3].getText().equals(jogador) && bt[6].getText().equals(jogador))
                || (bt[1].getText().equals(jogador) && bt[4].getText().equals(jogador)
                        && bt[7].getText().equals(jogador))
                || (bt[2].getText().equals(jogador) && bt[5].getText().equals(jogador)
                        && bt[8].getText().equals(jogador));
    }

    private boolean verificarDiagonais(String jogador) {
        return (bt[0].getText().equals(jogador) && bt[4].getText().equals(jogador) && bt[8].getText().equals(jogador))
                || (bt[2].getText().equals(jogador) && bt[4].getText().equals(jogador)
                        && bt[6].getText().equals(jogador));
    }

    private void limpar() {
        for (int i = 0; i < 10; i++) {
            bt[i].setText("");
            click[i] = false;
        }
        xo = false;
    }

    private void jogarIA() {
        List<int[]> vitoria = new ArrayList<>();
        List<int[]> risco = new ArrayList<>();
        List<int[]> tentativaVitoria = new ArrayList<>();
        List<int[]> inicializacao = new ArrayList<>();

        for (int[] combinacao : WINS_POSSIBLE) {
            int contadorX = 0;
            int contadorO = 0;
            int posicaoLivre = -1;

            for (int id : combinacao) {
                if (bt[id].getText().equals("X")) {
                    contadorX++;
                } else if (bt[id].getText().equals("O")) {
                    contadorO++;
                } else {
                    posicaoLivre = id;
                }
            }

            if (contadorO == 2 && posicaoLivre != -1) {
                risco.add(combinacao);
            } else if (contadorO == 1 && contadorX == 0) {
                tentativaVitoria.add(combinacao);
            } else if (contadorX == 1 && contadorO == 0) {
                inicializacao.add(combinacao);
            } else if (contadorO == 0 && contadorX == 0) {
                vitoria.add(combinacao);
            }
        }

        if (!vitoria.isEmpty()) {
            fazerJogada(vitoria);
        } else if (!risco.isEmpty()) {
            fazerJogada(risco);
        } else if (!tentativaVitoria.isEmpty()) {
            fazerJogada(tentativaVitoria);
        } else if (!inicializacao.isEmpty()) {
            fazerJogada(inicializacao);
        }
    }

    private void fazerJogada(List<int[]> combinacoes) {
        List<Integer> posicoesLivres = new ArrayList<>();
        for (int[] combinacao : combinacoes) {
            for (int id : combinacao) {
                if (bt[id].getText().isEmpty()) {
                    posicoesLivres.add(id);
                }
            }
        }
        if (!posicoesLivres.isEmpty()) {
            Random random = new Random();
            int idAleatorio = posicoesLivres.get(random.nextInt(posicoesLivres.size()));
            bt[idAleatorio].setText("O");
            click[idAleatorio] = true;
            verificarVitoria();
        }
    }
}
