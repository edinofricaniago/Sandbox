package com.distraction.sandbox

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.math.Vector3

class Utils {
    companion object {
        fun abs(f: Float) = if (f < 0) f * -1 else f
        fun dist(x1: Float, y1: Float, x2: Float, y2: Float) = Math.sqrt(1.0 * (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)).toFloat()
        fun max(f1: Float, f2: Float) = if (f2 > f1) f2 else f1
    }
}

fun AssetManager.getAtlas(str: String = "sandboxpack.atlas") = get(str, TextureAtlas::class.java)

fun log(str: String) = Gdx.app.log("tag", str)

inline fun SpriteBatch.use(action: () -> Unit): Unit {
    begin()
    action()
    end()
}

fun clearScreen(r: Int = 255, g: Int = 255, b: Int = 255, a: Int = 255) {
    Gdx.gl.glClearColor(r / 255f, g / 255f, b / 255f, a / 255f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
}

fun Vector3.lerp(x: Float, y: Float, z: Float, amount: Float): Vector3 {
    this.x += amount * (x - this.x)
    this.y += amount * (y - this.y)
    this.z += amount * (z - this.z)
    return this
}

fun loadFont(
        context: Context,
        ttf: String,
        size: Int = 12,
        chars: String = FreeTypeFontGenerator.DEFAULT_CHARS,
        color: Color = Color(1f, 1f, 1f, 1f),
        strokeColor: Color = Color(0f, 0f, 0f, 1f)) {
    context.assets.load(ttf, BitmapFont::class.java,
            FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
                fontFileName = ttf
                fontParameters.size = size
                fontParameters.characters = chars
                fontParameters.color = color
                fontParameters.borderColor = strokeColor
                fontParameters.borderWidth = 1f
                fontParameters.minFilter = Texture.TextureFilter.Nearest
                fontParameters.magFilter = Texture.TextureFilter.Nearest
                fontParameters.borderStraight = true
            })
}