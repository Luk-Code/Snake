import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SnakeTest {
    @BeforeEach
    public void initEach(){

    }

    @Test
    public void snakeShouldGrowWhenEat(){
        Snake snake = new Snake();
        Apple apple = new Apple();

        snake.eat(apple);

        assertEquals(snake.body.size(), snake.startWith + apple.getKcal() + 1);
    }

    @Test
    public void snakeShouldMove(){
        Snake snake = new Snake();
        Snake snake2 = new Snake();

        snake.directionY = -5;
        snake.move();
        snake.directionY = 0;
        snake.directionX = 5;
        snake.move();

        for (int i = snake.startWith; i>1;i--){
            assertEquals(snake.body.get(i).getX(), snake2.body.get(i).father.father.getX());
            assertEquals(snake.body.get(i).getY(), snake2.body.get(i).father.father.getY());
        }
        assertEquals(snake.getPosition().getX(), snake2.getPosition().getX() + 5);
        assertEquals(snake.getPosition().getY(), snake2.getPosition().getX() - 5);
    }
}
