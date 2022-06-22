package com.example.customview.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.R
import com.example.customview.databinding.MessagePreViewBinding

class MessagePreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: MessagePreViewBinding

    var username: String? = null
        set(value) {
            field = value
            binding.messageUsername.text = value
            invalidate()
            forceLayout()
        }

    var message: String? = null
        set(value) {
            field = value
            binding.messageText.text = value
            invalidate()
            requestLayout()
        }

    var timestamp: String? = null
        set(value) {
            field = value
            binding.messageTimestamp.text = value
            invalidate()
            requestLayout()
        }

    var avatar: Int = 0
        set(value) {
            field = value
            with(binding.messageAvatar) {
                name = username
                image = value
            }
            invalidate()
            requestLayout()
        }

    init {
        binding = MessagePreViewBinding.inflate(LayoutInflater.from(context), this)
        val ats = context.obtainStyledAttributes(attrs, R.styleable.MessagePreView)
        username = ats.getString(R.styleable.MessagePreView_username)
        message = ats.getString(R.styleable.MessagePreView_message)
        timestamp = ats.getString(R.styleable.MessagePreView_time)
        avatar = R.styleable.MessagePreView_avatar
        ats.recycle()
    }
}
