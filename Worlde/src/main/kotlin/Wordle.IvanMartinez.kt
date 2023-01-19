/**
 * @author Iván Martínez Cañero
 * @version 1.3 - 2023/01/14
 */
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

/**
 * These constants are used to color the foreground and background of each character when needed
 * Write the name or names of the constants you want to use before the next word or character and then
 * use the constant "reset" after the desired word or character to end the formatting
 */
const val reset = "\u001b[0m"
const val box = "\u001b[51m"
const val bold = "\u001b[1m"
const val underline = "\u001b[21m"
const val bgGold = "\u001b[43m"
const val bgGreen = "\u001b[48;5;28m"
const val bgGray = "\u001b[47m"
const val red = "\u001b[31m"
const val cyan = "\u001b[38;5;87m"
const val green = "\u001b[38;5;10m"
const val gold = "\u001b[33m"
const val yellow = "\u001b[38;5;11m"
const val gray = "\u001b[38;5;7m"
const val pink = "\u001b[38;5;207m"
const val purple = "\u001b[38;5;99m"
const val blue = "\u001b[38;5;69m"
val scanner: Scanner = Scanner(System.`in`).useLocale(Locale.UK)
const val fileName = "Historial.txt"
val file = File("Worlde/src/main/kotlin/Archivos/$fileName")


/**
 * This function will explain the user the instructions of the game
 * - Will ask the user to type "START" to begin the game
 */
fun instruccions (){
    do {
        //println("$yellow$bold✩°｡⋆ $purple$bold Benvingut / Benvinguda! $pink(*•ᴗ•*)ノﾞ  $yellow$bold⋆｡°✩$reset  ")
        println("A continuació t'explicaré les ${underline}instruccions:$reset  ")
        println("- Tens $underline${bold}6 intents$reset per endevinar la paraula generada")
        println("- Les paraules tenen $underline${bold}5$reset lletres")
        println("""- Si el fons de la lletra introduida es possa ${gray}gris$reset vol dir que no existeix a la paraula
        |- Si es possa ${yellow}groc$reset vol dir que existeix però no està a la posició correcta                                                                               ║
        |- Si es possa ${green}verd$reset vol dir que està a la posició correcta
    """.trimMargin())
        println("Per començar a jugar entra $yellow$bold✩°｡⋆$bold$box$purple START $reset$yellow$bold⋆｡°✩$reset")

        val start = scanner.nextLine().uppercase()

    } while (start != "START")
}
fun fileMaker( userName: String, winOrLose: Boolean, random: String, intents: Int){

    val fileAlreadyExists= file.createNewFile() // True si no existe, False si existe

    file.appendText("$userName,$random,$winOrLose,$intents,${LocalDate.now()},${LocalTime.now()}\n")
}

