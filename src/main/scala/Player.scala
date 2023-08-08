import org.jfugue.Pattern
import org.jfugue.{Player => JPlayer}
import java.io.File

class Player {
  var player = new JPlayer

  def play(txt:String){
    var song = new Pattern(txt)

    player.play(song)
  }

  def saveMidi(pattern:String, path:String){
    var song = new Pattern(pattern)
    var file = new File(path)

    player.saveMidi(pattern, file)
  }
}
