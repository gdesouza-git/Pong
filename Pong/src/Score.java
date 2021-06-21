import java.awt.*;

/**
 Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
 instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
 de um player, quando a execução é iniciada.
 */
public class Score {

    private int score = 0;
    private String playerId;

    /**
     Construtor da classe Score.

     @param playerId uma string que identifica o player ao qual este placar está associado.
     */

    public Score(String playerId){

        this.playerId = playerId;
    }

    /**
     Método de desenho do placar.
     */

    public void draw(){

        if (playerId == Pong.PLAYER1) GameLib.drawText("Player 1: "+Integer.toString(score), 70.0D, GameLib.ALIGN_LEFT);
        if (playerId == Pong.PLAYER2) GameLib.drawText("Player 2: "+Integer.toString(score), 70.0D, GameLib.ALIGN_RIGHT);
    }

    /**
     Método que incrementa em 1 unidade a contagem de pontos.
     */

    public void inc(){
        score++;
    }

    /**
     Método que devolve a contagem de pontos mantida pelo placar.

     @return o valor inteiro referente ao total de pontos.
     */

    public int getScore(){

        return score;
    }
}