fun historyFilter(): MutableList<String>{
    val listOfHistoryGivenPredicate = mutableListOf<String>()
    println("Entra la instrucció de com vols filtrar")
    println(""" $bold$box$purple USUARI $reset $bold$box$green PARAULA $reset $bold$box$pink DATA $reset $bold$box$cyan INTENTS $reset $bold$box$yellow RESULTAT $reset
""".trimMargin())
    val filtrarPer = scanner.nextLine().uppercase()
    if (filtrarPer == "USUARI"){
        println("Entra el nom d'usuari")
        val usuari = scanner.nextLine().lowercase().capitalize()
        BufferedReader(FileReader(file)).use {
            it.lines().forEach() {
                if (it.contains(usuari)){
                    listOfHistoryGivenPredicate.add(it)
                }
            }

        }
    }
    else if (filtrarPer == "PARAULA"){
        println("Entra la paraula")
        val word = scanner.nextLine().uppercase()
        BufferedReader(FileReader(file)).use {
            it.lines().forEach() {
                if (it.contains(word)){
                    listOfHistoryGivenPredicate.add(it)
                }
            }

        }
    }
    else if (filtrarPer == "DATA"){
        println("Entra la data en format AAAA-MM-DD")
        val date = scanner.nextLine()
        BufferedReader(FileReader(file)).use {
            it.lines().forEach() {
                if (it.contains(date)){
                    listOfHistoryGivenPredicate.add(it)
                }
            }

        }
    }

    else if (filtrarPer == "INTENTS"){
        println("Entra els intents de (1 a 6)")
        val tries = scanner.nextInt().toString()
        val lines= file.readLines()
        for (i in 0 .. lines.lastIndex) {
            if (lines[i].split(",")[3] == tries ){
                listOfHistoryGivenPredicate.add(lines[i])
            }
        }
    }

    else if (filtrarPer == "RESULTAT"){
        println("Entra WIN o LOSE")
        var resultat = scanner.nextLine().uppercase()
        if (resultat == "WIN"){
            resultat = "true"
        } else if (resultat == "LOSE"){
            resultat = "false"
        }
        BufferedReader(FileReader(file)).use {
            it.lines().forEach() {
                if (it.contains(resultat)){
                    listOfHistoryGivenPredicate.add(it)
                }
            }

        }
    }
    if (listOfHistoryGivenPredicate.isEmpty()){
        println("No hi han resultats guardats amb aquest filtre, retornant al menú...\n")
    }
    return listOfHistoryGivenPredicate
}
fun historyFormater(list: MutableList<String>){
    for (i in 0..list.lastIndex){
        val userName = list[i].split(",")[0]
        val random = list[i].split(",")[1]
        val winOrLose = list[i].split(",")[2]
        val result: String = if (winOrLose == "true"){
            "$bold$box$green WIN $reset"
        } else{
            "$bold$box$red LOSE $reset"
        }
        val tries = list[i].split(",")[3]
        val date = list[i].split(",")[4]
        val year = date.split("-")[0]
        val month = date.split("-")[1]
        val day = date.split("-")[2]
        val time = list[i].split(",")[5]
        val formatedTime = time.split(".")[0]
        if (i == 0) println("══════════════════════════════════")
        println("""${bold}User:$reset $userName
            |${bold}Random Word:$reset $random
            |${bold}Tries:$reset $tries           ${bold}Result:$reset $result
            |${bold}Time:$reset $formatedTime
            |${bold}Year:$reset $year ${bold}|$reset ${bold}Month:$reset $month ${bold}|$reset$bold Day:$reset $day
            |══════════════════════════════════
        """.trimMargin())
    }
}
fun menu(): Boolean {
    var message: Boolean
    var wantToPlay = true
    println("$yellow$bold✩°｡⋆ $purple$bold Benvingut / Benvinguda! $pink(*•ᴗ•*)ノﾞ  $yellow$bold⋆｡°✩$reset\n")
    do {
        println("""- Si vols jugar entra $yellow$bold✩°｡⋆$bold$box$purple JUGAR $reset$yellow$bold⋆｡°✩$reset
                |
                |- Si vols veure les instruccions entra $green$bold$box HELP $reset
                |
                |- Si vols veure l'historial de partides entra $cyan$bold$box HISTORY $reset
                |
                |- Si has canviat d'idea i t'en vols anar, entra $red$bold$box EXIT $reset""".trimMargin())

        val instruction = scanner.nextLine().uppercase()
        message = true

        if (instruction.uppercase() == "JUGAR") {
            wantToPlay = true
            message = false
        }
        if (instruction.uppercase() == "HISTORY") {
            historyFormater(historyFilter())
        }
        if (instruction.uppercase() == "HELP") {
            message = false
            wantToPlay = true
            instruccions()
        }
        if (instruction.uppercase() == "EXIT") {
            println("\nFins un altre $pink$bold(~‾▿‾)~$reset")
            wantToPlay = false
            message = false
        }
    } while (message)

    return  wantToPlay

}
/**
 * The main fuction will call in order other functions
 *
 * If the user guesses the word at first round,will print a special message.
 *
 * Then will ask the user if it wants to play again, read the rules or stop playing.
 */
