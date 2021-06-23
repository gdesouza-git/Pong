import java.awt.*;
/**
 Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
 instancia um objeto deste tipo quando a execução é iniciada.
 */

public class Ball {

    private double cx;
    private double cy;
    private double width;
    private double height;
    private Color color;
    private double speed;
    private double xVal = 1;
    private double yVal = -1;


    /**
     Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola
     (em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada
     aleatóriamente pelo construtor.

     @param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
     @param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
     @param width largura do retangulo que representa a bola.
     @param height altura do retangulo que representa a bola.
     @param color cor da bola.
     @param speed velocidade da bola (em pixels por millisegundo).
     */

    public Ball(double cx, double cy, double width, double height, Color color, double speed){
         this.cx = cx;
         this.cy = cy;
         this.width = width;
         this.height = height;
         this.color = color;
         this.speed = speed;
    }


    /**
     Método chamado sempre que a bola precisa ser (re)desenhada.
     */

    public void draw(){

        GameLib.setColor(color);
        GameLib.fillRect(cx, cy, width, height);
    }

    /**
     Método chamado quando o estado (posição) da bola precisa ser atualizado.

     @param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
     */

    public void update(long delta){
        
        this.cx -= xVal * speed * delta;
        this.cy -= yVal * speed * delta;
        
    }

    /**
     Método chamado quando detecta-se uma colisão da bola com um jogador.

     @param playerId uma string cujo conteúdo identifica um dos jogadores.
     */

    public void onPlayerCollision(String playerId){
            xVal *= -1;
        }
    

    /**
     Método chamado quando detecta-se uma colisão da bola com uma parede.

     @param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
     */

    public void onWallCollision(String wallId){

        System.out.println(wallId);


        if(wallId.compareToIgnoreCase("top") == 0){
            if(yVal > 0) yVal *= -1;
			
		} else if(wallId.compareToIgnoreCase("bottom") == 0){
            if(yVal < 0) yVal *= -1;

		} else if(wallId.compareToIgnoreCase("left") == 0){
            if(xVal > 0) xVal *= -1;

		} else if(wallId.compareToIgnoreCase("right") == 0){
            if(xVal < 0) xVal *= -1;
		}
        
    }

    /**
     Método que verifica se houve colisão da bola com uma parede.

     @param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
     @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
     */

    public boolean checkCollision(Wall wall){

        double wallb = cy - 10;
        double wallt = cy + 10;
        double ballL = cx - 10;
        double ballR = cx + 10; 
        double aux;

        if(wall.getId().compareToIgnoreCase("top") == 0){
            aux = wall.getCy() + (wall.getHeight() / 2);
            if(wallb <= aux) return true;
        }

        if(wall.getId().compareToIgnoreCase("bottom") == 0){
            aux = wall.getCy() - (wall.getHeight() / 2);
            if(wallt >= aux) return true;
        }

        if(wall.getId().compareToIgnoreCase("left") == 0){
			aux = wall.getCx() + (wall.getWidth() / 2);
			if(ballL <= aux) return true;
			
        }

        if(wall.getId().compareToIgnoreCase("right") == 0){
			aux = wall.getCx() - (wall.getWidth() / 2);
			if(ballR >= aux) return true;
		}

        return false;
    }

    /**
     Método que verifica se houve colisão da bola com um jogador.

     @param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
     @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
     */

    public boolean checkCollision(Player player){

        double ballTop = cy - 10;
		double ballBottom = cy + 10;

		double ballLeft = cx - 10;
		double ballRight = cx + 10;

		double playerTop = player.getCy() - 50;
		double playerBottom = player.getCy() + 50;

		double playerLeft = player.getCx() - 10;
		double playerRight = player.getCx() + 10;


		boolean rightColision = ballLeft <= playerRight;
		boolean leftColision = ballRight >= playerLeft;

		boolean isBallBetweenPlayerTopAndBottom = ballBottom >= playerTop && ballTop <= playerBottom;

		return rightColision && leftColision && isBallBetweenPlayerTopAndBottom;
    }

    /**
     Método que devolve a coordenada x do centro do retângulo que representa a bola.
     @return o valor double da coordenada x.
     */

    public double getCx(){
        return cx;
    }

    /**
     Método que devolve a coordenada y do centro do retângulo que representa a bola.
     @return o valor double da coordenada y.
     */

    public double getCy(){
        return cy;
    }

    /**
     Método que devolve a velocidade da bola.
     @return o valor double da velocidade.

     */

    public double getSpeed(){
        return speed;
    }

}
