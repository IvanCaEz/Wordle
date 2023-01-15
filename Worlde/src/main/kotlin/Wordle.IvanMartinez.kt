/**
 * @author Iván Martínez Cañero
 * @version 1.3 - 2023/01/14
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
val scanner: Scanner = Scanner(System.`in`).useLocale(Locale.UK)


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
 * If the user guesses the word at first round,will print a special message.
 *
 * Then will ask the user if it wants to play again, read the rules or stop playing.
 */
fun main() {
    instruccions()
    var playAgain: Boolean
    do {
        val wordPool = arrayOf("Crema","Dutxa","Caqui","Estoc","Fideu","Calor","Cavar",
            "Astre","Bruna","Bufet","Porta","Movil","Cotxe","Fluid","Abril","Corda","Clima","Terra","Amiga",
            "Tecla","Digne","Deure","Apunt","Color","Arbre","Solar","Cursa","Repte","Bomba","Barba")
        val random =  wordPool.random().uppercase()
        var intents = 6
        var ronda = 0
        val historyList: MutableList<String> = mutableListOf()
        println("Bona sort! $bold$cyan(｡•̀ᴗ-)$yellow⋆✧ $reset")

        var userGuess: String

        do {
            do {
                println("Entra la teva paraula")
                userGuess = scanner.nextLine().uppercase()
                if (!characterChecker(userGuess)){
                    println("La paraula ha de tenir $underline$yellow${bold}5$reset lletres")
                }

            } while (!characterChecker(userGuess))

            terminalPrinter(characterPainter(userGuess, random), historyList)

            intents--
            ronda++

            if (intents!=0 && userGuess!=random){
                println("\nEt queden $cyan$bold$box $intents $reset intents\n")
            }


        } while (userGuess!=random && intents != 0)

        if (userGuess == random ){
            if (ronda==1){
                println ("\n$yellow$bold✩°｡⋆$reset Increíble! Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box 1 $reset intent! $yellow$bold⋆｡°✩$reset\n" )
            } else {
                println ("\n$yellow$bold✩°｡⋆$reset Has encertat la paraula $green$bold$box $random $reset en $gold$bold$box $ronda $reset intents! $yellow$bold⋆｡°✩$reset\n" )
            }
        }
        if ( intents == 0 && userGuess != random){
            println("\n$cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset Ja no et queden intents $cyan$bold ･ﾟ･(｡>ω<｡)･ﾟ･ $reset\n")
        }

        println("""- Si vols tornar a jugar entra $pink$bold$box AGAIN $reset
                |
                |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                |
                |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())

        playAgain = false
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

    } while (playAgain)
}


/**
 * This function will check if the length of the guessed word the user enters has 5 characters, if true,
 * will check if all characters are alphabetical and will throw false if there's a non-alphabetical character or
 * the length is not 5.
 *
 * @param userGuess A string containing the word that the user previosly entered
 * @return A boolean
 */
fun characterChecker(userGuess: String): Boolean {
    if (userGuess.length == 5){
        for (char in userGuess)
        {
            if (char !in 'A'..'Z' && char !in 'a'..'z') {
                return false
            }
        }
        return true
    }
    else return false
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