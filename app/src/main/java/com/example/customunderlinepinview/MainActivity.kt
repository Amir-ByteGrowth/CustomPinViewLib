package com.example.customunderlinepinview

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initSettings()
initPassCodePinViewSettings()
    }

    private fun initSettings() {
        val pinView = findViewById<PinView>(R.id.secondPinView)
        pinView.setTextColor(
            ResourcesCompat.getColor(resources, R.color.colorAccent, theme)
        )
        pinView.setTextColor(
            ResourcesCompat.getColorStateList(resources, R.color.text_colors, theme)
        )
        pinView.setLineColor(
            ResourcesCompat.getColor(resources, R.color.colorPrimary, theme)
        )
        pinView.setLineColor(
            ResourcesCompat.getColorStateList(resources, R.color.line_colors, theme)
        )
        pinView.itemCount = 6
        pinView.itemHeight = resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_size)
        pinView.itemWidth = resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_size)
        pinView.itemRadius = resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_radius)
        pinView.itemSpacing = resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing)
        pinView.lineWidth = resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width)
        pinView.setAnimationEnable(true) // start animation when adding text
        pinView.isCursorVisible = false
        pinView.cursorColor = ResourcesCompat.getColor(resources, R.color.line_selected, theme)
        pinView.cursorWidth = resources.getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width)
        pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d(
                    "TAG",
                    "onTextChanged() called with: s = [$s], start = [$start], before = [$before], count = [$count]"
                )
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        pinView.setItemBackgroundColor(Color.BLACK)
        pinView.setItemBackground(resources.getDrawable(R.drawable.item_background))
        pinView.setItemBackgroundResources(R.drawable.item_background)
        pinView.setHideLineWhenFilled(false)

        (findViewById<View>(R.id.password) as EditText).transformationMethod
//            AsteriskPasswordTransformationMethod()
    }

    private fun initPassCodePinViewSettings() {
        val passcodePinView = findViewById<PinView>(R.id.underLinePinView)
        passcodePinView.itemCount = 6

        passcodePinView.apply {

            setTextColor(ContextCompat.getColor(context, R.color.black))
            setLineColor(ContextCompat.getColor(context, R.color.under_line_color))
            cursorColor = (ContextCompat.getColor(context, R.color.black))
            itemWidth = resources.getDimensionPixelSize(R.dimen.line_width)
            inputType= InputType.TYPE_CLASS_NUMBER
            setAnimationEnable(true)

            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    Log.d(
                        "TAG",
                        "onTextChanged() called with: s = [$s], start = [$start], before = [$before], count = [$count]"
                    )
                }

                override fun afterTextChanged(s: Editable) {
                }
            })
        }
    }
}