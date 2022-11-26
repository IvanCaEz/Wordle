/*
* AUTHOR: Iván Martínez Cañero
* DATE: 2022/11/06
* TITLE: Wordle
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

/**
 * This function will explain the user the instructions of the game
 * - Will ask the user to type "START" to begin the game
 */
fun instruccions (){
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
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
 */
fun main() {
    instruccions()
    codi()
}

/**
 * The codi function is where the word pool of the game and all the logic of the application lies.
 * - First will take a random word from the wordPool and will ask the user for a Word
 * - If the word has repeated chars or doesn't have a length of 5 chars it will throw a warning and will ask
 * for a word again.
 *
 * ### When the word is accepted and isn't the right guess:
 *
 * - It will scan every char at the userGuess word and compare the position with the same position
 * of the char of the random word and at the same time will be colored based on the position and added
 * to a variable called "history"
 * - Then it will be added to a list called "historyList" and with each iteration will print the content
 * of the list creating the history of the game
 * - The program will also print the number of the current round.
 * - Last it will rest 1 try
 *
 * ### When the word is the right word:
 *
 * - Will print a congratulation message with the correct word
 *
 * ### When the user spends all their tries
 *
 * - Will end the programm and the game with a lose.
 *
 * ### After the game
 *
 * - If the tries reach 0 or the user guesses the word it will ask if the user wants to continue playing,
 * read the rules or stop playing.
 */
fun codi() {
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    val wordPool = arrayOf("Crema","Dutxa","Caqui","Estoc","Fideu","Calor",
        "Astre","Bruna","Bufet","Porta","Movil","Cotxe","Fluid","Abril","Corda","Clima",
        "Tecla","Digne","Força","Apunt")
    val random = wordPool.random().uppercase()
    var intents = 6
    var ronda = 0
    val historyList: MutableList<String> = mutableListOf()
    println("Bona sort! $bold$cyan(｡•̀ᴗ-)$yellow⋆✧ $reset")
    // println(random) Treure el comentari per veure la paraula i fer proves
    do {
        var userGuess: String
        do {
            var lletraRepetida = false
            println("Entra la teva paraula")
            userGuess = scanner.nextLine().uppercase()
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
            if (userGuess.length != 5){
                println("La paraula ha de tenir $underline$yellow${bold}5$reset lletres")
            }
        } while (lletraRepetida || userGuess.length != 5)

        var history = ""
        if (userGuess!=random) {
            for (lletra in 0..userGuess.lastIndex) {
                if (userGuess[lletra] !in random) { // No está
                   history +=  (" $box$bgGray ${userGuess[lletra]} $reset ")
                }
                if (userGuess[lletra] == random[lletra]){ // Posición correcta
                    history += (" $box$bgGreen ${userGuess[lletra]} $reset ")
                }
                if (userGuess[lletra] in random  && userGuess[lletra] != random[lletra]){ // Posición incorrecta
                    history += (" $box$bgGold ${userGuess[lletra]} $reset ")
                }
            }
            historyList.add(history) //añade la palabra al historial
            for (word in 0..historyList.lastIndex){
                println("╔═════════════════════$cyan$bold$box ${6-word} $reset═╗")
                println("║${historyList[word]}║")
                println("╚═════════════════════════╝")
            }
            intents--
            ronda++
        }
        if (userGuess==random){
            for (lletra in 0..userGuess.lastIndex) {
                if (userGuess[lletra] == random[lletra]) { // Posición correcta
                    history += (" $box$bgGreen ${userGuess[lletra]} $reset ")
                }
            }
            historyList.add(history)
            for (word in 0..historyList.lastIndex){
                println("╔═════════════════════$cyan$bold$box ${6-word} $reset═╗")
                println("║${historyList[word]}║")
                println("╚═════════════════════════╝")
            }
            if (ronda==0){
                println ("\n$yellow$bold✩°｡⋆$reset Increíble! Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box 1 $reset intent! $yellow$bold⋆｡°✩$reset\n" )
            }
            else {
                println ("\n$yellow$bold✩°｡⋆$reset Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box ${ronda+1} $reset intents! $yellow$bold⋆｡°✩$reset\n" )
            }
            println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
            val again = scanner.nextLine()
            if (again.uppercase() == "AGAIN"){
                codi()
            }
            if (again.uppercase() == "HELP") {
                main()
            }
            if (again.uppercase() == "EXIT") {
                println("Fins un altre $pink$bold(~‾▿‾)~$reset")
            }
        }
        if (intents!=0 && userGuess!=random){
            println("\nEt queden $cyan$bold$box $intents $reset intents\n")
        }
        if (intents==0) {
            println("\n$cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset Ja no et queden intents $cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset\n")
            println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
            val again = scanner.nextLine()
            if (again.uppercase() == "AGAIN") {
                codi()
            }
            if (again.uppercase() == "HELP") {
                main()
            }
            if (again.uppercase() == "EXIT") {
                println("\nFins un altre $pink$bold(~‾▿‾)~$reset")
            }
        }
    } while (userGuess!=random && intents != 0)
}