/*
* AUTHOR: Iván Martínez Cañero
* DATE: 2022/11/06
* TITLE: Wordle
*/
import java.util.*
import kotlin.random.Random

 const val reset = "\u001b[0m"
 const val box = "\u001b[51m"
 const val bold = "\u001b[1m"
 const val underline = "\u001b[21m"
 const val bgGold = "\u001b[43m"
 const val bgBlue = "\u001b[48;5;31m"
 const val bgRed = "\u001b[41m"
 const val bgGreen = "\u001b[48;5;28m"
 const val bgGray = "\u001b[47m"
 const val red = "\u001b[31m"
 const val cyan = "\u001b[38;5;87m"
 const val green = "\u001b[38;5;10m"
 const val gold = "\u001b[33m"
 const val yellow = "\u001b[38;5;11m"
 const val black = "\u001b[30m"
 const val gray = "\u001b[38;5;7m"
 const val pink = "\u001b[38;5;207m"
 const val purple = "\u001b[38;5;99m"
 const val blue = "\u001b[38;5;69m"


fun instruccions (){     //INSTRUCCIONS
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    do {
        println("Benvingut\nA continuació t'explicaré les ${underline}instruccions:$reset")
        println("- Tens $underline${bold}6 intents$reset per endevinar la paraula generada")
        println("- Les paraules tenen $underline${bold}5$reset lletres")
        println("- La paraula generada $underline${bold}no$reset repeteix lletres")
        println("""- Si la lletra introduida es possa ${gray}gris$reset vol dir que no existeix a la paraula
        |- Si es possa ${yellow}groga$reset vol dir que existeix però no està a la posició correcta
        |- Si es possa ${green}verda$reset vol dir que està a la posició correcta
    """.trimMargin())
        println("Per començar a jugar entra $bold$box$purple START $reset")
        val start = scanner.nextLine().uppercase()
    } while (start != "START")
}

fun main() {
    instruccions()
    interfaz()
    codi()
}

fun interfaz() {
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    println("$box Entra el teu nom $reset")
    val name = scanner.nextLine()
    println("$box Benvingut $name $reset")
}

fun codi() {
    val scanner = Scanner(System.`in`).useLocale(Locale.UK)
    val wordPool = arrayOf("Crema","Dutxa","Caqui","Estoc","Fideu","Calor",
        "Astre","Bruna","Bufet","Porta","Movil","Cotxe","Fluid","Abril","Corda","Clima",
        "Tecla","Digne","Força","Apunt")
    val random = wordPool.random().uppercase()
    var intents = 6
    val historyList: MutableList<String> = mutableListOf()
    println(random)
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
        if (userGuess==random){
            println ("Has encertat la paraula $green$bold$box $random! $reset" )
            println("""- Si vols tornar a començar entra $pink$bold$box AGAIN $reset
                 |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                 |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
            val again = scanner.nextLine()
            if (again.uppercase() == "AGAIN"){
                codi()
            }
            if (again.uppercase() == "HELP") {
                main()
            }
        }
        if (userGuess!=random) {
            var history = "\n"
            for (lletra in 0..userGuess.lastIndex) {
                if (userGuess[lletra] !in random) { // No está
                   history += (bgGray + black + userGuess[lletra] + reset)
                }
                if (userGuess[lletra] == random[lletra]){ // Posición correcta
                    history += ( bgGreen+ black + userGuess[lletra] + reset)
                }
                if (userGuess[lletra] in random  && userGuess[lletra] != random[lletra]){ // Posición incorrecta
                    history += ( bgGold+ black + userGuess[lletra] + reset)
                }
            }
            historyList.add(history) //añade la palabra al historial
            for (word in 0..historyList.lastIndex){
                print (historyList[word])
            }
            intents--
        }
        if (intents!=0 && userGuess!=random) println("\nEt queden $cyan$bold$box $intents $reset intents")
        if (intents==0) {
            println("\n $box Ja no et queden intents :( $reset")
            println("""- Si vols tornar a començar entra $pink$bold$box AGAIN $reset
                 |- Si vols deixar de jugar entra $red$bold$box EXIT $reset
                 |- Si vols revisar les instruccions entra $blue$bold$box HELP $reset""".trimMargin())
            val again = scanner.nextLine()
            if (again.uppercase() == "AGAIN") {
                codi()
            }
            if (again.uppercase() == "HELP") {
                main()
            }
        }
    } while (userGuess!=random && intents != 0)
}


/*/
fun verification() {
   var userGuess=""
        do {
            val scanner = Scanner(System.`in`).useLocale(Locale.UK)
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
                println("No pots repetir lletra a la paraula")
            }
        } while (lletraRepetida)
}


 TODO
  - Interfaz
  - Nombre Usuario
  - Pensar cuando el usuario es retarded
  - Limpiar
 */