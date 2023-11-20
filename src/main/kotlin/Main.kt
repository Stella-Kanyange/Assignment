fun convertNumberToWords(number: Int): String {
    if (number < 0 || number >= 1000000) {
        return "Number out of range (0-999999)"
    }

    val units = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
    val teens = arrayOf("Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
    val tens = arrayOf("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

    fun convertBelowThousand(num: Int): String {
        return when {
            num < 10 -> units[num]
            num in 11..19 -> teens[num - 11]
            num < 100 -> tens[num / 10] + if (num % 10 != 0) " " + units[num % 10] else ""
            num < 1000 -> units[num / 100] + " Hundred" + if (num % 100 != 0) " and " + convertBelowThousand(num % 100) else ""
            else -> convertBelowThousand(num / 1000) + " Thousand" + if (num % 1000 != 0) " " + convertBelowThousand(num % 1000) else ""
        }
    }

    return if (number == 0) "Zero" else convertBelowThousand(number).trim()
}

fun main() {
    print("Enter a number less than a million: ")
    val userInput = readLine()

    try {
        val number = userInput?.toInt() ?: 0
        val result = convertNumberToWords(number)
        println("$number in words: $result")
    } catch (e: NumberFormatException) {
        println("Invalid input. Please enter a valid number.")
    }
}