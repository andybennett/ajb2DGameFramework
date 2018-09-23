package ajb.game;

import ajb.interfaces.Loop;

public class GameLoop {

    private boolean gameRunning = true;
    private long lastFpsTime;
    private int fps;

    private Loop game;

    public GameLoop(Loop game) {
        
        this.game = game;
        
    }

    public void go() {

        long lastLoopTime = System.nanoTime();

        final int TARGET_FPS = 60;

        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        // keep looping round until the game ends
        while (gameRunning) {
            
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should move this
            // loop
            long now = System.nanoTime();

            long updateLength = now - lastLoopTime;

            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;

            fps++;

            // update our FPS counter if a second has passed since we last
            // recorded
            if (lastFpsTime >= 1000000000) {
                
                lastFpsTime = 0;
                fps = 0;
                
            }

            // update the game logic
            game.doLogic(delta);

            // draw everything
            game.render();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds

            // to this and then factor in the current time to give
            // us our final value to wait for

            // remember this is in ms, whereas our lastLoopTime etc. vars are in
            // ns.

            try {
                
            	long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(sleepTime > 0 ? sleepTime : 0);
                
            } catch (InterruptedException e) {
                
                e.printStackTrace();
                
            }
            
        }
        
    }

    public boolean isGameRunning() {
        
        return gameRunning;
        
    }

    public void setGameRunning(boolean gameRunning) {
        
        this.gameRunning = gameRunning;
        
    }

    public int getFps() {
        
        return fps;
        
    }
}
