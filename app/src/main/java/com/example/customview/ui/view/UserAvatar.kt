package com.example.customview.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.content.ContextCompat
import com.example.customview.R

class UserAvatar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ImageFilterView(context, attrs) {
    private val rotate = RotateAnimation(
        0F, 359F,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f
    ).apply {
        duration = 1000
        repeatCount = Animation.ABSOLUTE
    }
    var image: Int = 0
        set(image) {
            field = image
            if (image != 0) {
                background = ContextCompat.getDrawable(context, image)
                foreground = null
            }
            invalidate()
            requestLayout()
        }

    var name: String? = null
        set(value) {
            field = value
            if (background == null || image == 0) {
                setBackgroundColor((Math.random() * 16777215).toInt() or (0xFF shl 24))
                foreground = when {
                    value.isNullOrBlank() || value.length < 2 -> getTextDrawable("><")
                    value.contains(' ') -> getTextDrawable(
                        value
                            .split(" ")
                            .joinToString("") {
                                it.uppercase().first().toString()
                            }
                    )
                    else -> getTextDrawable(value.slice(0..2))
                }

                invalidate()
                requestLayout()
            }
        }

    init {
        isClickable = true

        val ats = context.theme.obtainStyledAttributes(attrs, R.styleable.UserAvatar, 0, 0)
        image = ats.getResourceId(R.styleable.UserAvatar_image, 0)
        name = ats.getString(R.styleable.UserAvatar_name)
        ats.recycle()

        setOnClickListener() { startAnimation(rotate) }
    }

    private fun getTextDrawable(text: String): Drawable {
        return ShapeDrawable(object : Shape() {
            override fun draw(canvas: Canvas, paint: Paint) {
                paint.color = Color.WHITE
                paint.textSize = (canvas.width / 2).toFloat()
                paint.typeface = Typeface.DEFAULT_BOLD
                paint.textAlign = Paint.Align.CENTER
                canvas.drawText(
                    text,
                    (canvas.width / 2).toFloat(),
                    (canvas.height / 2).toFloat() - ((paint.descent() + paint.ascent()) / 2),
                    paint
                )
            }
        }
        )
    }
}
