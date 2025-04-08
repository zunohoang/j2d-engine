package engine.graphics.animation;

import engine.components.Animation;
import engine.components.Animations;
import engine.localdata.RepositoryService;
import engine.utils.JsonManager;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimationRefactor {

    private static AnimationRefactor instance;

    public static AnimationRefactor getInstance() {
        if (instance == null) {
            instance = new AnimationRefactor();
        }
        return instance;
    }

    public AnimationRefactor() {
    }

    public static Animations loadAnimation(String animationName) {
        Animations animations = new Animations();

        JsonManager.JsonObject jsonObjects = RepositoryService.getInstance().getObjectInRepository("characters");
        if (jsonObjects == null) {
            System.out.println("Animation not found");
            return null;
        }

        JsonManager.JsonObject dataNode = jsonObjects.getJsonObject("data");
        if (dataNode == null) {
            Logger.log(AnimationRefactor.getInstance(), "Data node not found in animation: " + animationName, LOG_TYPE.ERROR);
            return null;
        }

        JsonManager.JsonArray charactersNode = dataNode.getJsonArray("characters");
        if (charactersNode == null) {
            Logger.log(AnimationRefactor.getInstance(), "Characters array not found in animation: " + animationName, LOG_TYPE.ERROR);
            return null;
        }

        for (int i = 0; i < charactersNode.size(); i++) {
            JsonManager.JsonObject characterNode = charactersNode.getJsonObject(i);

            if(!Objects.equals(characterNode.getString("name"), animationName)) {
                continue;
            }

            String name = characterNode.getString("name");
            JsonManager.JsonArray animationsNode = characterNode.getJsonArray("animations");
            if (animationsNode == null) {
                Logger.log(AnimationRefactor.getInstance(), "Animations node not found in character: " + name, LOG_TYPE.ERROR);
                return null;
            }


            for(int j = 0; j < animationsNode.size(); j++) {
                JsonManager.JsonObject animationNode = animationsNode.getJsonObject(j);
                String animationNameNode = animationNode.getString("name");
                int loop = animationNode.getInt("loop");
                JsonManager.JsonArray pathsNode = animationNode.getJsonArray("paths");
                if (pathsNode == null) {
                    Logger.log(AnimationRefactor.getInstance(), "Paths array not found in animation: " + animationNameNode, LOG_TYPE.ERROR);
                    return null;
                }

                List<String> paths = new ArrayList<>();

                for (int k = 0; k < pathsNode.size(); k++) {
                    String path = pathsNode.getString(k);
                    paths.add(path);
                }

                animations.addAnimation(animationNameNode, new Animation(paths), loop);
            }

        }

        return animations;
    }

}
