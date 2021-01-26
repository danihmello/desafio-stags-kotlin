package model

class Rei(cor: Cor) : Peca(cor) {
    
    fun movimentos(linha: Int, coluna: Int) : ArrayList<ArrayList<Int>> {
        
        if(linha != 0 && coluna != 0) {
            movimentosPossiveis.add(criaPosicao(linha-1, coluna-1))
        }
        
        if(coluna != 0) {
            movimentosPossiveis.add(criaPosicao(linha, coluna-1))
        }
        
        if(linha != 7 && coluna != 0) {
            movimentosPossiveis.add(criaPosicao(linha+1, coluna -1))
        }
        
        if(linha != 0) {
            movimentosPossiveis.add(criaPosicao(linha-1, coluna))
        }
        
        if(linha != 7) {
            movimentosPossiveis.add(criaPosicao(linha+1, coluna))
        }
        
        if(linha != 0 && coluna != 7) {
            movimentosPossiveis.add(criaPosicao(linha-1, coluna+1))
        }
        
        if(coluna != 7) {
            movimentosPossiveis.add(criaPosicao(linha, coluna+1))
        }
        
        if(linha != 7 && coluna != 7) {
            movimentosPossiveis.add(criaPosicao(linha+1, coluna+1))
        }
        
        return movimentosPossiveis
    }
}