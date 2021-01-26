package model

enum class CorTabuleiro {CLARO, ESCURO}
enum class CasaEvento {SELECAO, PERMITIDO, CAPTURA}

data class Casa (val linha: Int, val coluna: Int) {
    var corTabuleiro: CorTabuleiro = CorTabuleiro.CLARO
    var ocupante: Peca? = null
    val callbacks = ArrayList<(Casa, CasaEvento) -> Unit>()
    
    fun onEvento(callback: (Casa, CasaEvento) -> Unit) {
        callbacks.add(callback)
    }
    
    fun selecionar() {
        callbacks.forEach { it(this, CasaEvento.SELECAO) }
    }
}