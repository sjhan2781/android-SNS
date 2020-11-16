package com.example.sarang.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.sarang.R

class RoundImageView(context: Context, attrs:AttributeSet) :
    AppCompatImageView(context, attrs) {

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView,
            0, 0)
//        val symbols = a.getDrawable(R.styleable.RoundImageView_symbol)
//        this.setImageDrawable(symbols)
//        setImageResource(a.getResourceId(R.styleable.RoundImageView_symbol, R.drawable.profile_test))
        a.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        if(isInEditMode) {
            super.onDraw(canvas)
            return
        }

        val drawable = drawable ?: return

        if (width == 0 || height == 0) {
            return
        }
        val b = (drawable as BitmapDrawable).bitmap
        val bitmap = b.copy(Bitmap.Config.ARGB_8888, true)

        val w = width
        val h = height

        val roundBitmap: Bitmap = getRoundedCroppedBitmap(bitmap, w)
        canvas?.drawBitmap(roundBitmap, 0f, 0f, null)
    }

    private fun getRoundedCroppedBitmap(bitmap: Bitmap, radius: Int): Bitmap {
        val finalBitmap: Bitmap =
            if (bitmap.width != radius || bitmap.height != radius) Bitmap.createScaledBitmap(
                bitmap, radius, radius,
                false
            ) else bitmap
        val output = Bitmap.createBitmap(
            finalBitmap.width,
            finalBitmap.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(
            0, 0, finalBitmap.width,
            finalBitmap.height
        )
        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = Color.parseColor("#BAB399")
        canvas.drawCircle(
            finalBitmap.width / 2 + 0.7f,
            finalBitmap.height / 2 + 0.7f,
            finalBitmap.width / 2 + 0.1f, paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(finalBitmap, rect, rect, paint)
        return output
    }
}
