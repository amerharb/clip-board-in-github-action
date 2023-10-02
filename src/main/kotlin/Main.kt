import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Test Clip Board in Github Actions...")
    val expected = "This is a test value"
    putClipboard(expected)
    val actual = getClipboard()
    if (expected != actual) {
        println("ERROR: Expected: $expected, Actual: $actual")
        exitProcess(1)
    }
    println("Test Passed")
}

fun getClipboard(): String {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    return clipboard.getData(DataFlavor.stringFlavor) as String
}

fun putClipboard(value: String) {
    try {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(StringSelection(value), null)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        System.err.println(e.message)
    }
}
