/**
* @author Iván Martínez Cañero
* @version 1.2 - 2022/12/16
*/
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
 val scanner = Scanner(System.`in`).useLocale(Locale.UK)


/**
 * This function will explain the user the instructions of the game
 * - Will ask the user to type "START" to begin the game
 */
fun instruccions (){
    do {
        println("$yellow$bold✩°｡⋆ $purple$bold Benvingut / Benvinguda! $pink(*•ᴗ•*)ノﾞ  $yellow$bold⋆｡°✩$reset  ")
        println("A continuació t'explicaré les ${underline}instruccions:$reset  ")
        println("- Tens $underline${bold}6 intents$reset per endevinar la paraula generada")
        println("- Les paraules tenen $underline${bold}5$reset lletres")
        println("- La paraula generada $underline${bold}no$reset repeteix lletres ")
        println("""- Si el fons de la lletra introduida es possa ${gray}gris$reset vol dir que no existeix a la paraula
        |- Si es possa ${yellow}groc$reset vol dir que existeix però no està a la posició correcta                                                                               ║
        |- Si es possa ${green}verd$reset vol dir que està a la posició correcta
    """.trimMargin())
        println("Per començar a jugar entra $yellow$bold✩°｡⋆$bold$box$purple START $reset$yellow$bold⋆｡°✩$reset")

        val start = scanner.nextLine().uppercase()

    } while (start != "START")
}

/**
 * The main fuction will call in order other functions
 *
 * Then will ask the user if it wants to play again, read the rules or stop playing.
 */
fun main() {
    instruccions()
    var playAgain = false
    do {
        val wordPool = arrayOf("Crema","Dutxa","Caqui","Estoc","Fideu","Calor",
            "Astre","Bruna","Bufet","Porta","Movil","Cotxe","Fluid","Abril","Corda","Clima",
            "Tecla","Digne","Força","Apunt")
        val random = wordPool.random().uppercase()
        var intents = 6
        var ronda = 0
        val historyList: MutableList<String> = mutableListOf()
        println("Bona sort! $bold$cyan(｡•̀ᴗ-)$yellow⋆✧ $reset")
        println(random) //Treure el comentari per veure la paraula i fer proves

        do {
            val userGuess = characterChecker()
            characterPainter(userGuess, random, historyList)



            intents--
            ronda++

            if (intents!=0 && userGuess!=random){
                println("\nEt queden $cyan$bold$box $intents $reset intents\n")
            }


        } while (userGuess!=random && intents != 0)
        if (ronda==1){
            println ("\n$yellow$bold✩°｡⋆$reset Increíble! Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box 1 $reset intent! $yellow$bold⋆｡°✩$reset\n" )
        }
        else {
            println ("\n$yellow$bold✩°｡⋆$reset Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box ${ronda} $reset intents! $yellow$bold⋆｡°✩$reset\n" )
        }

        playAgain = false
        if (intents == 0){
            println("\n$cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset Ja no et queden intents $cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset\n")
        }

        println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
        val again = scanner.nextLine()
        if (again.uppercase() == "AGAIN") {
            playAgain = true
        }
        if (again.uppercase() == "HELP") {
            main()
        }
        if (again.uppercase() == "EXIT") {
            println("\nFins un altre $pink$bold(~‾▿‾)~$reset")
        }


    } while (playAgain == true)
}


/**
 * This function ask the user to guess a word, then checks if the length of the user word has a lenght
 * of 5 or not. If it has a length of 5 it returns the user word.
 */
fun characterChecker(): String{
    var userGuess: String
    do {
        println("Entra la teva paraula")
        userGuess = scanner.nextLine().uppercase()
        /*
        var lletraRepetida = false
        for (lletra in 0..userGuess.lastIndex){
            for (repetida in lletra+1..userGuess.lastIndex){
                if (userGuess[lletra] == userGuess[repetida]){
                    lletraRepetida = true
                }
            }
        }
        if (lletraRepetida){
            println("$underline$red${bold}No$reset pots repetir lletra a la paraula")
        }
         */
        if (userGuess.length != 5){
            println("La paraula ha de tenir $underline$yellow${bold}5$reset lletres")
        }
    } while ( userGuess.length != 5)
    return userGuess
}
/**
 * This function adds the painted word to a list, then iterates the list printing each entry
 *  within the list.
 *
 * @param history A string with the user word painted
 * @param historyList A mutable list with the all the entries of the user
 */
fun terminalPrinter(history:String, historyList: MutableList<String>){
    historyList.add(history)
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
 * character with the same position of the corresponding character at the random word and at the same
 * time will be colored and added to history.
 *
 * Then it will call the function terminalPrinter in order to print the history of the game.
 *
 * If the userGuess is the same as the random word, it will do the same of before and will check the ronda
 * parameter, if the round is 0, will print a special message. And will call the function playAgain
 * to ask the user if they want to play again.
 *
 * @param random A string containig the word from the Word Pool of the game
 * @param userGuess A string containing the word that the user previosly entered
 * @param history A string with the user word painted
 * @param historyList A mutable list with the all the entries of the user
 * @param ronda An int which counts the current round
 * @param intents An int which counts the tries
 */
fun characterPainter(userGuess: String, random: String, historyList: MutableList<String>) {
    var history = ""
    var repeatedCharCounter = 0
    if (userGuess != random) { //pinta las letras según la condición
        for (lletra in 0..userGuess.lastIndex) {
            if (userGuess[lletra] !in random) { // No está
                history += (" $box$bgGray ${userGuess[lletra]} $reset ")
            }
            if (userGuess[lletra] == random[lletra]) { // Posición correcta
                history += (" $box$bgGreen ${userGuess[lletra]} $reset ")
                repeatedCharCounter++

            }
            if (userGuess[lletra] in random && userGuess[lletra] != random[lletra] && repeatedCharCounter == 0 ) { // Posición incorrecta
                history += (" $box$bgGold ${userGuess[lletra]} $reset ")
            }
            if (userGuess[lletra] in random && userGuess[lletra] != random[lletra] && repeatedCharCounter != 0){
                history += (" $box$bgGray ${userGuess[lletra]} $reset ")
            }

        }
        terminalPrinter(history, historyList)
    }
    if (userGuess==random) {
        for (lletra in 0..userGuess.lastIndex) {
            if (userGuess[lletra] == random[lletra]) { // Posición correcta
                history += (" $box$bgGreen ${userGuess[lletra]} $reset ")
            }
        }
        terminalPrinter(history, historyList)

    }
}