fun main() {
    if (menu()){
        var userName: String
        do {
            println("Entra el teu nom d'usuari")
            userName = scanner.nextLine().lowercase().capitalize()
        } while (!wordChecker(userName, instruction = "name"))

        var playAgain: Boolean
        var language: String
        var languageSelected = false

        do {
            println("""En què idioma vols jugar?
            |   $bold$red C${yellow}A${red}T${yellow}A${red}L${yellow}À $reset $bold$blue E$reset${bold}N${red}G${blue}L$reset${bold}I${red}S${blue}H $reset
        """.trimMargin())
             language = scanner.nextLine().uppercase()
            if (language == "CATALA" || language == "CATALÀ"){
                language = "WordPoolCatala"
                languageSelected = true
            } else if (language == "ENGLISH"){
                language = "WordPoolEnglish"
                languageSelected = true
            }
        } while (!languageSelected)

        do {
            val random = File("Worlde/src/main/kotlin/Archivos/$language").readLines().random().uppercase()
           println(random)

            var intents = 6
            var ronda = 0
            val historyList: MutableList<String> = mutableListOf()
            println("Bona sort! $bold$underline$userName$reset $bold$cyan(｡•̀ᴗ-)$yellow⋆✧ $reset")

            var userGuess: String

            do {
                do {
                    println("Entra la teva paraula")
                    userGuess = scanner.nextLine().uppercase()
                    if (!wordChecker(userGuess, instruction = "word")){
                        println("La paraula ha de tenir $underline$yellow${bold}5$reset lletres")
                    }

                } while (!wordChecker(userGuess, instruction = "word"))

                terminalPrinter(characterPainter(userGuess, random), historyList)

                intents--
                ronda++

                if (intents!=0 && userGuess!=random){
                    println("\nEt queden $cyan$bold$box $intents $reset intents\n")
                }


            } while (userGuess!=random && intents != 0)
            var winOrLose = true
            if (userGuess == random ){
                winOrLose = true
                if (ronda==1){
                    println ("\n$yellow$bold✩°｡⋆$reset Increíble! Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box 1 $reset intent! $yellow$bold⋆｡°✩$reset\n" )
                } else {
                    println ("\n$yellow$bold✩°｡⋆$reset Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box $ronda $reset intents! $yellow$bold⋆｡°✩$reset\n" )
                }
            }
            if ( intents == 0 && userGuess != random){
                winOrLose = false
                println("\n$cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset Ja no et queden intents $cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset\n")
            }

            println("- Si vols guardar la partida entra $cyan$bold$box SAVE $reset\n")

            val saveToHistory = scanner.nextLine()

            if (saveToHistory.uppercase() == "SAVE") {
                fileMaker(userName, winOrLose, random, ronda)
            }

            println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols tornar al menu entra $blue$bold$box MENU $reset""".trimMargin())

            playAgain = false
            val again = scanner.nextLine()

            if (again.uppercase() == "AGAIN") {
                playAgain = true
            }
            if (again.uppercase() == "MENU") {
                main()
            }
            if (again.uppercase() == "EXIT") {
                println("\nFins un altre $pink$bold(~‾▿‾)~$reset")
            }

        } while (playAgain)
    }

}

/**
 * This function will check if the length of the guessed word the user enters has 5 characters, if true,
 * will check if all characters are alphabetical and will throw false if there's a non-alphabetical character or
 * the length is not 5.
 *
 * @param userGuess A string containing the word that the user previosly entered
 * @return A boolean
 */
fun wordChecker(wordToCheck: String, instruction: String):Boolean{
    if (instruction == "name"){
        for (char in wordToCheck) {
            if (char.code in 58..64 || char.code in 91..96 || char.code in 123..126 || char.code in 33..47 ) {
                return false
            }
        }
        return true
    }
    if (instruction == "word"){
        if (wordToCheck.length == 5){
            for (char in wordToCheck) {
                if (char !in 'A'..'Z' && char !in 'a'..'z') {
                    return false
                }
            }
            return true
        }
        else return false
    }
    return false
}





/**
 * This function adds the painted word to a list, then iterates the list printing each entry
 *  within the list with, in my opinion, a cool touch.
 *  Also, will indicate the number of the round for that intent.
 *
 * @param formatedHistory A string with the user word painted
 * @param historyList A mutable list with all the entries of the user
 */
fun terminalPrinter(formatedHistory: String, historyList: MutableList<String>){

    historyList.add(formatedHistory)
    for (word in 0..historyList.lastIndex){
        println("╔═════════════════════$cyan$bold$box ${6-word} $reset═╗")
        println("║${historyList[word]}║")
        println("╚═════════════════════════╝")
    }
}
/**
 * This function compares the user word with the random word.
 *
 * Will iterate for each character within the word and compare the position of that
 * character with the same position of the corresponding character at the random word.
 *
 * If the character is in the correct position will paint that character green and add it
 * at the correct position in an empty list we created with a size of 5, else will add the character of
 * the random word at the current iteration to a list.
 *
 * Then will iterate again the guessed word and will check if each character is in the list of chars that was
 * created on the previous for loop, if it is, will paint it yellow and then will iterate the list of chars and remove
 * that letter.
 *
 * And if it isn't present in the list, will paint it gray.
 *
 * Finally, will iterate the mutable list of painted words in order to create and return a string with the guessed
 * word painted.
 *
 *
 * @param random A string containig the word from the Word Pool of the game
 * @param userGuess A string containing the word that the user previosly entered
 *
 * @return A string with the word the user has entered with each character colored
 */
fun characterPainter(userGuess: String, random: String): String {
    val history = MutableList(5){""}
    var formatedHistory = ""
    val charCheckList = mutableListOf<Char>()
    for (lletra in 0..userGuess.lastIndex) {

        // Posición correcta
        if (userGuess[lletra] == random[lletra]) {
            history[lletra] = (" $box$bgGreen ${userGuess[lletra]} $reset ")
        }
        else {
            charCheckList.add(random[lletra])
        }
    }
    for (lletra in 0..userGuess.lastIndex){
        // Posición incorrecta
        if (userGuess[lletra] in charCheckList && history[lletra] == "" ){
            history[lletra] = (" $box$bgGold ${userGuess[lletra]} $reset ")
            for (i in 0..charCheckList.lastIndex){
                if (charCheckList[i] == userGuess[lletra]) {
                    charCheckList.removeAt(i)
                    break
                }
            }
        }
        // No está
        if (userGuess[lletra] !in charCheckList && history[lletra] == "" ) {
            history[lletra] = (" $box$bgGray ${userGuess[lletra]} $reset ")
        }

    }
    for (lletra in 0..history.lastIndex){
        formatedHistory += history[lletra]
    }
    return formatedHistory

}