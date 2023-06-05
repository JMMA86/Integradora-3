package com.nt.throne.model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance() {
        if(instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    private FileManager() {

    }

    public ArrayList<ArrayList<Image>> loadSprites(String name) {
        File characterDir = new File( System.getProperty("user.dir") + "/src/main/resources/com/nt/throne/SpriteSheets/" + name);

        if(!characterDir.exists() || !characterDir.isDirectory()) return new ArrayList<>();

        ArrayList<ArrayList<Image>> sprites = new ArrayList<>(); // each element consists of a sequence, that is, a state
        for(File f : Objects.requireNonNull(characterDir.listFiles())) {
            ArrayList<Image> images = new ArrayList<>(); // each element is the frame of a specific state
            for(File g : Objects.requireNonNull(f.listFiles())) {
                images.add( new Image(g.getAbsolutePath()) );
            }
            sprites.add(images);
        }
        return sprites;

    }

}
