package com.zibran.widgets.presentation.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text

object MyWidget : GlanceAppWidget() {
    val countKey = intPreferencesKey("count")

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            CounterScreen()
        }
    }

    @Composable
    private fun CounterScreen() {
        val count = currentState(key = countKey) ?: 0
        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(GlanceTheme.colors.background),
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
        ) {

            Text(text = count.toString())
            Button(text = "Count", onClick = actionRunCallback<ObjectIncrementCallBack>())

        }
    }
}

class ObjectIncrementCallBack : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { pref ->
            val currentCount = pref[MyWidget.countKey]
            if (currentCount != null) {
                pref[MyWidget.countKey] = currentCount + 1
            } else {
                pref[MyWidget.countKey] = 1
            }
        }
        MyWidget.update(context, glanceId)
    }

}