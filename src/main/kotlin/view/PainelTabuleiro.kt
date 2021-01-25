package view

import java.awt.GridLayout
import javax.swing.JPanel
import model.Tabuleiro

class PainelTabuleiro(tabuleiro: Tabuleiro) : JPanel() {
    
    init {
        layout = GridLayout(8,8)
        tabuleiro.forEachCasa { casa ->
            val botao = BotaoCasa(casa)
            add(botao)
        }
    }
}