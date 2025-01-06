package AtividadeVitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {

    private Modelo modelo;
    private Visao visao;

    public Controlador(Modelo modelo, Visao visao) {
        this.modelo = modelo;
        this.visao = visao;

        visao.getBotaoClique().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.incrementar();
                visao.atualizarContador(modelo.getContador());
            }
        });

        visao.getBotaoResetar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.resetar();
                visao.atualizarContador(modelo.getContador());
            }
        });
    }
}