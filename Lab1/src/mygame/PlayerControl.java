/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author moleksiienko
 */
public class PlayerControl extends AbstractControl {
    private int screenWidth, screenHeight;
 
//    is the player currently moving?
    public Vector3f direction;
    private float rotation;
//    speed of the player
    private float speed = 5e-2f;
//    lastRotation of the player
 
    public PlayerControl(Vector3f direction, int width, int height) {
//        System.out.println(direction);
        this.direction = direction;
        this.screenWidth = width;
        this.screenHeight = height;
    }
 
    @Override
    protected void controlUpdate(float tpf) {
//        move the player in a certain direction
//        if he is not out of the screen
        Vector3f loc = spatial.getLocalTranslation();
        spatial.move(direction.mult(speed * tpf));

//        check boundaries
        if (loc.x > screenWidth || 
            loc.y > screenHeight ||
            loc.x < 0 ||
            loc.y < 0) {
            spatial.move(-loc.x + 3 * screenWidth / 4, -loc.y + 3 * screenHeight / 4, 0);
            reset();
//            spatial.removeFromParent();
        }
        
    
    }
 
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
 
//    reset the moving values (i.e. for spawning)
    public void reset() {
        direction = new Vector3f(0, 0, 0);
    }
}
