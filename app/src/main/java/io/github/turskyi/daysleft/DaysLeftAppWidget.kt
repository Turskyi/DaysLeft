package io.github.turskyi.daysleft

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.text.format.DateUtils
import android.widget.RemoteViews
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class DaysLeftAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val today = Calendar.getInstance()
    val catDay: Calendar = GregorianCalendar(today.get(Calendar.YEAR), Calendar.MAY, 30)
    val days = (catDay.timeInMillis - today.timeInMillis) / (24 * 60 * 60 * DateUtils.SECOND_IN_MILLIS) + 1
    val widgetText = context.getString(R.string.appwidget_text, days)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.days_left_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}