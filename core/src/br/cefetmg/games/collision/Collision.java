package br.cefetmg.games.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Utilitário para verificação de colisão.
 *
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public class Collision {
    /**
     * Verifica se dois círculos em 2D estão colidindo.
     *
     * @param c1 círculo 1
     * @param c2 círculo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean circlesOverlap(Circle c1, Circle c2) {
        Vector2 center_1, center_2;
        center_1 = new Vector2(c1.x, c1.y);
        center_2 = new Vector2(c2.x, c2.y);
        double sum_radious = c1.radius + c2.radius;
        if (center_1.dst2(center_2) <= sum_radious * sum_radious) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se dois retângulos em 2D estão colidindo. Esta função pode
     * verificar se o eixo X dos dois objetos está colidindo e então se o mesmo
     * ocorre com o eixo Y.
     *
     * @param r1 retângulo 1
     * @param r2 retângulo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean rectsOverlap(Rectangle r1, Rectangle r2) {
        double xmax_r1 = r1.x + r1.width;
        double xmax_r2 = r2.x + r2.width;
        double ymax_r1 = r1.y + r1.height;
        double ymax_r2 = r2.y + r2.height;
        if ((r2.x >= r1.x && xmax_r2 <= xmax_r1) || (r2.x <= xmax_r1 && xmax_r2 >= xmax_r1)) {
            if ((r2.y >= r1.y && ymax_r2 <= ymax_r1) || (r2.y <= ymax_r1 && ymax_r2 >= ymax_r1)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean colliderChallenge1(Rectangle r1, Collidable c1){
        if(rectsOverlap(r1,c1.getMinimumBoundingRectangle()))
            return circleOverLapRects(c1.getMinimumEnclosingBall(), r1);
        return false;
    }
    
    public static final boolean circleOverLapRects(Circle c1, Rectangle r1) {
        Vector2 centerC, centerR, distanceX, distanceY, point;

        centerC = new Vector2(c1.x, c1.y);
        centerR = new Vector2(r1.x + r1.width / 2, r1.y + +r1.height / 2);

        distanceX = new Vector2(c1.x - r1.x, 0).nor();
        distanceY = new Vector2(c1.y - r1.y, 0).nor();

        point = distanceX.scl(r1.width / 2).add(distanceY.scl(r1.width / 2));
        point.add(centerR);

        return centerC.dst(point) <= c1.radius;
    }

}
