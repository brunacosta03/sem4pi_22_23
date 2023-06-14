package org.shared.board.server;


import java.util.HashMap;
import java.util.Map;

public class BoardSynchronizer {

    private static BoardSynchronizer instance = null;
    Map<String, Object> lockObjects = new HashMap<>();

    public static BoardSynchronizer getInstance(){
        if(instance == null){
            instance = new BoardSynchronizer();
        }

        return instance;
    }

    public synchronized Object getOrCreateLockObject(String lockKey) {
        return lockObjects.computeIfAbsent(lockKey, k -> new Object());
    }

}
