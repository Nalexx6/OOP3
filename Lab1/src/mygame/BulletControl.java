/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author moleksiienko
 */
public class BulletControl extends AbstractControl {
    private int screenWidth, screenHeight;
 
    private float speed = 400f;
    public Vector3f direction;
    private float rotation;
 
    public BulletControl(){
        
    }
    
    public BulletControl(Vector3f direction, int screenWidth, int screenHeight) {
        this.direction = direction;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
 
    @Override
    protected void controlUpdate(float tpf) {
//        movement
        spatial.move(direction.mult(speed*tpf));
 
//        rotation
        float actualRotation = Main.getAngleFromVector(direction);
        if (actualRotation != rotation) {
            spatial.rotate(0,0,actualRotation - rotation);
            rotation = actualRotation;
        }
 
//        check boundaries
        Vector3f loc = spatial.getLocalTranslation();
        if (loc.x > screenWidth || 
            loc.y > screenHeight ||
            loc.x < 0 ||
            loc.y < 0) {
            spatial.removeFromParent();
        }
    }
 
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
}
