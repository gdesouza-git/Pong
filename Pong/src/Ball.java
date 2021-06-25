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
    private double xVal;
    private double yVal;
    private boolean temp;

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
     @param xVal determina se x é positivo ou negativo, modificando sua direção.
     @param yVal determina se y é positivo ou negativo, modificando sua direção.
     @param temp informa se o método onPlayerCollision foi o último método a ser invocado em relação a onWallCollision.
     */

    public Ball(double cx, double cy, double width, double height, Color color, double speed){
         this.cx = cx;
         this.cy = cy;
         this.width = width;
         this.height = height;
         this.color = color;
         this.speed = speed;
         this.xVal = 1;
         this.yVal = -1;
         this.temp = false;
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
        
        this.cx -= xVal * speed * delta * 0.7;
        this.cy -= yVal * speed * delta * 0.7;
        
    }

    /**
     Método chamado quando detecta-se uma colisão da bola com um jogador.

     @param playerId uma string cujo conteúdo identifica um dos jogadores.
     */

    public void onPlayerCollision(String playerId){
        
        if(temp) return;
        temp = true;

        xVal *= -1; //Muda direção de x.

    }
    

    /**
     Método chamado quando detecta-se uma colisão da bola com uma parede.

     @param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
     */

    public void onWallCollision(String wallId){

        temp = false;
        if(wallId.compareToIgnoreCase("top") == 0) yVal = -1;
		else if(wallId.compareToIgnoreCase("bottom") == 0) yVal = 1;
		else if(wallId.compareToIgnoreCase("left") == 0) xVal = -1;
		else if(wallId.compareToIgnoreCase("right") == 0) xVal = 1;
		
        
    }

    /**
     Método que verifica se houve colisão da bola com uma parede.

     @param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
     @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
     */

    public boolean checkCollision(Wall wall){

        double aux = 0;

        //Medidas paredes:
        double wallb = cy - 10;
        double wallt = cy + 10;

        //Medidas bola:
        double ballL = cx - 10;
        double ballR = cx + 10; 
        

        if(wall.getId().compareToIgnoreCase("left") == 0){
            aux = wall.getCx() + (wall.getWidth()/2);
            return(aux >= ballL);
			
        }

        if(wall.getId().compareToIgnoreCase("right") == 0){
            aux = wall.getCx() - (wall.getWidth()/2);
            return(aux <= ballR);
		}

        if(wall.getId().compareToIgnoreCase("top") == 0){
            aux = wall.getCy() + (wall.getHeight()/2);
            return(aux >= wallb);
        }

        if(wall.getId().compareToIgnoreCase("bottom") == 0){
            aux = wall.getCy() - (wall.getHeight()/2);
            return(aux <= wallt);
        }

        return false;
    }

    /**
     Método que verifica se houve colisão da bola com um jogador.

     @param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
     @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
     */

    public boolean checkCollision(Player player){

        //Medidas do player
        double pTop = player.getCy() - 50;
        double pBottom = player.getCy() + 50;
        double pLeft = player.getCx() - 10;
        double pRight = player.getCx() + 10;

        //Medidas da bola
        double bTop = cy - 10;
		double bBottom = cy + 10;
		double bLeft = cx - 10;
		double bRight = cx + 10;


		if(bLeft > pRight || bRight < pLeft) return false;
        else if(bBottom >= pTop && bTop <= pBottom) return true;
        return false;
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
