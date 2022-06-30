package com.example.customview.ui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.R
import com.example.customview.databinding.MessagePreViewBinding
import timber.log.Timber

class MessagePreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: MessagePreViewBinding

    var username: String? = null
        set(value) {
            Timber.tag("    ├── Lifecycle: MessagePreView: set USERNAME value").d(value.toString())
            field = value
            binding.messageUsername.text = value
        }

    var message: String? = null
        set(value) {
            Timber.tag("    ├── Lifecycle: MessagePreView: set MESSAGE value").d(value.toString())
            field = value
            binding.messageText.text = value
        }

    var timestamp: String? = null
        set(value) {
            Timber.tag("    ├── Lifecycle: MessagePreView: set TIMESTAMP value").d(value.toString())
            field = value
            binding.messageTimestamp.text = value
        }

    var avatar: Int? = 0
        set(value) {
            Timber.tag("    └── Lifecycle: MessagePreView: set AVATAR value").d(value.toString())
            field = value
            with(binding.messageAvatar) {
                image = value ?: 0
                name = username
            }
        }

    init {
        Timber.tag("Lifecycle: MessagePreView").d("init")
        binding = MessagePreViewBinding.inflate(LayoutInflater.from(context), this)
        val ats = context.obtainStyledAttributes(attrs, R.styleable.MessagePreView)
        username = ats.getString(R.styleable.MessagePreView_username)
        message = ats.getString(R.styleable.MessagePreView_message)
        timestamp = ats.getString(R.styleable.MessagePreView_time)
        avatar = R.styleable.MessagePreView_avatar
        ats.recycle()
    }

    override fun onAttachedToWindow() {
        Timber.tag("    └── Lifecycle: MessagePreView").d("ViewGroup - onAttachedToWindow")
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Timber.tag("    └── Lifecycle: MessagePreView").d("ConstraintLayout - onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Timber.tag("    └── Lifecycle: MessagePreView").d("ConstraintLayout - onLayout")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        Timber.tag("    └── Lifecycle: MessagePreView").d("ConstraintLayout - dispatchDraw")
        super.dispatchDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        Timber.tag("    └── Lifecycle: MessagePreView").d("View - draw")
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        Timber.tag("    └── Lifecycle: MessagePreView").d("View - onDraw")
        super.onDraw(canvas)
    }
}
