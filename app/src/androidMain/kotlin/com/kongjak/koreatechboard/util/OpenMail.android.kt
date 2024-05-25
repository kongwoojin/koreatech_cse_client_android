package com.kongjak.koreatechboard.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

actual fun openMail(context: Any, address: String, subject: String, body: String, exception: (message: String) -> Unit) {
    val appContext = context as Context

    val mailIntent = Intent(Intent.ACTION_SENDTO)
    mailIntent.data = Uri.parse("mailto:")
    mailIntent.putExtra(
        Intent.EXTRA_EMAIL,
        arrayOf(address)
    )
    mailIntent.putExtra(
        Intent.EXTRA_SUBJECT,
        subject
    )
    mailIntent.putExtra(
        Intent.EXTRA_TEXT,
        body
    )
    try {
        appContext.startActivity(mailIntent)
    } catch (e: ActivityNotFoundException) {
        exception("Error opening mail client: ${e.message}")
    }
}
