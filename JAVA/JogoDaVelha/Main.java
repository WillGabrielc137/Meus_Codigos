package JAVA.JogoDaVelha;

import javax.swing.*;

public class Main extends JFrame {

    JButton[] bt = new JButton[9];
    JButton novo = new JButton("NOVO JOGO");
    JButton zerar = new JButton("ZERAR PLACAR");

    JLabel placar = new JLabel("PLACAR");
    JLabel px = new JLabel("X: 0");
    JLabel po = new JLabel("O: 0");

    public Main() {
        setVisible(true);
        setTitle("JOGO DA VELHA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(250, 250, 700, 500);

        add(placar);
        add(px);
        add(po);
        placar.setBounds(425, 50, 100, 30);
        px.setBounds(425, 75, 100, 30);
        po.setBounds(425, 100, 100, 30);

        add(novo);
        add(zerar);
        novo.setBounds(410, 130, 140, 30);
        zerar.setBounds(410, 180, 140, 30);

        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bt[cont] = new JButton();
                add(bt[cont]);
                bt[cont].setBounds((100 * i) + 50, (100 * j) + 50, 95, 95);
                cont++;
            }
        }

        // Inicializa a classe BotoesAcao
        new BotoesAcao(bt, novo, zerar, px, po);
    }

    public static void main(String[] args) {
        new Main();
    }
}
