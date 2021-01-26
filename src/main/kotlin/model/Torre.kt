package model

class Torre(cor: Cor) : Peca(cor) {
    
    fun movimentos(linha: Int, coluna: Int) : ArrayList<ArrayList<Int>> {
        return movimentosVerticaisHorizontais(linha, coluna)
    }
}