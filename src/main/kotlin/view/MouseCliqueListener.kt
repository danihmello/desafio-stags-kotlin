package view

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import model.Casa

class MouseCliqueListener(
    private val casa: Casa,
    private val onBotaoEsquerdo: (Casa) -> Unit
) : MouseListener {
    
    override fun mousePressed(e: MouseEvent?) {
        when(e?.button) {
            MouseEvent.BUTTON1 -> onBotaoEsquerdo(casa)
        }
    }
    
    override fun mouseClicked(e: MouseEvent?) {}
    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    
}