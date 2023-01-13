import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Wordle_IvanMartinezKtTest{
    @Test
    fun characterCheckerChecksIfUserGuessHas5Chars(){
        assertEquals("Cotxe",characterChecker())
    }

    // CharacterPainter
    @Test
    fun characterPainterPaintsAllGreenWhenTheUserGuessIsCorrectWord(){
        assertEquals(" \u001B[51m\u001B[48;5;28m A \u001B[0m  \u001B[51m\u001B[48;5;28m B \u001B[0m  \u001B[51m\u001B[48;5;28m R \u001B[0m  \u001B[51m\u001B[48;5;28m I \u001B[0m  \u001B[51m\u001B[48;5;28m R \u001B[0m ",
        characterPainter("ABRIR", "ABRIR"))
    }
    @Test
    fun characterPainterPaintsAllGrayWhenCharIsNotInCorrectWord(){
        assertEquals(" \u001B[51m\u001B[47m P \u001B[0m  \u001B[51m\u001B[47m E \u001B[0m  \u001B[51m\u001B[47m S \u001B[0m  \u001B[51m\u001B[47m T \u001B[0m  \u001B[51m\u001B[47m E \u001B[0m ",
            characterPainter("ABRIR", "ABRIR"))
    }
    @Test
    fun characterPainterPaintsYellowTheNumberOfCorrectTimes(){
        assertEquals(" \u001B[51m\u001B[43m B \u001B[0m  \u001B[51m\u001B[43m A \u001B[0m  \u001B[51m\u001B[48;5;28m R \u001B[0m  \u001B[51m\u001B[47m B \u001B[0m  \u001B[51m\u001B[47m A \u001B[0m ",
            characterPainter("BARBA", "ABRIR"))
    }

}