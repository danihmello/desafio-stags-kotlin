package view

import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JButton
import model.Bispo
import model.Casa
import model.CasaEvento
import model.Cor
import model.CorTabuleiro
import model.Rainha
import model.Rei
import model.Torre

private val COR_BG_ESCURO = Color(240,217,181)
private val COR_BG_CLARO = Color(181,136,99)
private val COR_CAPTURA = Color(255,40,0)
private val COR_PERMITIDO = Color(255,162,0)
private val COR_SELECAO = Color(0,92,255)

class BotaoCasa(val casa: Casa) : JButton() {

    init {
        isOpaque = true
        aplicarBackground()
        aplicarImagem()
        addMouseListener(MouseCliqueListener(casa) { it.selecionar() })
   
        casa.onEvento(this::aplicarEstilo)
    }
    
    private fun aplicarEstilo(casa: Casa, evento: CasaEvento) {
        when(evento) {
            CasaEvento.SELECAO -> aplicarEstiloSelecao()
            CasaEvento.CAPTURA -> aplicarEstiloCaptura()
            CasaEvento.PERMITIDO -> aplicarEstiloPermitido()
        }
    }
    
    private fun aplicarEstiloSelecao() {
        background = COR_SELECAO
    }
    
    private fun aplicarEstiloCaptura() {
        background = COR_CAPTURA
    }
    
    private fun aplicarEstiloPermitido() {
        background = COR_PERMITIDO
    }
    
    private fun aplicarBackground() {
        if(casa.corTabuleiro.equals(CorTabuleiro.CLARO)) {
            background = COR_BG_CLARO
        } else {
            background = COR_BG_ESCURO
        }
    }
    
    private fun aplicarImagem() {
        if(casa.ocupante?.cor == Cor.BRANCO) {
            when(casa.ocupante) {
                is Bispo  -> this.icon = ImageIcon("src/main/resources/Bispo_Branco.png")
                is Rainha -> this.icon = ImageIcon("src/main/resources/Rainha_Branco.png")
                is Torre -> this.icon = ImageIcon("src/main/resources/Torre_Branco.png")
                is Rei -> this.icon = ImageIcon("src/main/resources/Rei_Branco.png")
            }
        } else if(casa.ocupante?.cor == Cor.PRETO) {
            when(casa.ocupante) {
                is Bispo  -> this.icon = ImageIcon("src/main/resources/Bispo_Preto.png")
                is Rainha -> this.icon = ImageIcon("src/main/resources/Rainha_Preto.png")
                is Torre -> this.icon = ImageIcon("src/main/resources/Torre_Preto.png")
                is Rei -> this.icon = ImageIcon("src/main/resources/Rei_Preto.png")
            }
        }
    }

}