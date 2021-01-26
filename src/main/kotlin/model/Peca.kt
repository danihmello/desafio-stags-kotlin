package model

enum class Cor {BRANCO, PRETO}

open class Peca(var cor: Cor? = null) {
    
    var movimentosPossiveis: ArrayList<ArrayList<Int>> = ArrayList()
    
    fun criaPosicao(linha: Int, coluna: Int): ArrayList<Int> {
        var posicao = ArrayList<Int>()
        posicao.add(linha)
        posicao.add(coluna)
        return posicao
    }
    
    fun movimentosVerticaisHorizontais(linha: Int, coluna: Int) : ArrayList<ArrayList<Int>> {
        for(valor in 0..7) {
            if(valor != coluna) {
                movimentosPossiveis.add(criaPosicao(linha, valor))
            }
            if(valor != linha) {
                movimentosPossiveis.add(criaPosicao(valor, coluna))
            }
        }
        return movimentosPossiveis
    }
    
    fun movimentosDiagonais(linha: Int, coluna: Int): ArrayList<ArrayList<Int>> {
        var linhaAtual = linha
        var colunaAtual = coluna
        
        while(linhaAtual > 0 && colunaAtual > 0) {
            movimentosPossiveis.add(criaPosicao(linhaAtual-1, colunaAtual-1))
            linhaAtual--
            colunaAtual--
        }
        linhaAtual = linha
        colunaAtual = coluna
        
        while(linhaAtual > 0 && colunaAtual < 7) {
            movimentosPossiveis.add(criaPosicao(linhaAtual-1, colunaAtual+1))
            linhaAtual--
            colunaAtual++
        }
        linhaAtual = linha
        colunaAtual = coluna
        
        while (linhaAtual < 7 && colunaAtual > 0) {
            movimentosPossiveis.add(criaPosicao(linhaAtual+1, colunaAtual-1))
            linhaAtual++
            colunaAtual--
        }
        linhaAtual = linha
        colunaAtual = coluna
        
        while(linhaAtual < 7 && colunaAtual < 7) {
            movimentosPossiveis.add(criaPosicao(linhaAtual+1, colunaAtual+1))
            linhaAtual++
            colunaAtual++
        }
        
        return movimentosPossiveis
    }
}