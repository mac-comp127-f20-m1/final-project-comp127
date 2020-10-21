package GoldMiner;
import java.util.*;
import java.util.concurrent.*;

public class CountDown {
    private int limitSec;
	public CountDown(int limitSec) throws InterruptedException{
		this.limitSec = limitSec;
		System.out.println("Count from "+limitSec);
		while(limitSec > 0){
			System.out.println("remians "+ --limitSec +" s");
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println("Time is out, game over!");
	}

}
