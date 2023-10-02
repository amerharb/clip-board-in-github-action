import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Test Clip Board in Github Actions...")
    println("Environment variable DISPLAY: ${System.getenv("DISPLAY")}")
    val expected = if (args.isNotEmpty() && args[0].isNotBlank()) args[0] else "This is a test value"
    println("Copying \"$expected\" to clipboard 📋...")
    putClipboard(expected)
    println("Sleeping for 3 seconds ⏳...")
    Thread.sleep(3000)
    println("Reading clipboard 📋...")
    val actual = getClipboard()
    println("Actual: \"$actual\"")
    if (expected != actual) {
        println("❌ERROR: Expected: $expected, Actual: $actual")
        exitProcess(1)
    }
    println("Test Passed ✅")
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
