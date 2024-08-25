package JAVA.JogoDaVelha;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesAcao {

    private JButton[] bt;
    private JButton novo;
    private JButton zerar;
    private JLabel px;
    private JLabel po;

    private int pontosX = 0;
    private int pontosO = 0; 
    private boolean xo = false;
    private boolean[] click = new boolean[9];

    public BotoesAcao(JButton[] bt, JButton novo, JButton zerar, JLabel px, JLabel po) {
        this.bt = bt;
        this.novo = novo;
        this.zerar = zerar;
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

        for (int i = 0; i < 9; i++) {
            final int index = i;
            bt[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!click[index]) {
                        click[index] = true;
                        mudar(bt[index]);
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
            xo = true;
        }
        verificarVitoria();
    }

    private void atualizarPlacar(){
        px.setText("X: " + pontosX);
        po.setText("O: " + pontosO);
    }

    private void verificarVitoria() {
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            if(click[i]){
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

        } else if (cont == 9){
            JOptionPane.showMessageDialog(null, "EMPATE");
            limpar();
        }
    }

    private boolean verificarLinhas(String jogador) {
        return (bt[0].getText().equals(jogador) && bt[1].getText().equals(jogador) && bt[2].getText().equals(jogador))
            || (bt[3].getText().equals(jogador) && bt[4].getText().equals(jogador) && bt[5].getText().equals(jogador))
            || (bt[6].getText().equals(jogador) && bt[7].getText().equals(jogador) && bt[8].getText().equals(jogador));
    }

    private boolean verificarColunas(String jogador) {
        return (bt[0].getText().equals(jogador) && bt[3].getText().equals(jogador) && bt[6].getText().equals(jogador))
            || (bt[1].getText().equals(jogador) && bt[4].getText().equals(jogador) && bt[7].getText().equals(jogador))
            || (bt[2].getText().equals(jogador) && bt[5].getText().equals(jogador) && bt[8].getText().equals(jogador));
    }

    private boolean verificarDiagonais(String jogador) {
        return (bt[0].getText().equals(jogador) && bt[4].getText().equals(jogador) && bt[8].getText().equals(jogador))
            || (bt[2].getText().equals(jogador) && bt[4].getText().equals(jogador) && bt[6].getText().equals(jogador));
    }

    private void limpar() {
        for (int i = 0; i < 9; i++) {
            bt[i].setText("");
            click[i] = false;
        }
        xo = false;
    }
}
