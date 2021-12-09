package mygame;
 
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
 
public class Main extends SimpleApplication implements ActionListener {
 
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    private Spatial player;
    private long bulletCooldown = 5;
    private Spatial bullet;
    private float fault = 30f;


    @Override
    public void simpleInitApp() {

           //        setup camera for 2D games
        cam.setParallelProjection(true);
        cam.setLocation(new Vector3f(0,0,0.5f));
        getFlyByCamera().setEnabled(false);
 
//        turn off stats view (you can leave it on, if you want)
        setDisplayStatView(false);
        setDisplayFps(false);
        
        //        setup the player
        player = getSpatial("Player");
        player.setUserData("alive",true);
        player.move(settings.getWidth()/2, settings.getHeight()/2, 0);
        guiNode.attachChild(player);
        
        bullet = getSpatial("Bullet");
        bullet.move(settings.getWidth()/10, settings.getHeight()/10, 0);

        guiNode.attachChild(bullet);
    }
    
    private Spatial getSpatial(String name) {
        Node node = new Node(name);
//        load picture
        Picture pic = new Picture(name);
        Texture2D tex = (Texture2D) assetManager.loadTexture("Textures/"+name+".png");
        pic.setTexture(assetManager,tex,true);
 
//        adjust picture
        float width = tex.getImage().getWidth();
        float height = tex.getImage().getHeight();
        pic.setWidth(width);
        pic.setHeight(height);
        pic.move(-width/2f,-height/2f,0);
 
//        add a material to the picture
        Material picMat = new Material(assetManager, "Common/MatDefs/Gui/Gui.j3md");
        picMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.AlphaAdditive);
        node.setMaterial(picMat);
 
//        set the radius of the spatial
//        (using width only as a simple approximation)
        node.setUserData("radius", width/2);
 
//        attach the picture to the node and return it
        node.attachChild(pic);
        return node;
    }
    
    private Vector3f getCursorDirection() {
        Vector2f mouse = inputManager.getCursorPosition();
        Vector3f playerPos = player.getLocalTranslation();
        if(Math.abs(mouse.x - playerPos.x) > fault || Math.abs(mouse.y - playerPos.y) > fault){
            return new Vector3f(mouse.x-playerPos.x,mouse.y-playerPos.y,0).normalizeLocal();

        } else {
            player.getControl(PlayerControl.class).setEnabled(false);
            player.removeControl(PlayerControl.class);
            return new Vector3f(0, 0, 0).normalizeLocal();
        }        
    }
    
        
    private Vector3f getPlayerDirection() {
        Vector3f playerPos = player.getLocalTranslation();
        Vector3f bulletPos = bullet.getLocalTranslation();

        if(Math.abs(playerPos.x - bulletPos.x) > fault || Math.abs(playerPos.y - bulletPos.y) > fault){
            return new Vector3f(playerPos.x - bulletPos.x,playerPos.y - bulletPos.y,0).normalizeLocal();

        } else {
            System.out.println("Game over");
            System.exit(0);
        }    
        
        return new Vector3f(0, 0, 0).normalizeLocal();
    }

    
    @Override
    public void simpleUpdate(float tpf) {
        Vector3f bulletAim = getPlayerDirection();
        Vector3f playerAim = getCursorDirection();

        
        System.out.println(playerAim);
        PlayerControl pControl = new PlayerControl(playerAim, settings.getWidth(), settings.getHeight());
        player.addControl(pControl);
//        pControl.setEnabled(false);
        BulletControl bControl = new BulletControl(bulletAim, settings.getWidth(), settings.getHeight());
        bullet.addControl(bControl);
//        bControl.setEnabled(false);

    }
 
    @Override
    public void simpleRender(RenderManager rm) {
 
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
    }
    
}