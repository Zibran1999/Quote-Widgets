package com.zibran.widgets.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.zibran.widgets.presentation.widgets.MyWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CounterWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = MyWidget
}