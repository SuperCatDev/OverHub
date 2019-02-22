package com.sc.overhub.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sc.overhub.R

class WikiListHeroesItemView(context: Context, attrs: AttributeSet): View(context, attrs) {

    private val polyPaint = Paint()
    private val polyTextPaint = Paint()
    private var polyPath = Path()
    private var polyPathText = Path()


    private val defaultWidth = resources.getDimensionPixelSize(R.dimen.framing_list_hero_default_width)
    private val defaultHeight = resources.getDimensionPixelSize(R.dimen.framing_list_hero_default_height)
    private val depth = 0.05f
    private val firstAngle = 0.275f
    private val secondAngle = 0.45f

    private val pointsFrame = listOf(
        0.0f to 1.0f,
        (firstAngle + 0.3f * depth) to 1.0f,
        (secondAngle + 0.3f * depth) to depth,
        1.0f to depth,
        1.0f to 0.0f,
        secondAngle to 0.0f,
        firstAngle to (1 - depth),
        0.0f to (1 - depth)
    )

    private val points2 = listOf(
        (firstAngle + 0.3f * depth) to 1.0f,
        1.0f to 1.0f,
        1.0f to depth,
        (secondAngle + 0.3f * depth) to depth
    )

    init {
        polyPaint.reset()
        polyPaint.isAntiAlias = true
        polyPaint.color = Color.rgb(0, 85, 200)
        polyPaint.style = Paint.Style.FILL
        polyPaint.flags = Paint.ANTI_ALIAS_FLAG
        polyPaint.setShadowLayer(5.0f, 7.0f, 7.0f, Color.GRAY);
        setLayerType(LAYER_TYPE_SOFTWARE, polyPaint)

        polyTextPaint.isAntiAlias = true
        polyTextPaint.color = Color.rgb(132, 173, 255)
        polyTextPaint.style = Paint.Style.FILL

        setLayerType(LAYER_TYPE_SOFTWARE, polyTextPaint)
    }

    fun setHexColor(borderHexColor: String, backgroundHexColor: String) {
        polyPaint.color = Color.parseColor(borderHexColor)
        polyTextPaint.color = Color.parseColor(backgroundHexColor)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            View.MeasureSpec.EXACTLY -> widthSize
            View.MeasureSpec.AT_MOST -> defaultWidth
            View.MeasureSpec.UNSPECIFIED -> defaultWidth
            else -> defaultWidth
        }

        val height = when (heightMode) {
            View.MeasureSpec.EXACTLY -> heightSize
            View.MeasureSpec.AT_MOST -> defaultHeight
            View.MeasureSpec.UNSPECIFIED -> defaultHeight
            else -> defaultHeight
        }

        setMeasuredDimension(width, height)
    }

    private fun drawPolygon(points: List<Pair<Float, Float>>, path: Path, paint: Paint, canvas: Canvas) {
        path.moveTo(points[0].first * width, points[0].second * height)
        for (point in points) {
            path.lineTo(point.first * width, point.second * height)
        }
        path.moveTo(points[0].first * width, points[0].second * height)
        canvas.drawPath(path, paint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPolygon(points2, polyPathText, polyTextPaint, canvas)
        drawPolygon(pointsFrame, polyPath, polyPaint, canvas)

    }
}