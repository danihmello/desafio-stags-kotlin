package view

import javax.swing.JFrame
import model.Tabuleiro

class TelaPrincipal : JFrame() {

    private val tabuleiro = Tabuleiro(6, 6)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)

    init {
        add(painelTabuleiro)
        setSize(600,600)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Chess"
        isVisible = true
    }

}