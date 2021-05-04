package io.github.turskyi.daysleft

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [DateAppWidgetConfigureActivity]
 */
class DateAppWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

//        for (appWidgetId in appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId)
//            Toast.makeText(
//                context, "onUpdate(): $appWidgetId : $appWidgetId", Toast.LENGTH_LONG
//            ).show()
//        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            deleteTitlePref(context, appWidgetId)
        }
        Toast.makeText(context, "onDeleted()", Toast.LENGTH_LONG).show()
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        Toast.makeText(context, "onEnabled()", Toast.LENGTH_LONG).show()
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        Toast.makeText(context, "onDisabled()", Toast.LENGTH_LONG).show()
    }

    private val formatter: SimpleDateFormat = SimpleDateFormat(
        "dd MMM yyyy  hh:mm:ss a",
        Locale.getDefault()
    )
    var sWidgetText = ""
    fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val currentTime = formatter.format(Date())
        sWidgetText = currentTime
        val widgetText = loadTitlePref(context, appWidgetId)
        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.date_app_widget)
        views.setTextViewText(
            R.id.appwidget_text,
            "[$appWidgetId widget id]\n$widgetText\n$sWidgetText"
        )

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)

        Toast.makeText(
            context,
            "updateAppWidget(): $appWidgetId\n$sWidgetText", Toast.LENGTH_LONG
        ).show()
    }
}

