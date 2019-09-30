package com.ray3k.plainjames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Core extends ApplicationAdapter {
    private Skin skin;
    private Stage stage;
    private static final String LONG_TEXT = "It became apparent to my eye that this uncouth, no-nonsense, haggler was determined and set in his ways. I said \"Not in my wheelhouse, you joker.\" And it was thus that I was identified as a hero of my people. A champion to be celebrated in the streets. The barriers were torn asunder and the power ripped through the street corner like a flash of almighty gorilla presence in the lap of luxury. All hail the great Gahula and their majestic bird prophecy. Fear the fearerator. Smell the smelliest smells. Join the Corps of Army Flute Players. The subtle, yet distinctive taste of defeat has enriched your mouth. Such endless drivel would only drive one mad. Two... two would be too much. But three is where it's at.";

    @Override
    public void create () {
        skin = new Skin(Gdx.files.internal("plain-james-ui/plain-james-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
    
        root.pad(20);
        Table table = new Table();
        root.add(table);
        
        table.defaults().space(25);
        TextButton textButton = new TextButton("Plain", skin);
        table.add(textButton);
        
        textButton = new TextButton("James", skin, "toggle");
        table.add(textButton);
    
        textButton = new TextButton("User Interface", skin, "special");
        table.add(textButton);
        
        root.row();
        CheckBox checkBox = new CheckBox("A new dimension in vanilla", skin);
        root.add(checkBox);
        
        root.row();
        Label label = new Label("Enjoy the thrill of nothing special!", skin);
        root.add(label);
        
        root.row();
        label = new Label("Lavish yourself in the bounty of pedestrian.", skin, "title");
        label.setWrap(true);
        root.add(label).growX();
        
        root.row();
        table = new Table();
        root.add(table);
        
        table.defaults().space(25);
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems("This is", "A monument", "To ugly", "User experience");
        table.add(selectBox);
        
        ProgressBar progressBar = new ProgressBar(0, 100,  1, false, skin);
        table.add(progressBar);
        
        Slider slider = new Slider(0, 100, 1, false, skin);
        table.add(slider);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                progressBar.setValue(slider.getValue());
            }
        });
        
        root.row();
        label = new Label(LONG_TEXT, skin);
        label.setWrap(true);
        Label label1 = new Label(LONG_TEXT, skin);
        label1.setWrap(true);
        SplitPane splitPane = new SplitPane(label, label1, false, skin);
        
        ScrollPane scrollPane = new ScrollPane(splitPane, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFlickScroll(false);
        root.add(scrollPane).growX();
        
        root.row();
        table = new Table();
        root.add(table);
        
        table.defaults().space(25);
        TextField textField = new TextField("", skin);
        table.add(textField);
        
        Touchpad touchpad = new Touchpad(0, skin);
        table.add(touchpad);
    }
    
    @Override
    public void render () {
        Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        stage.draw();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            dispose();
            create();
        }
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }
}
