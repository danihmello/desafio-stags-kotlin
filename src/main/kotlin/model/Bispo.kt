package model

class Bispo(cor: Cor) : Peca(cor) {
    
    fun movimentos(linha: Int, coluna: Int): ArrayList<ArrayList<Int>> {
        return movimentosDiagonais(linha, coluna)
    }
}