import java.awt.Color
import java.util.*
fun colorines() {
    // https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
    // Códigos de formato
    val reset = "\u001B[0m"
    val box = "\u001b[51m"
    val bold = "\u001b[1m"
    val italic = "\u001b[3m"
    val underline = "\u001b[21m"
    // Códigos de colores
    val black = "\u001b[30m"
    val red = "\u001b[31m"
    val darkgreen = "\u001b[32m"
    val green = "\u001b[38;5;10m"
    val gold = "\u001b[33m"
    val yellow = "\u001b[38;5;11m"
    val aqua = "\u001b[34m"
    val blue = "\u001b[38;5;69m"
    val purple = "\u001b[38;5;99m"
    val cyan = "\u001b[36m"
    val white = "\u001b[37m"
    val gray = "\u001b[38;5;8m"
    val pink = "\u001b[38;5;207m"
    // Códigos de color de fondo
    val bgBlue = "\u001b[48;5;31m"
    val bgPurple = "\u001b[48;5;30m"
    val bgTurqoise = "\u001b[48;5;29m"
    val bgGreen = "\u001b[48;5;28m"
    val bgBlack = "\u001b[40m"
    val bgRed = "\u001b[41m"
    val bgLime = "\u001b[42m"
    val bgGold = "\u001b[43m"
    val bgSkyblue = "\u001b[44m"
    val bgPink = "\u001b[45m"
    val bgCyan = "\u001b[46m"
    val bgGray = "\u001b[47m"
    println("$red Has encertat la paraula !")
}

