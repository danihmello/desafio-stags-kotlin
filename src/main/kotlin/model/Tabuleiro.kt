package model

import java.util.Random

enum class TabuleiroEvento {SELECAO}

class Tabuleiro (private val qtdePecasBrancas: Int, private val qtdePecasPretas: Int) {

    private val casas = ArrayList<ArrayList<Casa>>()
    private val pecasBrancas = ArrayList<Peca>()
    private val pecasPretas = ArrayList<Peca>()
    private val callbacks = ArrayList<(TabuleiroEvento) -> Unit>()
    
    init {
        gerarCasas()
        geraPecasBrancas()
        geraPecasPretas()
        inserirPecas()
    }
    
    private fun gerarCasas() {
        for(linha in 0..7) {
            casas.add(ArrayList())
            for(coluna in 0..7) {
                val novaCasa = Casa(linha, coluna)
                if(linha%2 == 0 && coluna%2 == 0 || linha%2 != 0 && coluna%2 != 0) {
                    novaCasa.corTabuleiro = CorTabuleiro.ESCURO
                }
                novaCasa.onEvento(this::verificarSelecao)
                casas[linha].add(novaCasa)
            }
        }
    }
    
    private fun verificarSelecao(casa: Casa, evento: CasaEvento) {
        if(evento == CasaEvento.SELECAO) {
            callbacks.forEach { it(TabuleiroEvento.SELECAO) }
            listarMovimentos(casa)
        }
    }
    
    private fun listarMovimentos(casa: Casa) {
        var listaMovimentos: ArrayList<ArrayList<Int>> = ArrayList()
        when(casa.ocupante) {
            is Bispo -> listaMovimentos = (casa.ocupante as Bispo).movimentos(casa.linha, casa.coluna)
            is Torre -> listaMovimentos = (casa.ocupante as Torre).movimentos(casa.linha, casa.coluna)
            is Rei -> listaMovimentos = (casa.ocupante as Rei).movimentos(casa.linha, casa.coluna)
            is Rainha -> listaMovimentos = (casa.ocupante as Rainha).movimentos(casa.linha, casa.coluna)
        }
        geraAcoesMovimentos(listaMovimentos, casa)
    }
    
    private fun geraAcoesMovimentos(listaMovimentos: ArrayList<ArrayList<Int>>, casaSelecionada: Casa) {
        var linha: Int
        var coluna: Int
        var casa: Casa
        
        for(mover in listaMovimentos) {
            linha = mover[0]
            coluna = mover[1]
            casa = casas[linha][coluna]
            
            if(casa.ocupante != null) {
                if(casaSelecionada.ocupante?.cor != casa.ocupante?.cor) {
                    casa.callbacks.forEach { it(casa, CasaEvento.CAPTURA) }
                }
            } else{
                casa.callbacks.forEach { it(casa, CasaEvento.PERMITIDO) }
            }
        }
    }
    
    private fun inserirPecas() {
        val gerador = Random()
        val geradorIndice = Random()
        var linhaSorteada = -1
        var colunaSorteada = -1
        var indicePeca = -1
        var qtdeBrancasAtual = 0
        var qtdePretasAtual = 0
        
        while(qtdeBrancasAtual < this.qtdePecasBrancas) {
            linhaSorteada = gerador.nextInt(3)
            colunaSorteada = gerador.nextInt(8)
            indicePeca = geradorIndice.nextInt(pecasBrancas.size)
            
            val casaSorteada = casas[7-linhaSorteada][colunaSorteada]
            val pecaSorteada = pecasBrancas.get(indicePeca)
            if (casaSorteada.ocupante == null) {
                casaSorteada.ocupante = pecaSorteada
                pecasBrancas.remove(pecaSorteada)
                qtdeBrancasAtual++
            }
        }
    
        while(qtdePretasAtual < this.qtdePecasPretas) {
            linhaSorteada = gerador.nextInt(3)
            colunaSorteada = gerador.nextInt(8)
            indicePeca = geradorIndice.nextInt(pecasPretas.size)
        
            val casaSorteada = casas[linhaSorteada][colunaSorteada]
            val pecaSorteada = pecasPretas.get(indicePeca)
            if (casaSorteada.ocupante == null) {
                casaSorteada.ocupante = pecaSorteada
                pecasPretas.remove(pecaSorteada)
                qtdePretasAtual++
            }
        }
    }
    
    fun forEachCasa(callback: (Casa) -> Unit) {
        casas.forEach { linha -> linha.forEach(callback) }
    }
    
    private fun geraPecasBrancas() {
        val bispo1 = Bispo(Cor.BRANCO)
        val bispo2 = Bispo(Cor.BRANCO)
        val torre1 = Torre(Cor.BRANCO)
        val torre2 = Torre(Cor.BRANCO)
        val rei = Rei(Cor.BRANCO)
        val rainha = Rainha(Cor.BRANCO)
        
        pecasBrancas.addAll(listOf(bispo1, bispo2, torre1, torre2, rei, rainha))
    }
    
    private fun geraPecasPretas() {
        val bispo1 = Bispo(Cor.PRETO)
        val bispo2 = Bispo(Cor.PRETO)
        val torre1 = Torre(Cor.PRETO)
        val torre2 = Torre(Cor.PRETO)
        val rei = Rei(Cor.PRETO)
        val rainha = Rainha(Cor.PRETO)
        
        pecasPretas.addAll(listOf(bispo1, bispo2, torre1, torre2, rei, rainha))
    }
    
}