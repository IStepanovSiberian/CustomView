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
import timber.log.Timber

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
        set(value) {
            Timber.tag("            └── Lifecycle: UserAvatar: set IMAGE value").d(value.toString())
            field = value
            if (value != 0) {
                background = ContextCompat.getDrawable(context, value)
                foreground = null
            } else {
                background = null
            }
            Timber.tag("            ├── Lifecycle: UserAvatar").e("INVALIDATE BEGIN")
            invalidate()
            Timber.tag("            ├── Lifecycle: UserAvatar").e("INVALIDATE END")
            Timber.tag("            ├── Lifecycle: UserAvatar").e("REQUEST_LAYOUT BEGIN")
            requestLayout()
            Timber.tag("            ├── Lifecycle: UserAvatar").e("REQUEST_LAYOUT END")
        }

    var name: String? = null
        set(value) {
            Timber.tag("            ├── Lifecycle: UserAvatar: set NAME value").d(value.toString())
            field = value
            if (background == null || image == 0) {
                setBackgroundColor((Math.random() * 16777215).toInt() or (0xFF shl 24))
                foreground = when {
                    value.isNullOrBlank() || value.length < 2 -> getTextDrawable("><")
                    value.contains(' ') -> getTextDrawable(
                        value.split(" ").joinToString("") {
                            it.uppercase().first().toString()
                        }
                    )
                    else -> getTextDrawable(value.slice(0..2))
                }
            }
        }

    init {
        Timber.tag("            └── Lifecycle: UserAvatar").d("init")
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
                canvas.drawText(
                    text,
                    (canvas.width / 2).toFloat(),
                    (canvas.height / 2).toFloat() - ((paint.descent() + paint.ascent()) / 2),
                    paint.apply {
                        color = Color.WHITE
                        textSize = (canvas.width / 2).toFloat()
                        typeface = Typeface.DEFAULT_BOLD
                        textAlign = Paint.Align.CENTER
                    }
                )
            }
        }
        )
    }

    override fun onAttachedToWindow() {
        Timber.tag("            └── Lifecycle: UserAvatar").d("ImageView - onAttachedToWindow")
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Timber.tag("            └── Lifecycle: UserAvatar").d("ImageView - onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Timber.tag("            ├── Lifecycle: UserAvatar").d("View - layout")
        super.layout(l, t, r, b)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Timber.tag("            └── Lifecycle: UserAvatar").d("View - onLayout")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        Timber.tag("            └── Lifecycle: UserAvatar").d("View - dispatchDraw")
        super.dispatchDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        Timber.tag("            ├── Lifecycle: UserAvatar").d("ImageFilterView - draw")
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        Timber.tag("            ├── Lifecycle: UserAvatar").d("ImageView - onDraw")
        super.onDraw(canvas)
    }

    override fun requestLayout() {
        Timber.tag("            └── Lifecycle: UserAvatar").d("View - requestLayout")
        super.requestLayout()
    }
}
