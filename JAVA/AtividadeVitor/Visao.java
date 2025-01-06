package AtividadeVitor;

import javax.swing.*;

public class Visao extends JFrame {

    JButton botaoClique = new JButton("CLIQUES");
    JButton botaoResetar = new JButton("RESETAR");
    JLabel contador = new JLabel("CONTADOR");
    JLabel pontosContador = new JLabel("CONTAGEM: 0");

    public Visao() {
        setTitle("CONTADOR DE CLIQUES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(250, 250, 700, 500);

        add(contador);
        add(pontosContador);
        contador.setBounds(425, 50, 100, 30);
        pontosContador.setBounds(425, 75, 150, 30);

        add(botaoClique);
        add(botaoResetar);
        botaoClique.setBounds(410, 130, 140, 30);
        botaoResetar.setBounds(410, 180, 140, 30);

        setVisible(true);

    }

    public JButton getBotaoClique(){
        return botaoClique;
    }

    public JButton getBotaoResetar(){
        return botaoResetar;
    }

    public void atualizarContador(int cont){
        pontosContador.setText("CONTAGEM: " + cont);        
    }

}