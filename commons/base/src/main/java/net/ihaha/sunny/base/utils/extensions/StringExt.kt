package net.ihaha.sunny.base.exception

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.util.regex.Pattern

//private const val phoneNumberPattern = "^\\s*(010|011|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$"
private const val newPhoneNumberPattern = "^[0-9]{3}[0-9]{4}[0-9]{4}$"
private const val oldPhoneNumberPattern = "^[0-9]{3}[0-9]{3}[0-9]{4}$"
private const val pattern = "yyyy/MM/dd hh:mm"
private const val passwordPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,}$"

fun String?.invalidPhoneNumber(): Boolean = this?.trim()?.let { newPhoneNumberPattern.toRegex().matches(it) || oldPhoneNumberPattern.toRegex().matches(it) } ?: false

fun String?.invalidEmail(): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String?.invalidEmailToCallingUserAccounts(): Boolean = EMAIL_ADDRESS.matcher(this).matches()

fun String?.invalidPassword(): Boolean = this?.trim()?.let { passwordPattern.toRegex().matches(it) } ?: false


val EMAIL_ADDRESS = Pattern.compile(
     "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+\\**]{1,256}" +
     "\\@" +
     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
     "(" +
     "\\." +
     "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
     ")+"
)
// endregion

//fun String.changeMonth(): String = if (this.length == 1) "0$this" else this

// "달, 일"을 가져와 1~9월이면 01~09 월로, 10~12 월이면 10~12월로 변경(일도 마찬가지)
fun String.plusZero(monthOfYear: Int, dayOfMonth: Int): String {
    var month = (monthOfYear + 1).toString()
    month = if(month.length == 1) "0$month" else month

    var day = dayOfMonth.toString()
    day = if(day.length == 1) "0$day" else day

    return "$this-$month-$day"
}

// html 추가
fun String.toSpanned(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY) else @Suppress("DEPRECATION") Html.fromHtml(this)

