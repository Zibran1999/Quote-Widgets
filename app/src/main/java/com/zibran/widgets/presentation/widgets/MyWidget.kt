package com.zibran.widgets.presentation.widgets

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontFamily
import androidx.glance.text.FontStyle
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.zibran.widgets.MainActivity
import com.zibran.widgets.R
import com.zibran.widgets.data.local.QuoteDatabase
import com.zibran.widgets.ui.theme.SkyBlue

object MyWidget : GlanceAppWidget() {
    val quote = stringPreferencesKey("quote")


    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            QuoteScreen()
        }
    }

    @Composable
    private fun QuoteScreen() {
        var quoteS = currentState(key = quote) ?: ""

        Box(
            modifier = GlanceModifier.fillMaxSize().background(GlanceTheme.colors.background)
                .clickable(onClick = actionStartActivity<MainActivity>())
        ) {
            Column(
                modifier = GlanceModifier.fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .background(imageProvider = ImageProvider(R.drawable.card_day)),
                verticalAlignment = Alignment.Vertical.CenterVertically,
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally
            ) {
                Log.d("LOG_TAG", quoteS)
                val startQuote = "\u275D " // Unicode for left stylish quote
                val endQuote = "\u275E" // Unicode for right stylish quote
                if (quoteS.isEmpty()) quoteS = "Why be normal, When you can be the best"
                Text(
                    text = " $startQuote $quoteS $endQuote ",
                    modifier = GlanceModifier.padding(10.dp), style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = GlanceModifier.height(16.dp))

            }
            Column(
                modifier = GlanceModifier.fillMaxWidth().padding(14.dp),
                horizontalAlignment = Alignment.End,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    colorFilter = ColorFilter.tint(ColorProvider(SkyBlue)),
                    provider = ImageProvider(R.drawable.refresh_icon),
                    contentDescription = null,
                    modifier = GlanceModifier.size(20.dp)
                        .clickable(onClick = actionRunCallback<ObjectIncrementCallBack>())
                )

            }


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
            val database = QuoteDatabase.getDBInstance(context)
            val dao = database.getQuoteDao()
            pref[MyWidget.quote] = dao.getQuotes().random().quote

        }
        MyWidget.update(context, glanceId)
    }

}
