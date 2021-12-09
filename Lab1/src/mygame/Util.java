/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 *
 * @author moleksiienko
 */
public class Util {
    public static float getAngleFromVector(Vector3f vec) {
    Vector2f vec2 = new Vector2f(vec.x,vec.y);
    return vec2.getAngle();
    }
 
    public static Vector3f getVectorFromAngle(float angle) {
        return new Vector3f(FastMath.cos(angle),FastMath.sin(angle),0);
    }
 
}
