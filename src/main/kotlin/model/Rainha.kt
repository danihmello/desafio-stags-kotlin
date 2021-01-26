package model

class Rainha(cor: Cor) : Peca(cor) {
    
    fun movimentos(linha: Int, coluna: Int): ArrayList<ArrayList<Int>> {
        
        var movimentos = movimentosVerticaisHorizontais(linha, coluna) + movimentosDiagonais(linha, coluna)
        
        return movimentos as ArrayList<ArrayList<Int>>
    }
}