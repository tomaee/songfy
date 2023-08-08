import scala.swing._
import java.io._

class FileHelper {
  def chooseFile():File = {
    val chooser = new FileChooser
    chooser.showOpenDialog(new Frame)
    return chooser.selectedFile
  }

  def loadText(file: File): String = {
    val reader = new FileReader(file)
    val buffer = new BufferedReader(reader)
    var txt = ""
    var line = buffer.readLine()

    while(line != null){
      txt += line + "\n"
      line = buffer.readLine()
    }

    reader.close()

    return txt
  }

  def chooseSavePath(): String = {
    val chooser = new FileChooser
    chooser.showSaveDialog(new Frame)

    return chooser.selectedFile.getPath()
  }
}
